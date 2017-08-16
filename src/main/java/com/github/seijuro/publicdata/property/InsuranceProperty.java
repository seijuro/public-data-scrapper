package com.github.seijuro.publicdata.property;

public class InsuranceProperty {
    static class CodePrefix {
        static final int IC_PREFIX = 0x00060000;
    }

    public static final String BODY = "body";
    public static final String ITEMS = "items";
    public static final String ITEM = "item";

    public static class Item {
        public static final String ADDRESS = "addr";
        public static final String INSURANCE_TYPE = "boheomFg";
        public static final String THE_NUMBER_OF_REGURAL_EMPLOYMENT = "gySangsiInwonCnt";
        public static final String DATE_OF_EMPLOYMENT = "gySeongripDt";
        public static final String ZIPCODE = "post";
        public static final String COMPANYNAME = "saeopjangNm";
        public static final String THE_NUMBER_OF_INSURED = "sjSangsiInwonCnt";
        public static final String DATE_OF_INSURED = "sjSeongripDt";
    }

    public static class ItemCode {
        public static final int IC_ADDRESS = CodePrefix.IC_PREFIX | 0x01;
        public static final int IC_INSURANCE_TYPE = CodePrefix.IC_PREFIX | 0x02;
        public static final int IC_THE_NUMBER_OF_REGURAL_EMPLOYMENT = CodePrefix.IC_PREFIX | 0x03;
        public static final int IC_DATE_OF_EMPLOYMENT = CodePrefix.IC_PREFIX | 0x04;
        public static final int IC_ZIPCODE = CodePrefix.IC_PREFIX | 0x05;
        public static final int IC_COMPANYNAME = CodePrefix.IC_PREFIX | 0x06;
        public static final int IC_THE_NUMBER_OF_INSURED = CodePrefix.IC_PREFIX | 0x07;
        public static final int IC_DATE_OF_INSURED = CodePrefix.IC_PREFIX | 0x08;
    }
}
