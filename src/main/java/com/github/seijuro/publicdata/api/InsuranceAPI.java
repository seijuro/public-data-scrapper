package com.github.seijuro.publicdata.api;

import lombok.AccessLevel;
import lombok.Getter;

public class InsuranceAPI extends PublicDataAPI {
    @Getter(AccessLevel.PUBLIC)
    public static final String serviceURL = "http://apis.data.go.kr/B490001/gybSjbPstateInfoService/getGySjBoheomBsshItem";

    /**
     * C'tor
     */
    public InsuranceAPI() {
        super(getServiceURL());
    }
}
