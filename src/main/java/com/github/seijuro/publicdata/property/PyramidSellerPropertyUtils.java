package com.github.seijuro.publicdata.property;

import java.util.HashMap;
import java.util.Map;

public class PyramidSellerPropertyUtils {
    public static class Item {
        static final Map<String, Integer> itemMap;

        static {
            itemMap = new HashMap<>();

            itemMap.put(PyramidSellerProperty.Item.SEQUENCE_ID, PyramidSellerProperty.ItemCode.IC_SEQUENCE_ID);
            itemMap.put(PyramidSellerProperty.Item.COMPANY_NAME, PyramidSellerProperty.ItemCode.IC_COMPANY_NAME);
            itemMap.put(PyramidSellerProperty.Item.SERIAL_NUMBER, PyramidSellerProperty.ItemCode.IC_SERIAL_NUMBER);
            itemMap.put(PyramidSellerProperty.Item.REPRESENTATIVE, PyramidSellerProperty.ItemCode.IC_REPRESENTATIVE);
            itemMap.put(PyramidSellerProperty.Item.DATE_OF_APPROVAL, PyramidSellerProperty.ItemCode.IC_DATE_OF_APPROVAL);
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
