package com.github.seijuro.task;

import com.github.seijuro.publicdata.PublicDataAPIException;
import com.github.seijuro.publicdata.PublicDataAPIServices;
import com.github.seijuro.publicdata.address.LegalDongAddress;
import com.github.seijuro.publicdata.address.reader.LegalDongAddressReader;
import com.github.seijuro.publicdata.api.PublicDataAPI;
import com.github.seijuro.publicdata.api.config.BusinessPlaceAPIConfig;
import com.github.seijuro.publicdata.api.config.ConfigProperty;
import com.github.seijuro.publicdata.api.config.PublicDataAPIConfig;
import com.github.seijuro.publicdata.result.PublicDataAPIErrorResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import com.github.seijuro.publicdata.runner.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;

public class BusinessPlaceAPITask extends PublicDataAPIPageableLoopTask {
    /**
     * Instance Properties
     */
    private LegalDongAddressReader addressReader = null;
    private PublicDataAPIServiceKeySupplier serviceKeySupplier = null;

    /**
     * C'tor
     *
     * @param $serviceKey
     * @throws PublicDataAPIException
     */
    public BusinessPlaceAPITask(final String $serviceKey) throws PublicDataAPIException {
        this(apiService -> $serviceKey);
    }

    /**
     * C'tor
     *
     * @param $serviceKeySupplier
     * @throws PublicDataAPIException
     */
    public BusinessPlaceAPITask(PublicDataAPIServiceKeySupplier $serviceKeySupplier) throws PublicDataAPIException {
        super(PublicDataAPIServices.NPS_BUSINESS_PLACE_NORMAL);

        serviceKeySupplier = $serviceKeySupplier;
    }

    public void setAddressFile(String filepath) throws IOException {
        this.addressReader = new LegalDongAddressReader(filepath);
    }

    public void setMaxTry(int max) {
        this.requestState.setMaxTry(max);
    }

    /**
     * implements <code>PublicDataAPIPageable</code> interface.
     *
     * @return
     */
    @Override
    public int getDefaultPageSize() {
        return 1000;
    }

    @Override
    public ConfigProperty getPageSizeKey() {
        return BusinessPlaceAPIConfig.Property.NUM_OF_ROWS;
    }

    @Override
    public ConfigProperty getPageNumberKey() {
        return BusinessPlaceAPIConfig.Property.PAGE_NO;
    }

    /**
     * implements <code>PublicDataAPIServiceKeySupplier</code> interface.
     *
     * @param apiService
     * @return
     */
    @Override
    public String getServiceKey(PublicDataAPIServices apiService) {
        return serviceKeySupplier.getServiceKey(apiService);
    }

    @Override
    public PublicDataAPIConfig getNextConfig() {
        try {
            LegalDongAddress address = (this.addressReader == null) ? null : this.addressReader.readAddress();

            if (address == null) { return null; }

            BusinessPlaceAPIConfig config = new BusinessPlaceAPIConfig();
            config.setLegalDongAddress(address.getCode());
            config.setProperty(BusinessPlaceAPIConfig.Property.NUM_OF_ROWS, 1000);
            config.setProperty(BusinessPlaceAPIConfig.Property.PAGE_NO, 1);

            return config;
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }

        return null;
    }
}
