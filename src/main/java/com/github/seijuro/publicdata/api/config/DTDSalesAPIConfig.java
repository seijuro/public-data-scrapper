package com.github.seijuro.publicdata.api.config;

import com.github.seijuro.publicdata.property.PublicDataProperty;
import lombok.AccessLevel;
import lombok.Getter;

public class DTDSalesAPIConfig extends PublicDataAPIConfig {
    public enum Property implements ConfigProperty {
        NUM_OF_ROWS(PublicDataProperty.Request.NUM_OF_ROWS),
        PAGE_NO(PublicDataProperty.Request.PAGE_NO),
        BEGIN_YMD("fromPermYmd"),
        END_YMD("toPermYmd");

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
}
