package com.github.seijuro.publicdata.runner.config;

import lombok.AccessLevel;
import lombok.Getter;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Properties;

public class PublicDataAPIRunnerConfig {
    @Getter(AccessLevel.PUBLIC)
    static final int defaultThreadPoolSize = 5;
    @Getter(AccessLevel.PUBLIC)
    static final long defaultAwaitTermMillis = 10 * DateUtils.MILLIS_PER_SECOND;

    /**
     * Property
     */
    public enum Property {
        THREADPOOL_SIZE,
        AWAITTERM_MILLIS;
    }

    private final Properties props = new Properties();

    public PublicDataAPIRunnerConfig setThreadPoolSize(int size) {
        this.props.put(Property.THREADPOOL_SIZE, new Integer(size > 0 ? size : getDefaultThreadPoolSize()));
        return this;
    }

    public PublicDataAPIRunnerConfig setAwaitTermMillis(long millis) {
        this.props.put(Property.AWAITTERM_MILLIS, new Long(millis > 0 ? millis : getDefaultAwaitTermMillis()));
        return this;
    }

    public boolean contains(Property property) {
        return this.props.containsKey(property);
    }

    public <T> T get(Property property, Class<T> Clazz) {
        return Clazz.cast(this.props.get(property));
    }
}
