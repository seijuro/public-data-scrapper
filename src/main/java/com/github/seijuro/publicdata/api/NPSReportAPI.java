package com.github.seijuro.publicdata.api;

import lombok.AccessLevel;
import lombok.Getter;

public class NPSReportAPI extends PublicDataAPI {
    @Getter(AccessLevel.PUBLIC)
    public static final String serviceURL = "http://apis.data.go.kr/B552015/NpsBplcInfoInqireService/getPdAcctoSttusInfoSearch";

    /*
    http://apis.data.go.kr/B552015/NpsBplcInfoInqireService/getPdAcctoSttusInfoSearch?seq=3&data_crt_ym=201510&ServiceKey=vijbBhIlEb%2FU9SmD%2B6f3m%2FJD846%2FGTERxjJcQteqo2FN%2BgLSBwukgxLQJIkMg10LAwyh3Myxc8l5vkxRKmCpoA%3D%3D
    */

    /**
     * C'tor
     */
    public NPSReportAPI() {
        super(getServiceURL());
    }
}
