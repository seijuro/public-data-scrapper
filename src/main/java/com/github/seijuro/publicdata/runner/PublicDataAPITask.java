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

public abstract class PublicDataAPITask implements PublicDataAPIRunnable, PublicDataAPIServiceKeySupplier, IPublicDataAPIConfigSupplier {
    /**
     * Instance Properties
     */
    @Getter(AccessLevel.PROTECTED)
    private final PublicDataAPIServices apiService;
    @Getter(AccessLevel.PROTECTED)
    private final PublicDataAPIResponseParser parser;
    @Getter(AccessLevel.PROTECTED)
    private final PublicDataAPI api;
    @Setter(AccessLevel.PUBLIC)
    private PublicDataAPIResultDelegater delegater;
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private PublicDataAPIServiceKeySupplier serviceKeySupplier;

    /**
     * C'tor
     */
    public PublicDataAPITask(PublicDataAPIServices apiService) throws PublicDataAPIException {
        this.apiService = apiService;
        this.parser = PublicDataAPIResponseParserFactory.create(apiService);
        this.api = PublicDataAPIFactory.create(apiService);
    }

    /**
     * implements PublicDataAPIServiceKeySupplier
     *
     * @return
     */
    public String getServiceKey() {
        PublicDataAPIServiceKeySupplier serviceKeySupplier = getServiceKeySupplier();
        return (serviceKeySupplier == null) ? null : serviceKeySupplier.getServiceKey(getApiService());
    }

    @Override
    public void run() {
        request(getApi(), getServiceKey(this.apiService), getNextConfig());
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
}
