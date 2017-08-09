package com.github.seijuro.task;

import com.github.seijuro.publicdata.PublicDataAPIException;
import com.github.seijuro.publicdata.PublicDataAPIServices;
import com.github.seijuro.publicdata.api.config.PublicDataAPIConfig;
import com.github.seijuro.publicdata.result.PublicDataAPIErrorResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import com.github.seijuro.publicdata.runner.PublicDataAPITask;
import com.github.seijuro.publicdata.api.config.BusinessPlaceDetailAPIConfig;

import java.util.List;


public class BusinessPlaceDetailAPITask extends PublicDataAPITask {
    /**
     * Instance Properties
     */
    private final List<String> seqs;
    private int currentIndex = 0;

    /**
     * C'tor
     *
     * @param ids
     * @throws PublicDataAPIException
     */
    public BusinessPlaceDetailAPITask(List<String> ids) throws PublicDataAPIException {
        super(PublicDataAPIServices.NPS_BUSINESS_PLACE_DETAIL);

        this.seqs = ids;
    }

    @Override
    public String getServiceKey(PublicDataAPIServices apiService) {
        return getServiceKeySupplier().getServiceKey(apiService);
    }

    @Override
    public PublicDataAPIConfig getNextConfig() {
        if (this.currentIndex < seqs.size()) {
            String seq = this.seqs.get(this.currentIndex);

            BusinessPlaceDetailAPIConfig config = new BusinessPlaceDetailAPIConfig();
            config.setProperty(BusinessPlaceDetailAPIConfig.Property.ID, seq);

            return config;
        }

        return null;
    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    public void handleHTTPResponse(int code, String response) {
        System.out.println("BusinessPlaceDetailAPIRunnable::handleHTTPResponse -> code : " + code + ", response : " + response);
    }

    @Override
    public void handleHTTPErrorResponse(int code, String response, String errmsg) {
        System.out.println("BusinessPlaceDetailAPIRunnable::handleHTTPErrorResponse -> code : " + code + ", response : " + response + "\nerror message : " + errmsg);
    }

    @Override
    public void handleResult(PublicDataAPIResult result) {
        System.out.println(result.toString());
    }

    @Override
    public void handleErrorResult(PublicDataAPIErrorResult result) {
        System.out.println(result.toString());
    }

    @Override
    public void shutdown() {

    }
}
