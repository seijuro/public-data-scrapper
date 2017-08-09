package com.github.seijuro.publicdata.property;

public class SpecificationInfoProperty {
    static class CodePrefix {
        static final int IC_PREFIX = 0x00030000;
    }

    public static final String BODY = "body";
    public static final String ITEMS = "items";
    public static final String ITEM = "item";

    public static class Item {
        public static final String BSNS_DIV_NAME = "bsnsDivNm";
        public static final String REF_NO = "refNo";
        public static final String PRODUCT_NAME = "prdctClsfcNoNm";

        public static final String ORDER_INSTR_NAME = "orderInsttNm";
        public static final String DEMAND_INST_NAME = "rlDminsttNm";
        public static final String ASIGN_BUDGET_AMOUNT = "asignBdgtAmt";

        public static final String RECEIPT_DATE = "rcptDt";
        public static final String OPITION_REG_CLOSE_DATE = "opninRgstClseDt";
        public static final String OFFICIAL_TELNO = "ofclTelNo";
        public static final String OFFICIAL_NAME = "ofclNm";
        public static final String SW_BIZOBJ_YN = "swBizObjYn";
        public static final String DELIVERY_TIMELIMIT_DATE = "dlvrTmlmtDt";
        public static final String DELIVERY_DAY_NUM = "dlvrDaynum";
        public static final String BEFORRE_SPEC_REG_NO = "bfSpecRgstNo";
        public static final String SPEC_DOCFILE_URL_1 = "specDocFileUrl1";
        public static final String SPEC_DOCFILE_URL_2 = "specDocFileUrl2";
        public static final String SPEC_DOCFILE_URL_3 = "specDocFileUrl3";
        public static final String SPEC_DOCFILE_URL_4 = "specDocFileUrl4";
        public static final String SPEC_DOCFILE_URL_5 = "specDocFileUrl5";
        public static final String PRODUCT_DETAIL_LIST = "prdctDtlList";
        public static final String REG_DATE = "rgstDt";
        public static final String CHANGE_DATE = "chgDt";
        public static final String BID_NOTICE_NO_LIST = "bidNtceNoList";
    }

    public static class ItemCode {
        public static final int IC_BSNS_DIV_NAME = CodePrefix.IC_PREFIX | 0x01;
        public static final int IC_REF_NO = CodePrefix.IC_PREFIX | 0x02;
        public static final int IC_PRODUCT_NAME = CodePrefix.IC_PREFIX | 0x03;

        public static final int IC_ORDER_INSTR_NAME = CodePrefix.IC_PREFIX | 0x04;
        public static final int IC_DEMAND_INST_NAME = CodePrefix.IC_PREFIX | 0x05;
        public static final int IC_ASSIGN_BUDGET_AMOUNT = CodePrefix.IC_PREFIX | 0x06;

        public static final int IC_RECEIPT_DATE = CodePrefix.IC_PREFIX | 0x07;
        public static final int IC_OPITION_REG_CLOSE_DATE = CodePrefix.IC_PREFIX | 0x08;
        public static final int IC_OFFICIAL_TELNO = CodePrefix.IC_PREFIX | 0x09;
        public static final int IC_OFFICIAL_NAME = CodePrefix.IC_PREFIX | 0x0A;
        public static final int IC_SW_BIZOBJ_YN = CodePrefix.IC_PREFIX | 0x0B;
        public static final int IC_DELIVERY_TIMELIMIT_DATE = CodePrefix.IC_PREFIX | 0x0C;
        public static final int IC_DELIVERY_DAY_NUM = CodePrefix.IC_PREFIX | 0x0D;
        public static final int IC_BEFORRE_SPEC_REG_NO = CodePrefix.IC_PREFIX | 0x0E;
        public static final int IC_SPEC_DOCFILE_URL_1 = CodePrefix.IC_PREFIX | 0x0F;
        public static final int IC_SPEC_DOCFILE_URL_2 = CodePrefix.IC_PREFIX | 0x10;
        public static final int IC_SPEC_DOCFILE_URL_3 = CodePrefix.IC_PREFIX | 0x11;
        public static final int IC_SPEC_DOCFILE_URL_4 = CodePrefix.IC_PREFIX | 0x12;
        public static final int IC_SPEC_DOCFILE_URL_5 = CodePrefix.IC_PREFIX | 0x13;
        public static final int IC_PRODUCT_DETAIL_LIST = CodePrefix.IC_PREFIX | 0x14;
        public static final int IC_REG_DATE = CodePrefix.IC_PREFIX | 0x15;
        public static final int IC_CHANGE_DATE = CodePrefix.IC_PREFIX | 0x16;
        public static final int IC_BID_NOTICE_NO_LIST = CodePrefix.IC_PREFIX | 0x17;
    }
}
