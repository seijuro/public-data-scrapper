package com.github.seijuro.publicdata.runner;

import com.github.seijuro.publicdata.api.config.ConfigProperty;

public interface PublicDataAPIPageable {
    public abstract ConfigProperty getPageSizeKey();
    public abstract ConfigProperty getPageNumberKey();
}
