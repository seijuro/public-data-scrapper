package com.github.seijuro.publicdata.property;

import java.util.HashMap;
import java.util.Map;

public class PyramidSellingPropertyUtils {
    public static class Item {
        static final Map<String, Integer> itemMap;

        static {
            itemMap = new HashMap<>();

            itemMap.put(PyramidSellingProperty.Item.SEQUENCE_ID, PyramidSellingProperty.ItemCode.IC_SEQUENCE_ID);
            itemMap.put(PyramidSellingProperty.Item.COMPANY_NAME, PyramidSellingProperty.ItemCode.IC_COMPANY_NAME);
            itemMap.put(PyramidSellingProperty.Item.SERIAL_NUMBER, PyramidSellingProperty.ItemCode.IC_SERIAL_NUMBER);
            itemMap.put(PyramidSellingProperty.Item.REPRESENTATIVE, PyramidSellingProperty.ItemCode.IC_REPRESENTATIVE);
            itemMap.put(PyramidSellingProperty.Item.DATE_OF_APPROVAL, PyramidSellingProperty.ItemCode.IC_DATE_OF_APPROVAL);
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
