package com.github.seijuro.publicdata.address;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;

@ToString
public class LegalDongAddressCode {
    static final int CODE_LENGTH = 10;
    static final int DG_LENGTH = 2;
    static final int SGGU_LENGTH = 3;
    static final int EMD_LENGTH = 3;
    static final int OTHERS_LENGTH = 2;

    static final int DG_OFFSET = 0;
    static final int SGGU_OFFSET = DG_LENGTH;
    static final int EMD_OFFSET = SGGU_OFFSET + SGGU_LENGTH;
    static final int OTHER_OFFSET = EMD_OFFSET + EMD_LENGTH;

    /**
     * parse interface
     *
     * @param codeText
     * @return
     */
    public static LegalDongAddressCode parse(String codeText) {
        if (codeText == null) {
            throw new NullPointerException("parameter is null.");
        }
        else if (codeText.length() != CODE_LENGTH) {
            throw new IllegalArgumentException("parameter, " + codeText + ", is illegal.");
        }

        String dg = codeText.substring(DG_OFFSET, DG_OFFSET + DG_LENGTH);
        String sggu = codeText.substring(SGGU_OFFSET, SGGU_OFFSET + SGGU_LENGTH);
        String emd = codeText.substring(EMD_OFFSET, EMD_OFFSET + EMD_LENGTH);
        String others = codeText.substring(OTHER_OFFSET, OTHER_OFFSET + OTHERS_LENGTH);

        try {
            return new LegalDongAddressCode(Integer.parseInt(dg), Integer.parseInt(sggu), Integer.parseInt(emd), Integer.parseInt(others));
        }
        catch (NumberFormatException excp) {
            throw new IllegalArgumentException(excp.getMessage());
        }
    }

    /**
     * Instance Properties
     */
    public int dg;
    public int sggu;
    public int emd;
    @Getter(AccessLevel.PUBLIC)
    public int others;

    /**
     * C'tor
     *
     * @param dg
     * @param sggu
     * @param emd
     * @param others
     */
    LegalDongAddressCode(int dg, int sggu, int emd, int others) {
        this.dg = dg;
        this.sggu = sggu;
        this.emd = emd;
        this.others = others;
    }

    public int getDG() {
        return this.dg;
    }

    public int getSGGU() {
        return this.sggu;
    }

    public int getEMD() {
        return this.emd;
    }

    public String getDGString() {
        return String.format("%02d", this.dg);
    }

    public String getSGGUString() {
        return String.format("%03d", this.sggu);
    }

    public String getEMDUString() {
        return String.format("%03d", this.emd);
    }

    public String getOthersString() {
        return String.format("%02d", this.others);
    }
}
