package com.github.seijuro.publicdata.api;

import com.github.seijuro.publicdata.PublicDataAPIException;
import com.github.seijuro.publicdata.PublicDataAPIServices;

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
        else if (type == PublicDataAPIServices.PYRAMID_SELLING) {
            return new PyramidSellingAPI();
        }
        else if (type == PublicDataAPIServices.PYRAMID_SELLING_DEATIL) {
            return new PyramidSellingDetailAPI();
        }
        else if (type == PublicDataAPIServices.TELE_MARKETING_SELLER) {
            return new TeleMarketingSellerAPI();
        }
        else if (type == PublicDataAPIServices.COLD_CALLING_SELLER) {
            return new ColdCallingSellerAPI();
        }
        else if (type == PublicDataAPIServices.DOOR_TO_DOOR_SEELER) {
            return new DoorToDoorSellerAPI();
        }

        throw new PublicDataAPIException("Unknown type or Not implemented yet.");
    }
}
