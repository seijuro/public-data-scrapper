package com.github.seijuro.publicdata.parser;

import com.github.seijuro.publicdata.property.NPSReportProperty;
import com.github.seijuro.publicdata.property.NPSReportPropertyUtils;
import com.github.seijuro.publicdata.result.NPSReportAPIResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import com.github.seijuro.publicdata.result.item.NPSReportData;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class NPSReportAPIResponseParser extends PublicDataAPIResponseXMLParser {
    /**
     * Instance Properties
     */
    protected NPSReportData.Builder dataBuilder = null;
    protected List<NPSReportData> dataList = null;
    @Setter
    protected Integer sequenceId = null;

    /**
     * C'tor
     */
    public NPSReportAPIResponseParser() {
        super();
    }

    @Override
    protected boolean handleTagBegin(String tag) {
        if (!super.handleTagBegin(tag)) {
            if (NPSReportPropertyUtils.Item.contains(tag)) {
                return true;
            }
            else if (NPSReportProperty.ITEM.equals(tag)) {
                //  create builder object
                this.dataBuilder = new NPSReportData.Builder();
                this.dataBuilder.setSequenceId(sequenceId);

                return true;
            }
            else if (NPSReportProperty.BODY.equals(tag)) {
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
            if (NPSReportPropertyUtils.Item.contains(tag)) {
                int code = NPSReportPropertyUtils.Item.getCode(tag, Integer.MIN_VALUE);

                assert (code != Integer.MIN_VALUE);
                assert (dataBuilder != null);

                switch (code) {
                    case NPSReportProperty.ItemCode.IC_SEQUENCEID:
                        dataBuilder.setSequenceId(value);
                        return true;
                    case NPSReportProperty.ItemCode.IC_CREATE_YM:
                        dataBuilder.setCreateYM(value);
                        return true;
                    case NPSReportProperty.ItemCode.IC_EMPLOYEED_PERSON_COUNT:
                        dataBuilder.setEmployeedPersonCount(value);
                        return true;
                    case NPSReportProperty.ItemCode.IC_RETIRED_PERSON_COUNT:
                        dataBuilder.setRetiredPersonCount(value);
                        return true;

                    default:
                        break;
                }
            }
            else if (NPSReportProperty.ITEM.equals(tag)) {
                assert (this.dataList != null);
                assert (this.dataBuilder != null);

                this.dataList.add(this.dataBuilder.build());

                this.dataBuilder = null;

                return true;
            }
            else if (NPSReportProperty.BODY.equals(tag)) {
                //  do nothing

                return true;
            }
        }

        return false;
    }

    @Override
    protected PublicDataAPIResult createResult() {
        if (!hasError()) {
            PublicDataAPIResult result = new NPSReportAPIResult(super.createResult());
            result.addData(this.dataList);

            return result;
        }

        return super.createResult();
    }
}
