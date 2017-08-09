package com.github.seijuro.task;

import com.github.seijuro.publicdata.PublicDataAPIException;
import com.github.seijuro.publicdata.PublicDataAPIServices;
import com.github.seijuro.publicdata.api.PublicDataAPI;
import com.github.seijuro.publicdata.api.config.PublicDataAPIConfig;
import com.github.seijuro.publicdata.runner.PublicDataAPILoopTask;
import com.github.seijuro.publicdata.runner.PublicDataAPIServiceKeySupplier;
import com.github.seijuro.publicdata.api.config.BusinessPlaceDetailAPIConfig;
import org.apache.commons.lang3.time.DateUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;


public class BusinessPlaceDetailAPITask extends PublicDataAPILoopTask {
    /**
     * Instance Properties
     */
    private BlockingQueue<String> seqs;
    private PublicDataAPIServiceKeySupplier serviceKeySupplier = null;

    /**
     * C'tor
     *
     * @param serviceKey
     * @param ids
     * @throws PublicDataAPIException
     */
    public BusinessPlaceDetailAPITask(final String serviceKey, BlockingQueue<String> ids) throws PublicDataAPIException {
        this(apiService -> serviceKey, ids);
    }

    /**
     * C'tor
     *
     * @param $serviceKeySupplier
     * @param ids
     * @throws PublicDataAPIException
     */
    public BusinessPlaceDetailAPITask(PublicDataAPIServiceKeySupplier $serviceKeySupplier, BlockingQueue<String> ids) throws PublicDataAPIException {
        super(PublicDataAPIServices.NPS_BUSINESS_PLACE_DETAIL);

        this.seqs = ids;
        this.serviceKeySupplier = $serviceKeySupplier;
    }

    @Override
    public String getServiceKey(PublicDataAPIServices apiService) {
        return serviceKeySupplier.getServiceKey(apiService);
    }

    @Override
    public PublicDataAPIConfig getNextConfig() {
        BusinessPlaceDetailAPIConfig config = new BusinessPlaceDetailAPIConfig();
        return config;
    }

    @Override
    public void handleLoop() {
        try {
            String seq = seqs.poll(5, TimeUnit.SECONDS);
            if (seq == null) {
                System.out.println("There are no sequence id during the last 5 second(s).");

                return;
            }

            requestState.reset();

            do {
                PublicDataAPI api = getApi();
                String serviceKey = getServiceKey(getApiService());

                PublicDataAPIConfig config = getNextConfig();
                config.setProperty(BusinessPlaceDetailAPIConfig.Property.ID, seq);

                while (config == null) {
                    Thread.sleep(DateUtils.MILLIS_PER_SECOND * 3);
                    config = getNextConfig();
                }

                request(api, serviceKey, config);
            } while (requestState.shouldRetry() && runningState == RunningState.RUNNING);
        }
        catch (InterruptedException excp) {
            excp.printStackTrace();
            runningState = RunningState.STOP;
        }
    }
}
