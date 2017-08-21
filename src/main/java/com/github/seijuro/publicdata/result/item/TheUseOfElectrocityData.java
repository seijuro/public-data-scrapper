package com.github.seijuro.publicdata.result.item;

public class TheUseOfElectrocityData extends TheUseOfEnergyData {
    /**
     * Construct
     *
     * @param builder
     */
    protected TheUseOfElectrocityData(Builder builder) {
        super(builder);
    }

    /**
     * Builder Pattern class
     */
    public static class Builder extends TheUseOfEnergyData.Builder {
        public Builder() {
            super();
        }

        /**
         * Builder Pattern method
         *
         * @return
         */
        @Override
        public TheUseOfElectrocityData build() {
            return new TheUseOfElectrocityData(this);
        }
    }
}