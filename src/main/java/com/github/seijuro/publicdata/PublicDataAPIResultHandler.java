package com.github.seijuro.publicdata;

import com.github.seijuro.publicdata.result.PublicDataAPIErrorResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public interface PublicDataAPIResultHandler {
    Logger logger = LoggerFactory.getLogger(PublicDataAPIResultHandler.class);

    default void handleHTTPResponse(String url, Properties params, int code, String response) {
        logger.info("status : {} -> url : {}, params : {}", code, url, params);
    }

    default void handleHTTPErrorResponse(String url, Properties params, int code, String response, String errmsg) {
        logger.error("status : {}, msg : {} -> url : {}, params : {}", code, errmsg, url, params);
    }

    public abstract void handleResult(String url, Properties params, String response, PublicDataAPIResult result);
    public abstract void handleErrorResult(String url, String serviceKey, Properties params, String response, PublicDataAPIErrorResult result);
}
