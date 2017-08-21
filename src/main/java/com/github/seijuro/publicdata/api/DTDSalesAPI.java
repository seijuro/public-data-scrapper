package com.github.seijuro.publicdata.api;

import lombok.AccessLevel;
import lombok.Getter;

public class DTDSalesAPI extends PublicDataAPI {
    @Getter(AccessLevel.PUBLIC)
    public static final String serviceURL = "http://apis.data.go.kr/1130000/ClSlInfoService/getClSlSttemntInfo";

    /**
     * C'tor
     */
    public DTDSalesAPI() {
        super(getServiceURL());
    }
}
