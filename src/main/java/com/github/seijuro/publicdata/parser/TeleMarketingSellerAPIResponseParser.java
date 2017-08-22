package com.github.seijuro.publicdata.parser;

import com.github.seijuro.publicdata.property.SellerProperty;
import com.github.seijuro.publicdata.result.item.SellerData;

public class TeleMarketingSellerAPIResponseParser extends SellerAPIResponseParser {
    /**
     * Construct
     */
    public TeleMarketingSellerAPIResponseParser() {
        super();
    }

    @Override
    protected boolean handleTagBegin(String tag) {
        if (!super.handleTagBegin(tag)) {
            if (SellerProperty.ITEM.equals(tag)) {
                //  create builder object
                dataBuilder = new SellerData.Builder();
                dataBuilder.setType(SellerData.Type.TelleMarket);

                return true;
            }
        }

        return false;
    }
}
