package com.github.seijuro.publicdata.result.item;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class TheUseOfEnergyData extends PublicData {
    /**
     * Instance Properties
     */
    @Getter
    public final String addressCodeDGSGGU;
    @Getter
    public final String addressCodeEMD;
    @Getter
    public final String typeCodeLand;
    @Getter
    public final String addressCodeJibunMajor;
    @Getter
    public final String addressCodeJibunMinor;
    @Getter
    public final String addressCodeRoad;
    @Getter
    public final String typeCodeGround;
    @Getter
    public final String addressNumberMajor;
    @Getter
    public final String addressNumberMinor;
    @Getter
    public final String useQuantity;
    @Getter
    public final Integer recordNumber;
    @Getter
    public final String useYM;
    @Getter
    public final String address;
    @Getter
    public final String addressRoad;


    /**
     * Construct <code>TheUseOfEnergyData</code>
     *
     * @param builder
     */
    protected TheUseOfEnergyData(Builder builder) {
        super(builder);

        addressCodeDGSGGU = builder.addressCodeDGSGGU;
        addressCodeEMD = builder.addressCodeEMD;
        typeCodeLand = builder.typeCodeLand;
        addressCodeJibunMajor = builder.addressCodeJibunMajor;
        addressCodeJibunMinor = builder.addressCodeJibunMinor;
        addressCodeRoad = builder.addressCodeRoad;
        typeCodeGround = builder.typeCodeGround;
        addressNumberMajor = builder.addressNumberMajor;
        addressNumberMinor = builder.addressNumberMinor;
        useQuantity = builder.useQuantity;
        recordNumber = builder.recordNumber;
        useYM = builder.useYM;
        address = builder.address;
        addressRoad = builder.addressRoad;
    }

    @ToString
    public static class Builder extends PublicData.Builder {
        @Setter
        private String addressCodeDGSGGU = null;
        @Setter
        private String addressCodeEMD = null;
        @Setter
        private String typeCodeLand = null;
        @Setter
        private String addressCodeJibunMajor = null;
        @Setter
        public String addressCodeJibunMinor = null;
        @Setter
        private String addressCodeRoad = null;
        @Setter
        private String typeCodeGround = null;
        @Setter
        private String addressNumberMajor = null;
        @Setter
        private String addressNumberMinor = null;
        @Setter
        private String useQuantity = null;
        private Integer recordNumber = null;
        @Setter
        private String useYM = null;
        @Setter
        private String address = null;
        @Setter
        private String addressRoad = null;

        public void setRecordNumber(String rnum) {
            try {
                recordNumber = Integer.parseInt(rnum);
            }
            catch (NumberFormatException excp) {
                excp.printStackTrace();
            }
        }

        @Override
        public TheUseOfEnergyData build() {
            return new TheUseOfEnergyData(this);
        }
    }
}
