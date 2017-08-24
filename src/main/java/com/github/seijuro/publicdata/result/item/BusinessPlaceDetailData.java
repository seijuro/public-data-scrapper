package com.github.seijuro.publicdata.result.item;

import com.github.seijuro.publicdata.property.NPSProperty;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Consumer;

@ToString
@EqualsAndHashCode(callSuper = true)
public class BusinessPlaceDetailData extends PublicData {
    /**
     * Instance Properties
     */
    @Getter
    private final Integer sequenceId;
    @Getter
    private final String type;
    @Getter
    private final String registrationDate;
    @Getter
    private final String withdrawalDate;
    @Getter
    private final String numberOfSubscriber;
    @Getter
    private final String notifiedAmount;
    @Getter
    private final String name;
    @Getter
    private final String registrationNumber;
    @Getter
    private final String addressRoad;
    @Getter
    private final String statusCode;
    @Getter
    private final String addressDG;
    @Getter
    private final String addressSGGU;
    @Getter
    private final String addressEMD;
    @Getter
    private final String divisionCode;
    @Getter
    private final String typeCode;

    /**
     * C'tor
     *
     * @param builder
     */
    protected BusinessPlaceDetailData(Builder builder) {
        super(builder);

        sequenceId = builder.sequenceId;
        type = builder.type;
        registrationDate = builder.registrationDate;
        withdrawalDate = builder.withdrawalDate;
        numberOfSubscriber = builder.numberOfSubscriber;
        notifiedAmount = builder.notifiedAmount;
        name = builder.name;
        registrationNumber = builder.registrationNumber;
        addressRoad = builder.addressRoad;
        statusCode = builder.statusCode;
        addressDG = builder.addressDG;
        addressSGGU = builder.addressSGGU;
        addressEMD = builder.addressEMD;
        divisionCode = builder.divisionCode;
        typeCode = builder.typeCode;
    }

    /**
     * Builder Pattern class
     */
    public static class Builder extends PublicData.Builder {
        /**
         * Instance Properties
         */
        @Setter
        private Integer sequenceId = null;
        @Setter
        private String type = StringUtils.EMPTY;
        @Setter
        private String registrationDate = StringUtils.EMPTY;
        @Setter
        private String withdrawalDate = StringUtils.EMPTY;
        @Setter
        private String numberOfSubscriber = StringUtils.EMPTY;
        @Setter
        private String notifiedAmount = StringUtils.EMPTY;
        @Setter
        private String name = StringUtils.EMPTY;
        @Setter
        private String registrationNumber = StringUtils.EMPTY;
        @Setter
        private String addressRoad = StringUtils.EMPTY;
        @Setter
        private String statusCode = StringUtils.EMPTY;
        @Setter
        private String addressDG = StringUtils.EMPTY;
        @Setter
        private String addressSGGU = StringUtils.EMPTY;
        @Setter
        private String addressEMD = StringUtils.EMPTY;
        @Setter
        private String divisionCode = StringUtils.EMPTY;
        @Setter
        private String typeCode = StringUtils.EMPTY;

        /**
         * Builder Pattern method
         *
         * @return
         */
        @Override
        public BusinessPlaceDetailData build() {
            return new BusinessPlaceDetailData(this);
        }
    }
}
