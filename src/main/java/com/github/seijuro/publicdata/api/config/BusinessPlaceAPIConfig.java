package com.github.seijuro.publicdata.api.config;

import com.github.seijuro.publicdata.address.LegalDongAddressCode;
import com.github.seijuro.publicdata.property.PublicDataProperty;
import lombok.AccessLevel;
import lombok.Getter;

public class BusinessPlaceAPIConfig extends PublicDataAPIConfig {
    /**
     * Property
     */
    public enum Property implements ConfigProperty {
        NUM_OF_ROWS(PublicDataProperty.Request.NUM_OF_ROWS),
        PAGE_NO(PublicDataProperty.Request.PAGE_NO),
        ADDRESS_DG("ldong_addr_mgpl_dg_cd"),
        ADDRESS_SGGU("ldong_addr_mgpl_sggu_cd"),
        ADDRESS_EMD("ldong_addr_mgpl_sggu_emd_cd"),
        NAME("wkpl_nm"),
        DIVISION_CODE("wkpl_styl_dvcd"),
        REGISTRATION_NUMBER("bzowr_rgst_no");

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
     * enum - DivisionCode
     */
    public enum DivisionCode implements ConfigPropertyValue {
        COPORATION("1"),
        INDIVISUAL("2");

        @Getter(AccessLevel.PUBLIC)
        private final String value;

        DivisionCode(String $code) {
            this.value = $code;
        }
    }

    @Override
    public <T extends ConfigProperty, V extends Number>
    Object setProperty(T property, V number) {
        assert property instanceof Property;

        if (property == Property.NUM_OF_ROWS) {
            return super.setProperty(property, PublicDataAPIConfigHelper.DataGoKr.getNumberOfRows(number));
        }
        else if (property == Property.PAGE_NO) {
            return super.setProperty(property, PublicDataAPIConfigHelper.DataGoKr.getPageNot(number));
        }

        return super.setProperty(property, number);
    }

    @Override
    public <T extends ConfigProperty, V extends String>
    Object setProperty(T property, V value) {
        assert property instanceof Property;

        if (property == Property.NUM_OF_ROWS) {
            return super.setProperty(property, PublicDataAPIConfigHelper.DataGoKr.getNumberOfRows(value));
        }
        else if (property == Property.PAGE_NO) {
            return super.setProperty(property, PublicDataAPIConfigHelper.DataGoKr.getPageNot(value));
        }

        return super.setProperty(property, value);
    }

    @Override
    public <T extends ConfigProperty, V extends ConfigPropertyValue>
    Object setProperty(T property, V value) {
        assert property instanceof Property;

        return super.setProperty(property, value);
    }

    /**
     * set properties related to address
     *
     * @param code
     * @return
     */
    public void setLegalDongAddress(LegalDongAddressCode code) {
        super.setProperty(Property.ADDRESS_DG, code.getDGString());
        super.setProperty(Property.ADDRESS_SGGU, code.getSGGUString());
        super.setProperty(Property.ADDRESS_EMD, code.getEMDUString());
    }

    /**
     * C'tor
     */
    public BusinessPlaceAPIConfig() {
        super();
    }
}
