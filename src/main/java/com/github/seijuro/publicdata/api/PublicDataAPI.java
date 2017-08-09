package com.github.seijuro.publicdata.api;

import com.github.seijuro.common.IURLEncoder;
import com.github.seijuro.common.http.rest.RestfulAPI;
import com.github.seijuro.publicdata.api.config.PublicDataAPIConfig;
import com.github.seijuro.publicdata.property.PublicDataProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class PublicDataAPI extends RestfulAPI {
    /**
     * Class Properties;
     */
    static final IURLEncoder ParameterEncoder = s -> URLEncoder.encode(s, PublicDataProperty.Encoding);
    static final RequestMethod APIRequestMethod = RequestMethod.GET;
    static final String ServiceKey = "ServiceKey";

    public static IURLEncoder getDefaultURLEncoder() {
        return ParameterEncoder;
    }

    /**
     * Instance Properties
     */
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String serviceKey;

    /**
     * C'tor
     *
     * @param url
     */
    public PublicDataAPI(String url) {
        super(APIRequestMethod, url);

        setEncodeFunc(getDefaultURLEncoder());
    }

    @Override
    protected String createRequestGETURL() throws UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer(super.createRequestGETURL());

        sb.append((getProperties().size() > 0) ? "&" : "?");
        sb.append(ParameterEncoder.encode(ServiceKey)).append("=").append(getServiceKey());

        String url = sb.toString();

        return url;
    }

    public void setConfig(PublicDataAPIConfig config) {
        super.setProperties(config.getProperties());
    }
}
