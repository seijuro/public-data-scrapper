package com.github.seijuro.publicdata.runner;

public interface PublicDataAPIPageableKeySupplier {
    public abstract int getDefaultPageSize();
    public abstract int getPageSize();
    public abstract int getPageNumber();
}
