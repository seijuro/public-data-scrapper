package com.github.seijuro.publicdata.property;

public class PublicDataProperty {
    public static final String Encoding = "UTF-8";

    public static class Request {
        public static final String NUM_OF_ROWS = "numOfRows";
        public static final String PAGE_NO = "pageNo";
    }

    static class CodePrefix {
        static final int RC_PREFIX = 0x00010000;
        static final int EC_PREFIX = 0x00020000;
    }

    public static class ResultCode {
        public static final int RC_RESULT_CODE = CodePrefix.RC_PREFIX | 0x01;
        public static final int RC_RESULT_MESSAGE = CodePrefix.RC_PREFIX | 0x02;

        public static final int RC_NUM_OF_ROWS = CodePrefix.RC_PREFIX | 0x03;
        public static final int RC_PAGE_NO = CodePrefix.RC_PREFIX | 0x04;
        public static final int RC_TOTAL_COUNT = CodePrefix.RC_PREFIX | 0x05;
    }

    public static class Result {
        public static final String RESULT_CODE = "resultCode";
        public static final String RESULT_MESSAGE = "resultMsg";

        public static final String NUM_OF_ROWS = "numOfRows";
        public static final String PAGE_NO = "pageNo";
        public static final String TOTAL_COUNT = "totalCount";
    }

    public static class ErrorCode {
        public static final int ERROR_MESSAGE = CodePrefix.EC_PREFIX | 0x01;
        public static final int REASON_CODE = CodePrefix.EC_PREFIX | 0x02;
        public static final int AUTHENTICATION_MESSAGE = CodePrefix.EC_PREFIX | 0x03;
    }

    public static class Error {
        public static final String ERROR_MESSAGE = "errMsg";
        public static final String REASON_CODE = "returnReasonCode";
        public static final String AUTHENTICATION_MESSAGE = "returnAuthMsg";
    }
}
