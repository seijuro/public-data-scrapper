package com.github.seijuro.publicdata.address.reader;

import com.github.seijuro.publicdata.address.LegalDongAddressCode;
import com.github.seijuro.publicdata.address.LegalDongAddress;
import com.github.seijuro.publicdata.address.LegalDongAddressStatus;
import org.junit.Test;

public class LegalDongAddressReaderTest {
    @Test
    public void testReader() {
        int countAvailable = 0;
        int countUnavailable = 0;

        try {
            LegalDongAddressReader reader = new LegalDongAddressReader(getClass().getClassLoader().getResource("legal-dong-addr.txt").getFile());
            LegalDongAddress address = null;

            while ((address = reader.readAddress()) != null) {
                assert (address instanceof LegalDongAddress);

                LegalDongAddressCode code = address.getCode();
                String text = address.getText();
                LegalDongAddressStatus status = address.getStatus();

                if (status == LegalDongAddressStatus.AVAILABLE) { ++countAvailable; }
                else { ++countUnavailable; }
            }
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }
    }
}
