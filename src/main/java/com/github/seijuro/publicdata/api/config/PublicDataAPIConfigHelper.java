package com.github.seijuro.publicdata.api.config;

import lombok.AccessLevel;
import lombok.Getter;

class PublicDataAPIConfigHelper {
    static class DataGoKr {
        @Getter(AccessLevel.PACKAGE)
        static final int defaultNumberOfROws = 10;
        @Getter(AccessLevel.PACKAGE)
        static final int upperBoundNumberOfRows = 9999;
        @Getter(AccessLevel.PACKAGE)
        static final int lowwerBoundNumberOfRows = 1;

        @Getter(AccessLevel.PACKAGE)
        static final int defaultPageNo = 1;
        @Getter(AccessLevel.PACKAGE)
        static final int lowerBoundPageNo = 1;

        static <T extends Number> String getNumberOfRows(T rows) {
            int intRows = rows.intValue();
            if (intRows > getUpperBoundNumberOfRows() ||
                    intRows < getLowwerBoundNumberOfRows()) {
                return Integer.toString(getDefaultNumberOfROws());
            }

            return Integer.toString(rows.intValue());
        }

        static String getNumberOfRows(String rows) {
            try {
                Integer intRows = new Integer(rows);

                if (intRows >= getLowwerBoundNumberOfRows() &&
                        intRows <= getUpperBoundNumberOfRows()) {
                    return rows;
                }
            }
            catch (NumberFormatException excp) {
                excp.printStackTrace();
            }

            return Integer.toString(getDefaultNumberOfROws());
        }

        static <T extends Number> String getPageNot(T no) {
            int intNo = no.intValue();
            if (intNo < getLowerBoundPageNo()) {
                return Integer.toString(getDefaultPageNo());
            }

            return Integer.toString(intNo);
        }

        static String getPageNot(String no) {
            try {
                Integer intNo = new Integer(no);

                if (intNo > getLowerBoundPageNo()) { return no; }
            }
            catch (NumberFormatException excp) {
                excp.printStackTrace();
            }

            return Integer.toString(getDefaultPageNo());
        }
    }

    static class IBTK {
        static final int DEFAULT_NUM_OF_ROWS = 100;
        static final int LOWERBOUND_NUM_OF_ROWS = 1;

        static final int DEFAULT_PAGE_NO = 0;
        static final int LOWERBOUND_PAGE_NO = 0;
    }
}
