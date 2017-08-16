package com.github.seijuro.publicdata.runner;

import com.github.seijuro.publicdata.api.config.PublicDataAPIConfig;

import java.util.Objects;

public interface PagePropertyExtractor<T extends PublicDataAPIConfig> {
    public abstract int apply(T config);

    default PagePropertyExtractor<T> or(PagePropertyExtractor<T> defaultExtractor) {
        Objects.requireNonNull(defaultExtractor);

        return (config) -> {
            int ret = apply(config);
            return ret == Integer.MIN_VALUE ? defaultExtractor.apply(config) : apply(config);
        };
    }
}
