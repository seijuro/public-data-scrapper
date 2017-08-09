package com.github.seijuro.publicdata.property;

import java.util.HashMap;
import java.util.Map;

public class PublicDataPropertyUtils {
    public static class Result {
        static final Map<String, Integer> ResultMap;

        static {
            ResultMap = new HashMap<>();

            ResultMap.put(PublicDataProperty.Result.RESULT_CODE, PublicDataProperty.ResultCode.RC_RESULT_CODE);
            ResultMap.put(PublicDataProperty.Result.RESULT_MESSAGE, PublicDataProperty.ResultCode.RC_RESULT_MESSAGE);
            ResultMap.put(PublicDataProperty.Result.NUM_OF_ROWS, PublicDataProperty.ResultCode.RC_NUM_OF_ROWS);
            ResultMap.put(PublicDataProperty.Result.PAGE_NO, PublicDataProperty.ResultCode.RC_PAGE_NO);
            ResultMap.put(PublicDataProperty.Result.TOTAL_COUNT, PublicDataProperty.ResultCode.RC_TOTAL_COUNT);
        }

        public static boolean contains(String key) {
            return ResultMap.containsKey(key);
        }

        public static int getCode(String key, int defaultValue) {
            Integer val = ResultMap.get(key);

            if (val instanceof Integer) {
                return val;
            }

            return defaultValue;
        }
    }

    public static class Error {
        static final Map<String, Integer> ErrorMap;

        static {
            ErrorMap = new HashMap<>();

            ErrorMap.put(PublicDataProperty.Error.ERROR_MESSAGE, PublicDataProperty.ErrorCode.ERROR_MESSAGE);
            ErrorMap.put(PublicDataProperty.Error.REASON_CODE, PublicDataProperty.ErrorCode.REASON_CODE);
            ErrorMap.put(PublicDataProperty.Error.AUTHENTICATION_MESSAGE, PublicDataProperty.ErrorCode.AUTHENTICATION_MESSAGE);
        }

        public static boolean contains(String key) {
            return ErrorMap.containsKey(key);
        }

        public static int getCode(String key, int defaultValue) {
            Integer val = ErrorMap.get(key);

            if (val instanceof Integer) {
                return val;
            }

            return defaultValue;
        }
    }
}
