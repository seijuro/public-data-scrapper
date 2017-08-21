package com.github.seijuro.publicdata.runner;

import com.github.seijuro.common.InputType;
import com.github.seijuro.common.http.rest.RestfulAPIErrorResponse;
import com.github.seijuro.common.http.rest.RestfulAPIResponse;
import com.github.seijuro.publicdata.PublicDataAPIException;
import com.github.seijuro.publicdata.PublicDataAPIServices;
import com.github.seijuro.publicdata.api.PublicDataAPIFactory;
import com.github.seijuro.publicdata.api.config.PublicDataAPIConfig;
import com.github.seijuro.publicdata.parser.PublicDataAPIResponseParserFactory;
import com.github.seijuro.publicdata.result.PublicDataAPIErrorResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import com.github.seijuro.publicdata.api.PublicDataAPI;
import com.github.seijuro.publicdata.parser.PublicDataAPIResponseParser;
import com.github.seijuro.publicdata.PublicDataAPIResultHandler;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Properties;

/**
 * For scapping data using PublicDataAPIs, send requests and handle response repeatedly.
 * To implements sending repeated requests & handling response, the class which extends <code>Thread</code> or implements <code>Runnable</code> interface is suitable.
 * For this purpose, <code>PublicDataAPITask</code> class is impplemnting <code>PublicDataAPIRunnable</code>.
 * <code>PublicDataAPITask</code> is implementing two more interfaces, <code>PublicDataAPIServiceKeySupplier</code> & <code>IPublicDataAPIConfigSupplier</code>.
 * <code>PublicDataAPITask</code> have no idea about serviceKey and configuration for API(these kinda information are decided later).
 *
 * {@link PublicDataAPIRunnable}
 * {@link PublicDataAPIServiceKeySupplier}
 * {@link IPublicDataAPIConfigSupplier}
 */
public abstract class PublicDataAPITask implements PublicDataAPIRunnable, VisitCheckable, PublicDataAPIServiceKeySupplier, IPublicDataAPIConfigSupplier {
    @Getter
    private static final long DefaultSleepSeconds = 3L;

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
        private long retryAfter = 3;

        public void incrementTry() {
            ++this.currentTry;
        }

        public void reset() {
            currentTry = 0;
            success = false;
            retryAfter = getDefaultSleepSeconds();
        }

        public long getRetryAfter() {
            retryAfter *= 5L;
            return retryAfter * DateUtils.MILLIS_PER_SECOND;
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
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC)
    private int revision = 0;

    public void setMaxTry(int max) {
        this.requestState.setMaxTry(max);
    }

    /**
     * Constructs an <code>PublicDataAPITask</code> and takes <code>PublicDataAPIServices</code> as an parameter.
     * The parameter, <code>PublicDataAPIServices</code>, will be used to make api using <code>PublicDataAPIFactory</code>,
     * retrieve serviceKey using <code>PublicDataAPIServiceKeySupplier</code>, etc ...
     *
     * @param apiService
     * @throws PublicDataAPIException
     */
    public PublicDataAPITask(PublicDataAPIServices apiService) throws PublicDataAPIException {
        this.apiService = apiService;
        this.parser = PublicDataAPIResponseParserFactory.create(apiService);
        this.api = PublicDataAPIFactory.create(apiService);
        this.runningState = RunningState.RUNNING;
    }

    /**
     * This method is called by run method.
     * If run method wrapped this method with loop, this method would be called multiple-times.
     * i.e
     * {@code @Override public void run() {
     *      try {
     *          do {
     *              handleLoop()
     *          } while (true)
     *      }
     *      catch (InterruptedException excp) {
     *          excp.printStackTrace();
     *      }
     * }}
     * Otherwise, this method would be called just once.
     * That is, How many time would be called totally depends on run method.
     *
     * @throws InterruptedException
     */
    public void handleLoop() throws InterruptedException {
        requestState.reset();

        try {
            do {
                PublicDataAPI api = getApi();
                String serviceKey = getServiceKey(apiService);
                PublicDataAPIConfig config = getNextConfig();

                if (config == null) {
                    System.out.println("[INFO] next config is 'NULL'.");
                    System.out.println("[INFO] stop thread ...");

                    stop();

                    return;
                }

                VisitCheckable.Result visitResult = didAlreadyVisit(api, config);

                if (visitResult == Result.VISITED ||
                        visitResult == Result.VISITED_AND_LAST_PAGE) {
                    break;
                }

                request(api, serviceKey, config);

                long sleepMillis = getDefaultSleepSeconds() * DateUtils.MILLIS_PER_SECOND;

                if (!requestState.isSuccess()) {
                    sleepMillis = requestState.getRetryAfter();

                    System.out.println(String.format("[SLEEP] %d ms (back off)", sleepMillis));
                }

                Thread.sleep(sleepMillis);

            } while (requestState.shouldRetry() && runningState == RunningState.RUNNING);
        }
        catch (TaskExeption excp) {
            excp.printStackTrace();
        }
    }

    /**
     * {@link Runnable#run()}
     */
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

    /**
     * Sending request message using <code>PublicDataAPI</code> and handle https response.
     * 1. Check a http response. If the response contained the http status code within the range, 200 ~ 299,
     * call <code>handleHTTPResponse</code> and go to next step. Otherwise, call <code>handleHTTPErrorResponse</code> and finish.
     * 2. Parse the http response message. If the result of parsing HTTP response message was ok, it would call <code>handleResult</code>.
     * Otherwise, call <code>handleErrorResult</code>.
     * Additionally, If you register the delegator, this will delegate all event.
     *
     * {@link PublicDataAPIResultDelegater}
     * {@link PublicDataAPIResultHandler}
     *
     * @param api
     * @param serviceKey
     * @param config
     */
    public void request(PublicDataAPI api, String serviceKey, PublicDataAPIConfig config) {
        try {
            api.setServiceKey(serviceKey);
            api.setConfig(config);

            if (config != null) {
                System.out.println(String.format("[HTTP] request ... (timeout : %d)", api.getReadTimeout()));

                RestfulAPIResponse response = api.request();
                String url = api.getRequestURL();
                Properties props = config.getProperties();

                if (response instanceof RestfulAPIErrorResponse) {
                    RestfulAPIErrorResponse errorResponse = (RestfulAPIErrorResponse) response;
                    int status = errorResponse.getHttpResponseCode();
                    String res = errorResponse.getResponse();
                    String msg = errorResponse.getMessage();

                    handleHTTPErrorResponse(url, props, status, res, msg);

                    if (this.delegater != null) { this.delegater.handleHTTPErrorResponse(url, props, status, res, msg); }
                }
                else {
                    int status = response.getHttpResponseCode();
                    String res = response.getResponse();

                    handleHTTPResponse(url, config.getProperties(), status, res);
                    if (this.delegater != null) { this.delegater.handleHTTPResponse(url, props, status, res); }

                    this.parser.parse(InputType.TEXT, res);
                    PublicDataAPIResult result = this.parser.getResult();

                    if (result instanceof PublicDataAPIErrorResult) {
                        PublicDataAPIErrorResult errRet = (PublicDataAPIErrorResult) result;

                        handleErrorResult(url, props, res, errRet);
                        if (this.delegater != null) { this.delegater.handleErrorResult(url, props, res, errRet); }
                    }
                    else {
                        handleResult(url, config.getProperties(), res, result);
                        if (this.delegater != null) { this.delegater.handleResult(url, props, res, result); }
                    }
                }
            }
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }
    }

    /**
     * Implements <code>PublicDataAPIHandler#handleHTTPResponse</code>
     *
     * {@link PublicDataAPIResultHandler}
     *
     * @param url
     * @param code
     * @param response
     */
    @Override
    public void handleHTTPResponse(String url, Properties params, int code, String response) {
    }

    /**
     * Implements <code>PublicDataAPIHandler#handleHTTPResponse</code>.
     *
     * {@link PublicDataAPIResultHandler}
     *
     * @param url
     * @param code
     * @param response
     * @param errmsg
     */
    @Override
    public void handleHTTPErrorResponse(String url, Properties params, int code, String response, String errmsg) {
        requestState.setSuccess(false);
        requestState.incrementTry();
    }

    /**
     * Implements <code>PublicDataAPIHandler#handleResult</code>
     *
     * {@link PublicDataAPIResultHandler#handleResult}
     *
     * @param url
     * @param params
     * @param response
     * @param result
     */
    @Override
    public void handleResult(String url, Properties params, String response, PublicDataAPIResult result) {
        requestState.setSuccess(true);
    }

    /**
     * Implements <code>PublicDataAPIHandler#handleErrorResult</code>
     *
     * {@link PublicDataAPIResultHandler#handleErrorResult}
     *
     * @param url
     * @param params
     * @param response
     * @param result
     */
    @Override
    public void handleErrorResult(String url, Properties params, String response, PublicDataAPIErrorResult result) {
        requestState.setSuccess(false);
        requestState.incrementTry();
    }

    @Override
    public void shutdown() {
        this.runningState = RunningState.SHUTDOWN;
    }

    @Override
    public void stop() { this.runningState = RunningState.STOP; }
}
