package com.github.seijuro.publicdata.parser;

import com.github.seijuro.publicdata.property.SpecificationInfoProperty;
import com.github.seijuro.publicdata.property.SpecificationInfoPropertyUtils;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import com.github.seijuro.publicdata.result.SpeficiationInfoResult;
import com.github.seijuro.publicdata.result.item.SpecificationData;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class SpecificationInfoAPIResponseParser extends PublicDataAPIResponseXMLParser {
    /**
     * Instance Properties
     */
    @Getter(AccessLevel.PROTECTED)
    private List<SpecificationData> specificationInfos = null;
    @Getter(AccessLevel.PROTECTED)
    private SpecificationData.Builder productStdInfoBuilder = null;

    /**
     * C'tor
     */
    public SpecificationInfoAPIResponseParser() {
        super();
    }

    @Override
    public void clear() {
        if (this.specificationInfos != null) this.specificationInfos.clear();

        this.specificationInfos = null;
        this.productStdInfoBuilder = null;
    }

    @Override
    protected boolean handleTagBegin(String tag) {
        if (!super.handleTagBegin(tag)) {
            if (SpecificationInfoProperty.ITEMS.equals(tag)) {
                this.specificationInfos = new ArrayList<>(100);

                return true;
            }
            else if (SpecificationInfoProperty.ITEM.equals(tag)) {
                this.productStdInfoBuilder = new SpecificationData.Builder();

                return true;
            }
            else if (SpecificationInfoProperty.BODY.equals(tag)) {
                //  do nothing

                return true;
            }

            return false;
        }

        return true;
    }

    @Override
    protected boolean handleTagEnd(String tag, String value) {
        if (!super.handleTagEnd(tag, value)) {
            if (SpecificationInfoPropertyUtils.Item.contains(tag)) {
                int code = SpecificationInfoPropertyUtils.Item.getCode(tag, Integer.MIN_VALUE);

                switch (code) {
                    case SpecificationInfoProperty.ItemCode.IC_BSNS_DIV_NAME:
                        this.productStdInfoBuilder.setBusinessDivisionName(value);
                        return true;
                    case SpecificationInfoProperty.ItemCode.IC_REF_NO:
                        this.productStdInfoBuilder.setRefNo(value);
                        return true;
                    case SpecificationInfoProperty.ItemCode.IC_PRODUCT_NAME:
                        this.productStdInfoBuilder.setProductName(value);
                        return true;
                    case SpecificationInfoProperty.ItemCode.IC_ORDER_INSTR_NAME:
                        this.productStdInfoBuilder.setOrderingInstName(value);
                        return true;
                    case SpecificationInfoProperty.ItemCode.IC_DEMAND_INST_NAME:
                        this.productStdInfoBuilder.setDemandInstName(value);
                        return true;
                    case SpecificationInfoProperty.ItemCode.IC_ASSIGN_BUDGET_AMOUNT:
                        this.productStdInfoBuilder.setAssignBudgetAmount(value);
                        return true;
                    case SpecificationInfoProperty.ItemCode.IC_RECEIPT_DATE:
                        this.productStdInfoBuilder.setReceiptDate(value);
                        return true;
                    case SpecificationInfoProperty.ItemCode.IC_OPITION_REG_CLOSE_DATE:
                        this.productStdInfoBuilder.setRegOptionDueDate(value);
                        return true;
                    case SpecificationInfoProperty.ItemCode.IC_OFFICIAL_TELNO:
                        this.productStdInfoBuilder.setOfficalTel(value);
                        return true;
                    case SpecificationInfoProperty.ItemCode.IC_OFFICIAL_NAME:
                        this.productStdInfoBuilder.setOfficalName(value);
                        return true;
                    case SpecificationInfoProperty.ItemCode.IC_SW_BIZOBJ_YN:
                        this.productStdInfoBuilder.setIsSWBusiness(value);
                        return true;
                    case SpecificationInfoProperty.ItemCode.IC_DELIVERY_TIMELIMIT_DATE:
                        this.productStdInfoBuilder.setDeliveryDueDate(value);
                        return true;
                    case SpecificationInfoProperty.ItemCode.IC_DELIVERY_DAY_NUM:
                        this.productStdInfoBuilder.setDeliveryDayNum(value);
                        return true;
                    case SpecificationInfoProperty.ItemCode.IC_BEFORRE_SPEC_REG_NO:
                        this.productStdInfoBuilder.setSpecificationRegNo(value);
                        return true;
                    case SpecificationInfoProperty.ItemCode.IC_SPEC_DOCFILE_URL_1:
                        this.productStdInfoBuilder.setSpecificationDocFileURL1(value);
                        return true;
                    case SpecificationInfoProperty.ItemCode.IC_SPEC_DOCFILE_URL_2:
                        this.productStdInfoBuilder.setSpecificationDocFileURL2(value);
                        return true;
                    case SpecificationInfoProperty.ItemCode.IC_SPEC_DOCFILE_URL_3:
                        this.productStdInfoBuilder.setSpecificationDocFileURL3(value);
                        return true;
                    case SpecificationInfoProperty.ItemCode.IC_SPEC_DOCFILE_URL_4:
                        this.productStdInfoBuilder.setSpecificationDocFileURL4(value);
                        return true;
                    case SpecificationInfoProperty.ItemCode.IC_SPEC_DOCFILE_URL_5:
                        this.productStdInfoBuilder.setSpecificationDocFileURL5(value);
                        return true;
                    case SpecificationInfoProperty.ItemCode.IC_PRODUCT_DETAIL_LIST:
                        this.productStdInfoBuilder.setProductDetails(value);
                        return true;
                    case SpecificationInfoProperty.ItemCode.IC_REG_DATE:
                        this.productStdInfoBuilder.setRegDate(value);
                        return true;
                    case SpecificationInfoProperty.ItemCode.IC_CHANGE_DATE:
                        this.productStdInfoBuilder.setChangedDate(value);
                        return true;
                    case SpecificationInfoProperty.ItemCode.IC_BID_NOTICE_NO_LIST:
                        this.productStdInfoBuilder.setBidNoticeNoList(value);
                        return true;

                    default:
                        break;
                }
            }
            else if (SpecificationInfoProperty.ITEM.equals(tag)) {
                assert (this.specificationInfos != null);
                assert (this.productStdInfoBuilder != null);

                this.specificationInfos.add(this.productStdInfoBuilder.build());
                this.productStdInfoBuilder = null;

                return true;
            }
            else if (SpecificationInfoProperty.ITEMS.equals(tag)) {
                //  do nothing
                return true;
            }
            else if (SpecificationInfoProperty.BODY.equals(tag)) {
                //  do nothing
                return true;
            }

            return false;
        }

        return true;
    }

    @Override
    protected PublicDataAPIResult createResult() {
        if (!hasError()) {
            PublicDataAPIResult result = new SpeficiationInfoResult(this.getResultCode(), this.getResultMessage(), this.getPageNo(), this.getNumberOfRows(), this.getTotalCount());
            result.addData(this.specificationInfos);

            return result;
        }

        return super.createResult();
    }

    @Override
    public SpeficiationInfoResult getResult() {
        return (SpeficiationInfoResult)super.getResult();
    }
}
