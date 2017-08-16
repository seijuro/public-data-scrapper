package com.github.seijuro.publicdata.parser;

import com.github.seijuro.common.InputType;
import com.github.seijuro.publicdata.result.RecallAPIPageableResult;
import com.google.gson.Gson;
import com.github.seijuro.publicdata.result.PublicDataAPIErrorResult;

public class RecallAPIPageableResponseParser extends RecallAPIResponseParser {
    /**
     * C'tor
     */
    public RecallAPIPageableResponseParser() {
        super();
    }

    @Override
    public void parse(InputType type, String input) {
        try {
            Gson gson = new Gson();
            System.out.println("parser input : " + input);
            RecallAPIPageableResult.Builder builder = gson.fromJson(input, RecallAPIPageableResult.Builder.class);

            assert (builder != null);

            setResult(builder.build());
        }
        catch (Exception excp) {
            excp.printStackTrace();

            setResult(new PublicDataAPIErrorResult(null, excp.getMessage(), null));
        }
    }


}
