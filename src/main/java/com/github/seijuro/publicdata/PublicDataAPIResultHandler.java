package com.github.seijuro.publicdata;

import com.github.seijuro.publicdata.result.PublicDataAPIErrorResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;

public interface PublicDataAPIResultHandler {
    public abstract void handleHTTPResponse(String url, int code, String response);
    public abstract void handleHTTPErrorResponse(String url, int code, String response, String errmsg);
    public abstract void handleResult(String url, PublicDataAPIResult result);
    public abstract void handleErrorResult(String url, PublicDataAPIErrorResult result);
}
