package com.github.seijuro.task.specific;

import org.apache.commons.lang3.time.DateUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class SequenceIdSupplier implements Runnable {
    /**
     * Class Properties
     */
    private static int THRESHOLD = 1000;
    private static int PATCH_SIZE = 1000;
    private static long SLEEP_MILLIS = 10 * DateUtils.MILLIS_PER_SECOND;
    private static String SQL_SELECT_SEQ;

    static {
        StringBuffer sb = new StringBuffer("SELECT ");
        sb.append(BusinessPlaceTable.Columns.SequenceId)
                .append(" FROM ").append(BusinessPlaceTable.TablName)
                .append(" LIMIT ? OFFSET ?");
        SQL_SELECT_SEQ = sb.toString();
    }

    /**
     * Instance Properties
     */
    private final BlockingQueue<String> queue;
    private boolean runningState;
    private String connString;
    private int totalCount = 0;
    private int lastIndex = 0;
    private int patchSize = PATCH_SIZE;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException excp) {
            excp.printStackTrace();
        }
    }

    /**
     * C'tor
     *
     * @param $queue
     * @param $connString
     */
    public SequenceIdSupplier(String $connString, BlockingQueue<String> $queue) {
        queue = $queue;
        runningState = true;
        connString = $connString;
    }

    @Override
    public void run() {
        try {
            do {
                Thread.sleep(SLEEP_MILLIS);

                if (this.queue.size() < THRESHOLD) {
                    try {
                        Connection conn = DriverManager.getConnection(connString);

                        List<String> tmpList = new ArrayList<>(patchSize);
                        PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_SEQ);
                        stmt.setInt(1, patchSize);
                        stmt.setInt(2, lastIndex);

                        ResultSet rs = stmt.executeQuery();

                        rs.beforeFirst();

                        while (rs.next()) {
                            long seq = rs.getLong(1);
                            tmpList.add(Long.toString(seq));
                        }

                        rs.close();
                        stmt.close();
                        conn.close();

                        for (String seq : tmpList) {
                            boolean isDone;

                            do {
                                isDone = queue.offer(seq, 5, TimeUnit.SECONDS);
                            } while (isDone);

                            ++totalCount;
                            ++lastIndex;
                        }

                        tmpList.clear();
                    }
                    catch (SQLException excp) {
                        excp.printStackTrace();
                    }
                }
            } while (runningState);
        }
        catch (InterruptedException excp) {
            excp.printStackTrace();
        }
    }
}
