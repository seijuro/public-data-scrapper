package com.github.seijuro.publicdata.address;

import com.github.seijuro.common.field.value.IBoolFieldValue;
import lombok.ToString;

@ToString
public enum LegalDongAddressStatus implements IBoolFieldValue {
    AVAILABLE(true),
    NOT_AVAILABLE(false);

    static final String STATUS_TEXT_AVAILABLE = "존재";
    static final String STATUS_TEXT_NOT_AVAILABLE = "폐지";

    /**
     * parse interface
     *
     * @param textStatus
     * @return
     */
    public static LegalDongAddressStatus parse(String textStatus) {
        if (STATUS_TEXT_AVAILABLE.equals(textStatus)) {
            return LegalDongAddressStatus.AVAILABLE;
        }
        else if (STATUS_TEXT_NOT_AVAILABLE.equals(textStatus)) {
            return LegalDongAddressStatus.NOT_AVAILABLE;
        }

        throw new IllegalArgumentException(String.format("status text(%s) is illegal.", textStatus));
    }


    /**
     * Instance Properties
     */
    private final boolean isAvailable;

    /**
     * C'tor
     *
     * @param $isAvailable
     */
    LegalDongAddressStatus(boolean $isAvailable) {
        this.isAvailable = $isAvailable;
    }

    /**
     * Implements 'IBoolFieldValue::getBoolValue' interface
     *
     * @return
     */
    @Override
    public boolean getBoolValue() {
        return this.isAvailable;
    }

    /**
     * Implements 'IBoolFieldValue::getBooleanValue' interface
     *
     * @return
     */
    @Override
    public Boolean getBooleanValue() {
        return this.isAvailable;
    }
}
