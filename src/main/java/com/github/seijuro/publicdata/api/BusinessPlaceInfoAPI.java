package com.github.seijuro.publicdata.api;

import lombok.AccessLevel;
import lombok.Getter;

public class BusinessPlaceInfoAPI extends PublicDataAPI {
    @Getter(AccessLevel.PUBLIC)
    public static final String serviceURL = "http://apis.data.go.kr/B552015/NpsBplcInfoInqireService/getBassInfoSearch";

    /**
     * C'tor
     */
    public BusinessPlaceInfoAPI() {
        super(getServiceURL());
    }
}
