package com.github.seijuro.publicdata.runner;

import com.github.seijuro.publicdata.PublicDataAPIServices;

public interface PublicDataAPIServiceKeySupplier {
    public abstract String getServiceKey(PublicDataAPIServices apiService);
}
