package com.github.seijuro.publicdata.result.item;

import com.github.seijuro.common.IPrettyPrint;
import lombok.EqualsAndHashCode;

import java.util.function.Consumer;

@EqualsAndHashCode
public abstract class PublicData implements IPrettyPrint {
    /**
     * Default C'tor
     */
    public PublicData() {
    }

    /**
     * C'tor for builder
     *
     * @param builder
     */
    public PublicData(Builder builder) {
    }

    @Override
    public void prettyPrint(Consumer<String> consumer) {
        //  default impl.
    }

    /**
     * Builder Pattern class
     */
    public static abstract class Builder {
        public abstract PublicData build();
    }
}
