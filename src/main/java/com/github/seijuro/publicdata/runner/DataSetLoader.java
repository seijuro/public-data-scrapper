package com.github.seijuro.publicdata.runner;

import lombok.Getter;

public abstract class DataSetLoader implements Runnable {
    @Getter
    private final String filepath;

    public DataSetLoader(String path) {
        filepath = path;
    }
}
