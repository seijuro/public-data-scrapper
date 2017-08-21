package com.github.seijuro.publicdata.address;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;

@ToString
public class JibunAddress {
    @Getter(AccessLevel.PUBLIC)
    public final LegalDongAddressCode code;
    public final int jibunMajor;
    public final int jibunMinor;

    public JibunAddress(LegalDongAddressCode code, int major, int minor) {
        this.code = code;
        this.jibunMajor = major;
        this.jibunMinor = minor;
    }

    public String getCodeDGSGGUString() { return String.format("%02d%03d", code.getDG(), code.getSGGU()); }
    public String getCodeEMDString() { return String.format("%03d%02d", code.getEMD(), code.getOthers()); }
    public String getJIBUNMajor() {
        return String.format("%04d", jibunMajor);
    }
    public String getJIBUNMinor() {
        return String.format("%04d", jibunMinor);
    }
}
