package com.github.seijuro.publicdata.runner;

import com.github.seijuro.publicdata.result.PublicDataAPIErrorResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;

public interface PublicDataAPIRunnable<T extends PublicDataAPIResult> extends Runnable {
    public abstract void handleHTTPResponse(String url, int code, String response);
    public abstract void handleHTTPErrorResponse(String url, int code, String response, String errmsg);
    public abstract void handleResult(String url, PublicDataAPIResult result);
    public abstract void handleErrorResult(String url, PublicDataAPIErrorResult result);

    public abstract void shutdown();
}
