package com.github.seijuro.publicdata;

import com.github.seijuro.publicdata.result.PublicDataAPIErrorResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;

public interface PublicDataAPIResultHandler {
    public abstract void handleHTTPResponse(int code, String response);
    public abstract void handleHTTPErrorResponse(int code, String response, String errmsg);
    public abstract void handleResult(PublicDataAPIResult result);
    public abstract void handleErrorResult(PublicDataAPIErrorResult result);
}
