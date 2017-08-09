package com.github.seijuro.publicdata.api.config;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class BusinessPlaceAPIConfigTest {
    @Test
    public void testInstantiation() {
        BusinessPlaceAPIConfig config = new BusinessPlaceAPIConfig();
        assertNotNull(config);

        config.setProperty(BusinessPlaceAPIConfig.Property.ADDRESS_DG, "01");
        config.setProperty(BusinessPlaceAPIConfig.Property.ADDRESS_SGGU, "101");
        config.setProperty(BusinessPlaceAPIConfig.Property.ADDRESS_EMD, "202");

    }
}
