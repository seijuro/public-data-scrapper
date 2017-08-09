package com.github.seijuro.publicdata.runner;

import com.github.seijuro.publicdata.runner.config.PublicDataAPIRunnerConfig;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PublicDataAPITaskRunner {
    /**
     * ConfigUtils
     */
    static class ConfigUtils {
        static int getThreadPoolSize(PublicDataAPIRunnerConfig config) {
            int size = config.contains(PublicDataAPIRunnerConfig.Property.THREADPOOL_SIZE) ? config.get(PublicDataAPIRunnerConfig.Property.THREADPOOL_SIZE, Integer.class) : PublicDataAPIRunnerConfig.getDefaultThreadPoolSize();

            return size + 1;
        }

        static long getAwaitTermMillis(PublicDataAPIRunnerConfig config) {
            return config.contains(PublicDataAPIRunnerConfig.Property.AWAITTERM_MILLIS) ? config.get(PublicDataAPIRunnerConfig.Property.AWAITTERM_MILLIS, Long.class) : PublicDataAPIRunnerConfig.getDefaultAwaitTermMillis();
        }
    }

    @Getter(AccessLevel.PROTECTED)
    private final ExecutorService executor;
    private final long awaitTermMillis;

    private final List<PublicDataAPIRunnable> runnables = new ArrayList<>();
    private final Thread hookShutdownThread;

    /**
     * C'tor
     *
     * @param config
     */
    public PublicDataAPITaskRunner(PublicDataAPIRunnerConfig config) {
        this(Executors.newFixedThreadPool(ConfigUtils.getThreadPoolSize(config)), ConfigUtils.getAwaitTermMillis(config));
    }

    public PublicDataAPITaskRunner(PublicDataAPIRunnerConfig config, ExecutorService executor) {
        this(executor, ConfigUtils.getAwaitTermMillis(config));
    }

    /**
     * C'tor
     *
     * @param $executor
     * @param $awaitTermMillis
     */
    public PublicDataAPITaskRunner(ExecutorService $executor, long $awaitTermMillis) {
        this.executor = $executor;
        this.awaitTermMillis = $awaitTermMillis;
        this.hookShutdownThread = new Thread(() -> {
            for (PublicDataAPIRunnable runnable : runnables) {
                runnable.shutdown();
            }

            try {
                executor.awaitTermination(awaitTermMillis, TimeUnit.MILLISECONDS);
            }
            catch (InterruptedException excp) {
                excp.printStackTrace();
            }
        });

        Runtime.getRuntime().addShutdownHook(this.hookShutdownThread);
    }
}
