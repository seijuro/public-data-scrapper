package com.github.seijuro.publicdata.property;

import java.util.HashMap;
import java.util.Map;

public class BusinessPlaceDetailPropertyUtils {
    public static class Item {
        static final Map<String, Integer> itemMap;

        static {
            itemMap = new HashMap<>();

            itemMap.put(BusinessPlaceDetailProperty.Item.SEQUENCEID, BusinessPlaceDetailProperty.ItemCode.IC_SEQUENCEID);
            itemMap.put(BusinessPlaceDetailProperty.Item.TYPE, BusinessPlaceDetailProperty.ItemCode.IC_TYPECODE);
            itemMap.put(BusinessPlaceDetailProperty.Item.REGISTRATIONDATE, BusinessPlaceDetailProperty.ItemCode.IC_REGISTRATIONDATE);
            itemMap.put(BusinessPlaceDetailProperty.Item.WITHDRAWALDATE, BusinessPlaceDetailProperty.ItemCode.IC_WITHDRAWALDATE);
            itemMap.put(BusinessPlaceDetailProperty.Item.NUMBEROFSUBSCRIBER, BusinessPlaceDetailProperty.ItemCode.IC_NUMBEROFSUBSCRIBER);
            itemMap.put(BusinessPlaceDetailProperty.Item.NOTIFIEDAMOOUNT, BusinessPlaceDetailProperty.ItemCode.IC_NOTIFIEDAMOOUNT);
            itemMap.put(BusinessPlaceDetailProperty.Item.NAME, BusinessPlaceDetailProperty.ItemCode.IC_NAME);
            itemMap.put(BusinessPlaceDetailProperty.Item.REGISTRATIONNUMBER, BusinessPlaceDetailProperty.ItemCode.IC_REGISTRATIONNUMBER);
            itemMap.put(BusinessPlaceDetailProperty.Item.ADDRESS_ROAD, BusinessPlaceDetailProperty.ItemCode.IC_ADDRESS_ROAD);
            itemMap.put(BusinessPlaceDetailProperty.Item.STATUSCODE, BusinessPlaceDetailProperty.ItemCode.IC_STATUSCODE);
            itemMap.put(BusinessPlaceDetailProperty.Item.ADDRESSCODE_DG, BusinessPlaceDetailProperty.ItemCode.IC_ADDRESSCODE_DG);
            itemMap.put(BusinessPlaceDetailProperty.Item.ADDRESSCODE_SGGU, BusinessPlaceDetailProperty.ItemCode.IC_ADDRESSCODE_SGGU);
            itemMap.put(BusinessPlaceDetailProperty.Item.ADDRESSCODE_EMD, BusinessPlaceDetailProperty.ItemCode.IC_ADDRESSCODE_EMD);
            itemMap.put(BusinessPlaceDetailProperty.Item.DIVISION, BusinessPlaceDetailProperty.ItemCode.IC_DIVISION);
            itemMap.put(BusinessPlaceDetailProperty.Item.TYPECODE, BusinessPlaceDetailProperty.ItemCode.IC_TYPECODE);

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
