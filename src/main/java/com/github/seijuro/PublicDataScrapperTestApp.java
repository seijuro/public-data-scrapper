package com.github.seijuro;

import com.github.seijuro.publicdata.result.PublicDataAPIErrorResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import com.github.seijuro.publicdata.result.item.BusinessPlaceData;
import com.github.seijuro.publicdata.runner.PublicDataAPIResultDelegater;
import com.github.seijuro.publicdata.runner.PublicDataAPITask;
import com.github.seijuro.task.BusinessPlaceAPITask;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PublicDataScrapperTestApp {
    static String serviceKey = "${serviceKey}";
    static String addressResourceFile = "legal-dong-addr.txt";

    private final List<PublicDataAPITask> tasks;

    /**
     * C'tor
     */
    public PublicDataScrapperTestApp() {
        tasks = new ArrayList<>();
    }

    private BusinessPlaceAPITask createBusinessPlaceAPITask() {
        try {
            BusinessPlaceAPITask task = new BusinessPlaceAPITask();
            task.setServiceKeySupplier(apiService -> serviceKey);
            task.setAddressFile(getClass().getClassLoader().getResource("legal-dong-addr.txt").getFile());
            task.setDelegater(new PublicDataAPIResultDelegater() {
                @Override
                public void handleHTTPResponse(String url, int code, String response) {
                }

                @Override
                public void handleHTTPErrorResponse(String url, int code, String response, String errmsg) {
                    //  do nothing;
                }

                @Override
                public void handleResult(String url, PublicDataAPIResult result) {
                    List<BusinessPlaceData> data = result.getData(BusinessPlaceData.class);

                    try {
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

                        for (BusinessPlaceData datum : data) {
                            writer.write(datum.toString());
                            writer.newLine();
                        }
                    }
                    catch (IOException excp) {
                        excp.printStackTrace();
                    }
                }

                @Override
                public void handleErrorResult(String url, PublicDataAPIErrorResult result) {
                    //  do nothing;
                }
            });

            return task;
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }

        return null;
    }

    private void execute() {
        try {
            //  #1
            {
                PublicDataAPITask apiTask = createBusinessPlaceAPITask();
                tasks.add(apiTask);
            }

            ExecutorService executors = Executors.newFixedThreadPool(1);
            tasks.forEach(task -> executors.execute(task));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PublicDataScrapperTestApp scraper = new PublicDataScrapperTestApp();
        scraper.execute();
    }
}
