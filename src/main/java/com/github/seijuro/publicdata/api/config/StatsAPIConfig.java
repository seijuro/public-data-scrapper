package com.github.seijuro.publicdata.api.config;

import lombok.AccessLevel;
import lombok.Getter;

public class StatsAPIConfig extends PublicDataAPIConfig {
    /**
     * Property
     */
    public enum Property implements ConfigProperty {
        ID("seq"),
        YEAR_MONTH("data_crt_ym");

        @Getter(AccessLevel.PUBLIC)
        private final String property;

        Property(String prop) {
            this.property = prop;
        }
    }

    /**
     * C'tor
     */
    public StatsAPIConfig() {
        super();
    }

    @Override
    public <T extends ConfigProperty, V extends Number>
    Object setProperty(T property, V value) {
        assert(property instanceof StatsAPIConfig.Property);
        return super.setProperty(property, value);
    }

    @Override
    public <T extends ConfigProperty, V extends String>
    Object setProperty(T property, V value) {
        assert(property instanceof StatsAPIConfig.Property);
        return super.setProperty(property, value);
    }

    @Override
    public <T extends ConfigProperty, V extends ConfigPropertyValue>
    Object setProperty(T property, V value) {
        assert(property instanceof StatsAPIConfig.Property);
        return super.setProperty(property, value);
    }
}
