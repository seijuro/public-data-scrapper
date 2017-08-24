package com.github.seijuro.publicdata.parser;

import com.github.seijuro.publicdata.property.SellerProperty;
import com.github.seijuro.publicdata.property.SellerPropertyUtils;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import com.github.seijuro.publicdata.result.SellerAPIResult;
import com.github.seijuro.publicdata.result.item.SellerData;

import java.util.ArrayList;
import java.util.List;

public class SellerAPIResponseParser extends PublicDataAPIResponseXMLParser {
    /**
     * Instance Properties
     */
    protected SellerData.Builder dataBuilder = null;
    protected List<SellerData> dataList = null;

    /**
     * Construct <code>SellerAPIResponseParser</code> instance.
     */
    public SellerAPIResponseParser() {
    }

    @Override
    public void clear() {
        if (this.dataList != null) this.dataList.clear();

        this.dataList = null;
        this.dataBuilder = null;
    }

    @Override
    protected boolean handleTagBegin(String tag) {
        if (!super.handleTagBegin(tag)) {
            if (SellerPropertyUtils.Item.contains(tag)) {
                return true;
            }
            else if (SellerProperty.ITEMS.equals(tag)) {
                return true;
            }
            else if (SellerProperty.BODY.equals(tag)) {
                //  create container
                dataList = new ArrayList<>();

                return true;
            }
        }

        return false;
    }

    @Override
    protected boolean handleTagEnd(String tag, String value) {
        if (!super.handleTagEnd(tag, value)) {
            if (SellerPropertyUtils.Item.contains(tag)) {
                int code = SellerPropertyUtils.Item.getCode(tag, Integer.MIN_VALUE);

                assert (code != Integer.MIN_VALUE);
                assert (this.dataBuilder != null);

                switch (code) {
                    case SellerProperty.ItemCode.IC_SEQUENCE_ID:
                        this.dataBuilder.setSequenceId(value);
                        return true;
                    case SellerProperty.ItemCode.IC_STATE:
                        this.dataBuilder.setState(value);
                        return true;
                    case SellerProperty.ItemCode.IC_COMPANY_NAME:
                        this.dataBuilder.setCompanyName(value);
                        return true;
                    case SellerProperty.ItemCode.IC_REPRESENTATIVE:
                        this.dataBuilder.setRepresentative(value);
                        return true;
                    case SellerProperty.ItemCode.IC_STATUS:
                        this.dataBuilder.setStatus(value);
                        return true;
                    case SellerProperty.ItemCode.IC_FILING_DATE:
                        this.dataBuilder.setFilingDate(value);
                        return true;
                    case SellerProperty.ItemCode.IC_DOMAIN:
                        this.dataBuilder.setDomain(value);
                        return true;
                    default:
                        break;
                }
            }
            else if (SellerProperty.ITEM.equals(tag)) {
                assert (this.dataList != null);
                assert (this.dataBuilder != null);

                this.dataList.add(this.dataBuilder.build());
                this.dataBuilder = null;

                return true;
            }
            else if (SellerProperty.ITEMS.equals(tag)) {
                //  do nothing

                return true;
            }
            else if (SellerProperty.BODY.equals(tag)) {
                //  do nothing

                return true;
            }
        }

        return false;
    }

    @Override
    protected PublicDataAPIResult createResult() {
        if (!hasError()) {
            PublicDataAPIResult result = new SellerAPIResult(super.createResult());
            result.addData(this.dataList);

            return result;
        }

        return super.createResult();
    }
}
