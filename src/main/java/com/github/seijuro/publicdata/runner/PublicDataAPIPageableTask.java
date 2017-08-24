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
    protected int pageSize = 1000;
    @Getter
    protected int pageNo = 1;

    public void setPageNo(int $pageNo) {
        pageNo = $pageNo;
        pageState.setInitPageIndex(pageNo);
    }

    public void setPageSize(int $pageSize) {
        pageSize = $pageSize;
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

        pageState = new PagingState(1, getPageNo());
    }

    @Override
    public void run() {
        try {
            handleLoop();
        }
        catch (InterruptedException excp) {
            excp.printStackTrace();
        }

        System.out.println("[INFO] stop thread ...");
    }

    @Override
    public void handleLoop() throws InterruptedException {
        requestState.reset();

        PublicDataAPI api = getApi();
        String serviceKey = getServiceKey(getApiService());
        PublicDataAPIConfig config = getNextConfig();

        if (config == null) {
            stop();

            return;
        }

        int pageSize = pageSizeExtractor.or((config1 -> getPageSize())).apply(config);

        pageState.reset();
        pageState.setPageSize(pageSize);

        try {
            do {
                requestState.reset();

                do {
                    VisitCheckable.Result visitResult = didAlreadyVisit(api, config);

                    if (visitResult == Result.VISITED) {
                        pageState.setCurrentPage(pageNoExtractor.apply(config));
                        pageState.setTotalCount(Integer.MAX_VALUE);

                        requestState.setSuccess(true);

                        break;
                    }
                    else if (visitResult == Result.VISITED_AND_LAST_PAGE) {
                        return;
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

        System.out.println(String.format("result := {no : %d, size : %d, total : %d}", result.getPageNo(), result.getNumberOfRows(), result.getTotalCount()));

        pageState.setCurrentPage(result.getPageNo());
        pageState.setCurrentResult(result.getNumberOfRows());
        pageState.setTotalCount(result.getTotalCount());
    }

    @Override
    public void handleErrorResult(String url, String serviceKey, Properties props, String response, PublicDataAPIErrorResult result) {
        super.handleErrorResult(url, serviceKey, props, response, result);
    }
}
