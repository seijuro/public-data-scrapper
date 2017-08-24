package com.github.seijuro.publicdata.result.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class PyramidSellerDetailData extends PyramidSellerData {
    /**
     * Instance Properties
     */
    @Getter(AccessLevel.PUBLIC)
    private final String capital;
    @Getter(AccessLevel.PUBLIC)
    private final String address;
    @Getter(AccessLevel.PUBLIC)
    private final String status;

    /**
     * Construct
     *
     * @param builder
     */
    protected PyramidSellerDetailData(Builder builder) {
        super(builder);

        capital = builder.capital;
        address = builder.address;
        status = builder.status;
    }

    @ToString
    public static class Builder extends PyramidSellerData.Builder {
        @Setter
        private String capital = null;
        @Setter
        private String address = StringUtils.EMPTY;
        @Setter
        private String status = null;


        /**
         * Builder Pattern method
         *
         * @return
         */
        public PyramidSellerDetailData build() {
            return new PyramidSellerDetailData(this);
        }
    }
}
