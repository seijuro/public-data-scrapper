package com.github.seijuro.publicdata.property;

public class TheUseOfEnergyProperty {
    static class CodePrefix {
        static final int IC_PREFIX = 0x00070000;
    }

    public static final String BODY = "body";
    public static final String ITEMS = "items";
    public static final String ITEM = "item";

    public static class Item {
        public static final String ADDRESSCODE_DG_SGGU = "sigunguCd";
        public static final String ADDRESSCODE_EMD= "bjdongCd";
        public static final String TYPECODE_LAND = "platGbCd";
        public static final String ADDRESSCODE_JIBUN_MOAJOR = "bun";
        public static final String ADDRESSCODE_JIBUN_MINOR = "ji";
        public static final String ADDRESSCODE_ROAD = "naRoadCd";
        public static final String TYPECODE_GROUND = "naUgrndCd";
        public static final String ADDRESSNUMBER_MAJOR = "naMainBun";
        public static final String ADDRESSNUMBER_MINOR = "naSubBun";
        public static final String USE_QUANTITY = "useQty";
        public static final String RECORD_NUMBER = "rnum";
        public static final String USE_YM = "useYm";
        public static final String ADDRESS = "platPlc";
        public static final String ADDRESS_ROAD = "newPlatPlc";
    }

    public static class ItemCode {
        public static final int IC_ADDRESSCODE_DG_SGGU = CodePrefix.IC_PREFIX | 0x01;
        public static final int IC_ADDRESSCODE_EMD= CodePrefix.IC_PREFIX | 0x02;
        public static final int IC_TYPECODE_LAND = CodePrefix.IC_PREFIX | 0x03;
        public static final int IC_ADDRESSCODE_JIBUN_MOAJOR = CodePrefix.IC_PREFIX | 0x04;
        public static final int IC_ADDRESSCODE_JIBUN_MINOR = CodePrefix.IC_PREFIX | 0x05;
        public static final int IC_ADDRESSCODE_ROAD = CodePrefix.IC_PREFIX | 0x06;
        public static final int IC_TYPECODE_GROUND = CodePrefix.IC_PREFIX | 0x07;
        public static final int IC_ADDRESSNUMBER_MAJOR = CodePrefix.IC_PREFIX | 0x08;
        public static final int IC_ADDRESSNUMBER_MINOR = CodePrefix.IC_PREFIX | 0x09;
        public static final int IC_USE_QUANTITY = CodePrefix.IC_PREFIX | 0x0A;
        public static final int IC_RECORD_NUMBER = CodePrefix.IC_PREFIX | 0x0B;
        public static final int IC_USE_YM = CodePrefix.IC_PREFIX | 0x0C;
        public static final int IC_ADDRESS = CodePrefix.IC_PREFIX | 0x0D;
        public static final int IC_ADDRESS_ROAD = CodePrefix.IC_PREFIX | 0x0E;
    }
}
