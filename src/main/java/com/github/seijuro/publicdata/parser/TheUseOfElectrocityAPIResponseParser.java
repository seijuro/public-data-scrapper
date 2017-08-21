package com.github.seijuro.publicdata.parser;

import com.github.seijuro.publicdata.property.TheUseOfEnergyProperty;
import com.github.seijuro.publicdata.property.TheUseOfEnergyPropertyUtils;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import com.github.seijuro.publicdata.result.TheUseOfElectrocityAPIResult;
import com.github.seijuro.publicdata.result.TheUseOfEnergyAPIResult;
import com.github.seijuro.publicdata.result.item.TheUseOfElectrocityData;

import java.util.ArrayList;
import java.util.List;

public class TheUseOfElectrocityAPIResponseParser extends PublicDataAPIResponseXMLParser {
    /**
     * Instance Properties
     */
    protected TheUseOfElectrocityData.Builder dataBuilder = null;
    protected List<TheUseOfElectrocityData> dataList = null;

    /**
     * Construct <code>InsuranceAPIResponseParser</code> instance.
     */
    public TheUseOfElectrocityAPIResponseParser() {

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
            if (TheUseOfEnergyPropertyUtils.Item.contains(tag)) {
                return true;
            }
            else if (TheUseOfEnergyProperty.ITEM.equals(tag)) {
                //  create builder object
                this.dataBuilder = new TheUseOfElectrocityData.Builder();

                return true;
            }
            else if (TheUseOfEnergyProperty.ITEMS.equals(tag)) {
                return true;
            }
            else if (TheUseOfEnergyProperty.BODY.equals(tag)) {
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
            if (TheUseOfEnergyPropertyUtils.Item.contains(tag)) {
                int code = TheUseOfEnergyPropertyUtils.Item.getCode(tag, Integer.MIN_VALUE);

                assert (code != Integer.MIN_VALUE);
                assert (this.dataBuilder != null);

                switch (code) {
                    case TheUseOfEnergyProperty.ItemCode.IC_ADDRESSCODE_DG_SGGU:
                        this.dataBuilder.setAddressCodeDGSGGU(value);
                        return true;
                    case TheUseOfEnergyProperty.ItemCode.IC_ADDRESSCODE_EMD:
                        this.dataBuilder.setAddressCodeEMD(value);
                        return true;
                    case TheUseOfEnergyProperty.ItemCode.IC_TYPECODE_LAND:
                        this.dataBuilder.setTypeCodeLand(value);
                        return true;
                    case TheUseOfEnergyProperty.ItemCode.IC_ADDRESSCODE_JIBUN_MOAJOR:
                        this.dataBuilder.setAddressCodeJibunMajor(value);
                        return true;
                    case TheUseOfEnergyProperty.ItemCode.IC_ADDRESSCODE_JIBUN_MINOR:
                        this.dataBuilder.setAddressCodeJibunMinor(value);
                        return true;
                    case TheUseOfEnergyProperty.ItemCode.IC_ADDRESSCODE_ROAD:
                        this.dataBuilder.setAddressCodeRoad(value);
                        return true;
                    case TheUseOfEnergyProperty.ItemCode.IC_TYPECODE_GROUND:
                        this.dataBuilder.setTypeCodeGround(value);
                        return true;
                    case TheUseOfEnergyProperty.ItemCode.IC_ADDRESSNUMBER_MAJOR:
                        this.dataBuilder.setAddressNumberMajor(value);
                        return true;
                    case TheUseOfEnergyProperty.ItemCode.IC_ADDRESSNUMBER_MINOR:
                        this.dataBuilder.setAddressNumberMinor(value);
                        return true;
                    case TheUseOfEnergyProperty.ItemCode.IC_USE_QUANTITY:
                        this.dataBuilder.setUseQuantity(value);
                        return true;
                    case TheUseOfEnergyProperty.ItemCode.IC_RECORD_NUMBER:
                        this.dataBuilder.setRecordNumber(value);
                        return true;
                    case TheUseOfEnergyProperty.ItemCode.IC_USE_YM:
                        this.dataBuilder.setUseYM(value);
                        return true;
                    case TheUseOfEnergyProperty.ItemCode.IC_ADDRESS:
                        this.dataBuilder.setAddress(value);
                        return true;
                    case TheUseOfEnergyProperty.ItemCode.IC_ADDRESS_ROAD:
                        this.dataBuilder.setAddressRoad(value);
                        return true;
                    default:
                        break;
                }
            }
            else if (TheUseOfEnergyProperty.ITEM.equals(tag)) {
                assert (this.dataList != null);
                assert (this.dataBuilder != null);

                this.dataList.add(this.dataBuilder.build());
                this.dataBuilder = null;

                return true;
            }
            else if (TheUseOfEnergyProperty.ITEMS.equals(tag)) {
                //  do nothing

                return true;
            }
            else if (TheUseOfEnergyProperty.BODY.equals(tag)) {
                //  do nothing

                return true;
            }
        }

        return false;
    }

    @Override
    protected PublicDataAPIResult createResult() {
        if (!hasError()) {
            PublicDataAPIResult result = new TheUseOfElectrocityAPIResult(super.createResult());
            result.addData(this.dataList);

            return result;
        }

        return super.createResult();
    }
}