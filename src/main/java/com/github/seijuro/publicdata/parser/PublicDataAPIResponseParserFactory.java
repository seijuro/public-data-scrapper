package com.github.seijuro.publicdata.parser;

import com.github.seijuro.publicdata.PublicDataAPIServices;

public class PublicDataAPIResponseParserFactory {
    /**
     * Factory method
     *
     * @param type
     * @return
     */
    public static PublicDataAPIResponseParser create(PublicDataAPIServices type) {
        if (type == PublicDataAPIServices.NPS_BUSINESS_PLACE_NORMAL) {
            return new BusinessPlaceInfoAPIResponseParser();
        }
        else if (type == PublicDataAPIServices.NPS_BUSINESS_PLACE_DETAIL) {
            return new BusinessPlaceDetailAPIResponseParser();
        }
        else if (type == PublicDataAPIServices.NPS_STATS) {
            return new NPSReportAPIResponseParser();
        }
        else if (type == PublicDataAPIServices.SPEC_CONSTRUCT ||
                type == PublicDataAPIServices.SPEC_FOREIGNCAPITAL ||
                type == PublicDataAPIServices.SPEC_PRODUCT ||
                type == PublicDataAPIServices.SPEC_SERVICE) {
            return new SpecificationInfoAPIResponseParser();
        }
        else if (type == PublicDataAPIServices.RECALL) {
            return new RecallAPIResponseParser();
        }
        else if (type == PublicDataAPIServices.RECALL_PAGEABLE) {
            return new RecallAPIPageableResponseParser();
        }
        else if (type == PublicDataAPIServices.INSURANCE) {
            return new InsuranceAPIResponseParser();
        }
        else if (type == PublicDataAPIServices.THE_USE_OF_GAS) {
            return new TheUseOfGasAPIResponseParser();
        }
        else if (type == PublicDataAPIServices.THE_USE_OF_ELECTROCITY) {
            return new TheUseOfElectrocityAPIResponseParser();
        }
        else if (type == PublicDataAPIServices.PYRAMID_SELLER) {
            return new PyramidSellerAPIResponseParser();
        }
        else if (type == PublicDataAPIServices.PYRAMID_SELLER_DEATIL) {
            return new PyramidSellerDetailAPIResponseParser();
        }
        else if (type == PublicDataAPIServices.TELE_MARKETING_SELLER) {
            return new TeleMarketingSellerAPIResponseParser();
        }
        else if (type == PublicDataAPIServices.COLD_CALLING_SELLER) {
            return new ColdCallingSellerAPIResponseParser();
        }
        else if (type == PublicDataAPIServices.DOOR_TO_DOOR_SEELER) {
            return new DoorToDoorSellerAPIResponseParser();
        }

        assert (0 == 1);

        return null;
    }
}
