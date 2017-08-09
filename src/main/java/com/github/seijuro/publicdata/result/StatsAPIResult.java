package com.github.seijuro.publicdata.result;

public class StatsAPIResult extends PublicDataAPIResult {

    /**
     * C'tor
     *
     * @param $resultCode
     * @param $resultMesg
     * @param $pageNo
     * @param $numOfRows
     * @param $totalCount
     */
    public StatsAPIResult(String $resultCode, String $resultMesg, Integer $pageNo, Integer $numOfRows, Integer $totalCount) {
        super($resultCode, $resultMesg, $pageNo, $numOfRows, $totalCount);
    }

    public <T extends PublicDataAPIResult> StatsAPIResult(T parent) {
        super(parent.getResultCode(), parent.getResultMessage(), parent.getPageNo(), parent.getNumberOfRows(), parent.getTotalCount());
    }
}
