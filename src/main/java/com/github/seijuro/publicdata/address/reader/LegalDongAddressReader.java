package com.github.seijuro.publicdata.address.reader;

import com.github.seijuro.publicdata.address.LegalDongAddress;
import com.github.seijuro.publicdata.address.LegalDongAddressCode;
import com.github.seijuro.publicdata.address.LegalDongAddressStatus;
import lombok.AccessLevel;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LegalDongAddressReader implements Closeable, AutoCloseable {
    /**
     * Instance Properties
     */
    @Getter(AccessLevel.PROTECTED)
    private BufferedReader reader = null;

    /**
     * C'tor
     *
     * @param $filepath
     * @throws NullPointerException
     * @throws FileNotFoundException
     */
    public LegalDongAddressReader(String $filepath) throws IOException {
        this.reader = Files.newBufferedReader(Paths.get($filepath));
    }

    /**
     * read address
     *
     * @return
     * @throws IOException
     */
    public LegalDongAddress readAddress() throws IOException {
        String line;

        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split("\\s+");
            int length = tokens.length;

            if (length < 3) {
                System.err.println("Illegal address : " + line);
                continue;
            }
            else {
                String _code = tokens[0];
                String _status = tokens[length - 1];
                StringBuffer sb = new StringBuffer(tokens[1]);

                for (int idx = 2; idx < length - 1; ++idx) {
                    sb.append(" ").append(tokens[idx]);
                }

                LegalDongAddressCode code = LegalDongAddressCode.parse(_code);
                LegalDongAddressStatus status = LegalDongAddressStatus.parse(_status);

                if (status == LegalDongAddressStatus.NOT_AVAILABLE) {
                    continue;
                }

                return new LegalDongAddress(code, sb.toString(), status);
            }
        }

        return null;
    }

    @Override
    public void close() throws IOException {
        if (this.reader != null) { this.reader.close(); }
        this.reader = null;
    }
}
