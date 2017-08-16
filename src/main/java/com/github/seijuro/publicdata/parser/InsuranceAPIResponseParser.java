package com.github.seijuro.publicdata.parser;

import com.github.seijuro.publicdata.property.InsuranceProperty;
import com.github.seijuro.publicdata.property.InsurancePropertyUtils;
import com.github.seijuro.publicdata.result.InsuranceAPIResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import com.github.seijuro.publicdata.result.item.InsuranceData;

import java.util.ArrayList;
import java.util.List;

public class InsuranceAPIResponseParser extends PublicDataAPIResponseXMLParser {
    /**
     * Instance Properties
     */
    protected InsuranceData.Builder dataBuilder = null;
    protected List<InsuranceData> dataList = null;

    /**
     * Construct <code>InsuranceAPIResponseParser</code> instance.
     */
    public InsuranceAPIResponseParser() {

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
            if (InsurancePropertyUtils.Item.contains(tag)) {
                return true;
            }
            else if (InsuranceProperty.ITEM.equals(tag)) {
                //  create builder object
                this.dataBuilder = new InsuranceData.Builder();

                return true;
            }
            else if (InsuranceProperty.ITEMS.equals(tag)) {
                return true;
            }
            else if (InsuranceProperty.BODY.equals(tag)) {
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
            if (InsurancePropertyUtils.Item.contains(tag)) {
                int code = InsurancePropertyUtils.Item.getCode(tag, Integer.MIN_VALUE);

                assert (code != Integer.MIN_VALUE);
                assert (this.dataBuilder != null);

                switch (code) {
                    case InsuranceProperty.ItemCode.IC_ADDRESS:
                        this.dataBuilder.setAddress(value);
                        return true;
                    case InsuranceProperty.ItemCode.IC_INSURANCE_TYPE:
                        this.dataBuilder.setType(value);
                        return true;
                    case InsuranceProperty.ItemCode.IC_THE_NUMBER_OF_REGURAL_EMPLOYMENT:
                        this.dataBuilder.setTheNumberOfRegularEmployment(value);
                        return true;
                    case InsuranceProperty.ItemCode.IC_DATE_OF_EMPLOYMENT:
                        this.dataBuilder.setDateOfEmployment(value);
                        return true;
                    case InsuranceProperty.ItemCode.IC_ZIPCODE:
                        this.dataBuilder.setZipCode(value);
                        return true;
                    case InsuranceProperty.ItemCode.IC_COMPANYNAME:
                        this.dataBuilder.setCompanyName(value);
                        return true;
                    case InsuranceProperty.ItemCode.IC_THE_NUMBER_OF_INSURED:
                        this.dataBuilder.setTheNumberOfInsuredWorkers(value);
                        return true;
                    case InsuranceProperty.ItemCode.IC_DATE_OF_INSURED:
                        this.dataBuilder.setDateOfInsured(value);
                        return true;
                    default:
                        break;
                }
            }
            else if (InsuranceProperty.ITEM.equals(tag)) {
                assert (this.dataList != null);
                assert (this.dataBuilder != null);

                this.dataList.add(this.dataBuilder.build());

                this.dataBuilder = null;

                return true;
            }
            else if (InsuranceProperty.ITEMS.equals(tag)) {
                //  do nothing

                return true;
            }
            else if (InsuranceProperty.BODY.equals(tag)) {
                //  do nothing

                return true;
            }
        }

        return false;
    }

    @Override
    protected PublicDataAPIResult createResult() {
        if (!hasError()) {
            PublicDataAPIResult result = new InsuranceAPIResult(super.createResult());
            result.addData(this.dataList);

            return result;
        }

        return super.createResult();
    }
}
