package com.github.seijuro.publicdata.property;

import java.util.HashMap;
import java.util.Map;

public class PyramidSellerDetailPropertyUtils {
    public static class Item {
        static final Map<String, Integer> itemMap;

        static {
            itemMap = new HashMap<>();

            itemMap.put(PyramidSellerDetailProperty.Item.SEQUENCE_ID, PyramidSellerDetailProperty.ItemCode.IC_SEQUENCE_ID);
            itemMap.put(PyramidSellerDetailProperty.Item.COMPANY_NAME, PyramidSellerDetailProperty.ItemCode.IC_COMPANY_NAME);
            itemMap.put(PyramidSellerDetailProperty.Item.SERIAL_NUMBER, PyramidSellerDetailProperty.ItemCode.IC_SERIAL_NUMBER);
            itemMap.put(PyramidSellerDetailProperty.Item.REPRESENTATIVE, PyramidSellerDetailProperty.ItemCode.IC_REPRESENTATIVE);
            itemMap.put(PyramidSellerDetailProperty.Item.DATE_OF_APPROVAL, PyramidSellerDetailProperty.ItemCode.IC_DATE_OF_APPROVAL);
            itemMap.put(PyramidSellerDetailProperty.Item.STATUS, PyramidSellerDetailProperty.ItemCode.IC_STATUS);
            itemMap.put(PyramidSellerDetailProperty.Item.ADDRESS, PyramidSellerDetailProperty.ItemCode.IC_ADDRESS);
            itemMap.put(PyramidSellerDetailProperty.Item.CAPITTAL, PyramidSellerDetailProperty.ItemCode.IC_CAPITTAL);
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
