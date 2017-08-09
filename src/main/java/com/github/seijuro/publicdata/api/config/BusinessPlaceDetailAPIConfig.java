package com.github.seijuro.publicdata.api.config;

import lombok.AccessLevel;
import lombok.Getter;

public class BusinessPlaceDetailAPIConfig extends PublicDataAPIConfig {
    /**
     * Property
     */
    public enum Property implements ConfigProperty {
        ID("seq");

        /**
         * Instance Property
         */
        @Getter(AccessLevel.PUBLIC)
        final String property;

        /**
         * C'tor
         */
        Property(String prop) {
            this.property = prop;
        }
    }

    /**
     * C'tor
     */
    public BusinessPlaceDetailAPIConfig() {
        super();
    }

    @Override
    public <T extends ConfigProperty, V extends Number>
    Object setProperty(T property, V value) {
        assert(property instanceof Property);
        return super.setProperty(property, value);
    }

    @Override
    public <T extends ConfigProperty, V extends String>
    Object setProperty(T property, V value) {
        assert(property instanceof Property);
        return super.setProperty(property, value);
    }

    @Override
    public <T extends ConfigProperty, V extends ConfigPropertyValue>
    Object setProperty(T property, V value) {
        assert(property instanceof Property);
        return super.setProperty(property, value);
    }
}
