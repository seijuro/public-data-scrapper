package com.github.seijuro.publicdata.api.config;

import com.github.seijuro.publicdata.property.RecallProperty;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RecallAPIConfigTest {
    @Test
    public void testProperty() {
        RecallAPIConfig.Property propSearch = RecallAPIConfig.Property.PARAM_SEARCH;
        RecallAPIConfig.Property propVisible = RecallAPIConfig.Property.PARAM_FIELD_VISIBILITY;
        RecallAPIConfig.Property propDistinct = RecallAPIConfig.Property.PARAM_DISTINCT;
        RecallAPIConfig.Property propPageable = RecallAPIConfig.Property.PARAM_PAGEABLE;

        assertEquals("model_query", RecallAPIConfig.Property.PARAM_SEARCH.getProperty());
        assertEquals("model_query_fields", RecallAPIConfig.Property.PARAM_FIELD_VISIBILITY.getProperty());
        assertEquals("model_query_distinct", RecallAPIConfig.Property.PARAM_DISTINCT.getProperty());
        assertEquals("model_query_pageable", RecallAPIConfig.Property.PARAM_PAGEABLE.getProperty());
    }

    @Test
    public void testFields() {
        RecallAPIConfig.Field fieldIDX = RecallAPIConfig.Field.IDX;
        RecallAPIConfig.Field fieldCOM = RecallAPIConfig.Field.COUNTRY_OF_MANUFACTURE;
        RecallAPIConfig.Field fieldPN = RecallAPIConfig.Field.PRODUCT_NAME;
        RecallAPIConfig.Field fieldTM = RecallAPIConfig.Field.TRADEMARK;
        RecallAPIConfig.Field fieldMODEL = RecallAPIConfig.Field.MODEL;
        RecallAPIConfig.Field fieldSN = RecallAPIConfig.Field.SERIAL_NUMBER;
        RecallAPIConfig.Field fieldTYPE = RecallAPIConfig.Field.TYPE;
        RecallAPIConfig.Field fieldCP = RecallAPIConfig.Field.COMPANY;
        RecallAPIConfig.Field fieldDOI = RecallAPIConfig.Field.DATE_OF_ISSUE;
        RecallAPIConfig.Field fieldDT = RecallAPIConfig.Field.DIMENSION_TYPE;

        assertEquals(RecallProperty.Content.FieldName.IDX, fieldIDX.getFieldName());
        assertEquals(RecallProperty.Content.FieldName.COUNTRY_OF_MANUFACTURE, fieldCOM.getFieldName());
        assertEquals(RecallProperty.Content.FieldName.PRODUCT_NAME, fieldPN.getFieldName());
        assertEquals(RecallProperty.Content.FieldName.TRADEMARK, fieldTM.getFieldName());
        assertEquals(RecallProperty.Content.FieldName.MODEL, fieldMODEL.getFieldName());
        assertEquals(RecallProperty.Content.FieldName.SERIAL_NUMBER, fieldSN.getFieldName());
        assertEquals(RecallProperty.Content.FieldName.TYPE, fieldTYPE.getFieldName());
        assertEquals(RecallProperty.Content.FieldName.COMPANY, fieldCP.getFieldName());
        assertEquals(RecallProperty.Content.FieldName.DATE_OF_ISSUE, fieldDOI.getFieldName());
        assertEquals(RecallProperty.Content.FieldName.DIMENSION_TYPE, fieldDT.getFieldName());
    }

    @Test
    public void testDirection() {
        assertEquals(1, RecallAPIConfig.Direction.ASC.getIntValue());
        assertEquals(-1, RecallAPIConfig.Direction.DESC.getIntValue());

        assertEquals(new Integer(1), RecallAPIConfig.Direction.ASC.getIntegerValue());
        assertEquals(new Integer(-1), RecallAPIConfig.Direction.DESC.getIntegerValue());
    }

    @Test
    public void testVisibility() {
        assertEquals(1, RecallAPIConfig.Visibility.VISIBLE.getIntValue());
        assertEquals(0, RecallAPIConfig.Visibility.INVISIBLE.getIntValue());

        assertEquals(new Integer(1), RecallAPIConfig.Visibility.VISIBLE.getIntegerValue());
        assertEquals(new Integer(0), RecallAPIConfig.Visibility.INVISIBLE.getIntegerValue());
    }

    @Test
    public void testBuilder() {
        RecallAPIConfig.Builder builder = new RecallAPIConfig.Builder();

        assertNotNull(builder);

        //  visibility #1
        {
            builder.setVisibility(Arrays.asList(RecallAPIConfig.Field.COMPANY), RecallAPIConfig.Visibility.VISIBLE);

            JSONObject jsonVisibility = builder.getVisibility();

            assertNotNull(jsonVisibility.get(RecallAPIConfig.Field.COMPANY.getFieldName()));

            assertEquals(RecallAPIConfig.Visibility.VISIBLE.getIntegerValue(), jsonVisibility.get(RecallAPIConfig.Field.COMPANY.getFieldName()));
        }

        //  visiblity #2
        {
            builder.setVisibility(new RecallAPIConfig.Field[] {RecallAPIConfig.Field.IDX, RecallAPIConfig.Field.COMPANY}, RecallAPIConfig.Visibility.VISIBLE);

            JSONObject jsonVisibility = builder.getVisibility();

            assertNotNull(jsonVisibility.get(RecallAPIConfig.Field.IDX.getFieldName()));
            assertNotNull(jsonVisibility.get(RecallAPIConfig.Field.COMPANY.getFieldName()));

            assertEquals(RecallAPIConfig.Visibility.VISIBLE.getIntegerValue(), jsonVisibility.get(RecallAPIConfig.Field.IDX.getFieldName()));
            assertEquals(RecallAPIConfig.Visibility.VISIBLE.getIntegerValue(), jsonVisibility.get(RecallAPIConfig.Field.COMPANY.getFieldName()));
        }

        //  visibility #3
        {
            Set<RecallAPIConfig.Field> fields = new HashSet();
            fields.add(RecallAPIConfig.Field.IDX);
            fields.add(RecallAPIConfig.Field.COMPANY);
            fields.add(RecallAPIConfig.Field.SERIAL_NUMBER);

            builder.setVisibility(fields, RecallAPIConfig.Visibility.VISIBLE);

            JSONObject jsonVisibility = builder.getVisibility();

            assertNotNull(jsonVisibility.get(RecallAPIConfig.Field.IDX.getFieldName()));
            assertNotNull(jsonVisibility.get(RecallAPIConfig.Field.COMPANY.getFieldName()));
            assertNotNull(jsonVisibility.get(RecallAPIConfig.Field.SERIAL_NUMBER.getFieldName()));

            assertEquals(RecallAPIConfig.Visibility.VISIBLE.getIntegerValue(), jsonVisibility.get(RecallAPIConfig.Field.IDX.getFieldName()));
            assertEquals(RecallAPIConfig.Visibility.VISIBLE.getIntegerValue(), jsonVisibility.get(RecallAPIConfig.Field.COMPANY.getFieldName()));
            assertEquals(RecallAPIConfig.Visibility.VISIBLE.getIntegerValue(), jsonVisibility.get(RecallAPIConfig.Field.SERIAL_NUMBER.getFieldName()));
        }
    }
}
