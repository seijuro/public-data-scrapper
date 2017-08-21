package com.github.seijuro.publicdata.result.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class PyramidSellingDetailData extends PyramidSellingData {
    /**
     * Instance Properties
     */
    @Getter(AccessLevel.PUBLIC)
    private final Long capital;
    @Getter(AccessLevel.PUBLIC)
    private final String address;
    @Getter(AccessLevel.PUBLIC)
    private final Integer status;

    /**
     * Construct
     *
     * @param builder
     */
    protected PyramidSellingDetailData(Builder builder) {
        super(builder);

        capital = builder.capital;
        address = builder.address;
        status = builder.status;
    }

    @ToString
    public static class Builder extends PyramidSellingData.Builder {
        private Long capital = null;
        @Setter
        private String address = StringUtils.EMPTY;
        private Integer status = null;

        public void setCapital(String value) {
            Objects.requireNonNull(value);

            capital = Long.parseLong(value);
        }

        public void setStatus(String status) {
            Objects.requireNonNull(status);

            this.status = Integer.parseInt(status);
        }

        /**
         * Builder Pattern method
         *
         * @return
         */
        public PyramidSellingDetailData build() {
            return new PyramidSellingDetailData(this);
        }
    }
}
