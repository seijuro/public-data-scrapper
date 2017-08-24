package com.github.seijuro.publicdata.property;

import java.util.HashMap;
import java.util.Map;

public class NPSReportPropertyUtils {
    public static class Item {
        static final Map<String, Integer> itemMap;

        static {
            itemMap = new HashMap<>();

            itemMap.put(NPSReportProperty.Item.SEQUENCEID, NPSReportProperty.ItemCode.IC_SEQUENCEID);
            itemMap.put(NPSReportProperty.Item.CREATE_YM, NPSReportProperty.ItemCode.IC_CREATE_YM);
            itemMap.put(NPSReportProperty.Item.EMPLOYEED_PERSON_COUNT, NPSReportProperty.ItemCode.IC_EMPLOYEED_PERSON_COUNT);
            itemMap.put(NPSReportProperty.Item.RETIRED_PERSON_COUNT, NPSReportProperty.ItemCode.IC_RETIRED_PERSON_COUNT);
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
