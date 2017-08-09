package com.github.seijuro.publicdata.result;

import com.github.seijuro.publicdata.result.item.RecallData;
import lombok.ToString;

import java.util.List;
import java.util.function.Consumer;

@ToString
public class RecallAPIResult extends PublicDataAPIResult {
    /**
     * C'tor
     *
     * @param $resultCode
     * @param $resultMesg
     * @param $pageNo
     * @param $numOfRows
     * @param $totalCount
     */
    public RecallAPIResult(String $resultCode, String $resultMesg, Integer $pageNo, Integer $numOfRows, Integer $totalCount) {
        super($resultCode, $resultMesg, $pageNo, $numOfRows, $totalCount);
    }

    /**
     * Implements IPrettyPrint
     *
     * @param consumer
     */
    @Override
    public void prettyPrint(Consumer<String> consumer) {
        super.prettyPrint(consumer);
        List<RecallData> results = getData(RecallData.class);

        consumer.accept("[result of recall API] (pageable : false)\n");
        for (RecallData recall : results) {
            recall.prettyPrint(consumer);
        }
    }
}
