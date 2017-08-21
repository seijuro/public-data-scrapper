package com.github.seijuro.publicdata.api.config;

import com.github.seijuro.publicdata.property.PublicDataProperty;
import lombok.AccessLevel;
import lombok.Getter;

public class PyramidSellingAPIConfig extends PublicDataAPIConfig {
    public enum Property implements ConfigProperty {
        NUM_OF_ROWS(PublicDataProperty.Request.NUM_OF_ROWS),
        PAGE_NO(PublicDataProperty.Request.PAGE_NO),
        BEGIN_YMD("fromYmd"),
        END_YMD("toYmd"),
        COMPANYNAME("bupNm");

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

        //  http://apis.data.go.kr/1130000/MMktInfoService/getMMktPuYearInfo?numOfRows=100&pageNo=2&fromYmd=19800101&toYmd=20151231&ServiceKey=oDaJp7XH2ZthVeeu7ziOWjwsBTjvB9PS8KqHUfIqaUE03EvVprAr9AeJWEIJtRlm9rwg6nh9Y49LXqkRme7wQA%3D%3D
        //  http://apis.data.go.kr/1130000/MMktInfoService/getMMktInfo?numOfRows=100&pageNo=2&fromYmd=19800101&toYmd=20151231&ServiceKey=oDaJp7XH2ZthVeeu7ziOWjwsBTjvB9PS8KqHUfIqaUE03EvVprAr9AeJWEIJtRlm9rwg6nh9Y49LXqkRme7wQA%3D%3D
        return super.setProperty(property, value);
    }

    @Override
    public <T extends ConfigProperty, V extends ConfigPropertyValue>
    Object setProperty(T property, V value) {
        assert property instanceof BusinessPlaceAPIConfig.Property;

        return super.setProperty(property, value);
    }

    public void setPageSize(int pageSize) {
        super.setProperty(Property.NUM_OF_ROWS, pageSize);
    }

    public void setPageNo(int pageNo) {
        super.setProperty(Property.PAGE_NO, pageNo);
    }

    public void setBeginYMD(String ymd) { super.setProperty(Property.BEGIN_YMD, ymd); }
    public void setEndYMD(String ymd) { super.setProperty(Property.END_YMD, ymd); }
    public void setCompanyName(String name) { super.setProperty(Property.COMPANYNAME, name); }

    /**
     * C'tor
     */
    public PyramidSellingAPIConfig() {
        super();
    }
}
