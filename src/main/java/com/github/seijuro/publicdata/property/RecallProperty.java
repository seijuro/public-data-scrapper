package com.github.seijuro.publicdata.property;

import lombok.ToString;

@ToString
public class RecallProperty {
    public static final String ENABLE = "enable";
    public static final String NUMBER = "number";
    public static final String SIZE = "size";
    public static final String TOTAL_PAGES = "totalPages";
    public static final String NUMBER_OF_ELEMENTS = "numberOfElements";
    public static final String TOTAL_ELEMENTS = "totalElements";
    public static final String HAS_PREVIOUS_PAGE = "hasPreviousPage";
    public static final String HAS_NEXT_PAGE = "hasNextPage";
    public static final String IS_FIRST_PAGE = "isFirstPage";
    public static final String IS_LAST_PAGE = "isLastPage";
    public static final String HAS_CONTENTS = "hasContent";
    public static final String BEGIN_PAGE = "beginPage";
    public static final String END_PAGE = "endPage";
    public static final String PREVIOUS_PAGE = "previousPage";
    public static final String NEXT_PAGE = "nextPage";
    public static final String CONTENT = "content";
    public static final String SORT = "sort";
    public static final String STATUS = "status";
    public static final String PAGE_NUMBER = "pageNumber";
    public static final String PAGE_SIZE = "pageSize";
    public static final String FIRST_PAGE = "firstPage";
    public static final String LAST_PAGE = "lastPage";

    public static class Content {
        public class FieldName {
            public static final String IDX = "idx";
            public static final String COUNTRY_OF_MANUFACTURE = "makingNation";
            public static final String PRODUCT_NAME = "productName";
            public static final String TRADEMARK = "trademark";
            public static final String MODEL = "model";
            public static final String SERIAL_NUMBER = "serialNumber";
            public static final String TYPE = "recallType";
            public static final String COMPANY = "companyName";
            public static final String DATE_OF_ISSUE = "signDate";
            public static final String DIMENSION_TYPE = "recallNationType";
            public static final String Extra = "_id";
        }

        public class Extra {
            public static final String TIME = "time";
            public static final String NEW = "new";
            public static final String MACHINE = "machine";
            public static final String TIMESECOND = "timeSecond";
            public static final String INC = "inc";
        }
    }
}
