package com.github.seijuro.publicdata.runner;

import com.github.seijuro.publicdata.PublicDataAPIException;
import com.github.seijuro.publicdata.PublicDataAPIServices;
import com.github.seijuro.publicdata.api.PublicDataAPI;
import com.github.seijuro.publicdata.api.config.ConfigProperty;
import com.github.seijuro.publicdata.api.config.PublicDataAPIConfig;
import com.github.seijuro.publicdata.result.PublicDataAPIErrorResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public abstract class PublicDataAPIPageableTask extends PublicDataAPITask implements PublicDataAPIPageableKeySupplier {
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
        private final int initPageIndex;

        @Getter @Setter
        int currentPage = 0;
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
            return currentPage * pageSize < totalCount;
        }
    }

    /**
     * Instance Properties
     */
    protected PagingState pageState;

    /**
     * C'tor
     *
     * @param apiService
     * @throws PublicDataAPIException
     */
    public PublicDataAPIPageableTask(PublicDataAPIServices apiService) throws PublicDataAPIException {
        super(apiService);

        pageState = new PagingState(1, getDefaultPageSize());
    }

    @Override
    public void run() {
        handleLoop();
    }

    @Override
    public void handleLoop() {
        requestState.reset();

        PublicDataAPI api = getApi();
        String serviceKey = getServiceKey(getApiService());
        PublicDataAPIConfig config = getNextConfig();
        ConfigProperty pageSizeKey = getPageSizeKey();
        ConfigProperty pageNumberKey = getPageNumberKey();

        String prop = config.getProperty(pageSizeKey, String.class);
        int pageSize = prop != null ? Integer.parseInt(prop) : getDefaultPageSize();

        pageState.reset();
        pageState.setPageSize(pageSize);

        do {
            requestState.reset();
            do {
                request(api, serviceKey, config);
            } while (requestState.shouldRetry() && runningState == RunningState.RUNNING);

            config.setProperty(pageNumberKey, pageState.getCurrentPage() + 1);
        } while (requestState.isSuccess() && pageState.hasMorePage()  && runningState == RunningState.RUNNING);
    }

    @Override
    public void handleHTTPResponse(String url, int code, String response) {
        super.handleHTTPResponse(url, code, response);
    }

    @Override
    public void handleHTTPErrorResponse(String url, int code, String response, String errmsg) {
        super.handleHTTPErrorResponse(url, code, response, errmsg);
    }

    @Override
    public void handleResult(String url, PublicDataAPIResult result) {
        super.handleResult(url, result);

        pageState.setCurrentPage(result.getPageNo());
        pageState.setCurrentResult(result.getNumberOfRows());
        pageState.setTotalCount(result.getTotalCount());
    }

    @Override
    public void handleErrorResult(String url, PublicDataAPIErrorResult result) {
        super.handleErrorResult(url, result);
    }
}
