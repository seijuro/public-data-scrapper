package com.github.seijuro.task;

import com.github.seijuro.publicdata.PublicDataAPIException;
import com.github.seijuro.publicdata.PublicDataAPIServices;
import com.github.seijuro.publicdata.address.LegalDongAddress;
import com.github.seijuro.publicdata.address.reader.LegalDongAddressReader;
import com.github.seijuro.publicdata.api.PublicDataAPI;
import com.github.seijuro.publicdata.api.config.BusinessPlaceAPIConfig;
import com.github.seijuro.publicdata.api.config.PublicDataAPIConfig;
import com.github.seijuro.publicdata.result.PublicDataAPIErrorResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import com.github.seijuro.publicdata.runner.PublicDataAPITask;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;

public class BusinessPlaceAPITask extends PublicDataAPITask {
    /**
     * enum RunningState
     */
    public enum RunningState {
        RUNNING,
        STOP,
        SHUTDOWN;
    }

    @ToString
    private class RequestState {
        /**
         * Instance Properties
         */
        @Setter(AccessLevel.PRIVATE)
        private int maxTry = 3;
        private int currentRetry = 0;
        @Getter(AccessLevel.PRIVATE)
        @Setter(AccessLevel.PRIVATE)
        private boolean success = false;

        void incrementRetry() {
            ++this.currentRetry;
        }

        void reset() {
            this.currentRetry = 0;
            this.success = false;
        }

        boolean shouldRetry() {
            return !isSuccess() && (currentRetry < this.maxTry);
        }
    }

    @ToString
    class PagingState {
        PagingState(int initPage, int size) {
            initPageIndex = initPage;
            pageSize = size;
            currentPage = initPageIndex;
        }

        /**
         * Instance Properties
         */
        private final int initPageIndex;

        @Getter(AccessLevel.PRIVATE) @Setter(AccessLevel.PRIVATE)
        int currentPage = 0;
        @Getter(AccessLevel.PRIVATE) @Setter(AccessLevel.PRIVATE)
        int pageSize = 0;
        @Getter(AccessLevel.PRIVATE) @Setter(AccessLevel.PRIVATE)
        int currentResult = 0;
        @Getter(AccessLevel.PRIVATE) @Setter(AccessLevel.PRIVATE)
        int totalCount = 0;

        void reset() {
            currentPage = initPageIndex;
            currentResult = 0;
            totalCount = 0;
        }

        boolean hasMorePage() {
            return currentPage * pageSize < totalCount;
        }
    }

    /**
     * Instance Properties
     */
    private RunningState runningState = RunningState.RUNNING;
    private RequestState requestState = new RequestState();
    private PagingState pageState = new PagingState(1, 10);

    private LegalDongAddressReader addressReader = null;

    /**
     * C'tor
     */
    public BusinessPlaceAPITask() throws PublicDataAPIException {
        super(PublicDataAPIServices.NPS_BUSINESS_PLACE_NORMAL);
    }

    public void setAddressFile(String filepath) throws IOException {
        this.addressReader = new LegalDongAddressReader(filepath);
    }

    public void setMaxTry(int max) {
        this.requestState.setMaxTry(max);
    }

    @Override
    public String getServiceKey(PublicDataAPIServices apiService) {
        return getServiceKeySupplier().getServiceKey(apiService);
    }

    @Override
    public PublicDataAPIConfig getNextConfig() {
        try {
            LegalDongAddress address = (this.addressReader == null) ? null : this.addressReader.readAddress();

            if (address == null) { return null; }

            BusinessPlaceAPIConfig config = new BusinessPlaceAPIConfig();
            config.setLegalDongAddress(address.getCode());
            config.setProperty(BusinessPlaceAPIConfig.Property.NUM_OF_ROWS, 1000);
            config.setProperty(BusinessPlaceAPIConfig.Property.PAGE_NO, 1);

            return config;
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }

        return null;
    }

    @Override
    public void run() {
        do {
            PublicDataAPI api = getApi();
            String serviceKey = getServiceKey();
            BusinessPlaceAPIConfig config = (BusinessPlaceAPIConfig) getNextConfig();

            String prop = config.getProperty(BusinessPlaceAPIConfig.Property.NUM_OF_ROWS, String.class);
            int pageSize = prop != null ? Integer.parseInt(prop) : 100;

            pageState.reset();
            pageState.setPageSize(pageSize);

            do {
                requestState.reset();
                do {
                    request(api, serviceKey, config);
                } while (requestState.shouldRetry());

                config.setProperty(BusinessPlaceAPIConfig.Property.PAGE_NO, pageState.getCurrentPage() + 1);
            } while (requestState.isSuccess() && pageState.hasMorePage());
        } while (this.runningState == RunningState.RUNNING);
    }

    @Override
    public void handleHTTPResponse(String url, int code, String response) {
    }

    @Override
    public void handleHTTPErrorResponse(String url, int code, String response, String errmsg) {
        requestState.setSuccess(false);
        requestState.incrementRetry();
    }

    @Override
    public void handleResult(String url, PublicDataAPIResult result) {
        requestState.setSuccess(true);
        pageState.setCurrentPage(result.getPageNo());
        pageState.setCurrentResult(result.getNumberOfRows());
        pageState.setTotalCount(result.getTotalCount());

        System.out.println(requestState.toString());
        System.out.println(pageState.toString());
    }

    @Override
    public void handleErrorResult(String url, PublicDataAPIErrorResult result) {
        requestState.setSuccess(false);
        requestState.incrementRetry();
    }

    @Override
    public void shutdown() {
        this.runningState = RunningState.SHUTDOWN;
    }
}
