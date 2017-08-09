package com.github.seijuro;

import com.github.seijuro.task.BusinessPlaceAPITask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataScrapperApp {
    static String serviceKey = "oDaJp7XH2ZthVeeu7ziOWjwsBTjvB9PS8KqHUfIqaUE03EvVprAr9AeJWEIJtRlm9rwg6nh9Y49LXqkRme7wQA%3D%3D";
    static String addressResourceFile = "legal-dong-addr.txt";

    /**
     * C'tor
     */
    public DataScrapperApp() {
    }

    private void execute() {
        try {
            BusinessPlaceAPITask task = new BusinessPlaceAPITask();
            task.setServiceKeySupplier(apiService -> serviceKey);
            task.setAddressFile(getClass().getClassLoader().getResource("legal-dong-addr.txt").getFile());

            ExecutorService executors = Executors.newFixedThreadPool(1);

            executors.execute(task);

            System.out.println("all task are done.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DataScrapperApp scraper = new DataScrapperApp();
        scraper.execute();
    }
}
