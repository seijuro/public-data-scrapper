package com.github.seijuro.publicdata.runner;

import com.github.seijuro.publicdata.PublicDataAPIException;
import com.github.seijuro.publicdata.PublicDataAPIServices;
import com.github.seijuro.publicdata.api.*;

public class PublicDataAPIFactory {
    public static PublicDataAPI create(PublicDataAPIServices type) throws PublicDataAPIException {
        if (type == PublicDataAPIServices.NPS_BUSINESS_PLACE_NORMAL) {
            return new BusinessPlaceInfoAPI();
        }
        else if (type == PublicDataAPIServices.NPS_BUSINESS_PLACE_DETAIL) {
            return new BusinessPlaceDetailInfoAPI();
        }
        else if (type == PublicDataAPIServices.NPS_STATS) {
            return new StatsAPI();
        }
        else if (type == PublicDataAPIServices.SPEC_CONSTRUCT) {
            return new ConstructSpecificationInfoAPI();
        }
        else if (type == PublicDataAPIServices.SPEC_FOREIGNCAPITAL) {
            return new ForeignCapitalSpecificationInfoAPI();
        }
        else if (type == PublicDataAPIServices.SPEC_PRODUCT) {
            return new ProductSpecificationInfoAPI();
        }
        else if (type == PublicDataAPIServices.SPEC_SERVICE) {
            return new ServiceSpecificationInfoAPI();
        }
        else if (type == PublicDataAPIServices.RECALL
                || type == PublicDataAPIServices.RECALL_PAGEABLE) {
            return new RecallAPI();
        }
        else if (type == PublicDataAPIServices.INSURANCE) {
            return new InsuranceAPI();
        }
        else if (type == PublicDataAPIServices.THE_USE_OF_ELECTROCITY) {
            return new TheUseOfElectricityAPI();
        }
        else if (type == PublicDataAPIServices.THE_USE_OF_GAS) {
            return new TheUseOfGasAPI();
        }

        throw new PublicDataAPIException("Unknown type or Not implemented yet.");
    }
}
