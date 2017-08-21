package com.github.seijuro.publicdata.api.config;

import com.github.seijuro.publicdata.address.JibunAddress;
import com.github.seijuro.publicdata.property.PublicDataProperty;
import lombok.AccessLevel;
import lombok.Getter;

public class TheUseOfEnergyAPIConfig extends PublicDataAPIConfig {
    /**
     * Property
     */
    public enum Property implements ConfigProperty {
        NUM_OF_ROWS(PublicDataProperty.Request.NUM_OF_ROWS),
        PAGE_NO(PublicDataProperty.Request.PAGE_NO),
        ADDRESS_DGSGGU("sigunguCd"),
        ADDRESS_EMD("bjdongCd"),
        ADDRESS_JIBUN_MAJOR("bun"),
        ADDRESS_JIBUN_MINOR("ji"),
        DATE_YM("useYm");

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
     * set properties related to address
     *
     * @param addr
     * @return
     */
    public void setJibunAddress(JibunAddress addr) {
        super.setProperty(Property.ADDRESS_DGSGGU, addr.getCodeDGSGGUString());
        super.setProperty(Property.ADDRESS_EMD, addr.getCodeEMDString());
        super.setProperty(Property.ADDRESS_JIBUN_MAJOR, addr.getJIBUNMajor());
        super.setProperty(Property.ADDRESS_JIBUN_MINOR, addr.getJIBUNMinor());
    }

    public void setUseYM(String dateYM) {
        super.setProperty(Property.DATE_YM, dateYM);
    }

    public String getUseYM() {
        return getProperty(Property.DATE_YM, String.class);
    }

    public void setPageSize(int pageSize) {
        super.setProperty(Property.NUM_OF_ROWS, pageSize);
    }

    public void setPageNo(int pageNo) {
        super.setProperty(Property.PAGE_NO, pageNo);
    }

    /**
     * C'tor
     */
    public TheUseOfEnergyAPIConfig() {
        super();
    }
}
