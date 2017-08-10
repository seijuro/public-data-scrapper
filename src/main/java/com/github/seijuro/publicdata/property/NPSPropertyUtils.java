package com.github.seijuro.publicdata.property;

import java.util.HashMap;
import java.util.Map;

public class NPSPropertyUtils {
    public static class Item {
        static final Map<String, Integer> itemMap;

        static {
            itemMap = new HashMap<>();

            itemMap.put(NPSProperty.Item.Normal.CREATED_YM, NPSProperty.ItemCode.Normal.IC_CREATED_YM);
            itemMap.put(NPSProperty.Item.Normal.ID , NPSProperty.ItemCode.Normal.IC_ID);
            itemMap.put(NPSProperty.Item.Normal.BUSINESSPLACE_NAME, NPSProperty.ItemCode.Normal.IC_BUSINESSPLACE_NAME);
            itemMap.put(NPSProperty.Item.Normal.REGISTRATION_NUMBER , NPSProperty.ItemCode.Normal.IC_REGISTRATION_NUMBER);
            itemMap.put(NPSProperty.Item.Normal.ADDRESS_ROAD, NPSProperty.ItemCode.Normal.IC_ADDRESS_ROAD);
            itemMap.put(NPSProperty.Item.Normal.STATUS_CODE, NPSProperty.ItemCode.Normal.IC_STATUS_CODE);
            itemMap.put(NPSProperty.Item.Normal.DIVISION_CODE, NPSProperty.ItemCode.Normal.IC_DIVISION_CODE);
            itemMap.put(NPSProperty.Item.Normal.ADDRESS_DG, NPSProperty.ItemCode.Normal.IC_ADDRESS_DG);
            itemMap.put(NPSProperty.Item.Normal.ADDRESS_SGGU, NPSProperty.ItemCode.Normal.IC_ADDRESS_SGGU);
            itemMap.put(NPSProperty.Item.Normal.ADDRESS_EMD, NPSProperty.ItemCode.Normal.IC_ADDRESS_EMD);

            itemMap.put(NPSProperty.Item.Detail.BUSINESSTYPE_NAME, NPSProperty.ItemCode.Detail.IC_BUSINESSTYPE_NAME);
            itemMap.put(NPSProperty.Item.Detail.BUSINESSTYPE_CODE, NPSProperty.ItemCode.Detail.IC_BUSINESSTYPE_CODE);
            itemMap.put(NPSProperty.Item.Detail.REGISTRATION_DATE, NPSProperty.ItemCode.Detail.IC_REGISTRATION_DATE);
            itemMap.put(NPSProperty.Item.Detail.WITHDRAWAL_DATE, NPSProperty.ItemCode.Detail.IC_WITHDRAWAL_DATE);
            itemMap.put(NPSProperty.Item.Detail.SUBSCRIBER_NUMBER, NPSProperty.ItemCode.Detail.IC_SUBSCRIBER_NUMBER);
            itemMap.put(NPSProperty.Item.Detail.NOTIFIED_AMOUNT_OF_THIS_MONTH, NPSProperty.ItemCode.Detail.IC_NOTIFIED_AMOUNT_OF_THIS_MONTH);

            itemMap.put(NPSProperty.Item.Stats.MONTHLY_EMPLOYMENT, NPSProperty.ItemCode.Stats.IC_MONTHLY_EMPLOYMENT);
            itemMap.put(NPSProperty.Item.Stats.MONTHLY_RETIREMENT, NPSProperty.ItemCode.Stats.IC_MONTHLY_RETIREMENT);
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
