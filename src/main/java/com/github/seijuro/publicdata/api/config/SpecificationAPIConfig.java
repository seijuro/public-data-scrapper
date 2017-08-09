package com.github.seijuro.publicdata.api.config;

import com.github.seijuro.publicdata.property.PublicDataProperty;
import lombok.AccessLevel;
import lombok.Getter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class SpecificationAPIConfig extends PublicDataAPIConfig {
    /**
     * Property
     */
    public enum Property implements ConfigProperty {
        NUM_OF_ROWS(PublicDataProperty.Request.NUM_OF_ROWS),
        PAGE_NO(PublicDataProperty.Request.PAGE_NO),

        INQUERY_DIV("inqryDiv"),
        INQUERY_BEGIN_DATE("inqryBgnDt"),
        INQUERY_END_DATE("inqryEndDt"),
        REGISTER_NO("bfSpecRgstNo");

        @Getter(AccessLevel.PUBLIC)
        private final String property;

        Property(String prop) {
            this.property = prop;
        }
    }

    /**
     * enum - InqueryDiv
     */
    public enum InqueryDiv implements ConfigPropertyValue {
        //  reserved
        INQUERY_DIV_DATE("1"),
        INQUERY_DIV_REG_NO("2");

        @Getter(AccessLevel.PUBLIC)
        private final String value;

        InqueryDiv(String $code) {
            this.value = $code;
        }
    }


    @Override
    public <T extends ConfigProperty, V extends Number>
    Object setProperty(T property, V number) {
        assert property instanceof Property;

        if (property == Property.NUM_OF_ROWS) {
            super.setProperty(property, PublicDataAPIConfigHelper.DataGoKr.getNumberOfRows(number));
        }
        else if (property == Property.PAGE_NO) {
            super.setProperty(property, PublicDataAPIConfigHelper.DataGoKr.getPageNot(number));
        }

        return super.setProperty(property, number);
    }

    @Override
    public <T extends ConfigProperty, V extends String>
    Object setProperty(T property, V value) {
        assert property instanceof Property;

        if (property == Property.NUM_OF_ROWS) {
            super.setProperty(property, PublicDataAPIConfigHelper.DataGoKr.getNumberOfRows(value));
        }
        else if (property == Property.PAGE_NO) {
            super.setProperty(property, PublicDataAPIConfigHelper.DataGoKr.getPageNot(value));
        }

        return super.setProperty(property, value);
    }

    @Override
    public <T extends ConfigProperty, V extends ConfigPropertyValue>
    Object setProperty(T property, V value) {
        assert property instanceof Property;

        return super.setProperty(property, value);
    }

    public SpecificationAPIConfig() {
        super();
    }

    public SpecificationAPIConfig setPageSize(int size) {
        this.setProperty(Property.NUM_OF_ROWS, size);

        return this;
    }

    public SpecificationAPIConfig setPageNo(int no) {
        this.setProperty(Property.PAGE_NO, no);

        return this;
    }

    public SpecificationAPIConfig byDate(String beginDatetime, String endDatetime) {
        this.setProperty(Property.INQUERY_DIV, InqueryDiv.INQUERY_DIV_DATE.getValue());
        this.setProperty(Property.INQUERY_BEGIN_DATE, beginDatetime);
        this.setProperty(Property.INQUERY_END_DATE, endDatetime);

        return this;
    }

    public SpecificationAPIConfig byDate(DateTime beginDatetime, DateTime endDatetime) {
        DateTimeFormatter dtFormatter = DateTimeFormat.forPattern("yyyyMMddHHmm");

        return byDate(dtFormatter.print(beginDatetime), dtFormatter.print(endDatetime));
    }

    public SpecificationAPIConfig byRegisterNo(String no) {
        this.setProperty(Property.INQUERY_DIV, InqueryDiv.INQUERY_DIV_REG_NO.getValue());
        this.setProperty(Property.REGISTER_NO, no);

        return this;
    }
}
