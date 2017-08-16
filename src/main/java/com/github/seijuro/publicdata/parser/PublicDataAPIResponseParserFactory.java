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
            return new BusinessPlaceDetailInfoAPIResponseParser();
        }
        else if (type == PublicDataAPIServices.NPS_STATS) {
            return new StatsAPIResponseParser();
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

        assert (0 == 1);

        return null;
    }
}
