package com.github.seijuro.publicdata.api;

import lombok.AccessLevel;
import lombok.Getter;

public class ProductSpecificationInfoAPI extends PublicDataAPI {
    @Getter(AccessLevel.PUBLIC)
    public static final String serviceURL = "http://apis.data.go.kr/1230000/HrcspSsstndrdInfoService/getPublicPrcureThngInfoThng";

    /**
     * C'tor
     */
    public ProductSpecificationInfoAPI() {
        super(getServiceURL());
    }
}
