package com.github.seijuro.publicdata.result;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RecallAPIResultTest {
    @Test
    public void testRecallAPIResult() {
        final String resultCode = "result code";
        final String resultMessage = "result message";
        final Integer pageNo = 10;
        final Integer numberOfRows = 100;
        final Integer totalCount = 2000;

        RecallAPIResult result = new RecallAPIResult(resultCode, resultMessage, pageNo, numberOfRows, totalCount);

        assertNotNull(result);

        assertEquals(resultCode, result.getResultCode());
        assertEquals(resultMessage, result.getResultMessage());
        assertEquals(pageNo, result.getPageNo());
        assertEquals(numberOfRows, result.getNumberOfRows());
        assertEquals(totalCount, result.getTotalCount());
    }
}
