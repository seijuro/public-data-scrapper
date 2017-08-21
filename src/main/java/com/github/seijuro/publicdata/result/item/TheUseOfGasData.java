package com.github.seijuro.publicdata.result.item;

public class TheUseOfGasData extends TheUseOfEnergyData {
    /**
     * Construct
     *
     * @param builder
     */
    protected TheUseOfGasData(Builder builder) {
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
        public TheUseOfGasData build() {
            return new TheUseOfGasData(this);
        }
    }
}
