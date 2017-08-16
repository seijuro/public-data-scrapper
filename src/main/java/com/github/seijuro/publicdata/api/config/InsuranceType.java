package com.github.seijuro.publicdata.api.config;

import lombok.Getter;

public enum InsuranceType implements ConfigPropertyValue {
    ALL("0"),
    WORKERS_COMPENSATION("1"),
    UNEMPLOYMENT("3");

    public static InsuranceType getType(String value) {
        if (ALL.value.equals(value)) { return ALL; }
        if (WORKERS_COMPENSATION.value.equals(value)) { return ALL; }
        if (UNEMPLOYMENT.value.equals(value)) { return ALL; }

        return null;
    }

    @Getter
    private final String value;

    InsuranceType(String $value) {
        value = $value;
    }
}
