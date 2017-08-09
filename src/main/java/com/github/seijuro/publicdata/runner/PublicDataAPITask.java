package com.github.seijuro.publicdata.runner;

import com.github.seijuro.common.InputType;
import com.github.seijuro.common.http.rest.RestfulAPIErrorResponse;
import com.github.seijuro.common.http.rest.RestfulAPIResponse;
import com.github.seijuro.publicdata.PublicDataAPIException;
import com.github.seijuro.publicdata.PublicDataAPIServices;
import com.github.seijuro.publicdata.api.config.PublicDataAPIConfig;
import com.github.seijuro.publicdata.parser.PublicDataAPIResponseParserFactory;
import com.github.seijuro.publicdata.result.PublicDataAPIErrorResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import com.github.seijuro.publicdata.api.PublicDataAPI;
import com.github.seijuro.publicdata.parser.PublicDataAPIResponseParser;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public abstract class PublicDataAPITask implements PublicDataAPIRunnable, PublicDataAPIServiceKeySupplier, IPublicDataAPIConfigSupplier {
    /**
     * enum RunningState
     */
    protected enum RunningState {
        RUNNING,
        STOP,
        SHUTDOWN;
    }

    @ToString
    protected class RequestState {
        /**
         * Instance Properties
         */
        @Setter
        private int maxTry = 0;
        private int currentTry = 0;
        @Getter @Setter
        private boolean success = false;

        public void incrementTry() {
            ++this.currentTry;
        }

        public void reset() {
            this.currentTry = 0;
            this.success = false;
        }

        public boolean shouldRetry() {
            return !isSuccess() && (currentTry < this.maxTry);
        }
    }

    /**
     * Instance Properties
     */
    protected RunningState runningState = RunningState.RUNNING;
    protected RequestState requestState = new RequestState();

    @Getter(AccessLevel.PROTECTED)
    private final PublicDataAPIServices apiService;
    @Getter(AccessLevel.PROTECTED)
    private final PublicDataAPIResponseParser parser;
    @Getter(AccessLevel.PROTECTED)
    private final PublicDataAPI api;
    @Setter(AccessLevel.PUBLIC)
    private PublicDataAPIResultDelegater delegater;

    /**
     * C'tor
     */
    public PublicDataAPITask(PublicDataAPIServices apiService) throws PublicDataAPIException {
        this.apiService = apiService;
        this.parser = PublicDataAPIResponseParserFactory.create(apiService);
        this.api = PublicDataAPIFactory.create(apiService);
        this.runningState = RunningState.RUNNING;
    }

    public void handleLoop() {
        requestState.reset();

        do {
            PublicDataAPI api = getApi();
            String serviceKey = getServiceKey(apiService);
            PublicDataAPIConfig config = getNextConfig();

            request(api, serviceKey, config);
        } while (requestState.shouldRetry() && runningState == RunningState.RUNNING);
    }

    @Override
    public void run() {
        handleLoop();
    }

    /**
     * sending request using api
     */
    public void request(PublicDataAPI api, String serviceKey, PublicDataAPIConfig config) {
        try {
            api.setServiceKey(serviceKey);
            api.setConfig(config);

            if (config != null) {
                RestfulAPIResponse response = api.request();
                String url = api.getURL();

                if (response instanceof RestfulAPIErrorResponse) {
                    RestfulAPIErrorResponse errorResponse = (RestfulAPIErrorResponse) response;
                    int status = errorResponse.getHttpResponseCode();
                    String res = errorResponse.getResponse();
                    String msg = errorResponse.getMessage();

                    handleHTTPErrorResponse(url, status, res, msg);

                    if (this.delegater != null) { this.delegater.handleHTTPErrorResponse(url, status, res, msg); }
                }
                else {
                    int status = response.getHttpResponseCode();
                    String res = response.getResponse();

                    handleHTTPResponse(url, status, res);
                    if (this.delegater != null) { this.delegater.handleHTTPResponse(url, status, res); }

                    this.parser.parse(InputType.TEXT, response.getResponse());
                    PublicDataAPIResult result = this.parser.getResult();

                    if (result instanceof PublicDataAPIErrorResult) {
                        PublicDataAPIErrorResult errRet = (PublicDataAPIErrorResult) result;

                        handleErrorResult(url, errRet);
                        if (this.delegater != null) { this.delegater.handleErrorResult(url,errRet); }
                    }
                    else {
                        handleResult(url, result);
                        if (this.delegater != null) { this.delegater.handleResult(url, result); }
                    }
                }
            }
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }
    }

    @Override
    public void handleHTTPResponse(String url, int code, String response) {
    }

    @Override
    public void handleHTTPErrorResponse(String url, int code, String response, String errmsg) {
        requestState.setSuccess(false);
        requestState.incrementTry();
    }

    @Override
    public void handleResult(String url, PublicDataAPIResult result) {
        requestState.setSuccess(true);
    }

    @Override
    public void handleErrorResult(String url, PublicDataAPIErrorResult result) {
        requestState.setSuccess(false);
        requestState.incrementTry();
    }

    @Override
    public void shutdown() {
        this.runningState = RunningState.SHUTDOWN;
    }
}
