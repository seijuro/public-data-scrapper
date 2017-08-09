package com.github.seijuro.publicdata.runner;

import com.github.seijuro.publicdata.PublicDataAPIServices;
import com.github.seijuro.publicdata.api.*;
import com.github.seijuro.publicdata.api.config.*;
import com.konantech.seijuro.publicdata.api.*;
import com.konantech.seijuro.publicdata.api.config.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class PublicDataAPIFactoryTest {
    final String sampleServiceKey = "test service-key";

    @Test
    public void testExceptionalAPIUsage() {
        try {
            BusinessPlaceAPIConfig config = new BusinessPlaceAPIConfig();
            PublicDataAPI api = PublicDataAPIFactory.create(null);
            api.setConfig(config);
            api.setServiceKey(sampleServiceKey);
        }
        catch (Exception excp) {
            return;
        }

        assertTrue(false);
    }

    @Test
    public void testCreateBusinessPlaceInfoAPI() {
        try {
            BusinessPlaceAPIConfig config = new BusinessPlaceAPIConfig();
            PublicDataAPI api = PublicDataAPIFactory.create(PublicDataAPIServices.NPS_BUSINESS_PLACE_NORMAL);
            api.setConfig(config);
            api.setServiceKey(sampleServiceKey);

            assertNotNull(api);
            assertEquals(BusinessPlaceInfoAPI.class, api.getClass());

            return;
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }

        assertTrue(false);
    }

    @Test
    public void testCreateBusinessPlaceDetailInfoAPI() {
        try {
            BusinessPlaceDetailAPIConfig config = new BusinessPlaceDetailAPIConfig();
            PublicDataAPI api = PublicDataAPIFactory.create(PublicDataAPIServices.NPS_BUSINESS_PLACE_DETAIL);
            api.setConfig(config);
            api.setServiceKey(sampleServiceKey);

            assertNotNull(api);
            assertEquals(BusinessPlaceDetailInfoAPI.class, api.getClass());

            return;
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }

        assertTrue(false);
    }

    @Test
    public void testCreateStatsAPI() {
        try {
            StatsAPIConfig config = new StatsAPIConfig();
            PublicDataAPI api = PublicDataAPIFactory.create(PublicDataAPIServices.NPS_STATS);
            api.setConfig(config);
            api.setServiceKey(sampleServiceKey);

            assertNotNull(api);
            assertEquals(StatsAPI.class, api.getClass());

            return;
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }

        assertTrue(false);
    }

    @Test
    public void testCreateConstructSpecificationInfoAPI() {
        try {
            SpecificationAPIConfig config = new SpecificationAPIConfig();
            PublicDataAPI api = PublicDataAPIFactory.create(PublicDataAPIServices.SPEC_CONSTRUCT);
            api.setConfig(config);
            api.setServiceKey(sampleServiceKey);

            assertNotNull(api);
            assertEquals(ConstructSpecificationInfoAPI.class, api.getClass());

            return;
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }

        assertTrue(false);
    }

    @Test
    public void testCreateForeignCapitalSpecificationInfoAPI() {
        try {
            SpecificationAPIConfig config = new SpecificationAPIConfig();
            PublicDataAPI api = PublicDataAPIFactory.create(PublicDataAPIServices.SPEC_FOREIGNCAPITAL);
            api.setConfig(config);
            api.setServiceKey(sampleServiceKey);

            assertNotNull(api);
            assertEquals(ForeignCapitalSpecificationInfoAPI.class, api.getClass());

            return;
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }

        assertTrue(false);
    }

    @Test
    public void testCreateServiceSpecificationInfoAPI() {
        try {
            SpecificationAPIConfig config = new SpecificationAPIConfig();
            PublicDataAPI api = PublicDataAPIFactory.create(PublicDataAPIServices.SPEC_SERVICE);
            api.setConfig(config);
            api.setServiceKey(sampleServiceKey);

            assertNotNull(api);
            assertEquals(ServiceSpecificationInfoAPI.class, api.getClass());

            return;
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }

        assertTrue(false);
    }

    @Test
    public void testProductServiceSpecificationInfoAPI() {
        try {
            SpecificationAPIConfig config = new SpecificationAPIConfig();
            PublicDataAPI api = PublicDataAPIFactory.create(PublicDataAPIServices.SPEC_PRODUCT);
            api.setConfig(config);
            api.setServiceKey(sampleServiceKey);

            assertNotNull(api);
            assertEquals(ProductSpecificationInfoAPI.class, api.getClass());

            return;
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }

        assertTrue(false);
    }

    @Test
    public void testRecallServiceAPI() {
        try {
            RecallAPIConfig.Builder confBuilder = new RecallAPIConfig.Builder();

            {
                RecallAPIConfig config = confBuilder.build();
                PublicDataAPI api = PublicDataAPIFactory.create(PublicDataAPIServices.RECALL);
                api.setConfig(config);
                api.setServiceKey(sampleServiceKey);

                assertNotNull(api);
                assertEquals(RecallAPI.class, api.getClass());
            }

            {
                RecallAPIConfig config = confBuilder.build();
                PublicDataAPI api = PublicDataAPIFactory.create(PublicDataAPIServices.RECALL_PAGEABLE);
                api.setConfig(config);
                api.setServiceKey(sampleServiceKey);

                assertNotNull(api);
                assertEquals(RecallAPI.class, api.getClass());
            }

            return;
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }

        assertTrue(false);
    }
}
