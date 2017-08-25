package com.github.seijuro.publicdata.runner;

import com.github.seijuro.publicdata.PublicDataAPIServices;

public interface PublicDataAPIServiceKeySupplier {
    /**
     * If you wanna use various PublicDataAPIs within single task, you could manage API service key by implementing this method.
     * i.e
     * {@code public String getServiceKey(PublicDataAPIServices apiService) {
     *     if (apiServiceKey == PublicDataAPIServices.NPS_BUSINESS_PLACE_NORMAL) { return serviceKey1; }
     *     if (apiServiceKey == PublicDataAPIServices.SPEC_CONSTRUCT) { return serviceKey2; }
     * }
     *
     * @param apiService
     * @return
     */
    public abstract String getServiceKey(PublicDataAPIServices apiService) throws InterruptedException;
}
