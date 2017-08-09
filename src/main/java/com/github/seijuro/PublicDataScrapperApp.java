package com.github.seijuro;

import com.github.seijuro.common.key.KeyFile;
import com.github.seijuro.publicdata.result.PublicDataAPIErrorResult;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import com.github.seijuro.publicdata.result.item.BusinessPlaceData;
import com.github.seijuro.publicdata.result.item.BusinessPlaceDetailData;
import com.github.seijuro.publicdata.runner.PublicDataAPIResultDelegater;
import com.github.seijuro.publicdata.runner.PublicDataAPITask;
import com.github.seijuro.task.BusinessPlaceAPITask;
import com.github.seijuro.task.BusinessPlaceDetailAPITask;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.*;

public class PublicDataScrapperApp {
    @Getter(AccessLevel.PUBLIC)
    static final String serviceKeyProperty="service.key";

    /**
     * App parameter options
     */
    enum Options {
        SERVICEKEY_FILEPATH("--servicekey"),
        ADDRESS_FILEPATH("--address");

        @Getter(AccessLevel.PUBLIC)
        final String optionString;

        Options(String opt) {
            this.optionString = opt;
        }
    }

    /**
     * Instance Properties
     */
    private final List<PublicDataAPITask> tasks;
    private KeyFile keyFile;
    private String addressFile;
    private BlockingQueue<String> queue = new LinkedBlockingDeque<>();

    /**
     * C'tor
     */
    public PublicDataScrapperApp(String serviceKeyFile, String $addressFile) {
        tasks = new ArrayList<>();
        keyFile = new KeyFile(serviceKeyFile);
        addressFile = $addressFile;
    }

    private BusinessPlaceDetailAPITask createBusinessPlaceDetailAPITask() {
        try {
            BusinessPlaceDetailAPITask task = new BusinessPlaceDetailAPITask(keyFile.getKey(getServiceKeyProperty()), queue);
            task.setDelegater(new PublicDataAPIResultDelegater() {
                @Override
                public void handleHTTPResponse(String url, int code, String response) {
                    System.out.println("response : " + response);
                }

                @Override
                public void handleHTTPErrorResponse(String url, int code, String response, String errmsg) {
                    System.err.println("[ERROR] code : " + code + ", error msg : [" + errmsg + "], response : [" + response + "], url : [" + url + "]");
                }

                @Override
                public void handleResult(String url, PublicDataAPIResult result) {
                    List<BusinessPlaceDetailData> data = result.getData(BusinessPlaceDetailData.class);

                    for (BusinessPlaceDetailData datum : data) {
                        System.out.println("##### 2. " + datum);
                    }
                }

                @Override
                public void handleErrorResult(String url, PublicDataAPIErrorResult result) {
                    System.err.println("[ERROR] result : [" + result.toString() + "]");
                }
            });

            return task;
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }

        return null;
    }

    private BusinessPlaceAPITask createBusinessPlaceAPITask() {
        try {
            BusinessPlaceAPITask task = new BusinessPlaceAPITask(keyFile.getKey(getServiceKeyProperty()));
            task.setMaxTry(3);
            task.setAddressFile(addressFile);
            task.setDelegater(new PublicDataAPIResultDelegater() {
                @Override
                public void handleHTTPResponse(String url, int code, String response) {
                }

                @Override
                public void handleHTTPErrorResponse(String url, int code, String response, String errmsg) {
                }

                @Override
                synchronized public void handleResult(String url, PublicDataAPIResult result) {
                    List<BusinessPlaceData> data = result.getData(BusinessPlaceData.class);
                    for (BusinessPlaceData datum : data) {
                        //  TODO remove ... (written for test)
//                        queue.offer(datum.getId());
                    }
                }

                @Override
                public void handleErrorResult(String url, PublicDataAPIErrorResult result) {
                }
            });

            return task;
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }

        return null;
    }

    private void createTasks() throws Exception {
        tasks.add(createBusinessPlaceAPITask());
        tasks.add(createBusinessPlaceDetailAPITask());
    }

    private void execute() {
        ExecutorService executors = Executors.newFixedThreadPool(2);
        tasks.forEach(task -> executors.execute(task));
    }

    public static void main(String[] args) {
        String keyFilepath = null;
        String addressFilepath = null;
        List<String> seqs = Collections.synchronizedList(new LinkedList<>());

        for (int index = 0; index < args.length; ++index) {
            String current = args[index];

            if (Options.SERVICEKEY_FILEPATH.getOptionString().equals(current) && index + 1 < args.length) {
                keyFilepath = args[++index];
            }
            else if (Options.ADDRESS_FILEPATH.getOptionString().equals(current) && index + 1 < args.length) {
                addressFilepath = args[++index];
            }
        }

        //  TODO check illegal argument(s)

        System.out.println("servicekey.filepath : " + keyFilepath);
        System.out.println("address.filepath : " + addressFilepath);

        try {
            PublicDataScrapperApp scraper = new PublicDataScrapperApp(keyFilepath, addressFilepath);
            scraper.createTasks();
            scraper.execute();
        }
        catch (Exception excp) {
            excp.printStackTrace();
        }
    }
}
