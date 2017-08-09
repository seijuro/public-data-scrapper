package com.github.seijuro.publicdata.property;

import java.util.HashMap;
import java.util.Map;

public class SpecificationInfoPropertyUtils {
    public static class Item {
        static final Map<String, Integer> itemMap;

        static {
            itemMap = new HashMap<>();

            itemMap.put(SpecificationInfoProperty.Item.BSNS_DIV_NAME, SpecificationInfoProperty.ItemCode.IC_BSNS_DIV_NAME);
            itemMap.put(SpecificationInfoProperty.Item.REF_NO, SpecificationInfoProperty.ItemCode.IC_REF_NO);
            itemMap.put(SpecificationInfoProperty.Item.PRODUCT_NAME, SpecificationInfoProperty.ItemCode.IC_PRODUCT_NAME);

            itemMap.put(SpecificationInfoProperty.Item.ORDER_INSTR_NAME, SpecificationInfoProperty.ItemCode.IC_ORDER_INSTR_NAME);
            itemMap.put(SpecificationInfoProperty.Item.DEMAND_INST_NAME, SpecificationInfoProperty.ItemCode.IC_DEMAND_INST_NAME);
            itemMap.put(SpecificationInfoProperty.Item.ASIGN_BUDGET_AMOUNT, SpecificationInfoProperty.ItemCode.IC_ASSIGN_BUDGET_AMOUNT);

            itemMap.put(SpecificationInfoProperty.Item.RECEIPT_DATE, SpecificationInfoProperty.ItemCode.IC_RECEIPT_DATE);
            itemMap.put(SpecificationInfoProperty.Item.OPITION_REG_CLOSE_DATE, SpecificationInfoProperty.ItemCode.IC_OPITION_REG_CLOSE_DATE);
            itemMap.put(SpecificationInfoProperty.Item.OFFICIAL_TELNO, SpecificationInfoProperty.ItemCode.IC_OFFICIAL_TELNO);
            itemMap.put(SpecificationInfoProperty.Item.OFFICIAL_NAME, SpecificationInfoProperty.ItemCode.IC_OFFICIAL_NAME);
            itemMap.put(SpecificationInfoProperty.Item.SW_BIZOBJ_YN, SpecificationInfoProperty.ItemCode.IC_SW_BIZOBJ_YN);
            itemMap.put(SpecificationInfoProperty.Item.DELIVERY_TIMELIMIT_DATE, SpecificationInfoProperty.ItemCode.IC_DELIVERY_TIMELIMIT_DATE);
            itemMap.put(SpecificationInfoProperty.Item.DELIVERY_DAY_NUM, SpecificationInfoProperty.ItemCode.IC_DELIVERY_DAY_NUM);
            itemMap.put(SpecificationInfoProperty.Item.BEFORRE_SPEC_REG_NO, SpecificationInfoProperty.ItemCode.IC_BEFORRE_SPEC_REG_NO);
            itemMap.put(SpecificationInfoProperty.Item.SPEC_DOCFILE_URL_1, SpecificationInfoProperty.ItemCode.IC_SPEC_DOCFILE_URL_1);
            itemMap.put(SpecificationInfoProperty.Item.SPEC_DOCFILE_URL_2, SpecificationInfoProperty.ItemCode.IC_SPEC_DOCFILE_URL_2);
            itemMap.put(SpecificationInfoProperty.Item.SPEC_DOCFILE_URL_3, SpecificationInfoProperty.ItemCode.IC_SPEC_DOCFILE_URL_3);
            itemMap.put(SpecificationInfoProperty.Item.SPEC_DOCFILE_URL_4, SpecificationInfoProperty.ItemCode.IC_SPEC_DOCFILE_URL_4);
            itemMap.put(SpecificationInfoProperty.Item.SPEC_DOCFILE_URL_5, SpecificationInfoProperty.ItemCode.IC_SPEC_DOCFILE_URL_5);
            itemMap.put(SpecificationInfoProperty.Item.PRODUCT_DETAIL_LIST, SpecificationInfoProperty.ItemCode.IC_PRODUCT_DETAIL_LIST);
            itemMap.put(SpecificationInfoProperty.Item.REG_DATE, SpecificationInfoProperty.ItemCode.IC_REG_DATE);
            itemMap.put(SpecificationInfoProperty.Item.CHANGE_DATE, SpecificationInfoProperty.ItemCode.IC_CHANGE_DATE);
            itemMap.put(SpecificationInfoProperty.Item.BID_NOTICE_NO_LIST, SpecificationInfoProperty.ItemCode.IC_BID_NOTICE_NO_LIST);
        }

        public static boolean contains(String key) {
            return itemMap.containsKey(key);
        }

        public static int getCode(String key, int defaultValue) {
            Integer val = itemMap.get(key);

            if (val instanceof Integer) {
                return val;
            }

            return defaultValue;
        }
    }
}
