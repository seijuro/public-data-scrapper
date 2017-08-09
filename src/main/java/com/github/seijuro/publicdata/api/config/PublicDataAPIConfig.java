package com.github.seijuro.publicdata.api.config;

import lombok.AccessLevel;
import lombok.Getter;
import org.json.simple.JSONObject;

import java.util.Properties;

public class PublicDataAPIConfig {
    @Getter(AccessLevel.PUBLIC)
    private Properties properties = new Properties();

    public <T extends ConfigProperty, V extends Number>
    Object setProperty(T property, V value) { return getProperties().put(property.getProperty(), value); }

    public <T extends ConfigProperty, V extends ConfigPropertyValue>
    Object setProperty(T property, V value) {
        return setProperty(property, value.getValue());
    }

    public <T extends ConfigProperty, V extends JSONObject>
    Object setProperty(T property, V value) { return setProperty(property, value.toJSONString()); }

    public <T extends ConfigProperty, V extends String>
    Object setProperty(T property,  V value) {
        return getProperties().put(property.getProperty(), value);
    }

    public <T1 extends ConfigProperty, T2>
    T2 getProperty(T1 property, Class<T2> Clazz) {
        return Clazz.cast(getProperties().get(property.getProperty()));
    }

    public PublicDataAPIConfig() {
        super();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        this.properties.forEach((Object k, Object v) -> {
            sb.append("config -> key : [" + k.toString() + "], value : [" + v.toString() + "]");
        });

        return sb.toString();
    }
}
