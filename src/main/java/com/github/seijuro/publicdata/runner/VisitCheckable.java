package com.github.seijuro.publicdata.runner;

import com.github.seijuro.publicdata.api.PublicDataAPI;
import com.github.seijuro.publicdata.api.config.PublicDataAPIConfig;

public interface VisitCheckable {
    public enum Result {
        VISITED,
        VISITED_AND_LAST_PAGE,
        NOT_VISITED;
    }

    default Result didAlreadyVisit(PublicDataAPI api, PublicDataAPIConfig config) throws TaskExeption {
        return Result.NOT_VISITED;
    }
}
