package com.github.seijuro.publicdata.result;

import com.github.seijuro.common.IPrettyPrint;
import com.github.seijuro.publicdata.property.PublicDataProperty;
import com.github.seijuro.publicdata.result.item.PublicData;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@ToString
public class PublicDataAPIResult implements IPrettyPrint {
    @Getter(AccessLevel.PUBLIC)
    private final String resultCode;
    @Getter(AccessLevel.PUBLIC)
    private final String resultMessage;
    @Getter(AccessLevel.PUBLIC)
    private Integer pageNo;
    @Getter(AccessLevel.PUBLIC)
    private Integer numberOfRows;
    @Getter(AccessLevel.PUBLIC)
    private Integer totalCount;

    protected List<PublicData> resultList = new ArrayList<>();

    /**
     * C'tor
     *
     * @param $resultCode
     * @param $resultMesg
     * @param $pageNo
     * @param $numOfRows
     * @param $totalCount
     */
    public PublicDataAPIResult(String $resultCode, String $resultMesg, Integer $pageNo, Integer $numOfRows, Integer $totalCount) {
        this.resultCode = $resultCode;
        this.resultMessage = $resultMesg;
        this.pageNo = $pageNo;
        this.numberOfRows = $numOfRows;
        this.totalCount = $totalCount;
    }

    @Override
    public void prettyPrint(Consumer<String> consumer) {
        StringBuffer sb = new StringBuffer("result :=\n");

        sb.append("[common]").append("\n")
                .append("\t").append(PublicDataProperty.Result.RESULT_CODE).append(" : [").append(this.resultCode).append("]\n")
                .append("\t").append(PublicDataProperty.Result.RESULT_MESSAGE).append(" : [").append(this.resultMessage).append("]\n")
                .append("\t").append(PublicDataProperty.Result.PAGE_NO).append(" : [").append(this.pageNo).append("]\n")
                .append("\t").append(PublicDataProperty.Result.NUM_OF_ROWS).append(" : [").append(this.numberOfRows).append("]\n")
                .append("\t").append(PublicDataProperty.Result.TOTAL_COUNT).append(" : [").append(this.totalCount).append("]\n");

        consumer.accept(sb.toString());
    }

    /**
     * release reference(s).
     */
    public void clear() {
        if (this.resultList != null) this.resultList.clear();
    }

    /**
     * set all elements in param
     *
     * @param list
     */
    public void setData(List<? extends PublicData> list) {
        this.resultList.clear();

        addData(list);
    }

    /**
     * add data all elements in param
     *
     * @param list
     */
    public void addData(List<? extends PublicData> list) {
        if (this.resultList != null) {
            list.forEach(this.resultList::add);
        }
    }

    /**
     * add datum
     *
     * @param element
     * @param <T>
     */
    public <T extends PublicData> void addDatum(T element) {
        if (element != null) {
            this.resultList.add(element);
        }
    }

    /**
     * get list which contains all elements.
     *
     * @param Clazz
     * @param <T>
     * @return
     */
    public <T extends PublicData> List<T> getData(Class<T> Clazz) {
        List<T> ret = new ArrayList<>();

        this.resultList.forEach(e -> {
            ret.add(Clazz.cast(e));
        });

        return ret;
    }
}
