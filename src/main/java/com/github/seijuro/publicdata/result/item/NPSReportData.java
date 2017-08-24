package com.github.seijuro.publicdata.result.item;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@ToString
public class NPSReportData extends PublicData {
    /**
     * Instance Properties
     */
    @Getter
    private final Integer sequenceId;
    @Getter
    private final String createYM;
    @Getter
    private final Integer employeedPersonCount;
    @Getter
    private final Integer retiredPersonCount;

    protected NPSReportData(Builder builder) {
        super(builder);

        sequenceId = builder.sequenceId;
        createYM = builder.createYM;
        employeedPersonCount = builder.employeedPersonCount;
        retiredPersonCount = builder.retiredPersonCount;
    }

    @ToString
    public static class Builder extends PublicData.Builder {
        private Integer sequenceId = null;
        @Setter
        private String createYM = null;
        private Integer employeedPersonCount = 0;
        private Integer retiredPersonCount = 0;

        public void setSequenceId(String value) {
            Objects.requireNonNull(value);

            try {
                setSequenceId(Integer.parseInt(value));

                return;
            }
            catch (NumberFormatException excp) {
                excp.printStackTrace();
            }

            this.sequenceId = null;
        }

        public void setSequenceId(Integer value) {
            this.sequenceId = value;
        }

        public void setEmployeedPersonCount(String value) {
            Objects.requireNonNull(value);

            try {
                this.employeedPersonCount = Integer.parseInt(value);

                return;
            }
            catch (NumberFormatException excp) {
                excp.printStackTrace();
            }

            this.employeedPersonCount = 0;
        }

        public void setRetiredPersonCount(String value) {
            Objects.requireNonNull(value);

            try {
                this.retiredPersonCount = Integer.parseInt(value);

                return;
            }
            catch (NumberFormatException excp) {
                excp.printStackTrace();
            }

            this.retiredPersonCount = 0;
        }

        @Override
        public NPSReportData build() {
            return new NPSReportData(this);
        }
    }
}
