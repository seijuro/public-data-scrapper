package com.github.seijuro.publicdata.parser;

import com.github.seijuro.publicdata.property.PyramidSellerProperty;
import com.github.seijuro.publicdata.property.PyramidSellerPropertyUtils;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import com.github.seijuro.publicdata.result.PyramidSellerAPIResult;
import com.github.seijuro.publicdata.result.item.PyramidSellerData;

import java.util.ArrayList;
import java.util.List;

public class PyramidSellerAPIResponseParser extends PublicDataAPIResponseXMLParser {
    /**
     * Instance Properties
     */
    protected PyramidSellerData.Builder dataBuilder = null;
    protected List<PyramidSellerData> dataList = null;

    /**
     * Construct <code>PyramidSellingAPIResponseParser</code> instance.
     */
    public PyramidSellerAPIResponseParser() {
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
            if (PyramidSellerPropertyUtils.Item.contains(tag)) {
                return true;
            }
            else if (PyramidSellerProperty.ITEM.equals(tag)) {
                //  create builder object
                this.dataBuilder = new PyramidSellerData.Builder();

                return true;
            }
            else if (PyramidSellerProperty.ITEMS.equals(tag)) {
                return true;
            }
            else if (PyramidSellerProperty.BODY.equals(tag)) {
                //  create container
                this.dataList = new ArrayList<>();

                return true;
            }
        }

        return false;
    }

    @Override
    protected boolean handleTagEnd(String tag, String value) {
        if (!super.handleTagEnd(tag, value)) {
            if (PyramidSellerPropertyUtils.Item.contains(tag)) {
                int code = PyramidSellerPropertyUtils.Item.getCode(tag, Integer.MIN_VALUE);

                assert (code != Integer.MIN_VALUE);
                assert (this.dataBuilder != null);

                switch (code) {
                    case PyramidSellerProperty.ItemCode.IC_SEQUENCE_ID:
                        this.dataBuilder.setSequenceId(value);
                        return true;
                    case PyramidSellerProperty.ItemCode.IC_COMPANY_NAME:
                        this.dataBuilder.setCompanyName(value);
                        return true;
                    case PyramidSellerProperty.ItemCode.IC_SERIAL_NUMBER:
                        this.dataBuilder.setSerialNumber(value);
                        return true;
                    case PyramidSellerProperty.ItemCode.IC_REPRESENTATIVE:
                        this.dataBuilder.setRepresentative(value);
                        return true;
                    case PyramidSellerProperty.ItemCode.IC_DATE_OF_APPROVAL:
                        this.dataBuilder.setDateOfApproval(value);
                        return true;
                    default:
                        break;
                }
            }
            else if (PyramidSellerProperty.ITEM.equals(tag)) {
                assert (this.dataList != null);
                assert (this.dataBuilder != null);

                this.dataList.add(this.dataBuilder.build());
                this.dataBuilder = null;

                return true;
            }
            else if (PyramidSellerProperty.ITEMS.equals(tag)) {
                //  do nothing

                return true;
            }
            else if (PyramidSellerProperty.BODY.equals(tag)) {
                //  do nothing

                return true;
            }
        }

        return false;
    }

    @Override
    protected PublicDataAPIResult createResult() {
        if (!hasError()) {
            PublicDataAPIResult result = new PyramidSellerAPIResult(super.createResult());
            result.addData(this.dataList);

            return result;
        }

        return super.createResult();
    }
}
