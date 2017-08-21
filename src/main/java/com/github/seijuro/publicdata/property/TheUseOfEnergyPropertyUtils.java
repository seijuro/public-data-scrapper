package com.github.seijuro.publicdata.property;

import java.util.HashMap;
import java.util.Map;

public class TheUseOfEnergyPropertyUtils {
    public static class Item {
        static final Map<String, Integer> itemMap;

        static {
            itemMap = new HashMap<>();

            itemMap.put(TheUseOfEnergyProperty.Item.ADDRESSCODE_DG_SGGU, TheUseOfEnergyProperty.ItemCode.IC_ADDRESSCODE_DG_SGGU);
            itemMap.put(TheUseOfEnergyProperty.Item.ADDRESSCODE_EMD, TheUseOfEnergyProperty.ItemCode.IC_ADDRESSCODE_EMD);
            itemMap.put(TheUseOfEnergyProperty.Item.TYPECODE_LAND, TheUseOfEnergyProperty.ItemCode.IC_TYPECODE_LAND);
            itemMap.put(TheUseOfEnergyProperty.Item.ADDRESSCODE_JIBUN_MOAJOR, TheUseOfEnergyProperty.ItemCode.IC_ADDRESSCODE_JIBUN_MOAJOR);
            itemMap.put(TheUseOfEnergyProperty.Item.ADDRESSCODE_JIBUN_MINOR, TheUseOfEnergyProperty.ItemCode.IC_ADDRESSCODE_JIBUN_MINOR);
            itemMap.put(TheUseOfEnergyProperty.Item.ADDRESSCODE_ROAD, TheUseOfEnergyProperty.ItemCode.IC_ADDRESSCODE_ROAD);
            itemMap.put(TheUseOfEnergyProperty.Item.TYPECODE_GROUND, TheUseOfEnergyProperty.ItemCode.IC_TYPECODE_GROUND);
            itemMap.put(TheUseOfEnergyProperty.Item.ADDRESSNUMBER_MAJOR, TheUseOfEnergyProperty.ItemCode.IC_ADDRESSNUMBER_MAJOR);
            itemMap.put(TheUseOfEnergyProperty.Item.ADDRESSNUMBER_MINOR, TheUseOfEnergyProperty.ItemCode.IC_ADDRESSNUMBER_MINOR);
            itemMap.put(TheUseOfEnergyProperty.Item.USE_QUANTITY, TheUseOfEnergyProperty.ItemCode.IC_USE_QUANTITY);
            itemMap.put(TheUseOfEnergyProperty.Item.RECORD_NUMBER, TheUseOfEnergyProperty.ItemCode.IC_RECORD_NUMBER);
            itemMap.put(TheUseOfEnergyProperty.Item.USE_YM, TheUseOfEnergyProperty.ItemCode.IC_USE_YM);
            itemMap.put(TheUseOfEnergyProperty.Item.ADDRESS, TheUseOfEnergyProperty.ItemCode.IC_ADDRESS);
            itemMap.put(TheUseOfEnergyProperty.Item.ADDRESS_ROAD, TheUseOfEnergyProperty.ItemCode.IC_ADDRESS_ROAD);
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
