package com.github.seijuro.publicdata.runner;

import com.github.seijuro.publicdata.api.PublicDataAPI;
import com.github.seijuro.publicdata.api.config.PublicDataAPIConfig;

public interface VisitCheckable {
    public abstract boolean didAlreadyVisit(PublicDataAPI api, PublicDataAPIConfig config) throws TaskExeption;
}
