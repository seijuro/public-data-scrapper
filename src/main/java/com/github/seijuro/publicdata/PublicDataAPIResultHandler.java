package com.github.seijuro.publicdata;

import com.github.seijuro.publicdata.result.PublicDataAPIErrorResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;

import java.util.Properties;

public interface PublicDataAPIResultHandler {
    public abstract void handleHTTPResponse(String url, Properties params, int code, String response);
    public abstract void handleHTTPErrorResponse(String url, Properties params, int code, String response, String errmsg);
    public abstract void handleResult(String url, Properties params, String response, PublicDataAPIResult result);
    public abstract void handleErrorResult(String url, Properties params, String response, PublicDataAPIErrorResult result);
}
