package com.github.seijuro.publicdata.snapshot.reader;

import java.util.ArrayList;
import java.util.List;

public abstract class BufferedSequencdIdReader<T>  {
    /**
     * Instance Properties
     */
    private int blockSize = /* default */200;
    private int nextOffset = 0;
    private int currentCount = 0;
    private int maxCount = /*default*/ 100000;
    private boolean hasNext = true;
    private boolean hasError = false;

    public BufferedSequencdIdReader(int offset, int count, int blockSize) {
        this.nextOffset = offset;
        this.maxCount = count;
        this.blockSize = blockSize;
    }

    public boolean hasNext() {
        return hasNext && (currentCount < maxCount);
    }

    public boolean hasError() {
        return hasError;
    }

    public List<T> read() {
        List<T> ret = null;

        if (hasNext) {
            ret = new ArrayList<>();

            try {
                int nextBlockSize = (currentCount + blockSize) <= maxCount ? blockSize : (maxCount - currentCount);
                ret = loadSeqBlock(nextOffset, nextBlockSize);

                if (ret.size() < nextBlockSize) {
                    hasNext = false;
                }

                nextOffset += ret.size();
                currentCount += ret.size();
            }
            catch (Exception excp) {
                excp.printStackTrace();

                hasError = true;
            }
        }

        return ret;
    }


    public abstract List<T> loadSeqBlock(int fromOffset, int blockSize) throws Exception;
}