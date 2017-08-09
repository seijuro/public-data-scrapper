package com.github.seijuro.publicdata.result;

import com.github.seijuro.publicdata.result.item.SpecificationData;

import java.util.List;

public class SpeficiationInfoResult extends PublicDataAPIResult {
    private List<SpecificationData> infoList = null;
    /**
     * C'tor
     *
     * @param $resultCode
     * @param $resultMesg
     * @param $pageNo
     * @param $numOfRows
     * @param $totalCount
     */
    public SpeficiationInfoResult(String $resultCode, String $resultMesg, Integer $pageNo, Integer $numOfRows, Integer $totalCount) {
        super($resultCode, $resultMesg, $pageNo, $numOfRows, $totalCount);
    }

    public <T extends PublicDataAPIResult> SpeficiationInfoResult(T parent) {
        super(parent.getResultCode(), parent.getResultMessage(), parent.getPageNo(), parent.getNumberOfRows(), parent.getTotalCount());
    }
}
