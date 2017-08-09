package com.github.seijuro.publicdata.runner.config;

import com.github.seijuro.publicdata.api.config.ConfigProperty;
import lombok.AccessLevel;
import lombok.Getter;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Properties;

public class PublicDataAPIRunnerConfig {
    @Getter(AccessLevel.PUBLIC)
    static final int defaultThreadPoolSize = 5;
    @Getter(AccessLevel.PUBLIC)
    static final long defaultAwaitTermMillis = 10 * DateUtils.MILLIS_PER_SECOND;
    @Getter(AccessLevel.PUBLIC)
    static final int defaultMaxTry = 3;

    /**
     * Property
     */
    public enum Property implements ConfigProperty {
        THREADPOOL_SIZE("threadpool.size"),
        AWAITTERM_MILLIS("awaitterm.millis"),
        MAX_TRY_COUNT("maxtry");

        /**
         * Instance Properties
         */
        @Getter(AccessLevel.PUBLIC)
        private final String property;

        /**
         * C'tor
         *
         * @param prop
         */
        Property(String prop) {
            this.property = prop;
        }
    }

    private final Properties props;

    /**
     * C'tor
     */
    public PublicDataAPIRunnerConfig() {
        this.props = new Properties();

        this.props.put(Property.THREADPOOL_SIZE, new Integer(getDefaultThreadPoolSize()));
        this.props.put(Property.AWAITTERM_MILLIS, new Long(getDefaultAwaitTermMillis()));
        this.props.put(Property.MAX_TRY_COUNT, new Integer(getDefaultMaxTry()));
    }

    public PublicDataAPIRunnerConfig setThreadPoolSize(int size) {
        if (size > 0) {
            this.props.put(Property.THREADPOOL_SIZE, new Integer(size));
            return this;
        }

        throw new IllegalArgumentException("The param, size, should be bigger than 0.");
    }

    public PublicDataAPIRunnerConfig setAwaitTermMillis(long millis) {
        if (millis > 0) {
            this.props.put(Property.AWAITTERM_MILLIS, new Long(millis));
            return this;
        }

        throw new IllegalArgumentException("The param, millis, should be bigger than 0.");
    }

    public PublicDataAPIRunnerConfig setMaxTryCount(int max) {
        if (max > 0) {
            this.props.put(Property.MAX_TRY_COUNT, new Integer(max));
            return this;
        }

        throw new IllegalArgumentException("The param, max, should be bigger than 0.");
    }

    public boolean contains(Property property) {
        return this.props.containsKey(property);
    }

    public <T> T get(Property property, Class<T> Clazz) {
        return Clazz.cast(this.props.get(property));
    }
}
