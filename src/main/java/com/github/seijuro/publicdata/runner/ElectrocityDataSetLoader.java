package com.github.seijuro.publicdata.runner;

import com.github.seijuro.common.jdbc.MySQLConnectionString;
import com.github.seijuro.db.writer.TheUseOfElectrocityWritter;
import com.github.seijuro.publicdata.energy.electrocity.ElectrocityDataFileReader;
import com.github.seijuro.publicdata.result.item.TheUseOfElectrocityData;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class ElectrocityDataSetLoader extends DataSetLoader implements Closeable, AutoCloseable {
    private ElectrocityDataFileReader reader = null;
    private MySQLConnectionString connectionString = null;
    private Connection connection = null;

    public ElectrocityDataSetLoader(String filepath, MySQLConnectionString connString) {
        super(filepath);

        reader = new ElectrocityDataFileReader(filepath);
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

            TheUseOfElectrocityData datum;
            List<TheUseOfElectrocityData> data = new ArrayList<>();
            TheUseOfElectrocityWritter writter = new TheUseOfElectrocityWritter(connection);

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
