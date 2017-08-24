package com.github.seijuro.publicdata.property;

public class BusinessPlaceDetailProperty {
    static class CodePrefix {
        static final int IC_PREFIX = 0x000A0000;
    }

    public static final String BODY = "body";
    public static final String ITEM = "item";

    public static class Item {
        public static final String SEQUENCEID = "seq";
        public static final String TYPE = "vldtVlKrnNm";
        public static final String REGISTRATIONDATE = "adptDt";
        public static final String WITHDRAWALDATE = "scsnDt";
        public static final String NUMBEROFSUBSCRIBER = "jnngpCnt";
        public static final String NOTIFIEDAMOOUNT = "crrmmNtcAmt";
        public static final String NAME = "wkplNm";
        public static final String REGISTRATIONNUMBER = "bzowrRgstNo";
        public static final String ADDRESS_ROAD = "wkplRoadNmDtlAddr";
        public static final String STATUSCODE = "wkplJnngStcd";
        public static final String ADDRESSCODE_DG = "ldongAddrMgplDgCd";
        public static final String ADDRESSCODE_SGGU = "ldongAddrMgplSgguCd";
        public static final String ADDRESSCODE_EMD = "ldongAddrMgplSgguEmdCd";
        public static final String DIVISION = "wkplStylDvcd";
        public static final String TYPECODE = "wkplIntpCd";
    }

    public static class ItemCode {
        public static final int IC_SEQUENCEID = CodePrefix.IC_PREFIX |  0x01;
        public static final int IC_TYPE = CodePrefix.IC_PREFIX |  0x02;
        public static final int IC_REGISTRATIONDATE = CodePrefix.IC_PREFIX |  0x03;
        public static final int IC_WITHDRAWALDATE = CodePrefix.IC_PREFIX |  0x04;
        public static final int IC_NUMBEROFSUBSCRIBER = CodePrefix.IC_PREFIX |  0x05;
        public static final int IC_NOTIFIEDAMOOUNT = CodePrefix.IC_PREFIX |  0x06;
        public static final int IC_NAME = CodePrefix.IC_PREFIX |  0x07;
        public static final int IC_REGISTRATIONNUMBER = CodePrefix.IC_PREFIX |  0x08;
        public static final int IC_ADDRESS_ROAD = CodePrefix.IC_PREFIX |  0x09;
        public static final int IC_STATUSCODE = CodePrefix.IC_PREFIX |  0x0A;
        public static final int IC_ADDRESSCODE_DG = CodePrefix.IC_PREFIX |  0x0B;
        public static final int IC_ADDRESSCODE_SGGU = CodePrefix.IC_PREFIX |  0x0C;
        public static final int IC_ADDRESSCODE_EMD = CodePrefix.IC_PREFIX |  0x0D;
        public static final int IC_DIVISION = CodePrefix.IC_PREFIX |  0x0E;
        public static final int IC_TYPECODE = CodePrefix.IC_PREFIX |  0x0F;
    }
}
