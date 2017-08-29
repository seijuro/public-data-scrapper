package com.github.seijuro.publicdata.runner;

import com.github.seijuro.publicdata.PublicDataAPIException;
import com.github.seijuro.publicdata.PublicDataAPIServices;
import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class PublicDataAPIPageableLoopTask extends PublicDataAPIPageableTask {
    /**
     * C'tor
     *
     * @param apiService
     * @throws PublicDataAPIException
     */
    public PublicDataAPIPageableLoopTask(PublicDataAPIServices apiService) throws PublicDataAPIException {
        super(apiService);
    }

    @Override
    public void run() {
        try {
            do {
                handleLoop();
            } while (runningState == RunningState.RUNNING);
        }
        catch (InterruptedException excp) {
            excp.printStackTrace();
        }

        //  Log
        log.debug("reach to the end of 'run' ...");
    }
}
