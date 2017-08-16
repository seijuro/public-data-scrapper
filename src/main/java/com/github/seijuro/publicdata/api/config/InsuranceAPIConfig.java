package com.github.seijuro.publicdata.api.config;

import com.github.seijuro.publicdata.property.PublicDataProperty;
import lombok.Getter;

public class InsuranceAPIConfig extends PublicDataAPIConfig {
    public enum Property implements ConfigProperty {
        NUM_OF_ROWS(PublicDataProperty.Request.NUM_OF_ROWS),
        PAGE_NO(PublicDataProperty.Request.PAGE_NO),
        TYPE("boheomFg");

        @Getter
        private final String property;

        /**
         * C'tor
         *
         * @param name
         */
        Property(String name) {
            property = name;
        }
    }

    @Override
    public <T extends ConfigProperty, V extends Number>
    Object setProperty(T property, V number) {
        assert property instanceof BusinessPlaceAPIConfig.Property;

        if (property == BusinessPlaceAPIConfig.Property.NUM_OF_ROWS) {
            return super.setProperty(property, PublicDataAPIConfigHelper.DataGoKr.getNumberOfRows(number));
        }
        else if (property == BusinessPlaceAPIConfig.Property.PAGE_NO) {
            return super.setProperty(property, PublicDataAPIConfigHelper.DataGoKr.getPageNot(number));
        }

        return super.setProperty(property, number);
    }

    @Override
    public <T extends ConfigProperty, V extends String>
    Object setProperty(T property, V value) {
        assert property instanceof BusinessPlaceAPIConfig.Property;

        if (property == BusinessPlaceAPIConfig.Property.NUM_OF_ROWS) {
            return super.setProperty(property, PublicDataAPIConfigHelper.DataGoKr.getNumberOfRows(value));
        }
        else if (property == BusinessPlaceAPIConfig.Property.PAGE_NO) {
            return super.setProperty(property, PublicDataAPIConfigHelper.DataGoKr.getPageNot(value));
        }

        return super.setProperty(property, value);
    }

    @Override
    public <T extends ConfigProperty, V extends ConfigPropertyValue>
    Object setProperty(T property, V value) {
        assert property instanceof BusinessPlaceAPIConfig.Property;

        return super.setProperty(property, value);
    }

    /**
     * C'tor
     */
    public InsuranceAPIConfig() {
        super();
    }
}
