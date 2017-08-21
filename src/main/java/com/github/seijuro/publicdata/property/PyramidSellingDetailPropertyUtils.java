package com.github.seijuro.publicdata.property;

import java.util.HashMap;
import java.util.Map;

public class PyramidSellingDetailPropertyUtils {
    public static class Item {
        static final Map<String, Integer> itemMap;

        static {
            itemMap = new HashMap<>();

            itemMap.put(PyramidSellingDetailProperty.Item.SEQUENCE_ID, PyramidSellingDetailProperty.ItemCode.IC_SEQUENCE_ID);
            itemMap.put(PyramidSellingDetailProperty.Item.COMPANY_NAME, PyramidSellingDetailProperty.ItemCode.IC_COMPANY_NAME);
            itemMap.put(PyramidSellingDetailProperty.Item.SERIAL_NUMBER, PyramidSellingDetailProperty.ItemCode.IC_SERIAL_NUMBER);
            itemMap.put(PyramidSellingDetailProperty.Item.REPRESENTATIVE, PyramidSellingDetailProperty.ItemCode.IC_REPRESENTATIVE);
            itemMap.put(PyramidSellingDetailProperty.Item.DATE_OF_APPROVAL, PyramidSellingDetailProperty.ItemCode.IC_DATE_OF_APPROVAL);
            itemMap.put(PyramidSellingDetailProperty.Item.STATUS, PyramidSellingDetailProperty.ItemCode.IC_STATUS);
            itemMap.put(PyramidSellingDetailProperty.Item.ADDRESS, PyramidSellingDetailProperty.ItemCode.IC_ADDRESS);
            itemMap.put(PyramidSellingDetailProperty.Item.CAPITTAL, PyramidSellingDetailProperty.ItemCode.IC_CAPITTAL);
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
