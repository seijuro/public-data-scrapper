package com.github.seijuro.publicdata.parser;

import com.github.seijuro.common.annotation.MethodDescription;
import com.github.seijuro.publicdata.property.NPSProperty;
import com.github.seijuro.publicdata.property.NPSPropertyUtils;
import com.github.seijuro.publicdata.result.BusinessPlaceInfoAPIResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import com.github.seijuro.publicdata.result.item.BusinessPlaceData;

import java.util.ArrayList;
import java.util.List;

public class BusinessPlaceInfoAPIResponseParser extends PublicDataAPIResponseXMLParser {
    /**
     * Instance Properties
     */
    protected BusinessPlaceData.Builder dataBuilder = null;
    protected List<BusinessPlaceData> dataList = null;

    /**
     * C'tor
     */
    public BusinessPlaceInfoAPIResponseParser() {
    }

    @Override
    public void clear() {
        if (this.dataList != null) this.dataList.clear();

        this.dataList = null;
        this.dataBuilder = null;
    }

    @MethodDescription(
            name = "builder class notifier.",
            description = "The return value will be used by DataBuilderFactory as paramter")
    protected Class getDataBuilderClass() {
        return BusinessPlaceData.Builder.class;
    }

    @Override
    protected boolean handleTagBegin(String tag) {
        if (!super.handleTagBegin(tag)) {
            if (NPSPropertyUtils.Item.contains(tag)) {
                return true;
            }
            else if (NPSProperty.ITEM.equals(tag)) {
                //  create builder object
                this.dataBuilder = (BusinessPlaceData.Builder)(NPSDataBuilderFactory.create(getDataBuilderClass()));

                return true;
            }
            else if (NPSProperty.ITEMS.equals(tag)) {
                return true;
            }
            else if (NPSProperty.BODY.equals(tag)) {
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
            if (NPSPropertyUtils.Item.contains(tag)) {
                int code = NPSPropertyUtils.Item.getCode(tag, Integer.MIN_VALUE);

                assert (code != Integer.MIN_VALUE);
                assert (this.dataBuilder != null);

                switch (code) {
                    case NPSProperty.ItemCode.Normal.IC_CREATED_YM:
                        this.dataBuilder.setCreatedDatedYM(value);
                        return true;
                    case NPSProperty.ItemCode.Normal.IC_ID:
                        this.dataBuilder.setId(value);
                        return true;
                    case NPSProperty.ItemCode.Normal.IC_BUSINESSPLACE_NAME:
                        this.dataBuilder.setName(value);
                        return true;
                    case NPSProperty.ItemCode.Normal.IC_REGISTRATION_NUMBER:
                        this.dataBuilder.setRegistrationNumber(value);
                        return true;
                    case NPSProperty.ItemCode.Normal.IC_ADDRESS_ROAD:
                        this.dataBuilder.setAddrStreet(value);
                        return true;
                    case NPSProperty.ItemCode.Normal.IC_STATUS_CODE:
                        this.dataBuilder.setStatusCode(value);
                        return true;
                    case NPSProperty.ItemCode.Normal.IC_DIVISION_CODE:
                        this.dataBuilder.setDivisionCode(value);
                        return true;
                    case NPSProperty.ItemCode.Normal.IC_ADDRESS_DG:
                        this.dataBuilder.setAddrCodeDG(value);
                        return true;
                    case NPSProperty.ItemCode.Normal.IC_ADDRESS_SGGU:
                        this.dataBuilder.setAddrCodeSGG(value);
                        return true;
                    case NPSProperty.ItemCode.Normal.IC_ADDRESS_EMD:
                        this.dataBuilder.setAddrCodeEMD(value);
                        return true;

                    default:
                        break;
                }
            }
            else if (NPSProperty.ITEM.equals(tag)) {
                assert (this.dataList != null);
                assert (this.dataBuilder != null);

                this.dataList.add(this.dataBuilder.build());

                this.dataBuilder = null;

                return true;
            }
            else if (NPSProperty.ITEMS.equals(tag)) {
                //  do nothing

                return true;
            }
            else if (NPSProperty.BODY.equals(tag)) {
                //  do nothing

                return true;
            }
        }

        return false;
    }

    @Override
    protected PublicDataAPIResult createResult() {
        if (!hasError()) {
            PublicDataAPIResult result = new BusinessPlaceInfoAPIResult(super.createResult());
            result.addData(this.dataList);

            return result;
        }

        return super.createResult();
    }
}
