package com.github.seijuro.publicdata.api;

import com.github.seijuro.common.http.rest.RestfulAPIResponse;
import lombok.AccessLevel;
import lombok.Getter;

public class TeleMarketingAPI extends PublicDataAPI {
    @Getter(AccessLevel.PUBLIC)
    public static final String serviceURL = "http://apis.data.go.kr/1130000/MllInfoService/getMllSttemntInfo";

    /**
     * C'tor
     */
    public TeleMarketingAPI() {
        super(getServiceURL());
    }

    @Override
    public RestfulAPIResponse request() throws Exception {
        return super.request();
    }
}
