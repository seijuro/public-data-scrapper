package com.github.seijuro.publicdata.writer;

import com.github.seijuro.publicdata.result.item.PublicData;

import java.io.Closeable;
import java.util.List;

public interface RecordWriter<T extends PublicData> extends Closeable, AutoCloseable {
    public abstract void write(List<T> data);
}
