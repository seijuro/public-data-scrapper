package com.github.seijuro.task;

import com.github.seijuro.publicdata.PublicDataAPIException;
import com.github.seijuro.publicdata.PublicDataAPIServices;
import com.github.seijuro.publicdata.api.PublicDataAPI;
import com.github.seijuro.publicdata.api.config.PublicDataAPIConfig;
import com.github.seijuro.publicdata.result.PublicDataAPIErrorResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import com.github.seijuro.publicdata.runner.PublicDataAPILoopTask;
import com.github.seijuro.publicdata.runner.PublicDataAPIServiceKeySupplier;
import com.github.seijuro.publicdata.runner.PublicDataAPITask;
import com.github.seijuro.publicdata.api.config.BusinessPlaceDetailAPIConfig;
import org.apache.commons.lang3.time.DateUtils;

import java.util.List;
import java.util.Queue;


public class BusinessPlaceDetailAPITask extends PublicDataAPILoopTask {
    /**
     * Instance Properties
     */
    private Queue<String> seqs;
    private PublicDataAPIServiceKeySupplier serviceKeySupplier = null;

    /**
     * C'tor
     *
     * @param serviceKey
     * @param ids
     * @throws PublicDataAPIException
     */
    public BusinessPlaceDetailAPITask(final String serviceKey, Queue<String> ids) throws PublicDataAPIException {
        this(apiService -> serviceKey, ids);
    }

    /**
     * C'tor
     *
     * @param $serviceKeySupplier
     * @param ids
     * @throws PublicDataAPIException
     */
    public BusinessPlaceDetailAPITask(PublicDataAPIServiceKeySupplier $serviceKeySupplier, Queue<String> ids) throws PublicDataAPIException {
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
        String seq = seqs.poll();

        System.out.println("retrieving config ... (seq # : " + seqs.size() + ")");

        if (seq != null) {
            BusinessPlaceDetailAPIConfig config = new BusinessPlaceDetailAPIConfig();
            config.setProperty(BusinessPlaceDetailAPIConfig.Property.ID, seq);

            return config;
        }

        return null;
    }

    @Override
    public void handleLoop() {
        requestState.reset();

        try {
            do {
                PublicDataAPI api = getApi();
                String serviceKey = getServiceKey(getApiService());
                PublicDataAPIConfig config = getNextConfig();

                while (config == null) {
                    Thread.sleep(DateUtils.MILLIS_PER_SECOND * 3);
                    config = getNextConfig();
                }

                request(api, serviceKey, config);
            } while (requestState.shouldRetry() && runningState == RunningState.RUNNING);
        }
        catch (InterruptedException excp) {
            excp.printStackTrace();
        }
    }
}
