package com.github.seijuro.publicdata.property;

public class SellerProperty {
    static class CodePrefix {
        static final int IC_PREFIX = 0x00090000;
    }

    public static final String BODY = "body";
    public static final String ITEMS = "items";
    public static final String ITEM = "item";

    public static class Item {
        public static final String SEQUENCE_ID = "seq";
        public static final String STATE= "sidoNm";
        public static final String COMPANY_NAME = "bupNm";
        public static final String REPRESENTATIVE = "repsntNm";
        public static final String STATUS = "mngStateCode";
        public static final String FILING_DATE = "permYmd";
        public static final String DOMAIN = "dmnNm";
    }

    public static class ItemCode {
        public static final int IC_SEQUENCE_ID = CodePrefix.IC_PREFIX | 0x01;
        public static final int IC_STATE = CodePrefix.IC_PREFIX | 0x02;
        public static final int IC_COMPANY_NAME = CodePrefix.IC_PREFIX | 0x03;
        public static final int IC_REPRESENTATIVE = CodePrefix.IC_PREFIX | 0x04;
        public static final int IC_STATUS = CodePrefix.IC_PREFIX | 0x05;
        public static final int IC_FILING_DATE = CodePrefix.IC_PREFIX | 0x06;
        public static final int IC_DOMAIN = CodePrefix.IC_PREFIX | 0x07;
    }
}
