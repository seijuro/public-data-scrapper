package com.github.seijuro.publicdata.result.item;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Objects;

public class SellerData extends PublicData {
    /**
     * Type
     */
    public enum Type {
        DoorToDoor(1),
        TelleMarket(2),
        ColdCalling(3);

        private int code;

        public int intValue() {
            return code;
        }

        Type(int code) {
            this.code = code;
        }
    }

    /**
     * Status
     */
    public enum Status {
        NORMAL("01"),
        CLOSE_TEMPORARILY("02"),
        CLOSURE("03"),
        AUTHORITY_REVOKED("04"),
        TRANSFER_OUT("05"),
        TRANSFER_IN("06"),
        AUTHORITY_CANCELED("07"),
        RESUME("08");

        /**
         * Instance Properties
         */
        private final int codeValue;
        private final String code;

        public int intValue() {
            return codeValue;
        }

        public String codeString() {
            return code;
        }

        /**
         * Construct
         *
         * @param code
         */
        Status(String code) {
            this.code = code;
            this.codeValue = Integer.parseInt(code);
        }
    }


    /**
     * Instance Properties
     */
    @Getter
    private final int type;
    @Getter
    private final Integer seq;
    @Getter
    private final String state;
    @Getter
    private final String companyName;
    @Getter
    private final String representative;
    @Getter
    private final String status;
    @Getter
    private final DateTime filingDate;
    @Getter
    private final String domain;


    /**
     * Construct
     *
     * @param builder
     */
    protected SellerData(Builder builder) {
        super(builder);

        type = builder.type.intValue();
        seq = builder.seq;
        state = builder.state;
        companyName = builder.companyName;
        representative = builder.representative;
        status = builder.status.codeString();
        filingDate = builder.filingDate;
        domain = builder.domain;
    }

    /**
     * Builder Pattern class
     */
    public static class Builder extends PublicData.Builder {
        static final DateTimeFormatter df = DateTimeFormat.forPattern("yyyyMMdd");

        /**
         * Instance Properties
         */
        @Setter
        private Type type = null;
        private Integer seq = null;
        @Setter
        private String state = StringUtils.EMPTY;
        @Setter
        private String companyName = StringUtils.EMPTY;
        @Setter
        private String representative = StringUtils.EMPTY;
        private Status status = null;
        private DateTime filingDate = null;
        @Setter
        private String domain = StringUtils.EMPTY;

        public void setSequenceId(String value) {
            Objects.requireNonNull(value);

            seq = Integer.parseInt(value);
        }

        public void setStatus(String value) {
            Objects.requireNonNull(value);

            for (Status status : Status.values()) {
                status.codeString().equals(value);

                this.status = status;
                return;
            }

            throw new IllegalArgumentException(String.format("Param, %s, is illegal(stats : [\"01\" ~ \"08\"]).", value));
        }

        public void setFilingDate(String value) {
            Objects.requireNonNull(value);

            filingDate = DateTime.parse(value, df);
        }

        /**
         * Builder Pattner method.
         *
         * @return
         */
        @Override
        public SellerData build() {
            return new SellerData(this);
        }
    }
}
