package com.github.seijuro.publicdata.runner;

import com.github.seijuro.publicdata.PublicDataAPIException;
import com.github.seijuro.publicdata.PublicDataAPIServices;

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

        System.out.println("[INFO] stop thread ...");
    }
}
