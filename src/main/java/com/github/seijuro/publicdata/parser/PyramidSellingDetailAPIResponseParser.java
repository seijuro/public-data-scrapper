package com.github.seijuro.publicdata.parser;

import com.github.seijuro.publicdata.property.PyramidSellingDetailProperty;
import com.github.seijuro.publicdata.property.PyramidSellingDetailPropertyUtils;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import com.github.seijuro.publicdata.result.PyramidSellingDetailAPIResult;
import com.github.seijuro.publicdata.result.item.PyramidSellingDetailData;

import java.util.ArrayList;
import java.util.List;

public class PyramidSellingDetailAPIResponseParser extends PublicDataAPIResponseXMLParser {
    /**
     * Instance Properties
     */
    protected PyramidSellingDetailData.Builder dataBuilder = null;
    protected List<PyramidSellingDetailData> dataList = null;

    /**
     * Construct <code>PyramidSellingDetailAPIResponseParser</code> instance.
     */
    public PyramidSellingDetailAPIResponseParser() {
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
            if (PyramidSellingDetailPropertyUtils.Item.contains(tag)) {
                return true;
            }
            else if (PyramidSellingDetailProperty.ITEM.equals(tag)) {
                //  create builder object
                this.dataBuilder = new PyramidSellingDetailData.Builder();

                return true;
            }
            else if (PyramidSellingDetailProperty.ITEMS.equals(tag)) {
                return true;
            }
            else if (PyramidSellingDetailProperty.BODY.equals(tag)) {
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
            if (PyramidSellingDetailPropertyUtils.Item.contains(tag)) {
                int code = PyramidSellingDetailPropertyUtils.Item.getCode(tag, Integer.MIN_VALUE);

                assert (code != Integer.MIN_VALUE);
                assert (dataBuilder != null);

                switch (code) {
                    case PyramidSellingDetailProperty.ItemCode.IC_SEQUENCE_ID:
                        dataBuilder.setSequenceId(value);
                        return true;
                    case PyramidSellingDetailProperty.ItemCode.IC_COMPANY_NAME:
                        dataBuilder.setCompanyName(value);
                        return true;
                    case PyramidSellingDetailProperty.ItemCode.IC_SERIAL_NUMBER:
                        dataBuilder.setSerialNumber(value);
                        return true;
                    case PyramidSellingDetailProperty.ItemCode.IC_REPRESENTATIVE:
                        dataBuilder.setRepresentative(value);
                        return true;
                    case PyramidSellingDetailProperty.ItemCode.IC_DATE_OF_APPROVAL:
                        dataBuilder.setDateOfApproval(value);
                        return true;
                    case PyramidSellingDetailProperty.ItemCode.IC_ADDRESS:
                        dataBuilder.setAddress(value);
                        return true;
                    case PyramidSellingDetailProperty.ItemCode.IC_CAPITTAL:
                        dataBuilder.setCapital(value);
                        return true;
                    case PyramidSellingDetailProperty.ItemCode.IC_STATUS:
                        dataBuilder.setStatus(value);
                        return true;
                    default:
                        break;
                }
            }
            else if (PyramidSellingDetailProperty.ITEM.equals(tag)) {
                assert (this.dataList != null);
                assert (this.dataBuilder != null);

                dataList.add(this.dataBuilder.build());
                dataBuilder = null;

                return true;
            }
            else if (PyramidSellingDetailProperty.ITEMS.equals(tag)) {
                //  do nothing

                return true;
            }
            else if (PyramidSellingDetailProperty.BODY.equals(tag)) {
                //  do nothing

                return true;
            }
        }

        return false;
    }

    @Override
    protected PublicDataAPIResult createResult() {
        if (!hasError()) {
            PublicDataAPIResult result = new PyramidSellingDetailAPIResult(super.createResult());
            result.addData(this.dataList);

            return result;
        }

        return super.createResult();
    }
}
