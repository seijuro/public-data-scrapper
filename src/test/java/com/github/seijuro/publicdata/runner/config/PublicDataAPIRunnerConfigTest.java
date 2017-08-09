package com.github.seijuro.publicdata.runner.config;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class PublicDataAPIRunnerConfigTest {
    @Test
    public void testSetGet() {
        final int threadPoolSize = 10;
        final long awaitTermMillis = 10 * DateUtils.MILLIS_PER_SECOND;

        int retThreadPoolSize = Integer.MIN_VALUE;
        long retAwaitTermMillis = Long.MIN_VALUE;

        PublicDataAPIRunnerConfig config = new PublicDataAPIRunnerConfig();
        config.setAwaitTermMillis(awaitTermMillis);
        config.setThreadPoolSize(threadPoolSize);

        {
            retThreadPoolSize = config.get(PublicDataAPIRunnerConfig.Property.THREADPOOL_SIZE, Integer.class);
            retAwaitTermMillis = config.get(PublicDataAPIRunnerConfig.Property.AWAITTERM_MILLIS, Long.class);

            assertEquals(threadPoolSize, retThreadPoolSize);
            assertEquals(awaitTermMillis, retAwaitTermMillis);
        }

        config.setAwaitTermMillis(-1);
        config.setThreadPoolSize(-1);

        {
            retThreadPoolSize = config.get(PublicDataAPIRunnerConfig.Property.THREADPOOL_SIZE, Integer.class);
            retAwaitTermMillis = config.get(PublicDataAPIRunnerConfig.Property.AWAITTERM_MILLIS, Long.class);

            assertEquals(PublicDataAPIRunnerConfig.getDefaultThreadPoolSize(), retThreadPoolSize);
            assertEquals(PublicDataAPIRunnerConfig.getDefaultAwaitTermMillis(), retAwaitTermMillis);
        }
    }

    @Test
    public void testContains() {
        final int threadPoolSize = 10;
        final long awaitTermMillis = 10 * DateUtils.MILLIS_PER_SECOND;

        PublicDataAPIRunnerConfig config = new PublicDataAPIRunnerConfig();

        assertFalse(config.contains(PublicDataAPIRunnerConfig.Property.THREADPOOL_SIZE));
        assertFalse(config.contains(PublicDataAPIRunnerConfig.Property.AWAITTERM_MILLIS));

        config.setThreadPoolSize(threadPoolSize);
        assertTrue(config.contains(PublicDataAPIRunnerConfig.Property.THREADPOOL_SIZE));
        assertFalse(config.contains(PublicDataAPIRunnerConfig.Property.AWAITTERM_MILLIS));

        config.setAwaitTermMillis(awaitTermMillis);
        assertTrue(config.contains(PublicDataAPIRunnerConfig.Property.THREADPOOL_SIZE));
        assertTrue(config.contains(PublicDataAPIRunnerConfig.Property.AWAITTERM_MILLIS));
    }
}
