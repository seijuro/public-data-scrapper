package com.github.seijuro.task;

import com.github.seijuro.publicdata.PublicDataAPIException;
import com.github.seijuro.publicdata.PublicDataAPIServices;
import com.github.seijuro.publicdata.api.PublicDataAPI;
import com.github.seijuro.publicdata.api.config.PublicDataAPIConfig;
import com.github.seijuro.publicdata.runner.IPublicDataAPIConfigSupplier;
import com.github.seijuro.publicdata.runner.PublicDataAPILoopTask;
import com.github.seijuro.publicdata.runner.PublicDataAPIServiceKeySupplier;
import com.github.seijuro.publicdata.api.config.BusinessPlaceDetailAPIConfig;
import com.github.seijuro.publicdata.runner.PublicDataAPITask;

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

    /**
     * implements PublicDataAPIServiceKeySupplier interface.
     *
     * see also {@link PublicDataAPIServiceKeySupplier#getServiceKey(PublicDataAPIServices)}
     *
     * @param apiService
     * @return
     */
    @Override
    public String getServiceKey(PublicDataAPIServices apiService) {
        return serviceKeySupplier.getServiceKey(apiService);
    }

    /**
     * implements IPublicDataAPIConfigSupplier interface.
     *
     * see also {@link IPublicDataAPIConfigSupplier#getNextConfig()}.
     *
     * @return
     */
    @Override
    public PublicDataAPIConfig getNextConfig() {
        BusinessPlaceDetailAPIConfig config = new BusinessPlaceDetailAPIConfig();
        return config;
    }

    /**
     * This method is called by run method.
     *
     * see also {@link PublicDataAPITask#handleLoop()}
     *
     * @throws InterruptedException
     */
    @Override
    public void handleLoop() throws InterruptedException {
        String seq = seqs.poll(5, TimeUnit.SECONDS);

        if (seq == null) {
            System.out.println("There are no sequence id during the last 5 second(s).");
            return;
        }

        requestState.reset();

        PublicDataAPI api = getApi();
        String serviceKey = getServiceKey(getApiService());
        PublicDataAPIConfig config = getNextConfig();
        config.setProperty(BusinessPlaceDetailAPIConfig.Property.ID, seq);

        do {
            request(api, serviceKey, config);
        } while (requestState.shouldRetry() && runningState == RunningState.RUNNING);
    }
}
