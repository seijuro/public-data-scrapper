package com.github.seijuro.publicdata.property;

public class NPSProperty {
    static class CodePrefix {
        static final int IC_PREFIX = 0x00040000;
    }

    public static final String BODY = "body";
    public static final String ITEMS = "items";
    public static final String ITEM = "item";

    public static class Item {
        public static class Normal {
            public static final String CREATED_YM = "dataCrtYm";
            public static final String ID = "seq";
            public static final String BUSINESSPLACE_NAME = "wkplNm";
            public static final String REGISTRATION_NUMBER = "bzowrRgstNo";
            public static final String ADDRESS_STREET = "wkplRoadNmDtlAddr";
            public static final String STATUS_CODE = "wkplJnngStcd";
            public static final String DIVISION_CODE = "wkplStylDvcd";
            public static final String ADDRESS_DG = "ldongAddrMgplDgCd";
            public static final String ADDRESS_SGG = "ldongAddrMgplSgguCd";
            public static final String ADDRESS_EMD = "ldongAddrMgplSgguEmdCd";
        }

        public static class Detail {
            public static final String BUSINESSTYPE_NAME = "vldtVlKrnNm";
            public static final String BUSINESSTYPE_CODE = "wkplIntpCd";
            public static final String REGISTRATION_DATE = "adptDt";
            public static final String WITHDRAWAL_DATE = "scsnDt";
            public static final String SUBSCRIBER_NUMBER = "jnngpCnt";
            public static final String NOTIFIED_AMOUNT_OF_THIS_MONTH = "crrmmNtcAmt";
        }

        public static class Stats {
            public static final String MONTHLY_EMPLOYMENT = "nwAczqrCnt";
            public static final String MONTHLY_RETIREMENT = "lssJnngpCnt";
        }
    }

    public static class ItemCode {
        public static class Normal {
            public static final int IC_CREATED_YM = CodePrefix.IC_PREFIX | 0x01;
            public static final int IC_ID = CodePrefix.IC_PREFIX | 0x02;
            public static final int IC_BUSINESSPLACE_NAME = CodePrefix.IC_PREFIX | 0x03;
            public static final int IC_REGISTRATION_NUMBER = CodePrefix.IC_PREFIX | 0x04;
            public static final int IC_ADDRESS_STREET = CodePrefix.IC_PREFIX | 0x05;
            public static final int IC_STATUS_CODE = CodePrefix.IC_PREFIX | 0x06;
            public static final int IC_DIVISION_CODE = CodePrefix.IC_PREFIX | 0x07;
            public static final int IC_ADDRESS_DG = CodePrefix.IC_PREFIX | 0x08;
            public static final int IC_ADDRESS_SGG = CodePrefix.IC_PREFIX | 0x09;
            public static final int IC_ADDRESS_EMD = CodePrefix.IC_PREFIX | 0x0A;
        }

        public static class Detail {
            public static final int IC_BUSINESSTYPE_NAME = CodePrefix.IC_PREFIX | 0x0101;
            public static final int IC_BUSINESSTYPE_CODE = CodePrefix.IC_PREFIX | 0x0102;
            public static final int IC_REGISTRATION_DATE = CodePrefix.IC_PREFIX | 0x0103;
            public static final int IC_WITHDRAWAL_DATE = CodePrefix.IC_PREFIX | 0x0104;
            public static final int IC_SUBSCRIBER_NUMBER = CodePrefix.IC_PREFIX | 0x0105;
            public static final int IC_NOTIFIED_AMOUNT_OF_THIS_MONTH = CodePrefix.IC_PREFIX | 0x0106;
        }

        public static class Stats {
            public static final int IC_MONTHLY_EMPLOYMENT = CodePrefix.IC_PREFIX | 0x0201;
            public static final int IC_MONTHLY_RETIREMENT = CodePrefix.IC_PREFIX | 0x0202;
        }
    }
}
