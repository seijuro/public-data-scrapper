package com.github.seijuro.publicdata.parser;

import com.github.seijuro.publicdata.property.BusinessPlaceDetailProperty;
import com.github.seijuro.publicdata.property.BusinessPlaceDetailPropertyUtils;
import com.github.seijuro.publicdata.result.BusinessPlaceDetailAPIResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import com.github.seijuro.publicdata.result.item.BusinessPlaceDetailData;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class BusinessPlaceDetailAPIResponseParser extends PublicDataAPIResponseXMLParser {
    /**
     * Instance Properties
     */
    protected BusinessPlaceDetailData.Builder dataBuilder = null;
    protected List<BusinessPlaceDetailData> dataList = null;
    @Setter
    protected Integer sequenceId = null;

    /**
     * C'tor
     */
    public BusinessPlaceDetailAPIResponseParser() {
        super();
    }

    @Override
    protected boolean handleTagBegin(String tag) {
        if (!super.handleTagBegin(tag)) {
            if (BusinessPlaceDetailPropertyUtils.Item.contains(tag)) {
                return true;
            }
            else if (BusinessPlaceDetailProperty.ITEM.equals(tag)) {
                //  create builder object
                this.dataBuilder = new BusinessPlaceDetailData.Builder();
                this.dataBuilder.setSequenceId(sequenceId);

                return true;
            }
            else if (BusinessPlaceDetailProperty.BODY.equals(tag)) {
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
            if (BusinessPlaceDetailPropertyUtils.Item.contains(tag)) {
                int code = BusinessPlaceDetailPropertyUtils.Item.getCode(tag, Integer.MIN_VALUE);

                assert (code != Integer.MIN_VALUE);
                assert (dataBuilder != null);

                switch (code) {
                    case BusinessPlaceDetailProperty.ItemCode.IC_TYPE:
                        dataBuilder.setType(value);
                        return true;
                    case BusinessPlaceDetailProperty.ItemCode.IC_REGISTRATIONDATE:
                        dataBuilder.setRegistrationDate(value);
                        return true;
                    case BusinessPlaceDetailProperty.ItemCode.IC_WITHDRAWALDATE:
                        dataBuilder.setWithdrawalDate(value);
                        return true;
                    case BusinessPlaceDetailProperty.ItemCode.IC_NUMBEROFSUBSCRIBER:
                        dataBuilder.setNumberOfSubscriber(value);
                        return true;
                    case BusinessPlaceDetailProperty.ItemCode.IC_NOTIFIEDAMOOUNT:
                        dataBuilder.setNotifiedAmount(value);
                        return true;
                    case BusinessPlaceDetailProperty.ItemCode.IC_NAME:
                        dataBuilder.setName(value);
                        return true;
                    case BusinessPlaceDetailProperty.ItemCode.IC_REGISTRATIONNUMBER:
                        dataBuilder.setRegistrationNumber(value);
                        return true;
                    case BusinessPlaceDetailProperty.ItemCode.IC_ADDRESS_ROAD:
                        dataBuilder.setAddressRoad(value);
                        return true;
                    case BusinessPlaceDetailProperty.ItemCode.IC_STATUSCODE:
                        dataBuilder.setStatusCode(value);
                        return true;
                    case BusinessPlaceDetailProperty.ItemCode.IC_ADDRESSCODE_DG:
                        dataBuilder.setAddressDG(value);
                        return true;
                    case BusinessPlaceDetailProperty.ItemCode.IC_ADDRESSCODE_SGGU:
                        dataBuilder.setAddressSGGU(value);
                        return true;
                    case BusinessPlaceDetailProperty.ItemCode.IC_ADDRESSCODE_EMD:
                        dataBuilder.setAddressEMD(value);
                        return true;
                    case BusinessPlaceDetailProperty.ItemCode.IC_DIVISION:
                        dataBuilder.setDivisionCode(value);
                        return true;
                    case BusinessPlaceDetailProperty.ItemCode.IC_TYPECODE:
                        dataBuilder.setTypeCode(value);
                        return true;

                    default:
                        break;
                }
            }
            else if (BusinessPlaceDetailProperty.ITEM.equals(tag)) {
                assert (this.dataList != null);
                assert (this.dataBuilder != null);

                this.dataList.add(this.dataBuilder.build());

                this.dataBuilder = null;

                return true;
            }
            else if (BusinessPlaceDetailProperty.BODY.equals(tag)) {
                //  do nothing

                return true;
            }
        }

        return false;
    }

    @Override
    protected PublicDataAPIResult createResult() {
        if (!hasError()) {
            PublicDataAPIResult result = new BusinessPlaceDetailAPIResult(super.createResult());
            result.addData(this.dataList);

            return result;
        }

        return super.createResult();
    }
}
