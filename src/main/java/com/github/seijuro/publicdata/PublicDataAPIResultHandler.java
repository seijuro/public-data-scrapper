package com.github.seijuro.publicdata;

import com.github.seijuro.publicdata.result.PublicDataAPIErrorResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;

import java.util.Properties;

public interface PublicDataAPIResultHandler {
    default void handleHTTPResponse(String url, Properties params, int code, String response) {
        System.out.println(String.format("[HTTP] (status : %d) -> url : %s, params : %s", code, url, params));
    }

    default void handleHTTPErrorResponse(String url, Properties params, int code, String response, String errmsg) {
        System.out.println(String.format("[HTTP] (status : %d, msg : %s) -> url : %s, params : %s", code, errmsg, url, params));
    }

    public abstract void handleResult(String url, Properties params, String response, PublicDataAPIResult result);
    public abstract void handleErrorResult(String url, String serviceKey, Properties params, String response, PublicDataAPIErrorResult result);
}
