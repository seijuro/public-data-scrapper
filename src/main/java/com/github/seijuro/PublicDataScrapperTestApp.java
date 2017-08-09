package com.github.seijuro;

import com.github.seijuro.common.key.KeyFile;
import com.github.seijuro.publicdata.result.PublicDataAPIErrorResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import com.github.seijuro.publicdata.result.item.BusinessPlaceData;
import com.github.seijuro.publicdata.runner.PublicDataAPIResultDelegater;
import com.github.seijuro.publicdata.runner.PublicDataAPITask;
import com.github.seijuro.task.BusinessPlaceAPITask;
import lombok.AccessLevel;
import lombok.Getter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PublicDataScrapperTestApp {
    @Getter(AccessLevel.PUBLIC)
    static final String serviceKeyProperty="service.key";
    static String addressResourceFile = "legal-dong-addr.txt";

    private final List<PublicDataAPITask> tasks;
    private KeyFile keyFile;

    /**
     * C'tor
     */
    public PublicDataScrapperTestApp(String keyFilepath) {
        tasks = new ArrayList<>();
        keyFile = new KeyFile(keyFilepath);
    }

    private BusinessPlaceAPITask createBusinessPlaceAPITask() {
        try {
            BusinessPlaceAPITask task = new BusinessPlaceAPITask();
            task.setServiceKeySupplier(apiService -> keyFile.getKey(getServiceKeyProperty()));
            task.setMaxTry(3);
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

    enum Options {
        KEYPATH("--key");

        @Getter(AccessLevel.PUBLIC)
        final String optionString;

        Options(String opt) {
            this.optionString = opt;
        }
    }

    public static void main(String[] args) {
        String keyFilepath = null;

        if (args.length > 0) {
            List<String> argList = Arrays.asList(args);

            String optionKey = Options.KEYPATH.getOptionString();

            if (argList.contains(optionKey)) {
                int index = argList.indexOf(optionKey);
                if (index + 1 < args.length) {
                    keyFilepath = argList.get(index + 1);
                }
            }
        }

        System.out.println("keypath : " + keyFilepath);

        PublicDataScrapperTestApp scraper = new PublicDataScrapperTestApp(keyFilepath);
        scraper.execute();
    }
}
