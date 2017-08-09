package com.github.seijuro.task;

import com.github.seijuro.publicdata.PublicDataAPIException;
import com.github.seijuro.publicdata.PublicDataAPIServices;
import com.github.seijuro.publicdata.api.config.PublicDataAPIConfig;
import com.github.seijuro.publicdata.result.PublicDataAPIErrorResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import com.github.seijuro.publicdata.runner.PublicDataAPITask;
import com.github.seijuro.publicdata.api.config.BusinessPlaceDetailAPIConfig;

import java.util.List;


public class BusinessPlaceDetailAPITask extends PublicDataAPITask {
    /**
     * Instance Properties
     */
    private final List<String> seqs;
    private int currentIndex = 0;

    /**
     * C'tor
     *
     * @param ids
     * @throws PublicDataAPIException
     */
    public BusinessPlaceDetailAPITask(List<String> ids) throws PublicDataAPIException {
        super(PublicDataAPIServices.NPS_BUSINESS_PLACE_DETAIL);

        this.seqs = ids;
    }

    @Override
    public String getServiceKey(PublicDataAPIServices apiService) {
        return getServiceKeySupplier().getServiceKey(apiService);
    }

    @Override
    public PublicDataAPIConfig getNextConfig() {
        if (this.currentIndex < seqs.size()) {
            String seq = this.seqs.get(this.currentIndex);

            BusinessPlaceDetailAPIConfig config = new BusinessPlaceDetailAPIConfig();
            config.setProperty(BusinessPlaceDetailAPIConfig.Property.ID, seq);

            return config;
        }

        return null;
    }

    @Override
    public void run() {
        super.run();
    }
}
