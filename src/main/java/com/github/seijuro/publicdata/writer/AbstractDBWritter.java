package com.github.seijuro.publicdata.writer;

import com.github.seijuro.publicdata.result.item.PublicData;
import com.github.seijuro.publicdata.writer.RecordWriter;
import lombok.Getter;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.util.List;

public abstract class AbstractDBWritter<T extends PublicData> implements RecordWriter<T> {
    @Getter
    private final static int DefaultCommitBlockSize = 500;

    /**
     * Instance Properties
     */
    protected Connection connection;

    public AbstractDBWritter(Connection conn) throws NullPointerException {
        if (conn == null) {
            throw new NullPointerException("Param, conn, is a null object.");
        }

        connection = conn;
    }

    @Override
    public void write(List<T> data) {
        write(data, getDefaultCommitBlockSize());
    }

    public abstract void write(List<T> data, int commitBlockSIze);


    @Override
    public void close() throws IOException {
        try {
            if (connection != null) {
                connection.close();
            }
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }
        finally {
            connection = null;
        }
    }

    public void commit() {
        try {
            if (connection != null) { connection.commit(); }
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }
    }
}
