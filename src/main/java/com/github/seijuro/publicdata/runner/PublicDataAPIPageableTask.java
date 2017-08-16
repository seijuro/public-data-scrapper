package com.github.seijuro.publicdata.runner;

import com.github.seijuro.publicdata.PublicDataAPIException;
import com.github.seijuro.publicdata.PublicDataAPIServices;
import com.github.seijuro.publicdata.api.PublicDataAPI;
import com.github.seijuro.publicdata.api.config.PublicDataAPIConfig;
import com.github.seijuro.publicdata.result.PublicDataAPIErrorResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Properties;

public abstract class PublicDataAPIPageableTask extends PublicDataAPITask {
    @ToString
    protected class PagingState {
        PagingState(int initPage, int size) {
            initPageIndex = initPage;
            pageSize = size;
            currentPage = initPageIndex;
        }

        /**
         * Instance Properties
         */
        @Getter @Setter
        private int initPageIndex = 1;
        @Getter @Setter
        int currentPage = 1;
        @Getter @Setter
        int pageSize = 0;
        @Getter @Setter
        int currentResult = 0;
        @Getter @Setter
        int totalCount = 0;

        public void reset() {
            currentPage = initPageIndex;
            currentResult = 0;
            totalCount = 0;
        }

        public boolean hasMorePage() {
            return (currentPage + (initPageIndex == 0 ? 1 : 0)) * pageSize < totalCount;
        }
    }

    /**
     * Instance Properties
     */
    protected PagingState pageState;
    @Setter
    protected PagePropertyExtractor pageNoExtractor;
    @Setter
    protected PagePropertySupplier pageNoSupplier;
    @Setter
    protected PagePropertyExtractor pageSizeExtractor;
    @Getter
    protected int defaultPageSize = 1000;
    @Getter
    protected int defaultPageNo = 1;

    public void setDefaultPageNo(int pageNo) {
        this.defaultPageNo = pageNo;
        pageState.setInitPageIndex(pageNo);
    }

    public void setDefaultPageSize(int pageSize) {
        defaultPageSize = pageSize;
        pageState.setPageSize(pageSize);
    }

    /**
     * C'tor
     *
     * @param apiService
     * @throws PublicDataAPIException
     */
    public PublicDataAPIPageableTask(PublicDataAPIServices apiService) throws PublicDataAPIException {
        super(apiService);

        pageState = new PagingState(1, getDefaultPageNo());
    }

    @Override
    public void run() {
        try {
            handleLoop();
        }
        catch (InterruptedException excp) {
            excp.printStackTrace();
        }
    }

    @Override
    public void handleLoop() throws InterruptedException {
        requestState.reset();

        PublicDataAPI api = getApi();
        String serviceKey = getServiceKey(getApiService());
        PublicDataAPIConfig config = getNextConfig();
        int pageSize = pageSizeExtractor.or((config1 -> getDefaultPageSize())).apply(config);

        pageState.reset();
        pageState.setPageSize(pageSize);

        try {
            do {
                requestState.reset();

                do {
                    boolean didVisit = didAlreadyVisit(api, config);

                    if (didVisit) {
                        int pageNumber = pageNoExtractor.or((config1 -> getDefaultPageNo())).apply(config);

                        pageState.setCurrentPage(pageNumber);
                        pageState.setTotalCount(Integer.MAX_VALUE);

                        requestState.setSuccess(true);

                        break;
                    }

                    request(api, serviceKey, config);

                    //  default
                    long sleepMillis = getDefaultSleepSeconds() * DateUtils.MILLIS_PER_SECOND;

                    if (!requestState.isSuccess()) {
                        //  back-off
                        sleepMillis = requestState.getRetryAfter();

                        System.out.println(String.format("[SLEEP] %d ms (back off)", sleepMillis));
                    }

                    Thread.sleep(sleepMillis);
                } while (requestState.shouldRetry() && runningState == RunningState.RUNNING);

                pageNoSupplier.supply(config, pageState.getCurrentPage() + 1);
            } while (pageState.hasMorePage() && runningState == RunningState.RUNNING);
        }
        catch (TaskExeption excp) {
        }
    }

    @Override
    public void handleHTTPResponse(String url, Properties props, int code, String response) {
        super.handleHTTPResponse(url, props, code, response);
    }

    @Override
    public void handleHTTPErrorResponse(String url, Properties props, int code, String response, String errmsg) {
        super.handleHTTPErrorResponse(url, props, code, response, errmsg);
    }

    @Override
    public void handleResult(String url, Properties props, String response, PublicDataAPIResult result) {
        super.handleResult(url, props, response, result);

        pageState.setCurrentPage(result.getPageNo());
        pageState.setCurrentResult(result.getNumberOfRows());
        pageState.setTotalCount(result.getTotalCount());
    }

    @Override
    public void handleErrorResult(String url, Properties props, String response, PublicDataAPIErrorResult result) {
        super.handleErrorResult(url, props, response, result);
    }
}
