package com.github.seijuro.publicdata.parser;

import com.github.seijuro.common.InputType;
import com.google.gson.Gson;
import com.github.seijuro.publicdata.result.PublicDataAPIErrorResult;
import com.github.seijuro.publicdata.result.RecallAPIResult;
import com.github.seijuro.publicdata.result.item.RecallData;

import java.util.Arrays;

public class RecallAPIResponseParser extends PublicDataAPIResponseJSONParser {
    /**
     * C'tor
     */
    public RecallAPIResponseParser() {
        super();
    }

    @Override
    public void clear() {
        //  do nothing
    }

    @Override
    public void parse(InputType type, String input) {
        try {
            Gson gson = new Gson();

            RecallData[] recalls = gson.fromJson(input, RecallData[].class);

            String resultMessage = "";
            String resultCode = "";
            int pageIndex = 0;
            int pageSize = recalls.length;
            int totalCount = recalls.length;

            RecallAPIResult result = new RecallAPIResult(
                    resultCode,
                    resultMessage,
                    pageIndex,
                    pageSize,
                    totalCount);
            assert (recalls != null);

            result.addData(Arrays.asList(recalls));
            setResult(result);
        }
        catch (Exception excp) {
            excp.printStackTrace();

            setResult(new PublicDataAPIErrorResult(null, excp.getMessage(), null));
        }
    }
}
