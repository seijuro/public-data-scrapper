package com.github.seijuro.publicdata.writer;

import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import com.github.seijuro.publicdata.result.item.PublicData;

import java.io.Closeable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public interface RecordWriter<T extends PublicData> extends Closeable, AutoCloseable {
    public abstract void write(List<T> data);

    default void write(PublicDataAPIResult result) {
        Class<T> clazz = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        List<T> data = result.getData(clazz);
        write(data);
    }
}
