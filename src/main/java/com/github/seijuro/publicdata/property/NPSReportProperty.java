package com.github.seijuro.publicdata.property;

public class NPSReportProperty {
    static class CodePrefix {
        static final int IC_PREFIX = 0x000B0000;
    }

    public static final String BODY = "body";
    public static final String ITEM = "item";

    public static class Item {
        public static final String SEQUENCEID = "seq";
        public static final String CREATE_YM = "data_crt_ym";
        public static final String EMPLOYEED_PERSON_COUNT = "nwAczqrCnt";
        public static final String RETIRED_PERSON_COUNT = "lssJnngpCnt";
    }

    public static class ItemCode {
        public static final int IC_SEQUENCEID = CodePrefix.IC_PREFIX |  0x01;
        public static final int IC_CREATE_YM = CodePrefix.IC_PREFIX |  0x02;
        public static final int IC_EMPLOYEED_PERSON_COUNT = CodePrefix.IC_PREFIX |  0x03;
        public static final int IC_RETIRED_PERSON_COUNT = CodePrefix.IC_PREFIX |  0x04;
    }
}
