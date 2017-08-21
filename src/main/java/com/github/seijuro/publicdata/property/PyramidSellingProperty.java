package com.github.seijuro.publicdata.property;

public class PyramidSellingProperty {
    static class CodePrefix {
        static final int IC_PREFIX = 0x00080000;
    }

    public static final String BODY = "body";
    public static final String ITEMS = "items";
    public static final String ITEM = "item";

    public static class Item {
        public static final String SEQUENCE_ID = "seq";
        public static final String COMPANY_NAME = "bupNm";
        public static final String SERIAL_NUMBER = "apvNo";
        public static final String REPRESENTATIVE = "repNm";
        public static final String DATE_OF_APPROVAL = "apvYmd";
    }

    public static class ItemCode {
        public static final int IC_SEQUENCE_ID = CodePrefix.IC_PREFIX | 0x01;
        public static final int IC_COMPANY_NAME = CodePrefix.IC_PREFIX | 0x02;
        public static final int IC_SERIAL_NUMBER = CodePrefix.IC_PREFIX | 0x03;
        public static final int IC_REPRESENTATIVE = CodePrefix.IC_PREFIX | 0x04;
        public static final int IC_DATE_OF_APPROVAL = CodePrefix.IC_PREFIX | 0x05;
    }
}
