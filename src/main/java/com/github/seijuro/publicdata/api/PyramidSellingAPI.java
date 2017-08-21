package com.github.seijuro.publicdata.api;

import com.github.seijuro.common.http.rest.RestfulAPIResponse;
import lombok.AccessLevel;
import lombok.Getter;

public class PyramidSellingAPI extends PublicDataAPI {
    @Getter(AccessLevel.PUBLIC)
    public static final String serviceURL = "http://apis.data.go.kr/1130000/MMktInfoService/getMMktInfo";

    /**
     * C'tor
     */
    public PyramidSellingAPI() {
        super(getServiceURL());
    }

    @Override
    public RestfulAPIResponse request() throws Exception {
        return super.request();
    }
}
