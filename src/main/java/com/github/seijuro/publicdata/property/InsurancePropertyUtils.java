package com.github.seijuro.publicdata.property;

import java.util.HashMap;
import java.util.Map;

public class InsurancePropertyUtils {
    public static class Item {
        static final Map<String, Integer> itemMap;

        static {
            itemMap = new HashMap<>();

            itemMap.put(InsuranceProperty.Item.ADDRESS, InsuranceProperty.ItemCode.IC_ADDRESS);
            itemMap.put(InsuranceProperty.Item.INSURANCE_TYPE, InsuranceProperty.ItemCode.IC_INSURANCE_TYPE);
            itemMap.put(InsuranceProperty.Item.THE_NUMBER_OF_REGURAL_EMPLOYMENT, InsuranceProperty.ItemCode.IC_THE_NUMBER_OF_REGURAL_EMPLOYMENT);
            itemMap.put(InsuranceProperty.Item.DATE_OF_EMPLOYMENT, InsuranceProperty.ItemCode.IC_DATE_OF_EMPLOYMENT);
            itemMap.put(InsuranceProperty.Item.ZIPCODE, InsuranceProperty.ItemCode.IC_ZIPCODE);
            itemMap.put(InsuranceProperty.Item.COMPANYNAME, InsuranceProperty.ItemCode.IC_COMPANYNAME);
            itemMap.put(InsuranceProperty.Item.THE_NUMBER_OF_INSURED, InsuranceProperty.ItemCode.IC_THE_NUMBER_OF_INSURED);
            itemMap.put(InsuranceProperty.Item.DATE_OF_INSURED, InsuranceProperty.ItemCode.IC_DATE_OF_INSURED);
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
