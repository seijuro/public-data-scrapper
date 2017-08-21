package com.github.seijuro.publicdata.result.item;

import com.github.seijuro.publicdata.api.config.InsuranceType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@ToString
public class InsuranceData extends PublicData {
    /**
     * Instance Properties
     */
    @Getter
    private final String address;
    @Getter
    private final InsuranceType type;
    @Getter
    private final Integer theNumberOfRegularEmployment;
    @Getter
    private final DateTime dateOfEmployment;
    @Getter
    private final String zipCode;
    @Getter
    private final String companyName;
    @Getter
    private final Integer theNumberOfInsuredWorkers;
    @Getter
    private final DateTime dateOfInsured;

    /**
     * Constrcuts <code>InsuranceData</code> instance.
     *
     * @param builder
     */
    protected InsuranceData(Builder builder) {
        super(builder);

        this.address = builder.address;
        this.type = builder.type;
        this.theNumberOfRegularEmployment = builder.theNumberOfRegularEmployment;
        this.dateOfEmployment = builder.dateOfEmployment;
        this.zipCode = builder.zipCode;
        this.companyName = builder.companyName;
        this.theNumberOfInsuredWorkers = builder.theNumberOfInsuredWorkers;
        this.dateOfInsured = builder.dateOfInsured;
    }

    /**
     * Builder Pattern class
     */
    public static class Builder extends PublicData.Builder {
        static final DateTimeFormatter dt = DateTimeFormat.forPattern("yyyyMMdd");
        /**
         * Instance Properties
         */
        @Setter
        private String address = null;
        private InsuranceType type = null;
        private Integer theNumberOfRegularEmployment = null;
        private DateTime dateOfEmployment = null;
        @Setter
        private String zipCode = null;
        @Setter
        private String companyName = null;
        private Integer theNumberOfInsuredWorkers = null;
        private DateTime dateOfInsured = null;

        public void setType(String type) {
            this.type = InsuranceType.getType(type);
        }

        public void setTheNumberOfRegularEmployment(String number) {
            try {
                theNumberOfRegularEmployment = Integer.parseInt(number);
            }
            catch (NumberFormatException excp) {
                theNumberOfRegularEmployment = null;
            }
        }

        public void setDateOfEmployment(String date) {
            dateOfEmployment = DateTime.parse(date, dt);
        }

        public void setTheNumberOfInsuredWorkers(String number) {
            try {
                theNumberOfInsuredWorkers = Integer.parseInt(number);
            }
            catch (NumberFormatException excp) {
                theNumberOfInsuredWorkers = null;
            }
        }

        public void setDateOfInsured(String date) {
            dateOfInsured = DateTime.parse(date, dt);
        }

        /**
         * Builder Pattern metdod
         * build <code>InsuranceData</code> instacne.
         *
         * @return
         */
        @Override
        public InsuranceData build() {
            return new InsuranceData(this);
        }
    }
}
