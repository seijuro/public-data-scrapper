package com.github.seijuro.publicdata.runner;

import com.github.seijuro.publicdata.api.config.PublicDataAPIConfig;

public interface PagePropertySupplier<T extends PublicDataAPIConfig> {
    public abstract void supply(T config, int value);
}
