package com.github.seijuro.publicdata.runner;

import com.github.seijuro.common.jdbc.MySQLConnectionString;
import com.github.seijuro.db.writer.TheUseOfGasWriter;
import com.github.seijuro.publicdata.energy.gas.GasDataFileReader;
import com.github.seijuro.publicdata.result.item.TheUseOfGasData;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class GasDataSetLoader extends DataSetLoader implements Closeable, AutoCloseable {
    private GasDataFileReader reader = null;
    private MySQLConnectionString connectionString = null;
    private Connection connection = null;

    public GasDataSetLoader(String filepath, MySQLConnectionString connString) {
        super(filepath);

        reader = new GasDataFileReader(filepath);
        connectionString = connString;
    }

    @Override
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }

        try {
            if (reader != null) {
                reader.close();
            }
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }

        connection = null;
        reader = null;
    }

    @Override
    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString.toConnectionString(), connectionString.getUser(), connectionString.getPassword());
            connection.setAutoCommit(false);

            TheUseOfGasData datum;
            List<TheUseOfGasData> data = new ArrayList<>();
            TheUseOfGasWriter writter = new TheUseOfGasWriter(connection);

            while ((datum = reader.readData()) != null) {
                data.add(datum);

                if (data.size() >= 400) {
                    writter.write(data);

                    data.clear();
                }
            }

            writter.write(data);
            data.clear();

            writter.close();
            writter = null;
            connection = null;
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }

        close();
    }
}
