package com.github.seijuro.publicdata.address.reader;

import com.github.seijuro.publicdata.address.JibunAddress;
import com.github.seijuro.publicdata.address.LegalDongAddressCode;
import lombok.AccessLevel;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class PNUReader implements Closeable, AutoCloseable {
    public enum FileType {
        JIBUN,
        BUILDING
    }

    public enum JIBUNFilePosition {
        LEGAL_DONG_CODE(0),
        DG_TEXT(1),
        SGGU(2),
        EMB(3),
        RI(4),
        LAND_TYPE(5),
        JIBUN_MAJOR(6),
        JIBUN_MINOR(7),
        ROAD_ADDRESS_CODE(8),
        UNDERGROUND_FLAG(9),
        BUILDING_MAJOR(10),
        BUILDING_MINOR(11),
        JIBUN_SERIAL(12),
        REASON_CODE(13)
        ;

        @Getter
        private final int position;

        JIBUNFilePosition(int pos) {
            position = pos;
        }
    }

    /**
     * Instance Properties
     */
    private FileType ftype;
    @Getter(AccessLevel.PROTECTED)
    private BufferedReader reader = null;


    /**
     * C'tor
     *
     * @param $filepath
     * @throws NullPointerException
     * @throws FileNotFoundException
     */
    public PNUReader(FileType type, String $filepath) throws IOException {
        Objects.nonNull(type);

        this.reader = Files.newBufferedReader(Paths.get($filepath));
    }

    /**
     * read address
     *
     * @return
     * @throws IOException
     */
    public JibunAddress readAddress() throws IOException {
        final int codePos = JIBUNFilePosition.LEGAL_DONG_CODE.getPosition();
        final int jibunMajorPos = JIBUNFilePosition.JIBUN_MAJOR.getPosition();
        final int jibunMinorPos = JIBUNFilePosition.JIBUN_MINOR.getPosition();

        String line;

        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split("\\|");
            LegalDongAddressCode code = LegalDongAddressCode.parse(StringUtils.strip(tokens[codePos]));
            int jibunMajor = Integer.parseInt(tokens[jibunMajorPos]);
            int jibunMinor = Integer.parseInt(tokens[jibunMinorPos]);

            return new JibunAddress(code, jibunMajor, jibunMinor);
        }

        return null;
    }


    @Override
    public void close() throws IOException {
        if (this.reader != null) { this.reader.close(); }
        this.reader = null;
    }
}
