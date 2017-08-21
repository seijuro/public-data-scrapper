package com.github.seijuro.publicdata.runner;

import com.github.seijuro.publicdata.result.PublicDataAPIErrorResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;

import java.util.Properties;

public interface PublicDataAPIRunnable<T extends PublicDataAPIResult> extends Runnable {
    public abstract void handleHTTPResponse(String url, Properties params, int code, String response);
    public abstract void handleHTTPErrorResponse(String url, Properties params, int code, String response, String errmsg);
    public abstract void handleResult(String url, Properties params, String response, PublicDataAPIResult result);
    public abstract void handleErrorResult(String url, Properties params, String response, PublicDataAPIErrorResult result);

    public abstract void handleLoop() throws InterruptedException;
    public abstract void shutdown();
    public abstract void stop();
}
