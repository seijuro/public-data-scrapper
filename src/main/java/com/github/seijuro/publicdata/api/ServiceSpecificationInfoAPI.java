package com.github.seijuro.publicdata.api;

import lombok.AccessLevel;
import lombok.Getter;

public class ServiceSpecificationInfoAPI extends PublicDataAPI {
    @Getter(AccessLevel.PUBLIC)
    static final String serviceURL = "http://apis.data.go.kr/1230000/HrcspSsstndrdInfoService/getPublicPrcureThngInfoCnstwk";

    /**
     * C'tor
     */
    public ServiceSpecificationInfoAPI() {
        super(getServiceURL());
    }
}
