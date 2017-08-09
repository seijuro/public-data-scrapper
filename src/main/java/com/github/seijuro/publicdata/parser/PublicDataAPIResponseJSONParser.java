package com.github.seijuro.publicdata.parser;

import com.github.seijuro.common.InputType;
import com.github.seijuro.publicdata.result.PublicDataAPIResult;
import lombok.AccessLevel;
import lombok.Setter;

public abstract class PublicDataAPIResponseJSONParser implements PublicDataAPIResponseParser {
    /**
     * Instance Properties
     */
    @Setter(AccessLevel.PROTECTED)
    private boolean hasError = false;

    //  final result
    @Setter(AccessLevel.PROTECTED)
    private PublicDataAPIResult result = null;

    /**
     * C'tor
     */
    protected PublicDataAPIResponseJSONParser() {
    }

    @Override
    public void clear() {
        if (this.result != null) result.clear();

        this.result = null;
        this.hasError = false;
    }

    @Override
    public boolean hasError() {
        return this.hasError;
    }

    @Override
    public PublicDataAPIResult getResult() {
        return this.result;
    }

    @Override
    public void parse(InputType type, String input) {
    }
}
