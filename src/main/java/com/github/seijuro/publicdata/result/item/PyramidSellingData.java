package com.github.seijuro.publicdata.result.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@ToString
public class PyramidSellingData extends PublicData {
    /**
     * Instance Properties
     */
    @Getter(AccessLevel.PUBLIC)
    private final Integer sequenceId;
    @Getter(AccessLevel.PUBLIC)
    private final String companyName;
    @Getter(AccessLevel.PUBLIC)
    private final String serialNumber;
    @Getter(AccessLevel.PUBLIC)
    private final String representative;
    @Getter(AccessLevel.PUBLIC)
    private final DateTime dateOfApproval;

    /**
     * Construct
     *
     * @param builder
     */
    protected PyramidSellingData(Builder builder) {
        super(builder);

        sequenceId = builder.sequenceId;
        companyName = builder.companyName;
        serialNumber = builder.serialNumber;
        representative = builder.representative;
        dateOfApproval = builder.dateOfApproval;
    }

    /**
     * Builder Pattern class
     */
    @ToString
    public static class Builder extends PublicData.Builder {
        /**
         * Instance Properties
         */
        private Integer sequenceId;
        @Setter
        private String companyName = StringUtils.EMPTY;
        @Setter
        private String serialNumber = StringUtils.EMPTY;
        @Setter
        private String representative = StringUtils.EMPTY;
        private DateTime dateOfApproval = null;

        public void setSequenceId(String seq) {
            try {
                sequenceId = Integer.parseInt(seq);
            }
            catch (NumberFormatException excp) {
                System.out.println(String.format("[CHECK] Param, seq, is [%s]", seq));
                excp.printStackTrace();
            }
        }

        public void setDateOfApproval(String date) {
            DateTimeFormatter df = DateTimeFormat.forPattern("yyyyMMdd");
            dateOfApproval = DateTime.parse(date, df);
        }

        /**
         * Builder Pattern method
         *
         * @return
         */
        @Override
        public PyramidSellingData build() {
            return new PyramidSellingData(this);
        }
    }
}
