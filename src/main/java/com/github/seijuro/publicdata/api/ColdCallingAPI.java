package com.github.seijuro.publicdata.api;

import lombok.AccessLevel;
import lombok.Getter;

public class ColdCallingAPI extends PublicDataAPI {
    @Getter(AccessLevel.PUBLIC)
    public static final String serviceURL = "http://apis.data.go.kr/1130000/TelIdSalInfoService/getTelIdSalSttemntInfo";

    /**
     * C'tor
     */
    public ColdCallingAPI() {
        super(getServiceURL());
    }
}
