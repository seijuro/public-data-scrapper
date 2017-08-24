package com.github.seijuro.publicdata.parser;

import com.github.seijuro.publicdata.property.PyramidSellerDetailProperty;
import com.github.seijuro.publicdata.property.PyramidSellerDetailPropertyUtils;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import com.github.seijuro.publicdata.result.PyramidSellerDetailAPIResult;
import com.github.seijuro.publicdata.result.item.PyramidSellerDetailData;

import java.util.ArrayList;
import java.util.List;

public class PyramidSellerDetailAPIResponseParser extends PublicDataAPIResponseXMLParser {
    /**
     * Instance Properties
     */
    protected PyramidSellerDetailData.Builder dataBuilder = null;
    protected List<PyramidSellerDetailData> dataList = null;

    /**
     * Construct <code>PyramidSellingDetailAPIResponseParser</code> instance.
     */
    public PyramidSellerDetailAPIResponseParser() {
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
            if (PyramidSellerDetailPropertyUtils.Item.contains(tag)) {
                return true;
            }
            else if (PyramidSellerDetailProperty.ITEM.equals(tag)) {
                //  create builder object
                this.dataBuilder = new PyramidSellerDetailData.Builder();

                return true;
            }
            else if (PyramidSellerDetailProperty.ITEMS.equals(tag)) {
                return true;
            }
            else if (PyramidSellerDetailProperty.BODY.equals(tag)) {
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
            if (PyramidSellerDetailPropertyUtils.Item.contains(tag)) {
                int code = PyramidSellerDetailPropertyUtils.Item.getCode(tag, Integer.MIN_VALUE);

                assert (code != Integer.MIN_VALUE);
                assert (dataBuilder != null);

                switch (code) {
                    case PyramidSellerDetailProperty.ItemCode.IC_SEQUENCE_ID:
                        dataBuilder.setSequenceId(value);
                        return true;
                    case PyramidSellerDetailProperty.ItemCode.IC_COMPANY_NAME:
                        dataBuilder.setCompanyName(value);
                        return true;
                    case PyramidSellerDetailProperty.ItemCode.IC_SERIAL_NUMBER:
                        dataBuilder.setSerialNumber(value);
                        return true;
                    case PyramidSellerDetailProperty.ItemCode.IC_REPRESENTATIVE:
                        dataBuilder.setRepresentative(value);
                        return true;
                    case PyramidSellerDetailProperty.ItemCode.IC_DATE_OF_APPROVAL:
                        dataBuilder.setDateOfApproval(value);
                        return true;
                    case PyramidSellerDetailProperty.ItemCode.IC_ADDRESS:
                        dataBuilder.setAddress(value);
                        return true;
                    case PyramidSellerDetailProperty.ItemCode.IC_CAPITTAL:
                        dataBuilder.setCapital(value);
                        return true;
                    case PyramidSellerDetailProperty.ItemCode.IC_STATUS:
                        dataBuilder.setStatus(value);
                        return true;
                    default:
                        break;
                }
            }
            else if (PyramidSellerDetailProperty.ITEM.equals(tag)) {
                assert (this.dataList != null);
                assert (this.dataBuilder != null);

                dataList.add(this.dataBuilder.build());
                dataBuilder = null;

                return true;
            }
            else if (PyramidSellerDetailProperty.ITEMS.equals(tag)) {
                //  do nothing

                return true;
            }
            else if (PyramidSellerDetailProperty.BODY.equals(tag)) {
                //  do nothing

                return true;
            }
        }

        return false;
    }

    @Override
    protected PublicDataAPIResult createResult() {
        if (!hasError()) {
            PublicDataAPIResult result = new PyramidSellerDetailAPIResult(super.createResult());
            result.addData(this.dataList);

            return result;
        }

        return super.createResult();
    }
}
