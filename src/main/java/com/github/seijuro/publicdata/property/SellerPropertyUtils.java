package com.github.seijuro.publicdata.property;

import java.util.HashMap;
import java.util.Map;

public class SellerPropertyUtils {
    public static class Item {
        static final Map<String, Integer> itemMap;

        static {
            itemMap = new HashMap<>();

            itemMap.put(SellerProperty.Item.SEQUENCE_ID, SellerProperty.ItemCode.IC_SEQUENCE_ID);
            itemMap.put(SellerProperty.Item.STATE, SellerProperty.ItemCode.IC_STATE);
            itemMap.put(SellerProperty.Item.COMPANY_NAME, SellerProperty.ItemCode.IC_COMPANY_NAME);
            itemMap.put(SellerProperty.Item.REPRESENTATIVE, SellerProperty.ItemCode.IC_REPRESENTATIVE);
            itemMap.put(SellerProperty.Item.STATUS, SellerProperty.ItemCode.IC_STATUS);
            itemMap.put(SellerProperty.Item.FILING_DATE, SellerProperty.ItemCode.IC_FILING_DATE);
            itemMap.put(SellerProperty.Item.DOMAIN, SellerProperty.ItemCode.IC_DOMAIN);
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
