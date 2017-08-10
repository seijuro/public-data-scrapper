package com.github.seijuro.publicdata.result.item;

import com.github.seijuro.publicdata.property.NPSProperty;
import lombok.*;

import java.util.function.Consumer;

@ToString
@EqualsAndHashCode(callSuper = true)
public class BusinessPlaceData extends NPSData {
    /**
     * Instsance Properties
     */
    @Getter(AccessLevel.PUBLIC)
    private final String id;
    @Getter(AccessLevel.PUBLIC)
    private final String createdDatedYM;
    @Getter(AccessLevel.PUBLIC)
    private final String name;
    @Getter(AccessLevel.PUBLIC)
    private final String registrationNumber;
    @Getter(AccessLevel.PUBLIC)
    private final String addrRoad;
    @Getter(AccessLevel.PUBLIC)
    private final String statusCode;
    @Getter(AccessLevel.PUBLIC)
    private final String divisionCode;
    @Getter(AccessLevel.PUBLIC)
    private final String addrCodeDG;
    @Getter(AccessLevel.PUBLIC)
    private final String addrCodeSGGU;
    @Getter(AccessLevel.PUBLIC)
    private final String addrCodeEMD;

    /**
     * C'tor for builder
     *
     * @param builder
     */
    protected BusinessPlaceData(Builder builder) {
        super(builder);

        this.id = builder.id;
        this.createdDatedYM = builder.createdDatedYM;
        this.name = builder.name;
        this.registrationNumber = builder.registrationNumber;
        this.addrRoad = builder.addrStreet;
        this.statusCode = builder.statusCode;
        this.divisionCode = builder.divisionCode;
        this.addrCodeDG = builder.addrCodeDG;
        this.addrCodeSGGU = builder.addrCodeSGG;
        this.addrCodeEMD = builder.addrCodeEMD;
    }

    @Override
    public void prettyPrint(Consumer<String> consumer) {
        StringBuffer sb = new StringBuffer();

        sb.append("business place information := \n")
                .append("\t").append(NPSProperty.Item.Normal.ID).append(" : [").append(this.id).append("]\n")
                .append("\t").append(NPSProperty.Item.Normal.CREATED_YM).append(" : [").append(this.createdDatedYM).append("]\n")
                .append("\t").append(NPSProperty.Item.Normal.BUSINESSPLACE_NAME).append(" : [").append(this.name).append("]\n")
                .append("\t").append(NPSProperty.Item.Normal.REGISTRATION_NUMBER).append(" : [").append(this.registrationNumber).append("]\n")
                .append("\t").append(NPSProperty.Item.Normal.ADDRESS_ROAD).append(" : [").append(this.addrRoad).append("]\n")
                .append("\t").append(NPSProperty.Item.Normal.STATUS_CODE).append(" : [").append(this.statusCode).append("]\n")
                .append("\t").append(NPSProperty.Item.Normal.DIVISION_CODE).append(" : [").append(this.divisionCode).append("]\n")
                .append("\t").append(NPSProperty.Item.Normal.ADDRESS_DG).append(" : [").append(this.addrCodeDG).append("]\n")
                .append("\t").append(NPSProperty.Item.Normal.ADDRESS_SGGU).append(" : [").append(this.addrCodeSGGU).append("]\n")
                .append("\t").append(NPSProperty.Item.Normal.ADDRESS_EMD).append(" : [").append(this.addrCodeEMD).append("]");

        consumer.accept(sb.toString());
    }

    /**
     * Builder Pattern class
     */
    public static class Builder extends NPSData.Builder {
        @Setter(AccessLevel.PUBLIC)
        private String id = null;
        @Setter(AccessLevel.PUBLIC)
        private String createdDatedYM = null;
        @Setter(AccessLevel.PUBLIC)
        private String name = null;
        @Setter(AccessLevel.PUBLIC)
        private String registrationNumber = null;
        @Setter(AccessLevel.PUBLIC)
        private String addrStreet = null;
        @Setter(AccessLevel.PUBLIC)
        private String statusCode = null;
        @Setter(AccessLevel.PUBLIC)
        private String divisionCode = null;
        @Setter(AccessLevel.PUBLIC)
        private String addrCodeDG = null;
        @Setter(AccessLevel.PUBLIC)
        private String addrCodeSGG = null;
        @Setter(AccessLevel.PUBLIC)
        private String addrCodeEMD = null;

        /**
         * build : Builder Pattern method
         */
        @Override
        public BusinessPlaceData build() {
            return new BusinessPlaceData(this);
        }
    }
}
