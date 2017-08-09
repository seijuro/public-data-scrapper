package com.github.seijuro.publicdata.runner;

import com.github.seijuro.publicdata.PublicDataAPIException;
import com.github.seijuro.publicdata.PublicDataAPIServices;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public abstract class PublicDataAPILoopTask extends PublicDataAPITask {
    public enum RunningState {
        RUNNING,
        SHUTDOWN;
    }

    /**
     * Instance Properties
     */
    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    private RunningState runningState = RunningState.RUNNING;

    /**
     * C'tor
     *
     * @param apiService
     */
    public PublicDataAPILoopTask(PublicDataAPIServices apiService) throws PublicDataAPIException {
        super(apiService);

        this.runningState = RunningState.RUNNING;
    }

    @Override
    public void run() {
        do {
            //  TODO do something ...


        } while (this.runningState == RunningState.RUNNING);
    }

    @Override
    public void shutdown() {
        this.runningState = RunningState.SHUTDOWN;
    }
}
