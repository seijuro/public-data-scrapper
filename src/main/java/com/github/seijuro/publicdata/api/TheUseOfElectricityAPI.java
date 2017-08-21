package com.github.seijuro.publicdata.api;

import lombok.AccessLevel;
import lombok.Getter;

public class TheUseOfElectricityAPI extends PublicDataAPI {
    @Getter(AccessLevel.PUBLIC)
    public static final String serviceURL = "http://apis.data.go.kr/1611000/BldEngyService/getBeElctyUsgInfo";

    /**
     * C'tor
     */
    public TheUseOfElectricityAPI() {
        super(getServiceURL());
    }
}
