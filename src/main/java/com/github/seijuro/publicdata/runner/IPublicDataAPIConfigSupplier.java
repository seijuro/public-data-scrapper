package com.github.seijuro.publicdata.runner;

import com.github.seijuro.publicdata.api.config.PublicDataAPIConfig;

public interface IPublicDataAPIConfigSupplier {
    public abstract PublicDataAPIConfig getNextConfig();
}
