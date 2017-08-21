package com.github.seijuro.publicdata.parser;

import com.github.seijuro.publicdata.property.PyramidSellingProperty;
import com.github.seijuro.publicdata.property.PyramidSellingPropertyUtils;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import com.github.seijuro.publicdata.result.PyramidSellingAPIResult;
import com.github.seijuro.publicdata.result.item.PyramidSellingData;

import java.util.ArrayList;
import java.util.List;

public class PyramidSellingAPIResponseParser extends PublicDataAPIResponseXMLParser {
    /**
     * Instance Properties
     */
    protected PyramidSellingData.Builder dataBuilder = null;
    protected List<PyramidSellingData> dataList = null;

    /**
     * Construct <code>PyramidSellingAPIResponseParser</code> instance.
     */
    public PyramidSellingAPIResponseParser() {
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
            if (PyramidSellingPropertyUtils.Item.contains(tag)) {
                return true;
            }
            else if (PyramidSellingProperty.ITEM.equals(tag)) {
                //  create builder object
                this.dataBuilder = new PyramidSellingData.Builder();

                return true;
            }
            else if (PyramidSellingProperty.ITEMS.equals(tag)) {
                return true;
            }
            else if (PyramidSellingProperty.BODY.equals(tag)) {
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
            if (PyramidSellingPropertyUtils.Item.contains(tag)) {
                int code = PyramidSellingPropertyUtils.Item.getCode(tag, Integer.MIN_VALUE);

                assert (code != Integer.MIN_VALUE);
                assert (this.dataBuilder != null);

                switch (code) {
                    case PyramidSellingProperty.ItemCode.IC_SEQUENCE_ID:
                        this.dataBuilder.setSequenceId(value);
                        return true;
                    case PyramidSellingProperty.ItemCode.IC_COMPANY_NAME:
                        this.dataBuilder.setCompanyName(value);
                        return true;
                    case PyramidSellingProperty.ItemCode.IC_SERIAL_NUMBER:
                        this.dataBuilder.setSerialNumber(value);
                        return true;
                    case PyramidSellingProperty.ItemCode.IC_REPRESENTATIVE:
                        this.dataBuilder.setRepresentative(value);
                        return true;
                    case PyramidSellingProperty.ItemCode.IC_DATE_OF_APPROVAL:
                        this.dataBuilder.setDateOfApproval(value);
                        return true;
                    default:
                        break;
                }
            }
            else if (PyramidSellingProperty.ITEM.equals(tag)) {
                assert (this.dataList != null);
                assert (this.dataBuilder != null);

                this.dataList.add(this.dataBuilder.build());
                this.dataBuilder = null;

                return true;
            }
            else if (PyramidSellingProperty.ITEMS.equals(tag)) {
                //  do nothing

                return true;
            }
            else if (PyramidSellingProperty.BODY.equals(tag)) {
                //  do nothing

                return true;
            }
        }

        return false;
    }

    @Override
    protected PublicDataAPIResult createResult() {
        if (!hasError()) {
            PublicDataAPIResult result = new PyramidSellingAPIResult(super.createResult());
            result.addData(this.dataList);

            return result;
        }

        return super.createResult();
    }
}
