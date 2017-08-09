package com.github.seijuro.publicdata.parser;

import com.github.seijuro.publicdata.property.NPSProperty;
import com.github.seijuro.publicdata.property.NPSPropertyUtils;
import com.github.seijuro.publicdata.result.BusinessPlaceDetailInfoAPIResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import com.github.seijuro.publicdata.result.item.BusinessPlaceDetailData;

public class BusinessPlaceDetailInfoAPIResponseParser extends BusinessPlaceInfoAPIResponseParser {
    /**
     * C'tor
     */
    public BusinessPlaceDetailInfoAPIResponseParser() {
        super();
    }

    @Override
    protected Class getDataBuilderClass() {
        return BusinessPlaceDetailData.Builder.class;
    }

    @Override
    protected boolean handleTagBegin(String tag) {
        if (!super.handleTagBegin(tag)) {
            if (NPSPropertyUtils.Item.contains(tag)) {
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
                    // details only
                    case NPSProperty.ItemCode.Detail.IC_BUSINESSTYPE_NAME:
                        BusinessPlaceDetailData.Builder.class.cast(this.dataBuilder).setBusinessTypeName(value);
                        return true;
                    case NPSProperty.ItemCode.Detail.IC_BUSINESSTYPE_CODE:
                        BusinessPlaceDetailData.Builder.class.cast(this.dataBuilder).setBusinessTypeCode(value);
                        return true;
                    case NPSProperty.ItemCode.Detail.IC_REGISTRATION_DATE:
                        BusinessPlaceDetailData.Builder.class.cast(this.dataBuilder).setRegistrationDate(value);
                        return true;
                    case NPSProperty.ItemCode.Detail.IC_WITHDRAWAL_DATE:
                        BusinessPlaceDetailData.Builder.class.cast(this.dataBuilder).setWithdrawalDate(value);
                        return true;
                    case NPSProperty.ItemCode.Detail.IC_SUBSCRIBER_NUMBER:
                        BusinessPlaceDetailData.Builder.class.cast(this.dataBuilder).setSubscriberNumber(value);
                        return true;
                    case NPSProperty.ItemCode.Detail.IC_NOTIFIED_AMOUNT_OF_THIS_MONTH:
                        BusinessPlaceDetailData.Builder.class.cast(this.dataBuilder).setNotifiedAmountOfThisMonth(value);
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
            PublicDataAPIResult result = new BusinessPlaceDetailInfoAPIResult(super.createResult());
            result.addData(this.dataList);

            return result;
        }

        return super.createResult();
    }
}
