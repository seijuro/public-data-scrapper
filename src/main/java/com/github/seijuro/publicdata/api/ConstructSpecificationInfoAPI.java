package com.github.seijuro.publicdata.api;

import lombok.AccessLevel;
import lombok.Getter;

public class ConstructSpecificationInfoAPI extends PublicDataAPI {
    @Getter(AccessLevel.PUBLIC)
    static final String serviceURL = "http://apis.data.go.kr/1230000/HrcspSsstndrdInfoService/getPublicPrcureThngInfoFrgcpt";

    /**
     * C'tor
     */
    public ConstructSpecificationInfoAPI() {
        super(getServiceURL());
    }
}
