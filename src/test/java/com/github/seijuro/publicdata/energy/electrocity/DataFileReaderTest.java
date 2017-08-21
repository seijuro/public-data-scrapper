package com.github.seijuro.publicdata.energy.electrocity;

import com.github.seijuro.publicdata.result.item.TheUseOfElectrocityData;
import org.junit.Test;

public class DataFileReaderTest {
    @Test
    public void test() {
        final String filepath = "/Users/myungjoonlee/Developer/app/pudblicdata-api/data/gas/gas_2016.txt";

        String line;
        ElectrocityDataFileReader reader = new ElectrocityDataFileReader(filepath);

        try {
            for (int index = 0; index < 20; ++index) {
                TheUseOfElectrocityData data = reader.readData();
                System.out.println("data : " + data.toString());
            }

            reader.close();
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }
    }
}
