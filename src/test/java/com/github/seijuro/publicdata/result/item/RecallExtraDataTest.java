package com.github.seijuro.publicdata.result.item;

import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecallExtraDataTest {
    @Test
    public void testCreateAndGetter() {
        /*String $time, String $new, String $machine, String $timeSecond, String $inc*/
        final String sampleText = "{\"time\":1417069728000,\"new\":false,\"machine\":-458209134,\"timeSecond\":1417069728,\"inc\":202048579}";

        try {
            Gson gson = new Gson();
            RecallExtraData extra = gson.fromJson(sampleText, RecallExtraData.class);

            assertNotNull(extra.get_new());
            assertNotNull(extra.getTime());
            assertNotNull(extra.getMachine());
            assertNotNull(extra.getTimeSecond());
            assertNotNull(extra.getInc());

            assertEquals(1417069728000L, Long.parseLong(extra.getTime()));
            assertEquals(false, Boolean.parseBoolean(extra.get_new()));
            assertEquals(-458209134, Integer.parseInt(extra.getMachine()));
            assertEquals(1417069728L, Long.parseLong(extra.getTimeSecond()));
            assertEquals(202048579L, Long.parseLong(extra.getInc()));

            return;
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }

        assertTrue(false);
    }
}