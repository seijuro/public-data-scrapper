package com.github.seijuro.publicdata.api.config;

import com.github.seijuro.common.IJSONConvertable;
import com.github.seijuro.common.field.IField;
import com.github.seijuro.common.field.value.IIntFieldValue;
import com.github.seijuro.publicdata.property.RecallProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

public class RecallAPIConfig extends PublicDataAPIConfig {
    /**
     * Property
     */
    public enum Property implements ConfigProperty {
        PARAM_SEARCH("model_query"),
        PARAM_FIELD_VISIBILITY("model_query_fields"),
        PARAM_DISTINCT("model_query_distinct"),
        PARAM_PAGEABLE("model_query_pageable");

        @Getter(AccessLevel.PUBLIC)
        private final String property;

        Property(String prop) {
            this.property = prop;
        }
    }

    /**
     * Condition
     */
    public static class Condition implements IJSONConvertable {
        /**
         * interface for composite
         */
        private interface Opr {
            String toOprString();
        }

        /**
         * Comparator
         *
         * comparator contains 'binary' operator, such as EQUAL, NOT_EQUALS, LIKE, GREATER_THAN, etc ...
         */
        public enum Comparator implements Opr {
            EQUAL(""),
            NOT_EQUALS("$ne"),
            LIKE("$regex"),
            GREATER_THAN("$gt"),
            LESS_THAN("$lt"),
            GREATER_THAN_OR_EQUALS("$gte"),
            LESS_THAN_OR_EQUALS("$lte"),
            IS_NOT_NULL("$ne"),
            IS_NULL("");

            private final String operator;

            Comparator(String opr) {
                this.operator = opr;
            }

            @Override
            public String toOprString() {
                return this.operator;
            }
        }

        /**
         * Combiner
         *
         * comparator contains 'and', 'or' operator
         */
        public enum Combiner implements Opr {
            AND("$and"),
            OR("$or");

            private final String operator;

            Combiner(String opr) {
                this.operator = opr;
            }

            @Override
            public String toOprString() {
                return this.operator;
            }
        }

        /**
         * Instance Properties
         */
        private final Opr opr;
        private final Field field;
        private final Object value;

        /**
         * C'tor
         *
         * @param $opr
         * @param $field
         * @param $value
         */
        public Condition (Comparator $opr, Field $field, Object $value) {
            this.opr = $opr;
            this.field = $field;
            this.value = $value;
        }

        /**
         * C'tor
         *
         * @param $opr
         * @param conds
         */
        public Condition (Combiner $opr, Set<Condition> conds) {
            this.opr = $opr;
            this.field = null;
            this.value = conds;
        }

        /**
         * implement IJSONConvertable interface.
         *
         * @return
         */
        @Override
        public JSONObject toJSONObject() {
            JSONObject jsonCondition = new JSONObject();

            if (this.opr instanceof Comparator) {
                if (this.value != null) {
                    if (this.opr == Comparator.EQUAL) {
                        jsonCondition.put(field.toString(), value);
                    }
                    else if (this.opr == Comparator.LIKE ||
                            this.opr == Comparator.GREATER_THAN ||
                            this.opr == Comparator.LESS_THAN ||
                            this.opr == Comparator.GREATER_THAN_OR_EQUALS ||
                            this.opr == Comparator.LESS_THAN_OR_EQUALS ||
                            this.opr == Comparator.NOT_EQUALS) {
                        JSONObject jsonValue = new JSONObject();
                        jsonValue.put(this.opr.toOprString(), value);
                        jsonCondition.put(this.field.toString(), jsonValue);
                    }
                }
                else if (this.opr == Comparator.IS_NULL) {
                    jsonCondition.put(this.field.toString(), null);
                }
                else if (this.opr == Comparator.IS_NOT_NULL) {
                    JSONObject jsonValue = new JSONObject();
                    jsonValue.put(this.opr.toOprString(), null);
                    jsonCondition.put(this.field.toString(), jsonValue);
                }
            }
            else if (this.opr instanceof Combiner) {
                if (this.value instanceof Set) {
                    Set<Condition> conds = (Set<Condition>)(this.value);

                    if (conds.size() > 1) {
                        JSONArray jsonConds = new JSONArray();

                        Iterator<Condition> iter = conds.iterator();

                        while (iter.hasNext()) {
                            jsonConds.add(iter.next().toJSONObject());
                        }

                        jsonCondition.put(this.opr.toOprString(), jsonConds);
                    }
                }
            }

            return jsonCondition;
        }

        /**
         * implements <code>hashCode</code> method.
         * <code>Condition</code> instance can be used with HashSet, HashMap, etc ...
         *
         * @return
         */
        @Override
        public int hashCode() {
            int code = 17;

            code = code * this.opr.toOprString().hashCode() << 31 + this.field.toString().hashCode();
            code = code << 31 + (this.value == null ? 0 : this.value.hashCode());

            return code;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Condition) {
                Condition rhs = (Condition)obj;

                if (this.opr != rhs) return false;
                if (this.field == null && rhs != null) return false;
                if (!this.field.equals(rhs.field)) return false;
                if (this.value == null && rhs.value != null) return false;
                if (!this.value.equals(rhs.value)) return false;

                return true;
            }

            return false;
        }
    }

    /**
     * enum Field
     */
    public enum Field implements IField {
        IDX(RecallProperty.Content.FieldName.IDX),
        COUNTRY_OF_MANUFACTURE(RecallProperty.Content.FieldName.COUNTRY_OF_MANUFACTURE),
        PRODUCT_NAME(RecallProperty.Content.FieldName.PRODUCT_NAME),
        TRADEMARK(RecallProperty.Content.FieldName.TRADEMARK),
        MODEL(RecallProperty.Content.FieldName.MODEL),
        SERIAL_NUMBER(RecallProperty.Content.FieldName.SERIAL_NUMBER),
        TYPE(RecallProperty.Content.FieldName.TYPE),
        COMPANY(RecallProperty.Content.FieldName.COMPANY),
        DATE_OF_ISSUE(RecallProperty.Content.FieldName.DATE_OF_ISSUE),
        DIMENSION_TYPE(RecallProperty.Content.FieldName.DIMENSION_TYPE);

        private final String fieldName;

        Field(String name) {
            this.fieldName = name;
        }

        @Override
        public String getFieldName() {
            return this.fieldName;
        }
    }

    /**
     * enum Direction
     */
    public enum Direction implements IIntFieldValue {
        ASC(1),
        DESC(-1);

        private final int value;

        Direction(int $value) {
            this.value = $value;
        }

        @Override
        public Integer getIntegerValue() { return this.value; }
        @Override
        public int getIntValue() { return this.value; }
    }

    /**
     * enum Visibility
     */
    public enum Visibility implements IIntFieldValue {
        VISIBLE(1),
        INVISIBLE(0);

        private final int flag;

        Visibility(int $flag) {
            this.flag = $flag;
        }

        @Override
        public int getIntValue() {
            return this.flag;
        }
        @Override
        public Integer getIntegerValue() {
            return this.flag;
        }
    }

    /**
     * enum SortOrder
     */
    private enum SortOrder implements IField {
        PROPERTY("property"),
        DIRECTION("direction");

        /**
         * Instance Property
         */
        private final String fieldName;

        /**
         * C'tor
         *
         * @param name
         */
        SortOrder(String name) {
            this.fieldName = name;
        }

        @Override
        public String getFieldName() {
            return this.fieldName;
        }
    }

    /**
     * enum Pageable
     */
    private enum Pageable implements IField {
        ENABLE("enable"),
        PAGE_NUMBER("pageNumber"),
        PAGE_SIZE("pageSize"),
        SORT_ORDERS("sortOrders");

        public static Boolean DEFAULT_ENABLE = new Boolean(true);
        public static Integer DEFAULT_PAGESIZE = new Integer(10);
        public static Integer DEFAULT_PAGENUMBER = new Integer(0);

        /**
         * Instance Property
         */
        private final String fieldName;

        /**
         * C'tor
         *
         * @param name
         */
        Pageable(String name) {
            this.fieldName = name;
        }

        @Override
        public String getFieldName() {
            return this.fieldName;
        }
    }

    /**
     * C'tor
     */
    protected RecallAPIConfig(Builder builder) {
        super();

        Properties props = getProperties();

        if (builder.query instanceof JSONObject) { props.put(Property.PARAM_SEARCH.getProperty(), builder.query.toJSONString()); }
        if (builder.pageable instanceof JSONObject) { props.put(Property.PARAM_PAGEABLE.getProperty(), builder.pageable.toJSONString()); }
        if (builder.visibility instanceof JSONObject) { props.put(Property.PARAM_FIELD_VISIBILITY.getProperty(), builder.visibility.toJSONString()); }
        if (builder.distinct instanceof Field) { props.put(Property.PARAM_DISTINCT.getProperty(), builder.distinct.toString()); }
    }

    @Override
    public String toString() {
        Properties props = getProperties();
        Enumeration e = props.propertyNames();
        boolean isFirst = true;

        StringBuffer sb = new StringBuffer("recall config := {");
        while (e.hasMoreElements()) {
            if (isFirst) { isFirst =false; }
            else { sb.append(", "); }

            Object prop = e.nextElement();
            sb.append(prop.toString()).append(" : ").append(props.get(prop));
        }

        sb.append("}");

        return sb.toString();
    }


    /**
     * Builder Pattern class
     */
    @ToString
    public static class Builder {
        /**
         * Instance Properties
         */
        JSONObject visibility = null;
        JSONObject pageable = null;
        JSONObject query = null;
        Field distinct = null;

        /**
         * C'tor
         */
        public Builder() {
            // set default(s)
            this.pageable = new JSONObject();

            this.pageable.put(Pageable.ENABLE, Pageable.DEFAULT_ENABLE);
            this.pageable.put(Pageable.PAGE_SIZE, Pageable.DEFAULT_PAGESIZE);
            this.pageable.put(Pageable.PAGE_NUMBER, Pageable.DEFAULT_PAGENUMBER);
        }

        /**
         * get visibility
         *
         * @return
         */
        public JSONObject getVisibility() {
            return this.visibility;
        }

        public JSONObject getPageable() {
            return this.pageable;
        }

        public JSONObject getQuery() {
            return this.query;
        }

        public Field getDistinct() {
            return this.distinct;
        }

        /**
         * set distinct
         *
         * @param field
         * @return
         */
        public Builder setDistinct(Field field) {
            this.distinct = field;

            return this;
        }

        /**
         * set 'query'
         *
         * @param condition
         * @return
         */
        public Builder setQuery(Condition condition) {
            this.query = condition.toJSONObject();

            return this;
        }

        /**
         * set visibility
         *
         * @param fields
         * @param $visibility
         * @return
         */
        public Builder setVisibility(Set<Field> fields, Visibility $visibility) {
            setVisibility(new ArrayList<>(fields), $visibility);

            return this;
        }

        /**
         * set visibility
         *
         * @param fields
         * @param $visibility
         * @return
         */
        public Builder setVisibility(Field[] fields, Visibility $visibility) {
            assert(fields != null);

            setVisibility(Arrays.asList(fields), $visibility);

            return this;
        }

        /**
         * set visibility #3
         *
         * @param fields
         * @param $visibility
         * @return
         */
        public Builder setVisibility(List<Field> fields, Visibility $visibility) {
            JSONObject jsonVisibility = new JSONObject();

            for (Field field : fields) {
                jsonVisibility.put(field.getFieldName(), $visibility.getIntegerValue());
            }

            this.visibility = jsonVisibility;

            return this;
        }

        public Builder setPageableEnabled(boolean flag) {
            this.pageable.put(Pageable.ENABLE.getFieldName(), new Boolean(flag));

            return this;
        }

        /**
         * set page number
         *
         * @param page
         * @return
         */
        public Builder setPageablePageNumber(int page) {
            this.pageable.put(Pageable.PAGE_NUMBER.getFieldName(), new Integer(page > 0 ? page : 0));

            return this;
        }

        /**
         * set 'page size'
         *
         * @param size
         * @return
         */
        public Builder setPageablePageSize(int size) {
            this.pageable.put(Pageable.PAGE_SIZE.getFieldName(), new Integer(size > 0 ? size : 10));

            return this;
        }

        /**
         * set 'sorting order'
         *
         * @param orders
         * @return
         */
        public Builder setPageableSortOrders(Map<Field, Direction> orders) {
            JSONArray jsonOrders = new JSONArray();

            for (Map.Entry<Field, Direction> order : orders.entrySet()) {
                JSONObject jsonOrder = new JSONObject();
                jsonOrder.put(SortOrder.PROPERTY.getFieldName(), order.getKey().toString());
                jsonOrder.put(SortOrder.DIRECTION.getFieldName(), new Integer(order.getValue().getIntValue()));

                jsonOrders.add(jsonOrder);
            }

            this.pageable.put(Pageable.SORT_ORDERS.getFieldName(), jsonOrders);

            return this;
        }

        /**
         * Builder pattern method
         *
         * @return
         */
        public RecallAPIConfig build() {
            return new RecallAPIConfig(this);
        }
    }

}
