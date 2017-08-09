package com.github.seijuro.publicdata.result;

import com.google.gson.annotations.SerializedName;
import com.github.seijuro.publicdata.property.RecallProperty;
import com.github.seijuro.publicdata.result.item.RecallData;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@ToString
public class RecallAPIPageableResult extends RecallAPIResult {
    /**
     * Instance Properties
     */
    @Getter(AccessLevel.PUBLIC)
    private final Boolean enabled;
    @Getter(AccessLevel.PUBLIC)
    private final Integer number;
    @Getter(AccessLevel.PUBLIC)
    private final Integer size;
    @Getter(AccessLevel.PUBLIC)
    private final Integer numberOfElements;
    @Getter(AccessLevel.PUBLIC)
    private final Integer totalElements;
    @Getter(AccessLevel.PUBLIC)
    private final Integer totalPages;
    @Getter(AccessLevel.PUBLIC)
    private final Boolean hasPreviousPage;
    @Getter(AccessLevel.PUBLIC)
    private final Boolean hasNextPage;
    @Getter(AccessLevel.PUBLIC)
    private final Boolean isFirstPage;
    @Getter(AccessLevel.PUBLIC)
    private final Boolean isLastPage;
    @Getter(AccessLevel.PUBLIC)
    private final Boolean hasContent;
    @Getter(AccessLevel.PUBLIC)
    private final Integer beginPage;
    @Getter(AccessLevel.PUBLIC)
    private final Integer endPage;
    @Getter(AccessLevel.PUBLIC)
    private final Integer previousPage;
    @Getter(AccessLevel.PUBLIC)
    private final Integer nextPage;
    @Getter(AccessLevel.PUBLIC)
    private final String sort;
    @Getter(AccessLevel.PUBLIC)
    private final String status;
    @Getter(AccessLevel.PUBLIC)
    private final Integer pageNumber;
    @Getter(AccessLevel.PUBLIC)
    private final Integer pageSize;
    @Getter(AccessLevel.PUBLIC)
    private final Boolean firstPage;
    @Getter(AccessLevel.PUBLIC)
    private final Boolean lastPage;

    /**
     * C'tor
     *
     * @param builder
     */
    protected RecallAPIPageableResult(Builder builder) {
        super(builder.status, "", builder.pageNumber, builder.pageSize, builder.totalElements);

        this.enabled = builder.enabled;
        this.number = builder.number;
        this.size = builder.size;
        this.numberOfElements = builder.numOfElements;
        this.totalElements = builder.totalElements;
        this.totalPages = builder.totalPages;
        this.hasPreviousPage = builder.hasPreviousPage;
        this.hasNextPage = builder.hasNextPage;
        this.isFirstPage = builder.isFirstPage;
        this.isLastPage = builder.isLastPage;
        this.hasContent = builder.hasContent;
        this.beginPage = builder.beginPage;
        this.endPage = builder.endPage;
        this.previousPage = builder.previousPage;
        this.nextPage = builder.nextPage;
        this.sort = builder.sort;
        this.status = builder.status;
        this.pageNumber = builder.pageNumber;
        this.pageSize = builder.pageSize;
        this.firstPage = builder.firstPage;
        this.lastPage = builder.lastPage;

        addData(builder.getContents());
    }

    @Override
    public void prettyPrint(Consumer<String> consumer) {
        super.prettyPrint(consumer);

        StringBuffer sb = new StringBuffer("[recall]\n");
        sb.append("\t").append(RecallProperty.ENABLE).append(" : [").append(this.enabled).append("]\n")
                .append("\t").append(RecallProperty.TOTAL_PAGES).append(" : [").append(this.totalPages).append("]\n")
                .append("\t").append(RecallProperty.HAS_PREVIOUS_PAGE).append(" : [").append(this.hasPreviousPage).append("]\n")
                .append("\t").append(RecallProperty.HAS_NEXT_PAGE).append(" : [").append(this.hasNextPage).append("]\n")
                .append("\t").append(RecallProperty.IS_FIRST_PAGE).append(" : [").append(this.isFirstPage).append("]\n")
                .append("\t").append(RecallProperty.IS_LAST_PAGE).append(" : [").append(this.isLastPage).append("]\n")
                .append("\t").append(RecallProperty.HAS_CONTENTS).append(" : [").append(this.hasContent).append("]\n")
                .append("\t").append(RecallProperty.BEGIN_PAGE).append(" : [").append(this.beginPage).append("]\n")
                .append("\t").append(RecallProperty.END_PAGE).append(" : [").append(this.endPage).append("]\n")
                .append("\t").append(RecallProperty.PREVIOUS_PAGE).append(" : [").append(this.previousPage).append("]\n")
                .append("\t").append(RecallProperty.NEXT_PAGE).append(" : [").append(this.nextPage).append("]\n");

        consumer.accept(sb.toString());

        List<RecallData> data = getData(RecallData.class);

        for (RecallData recall : data) {
            recall.prettyPrint(consumer);
        }
    }

    /**
     * Builder Pattern method.
     */
    @ToString
    public static class Builder {
        /**
         * Instance Properties
         */
        @Setter(AccessLevel.PUBLIC)
        @SerializedName(RecallProperty.ENABLE)
        private Boolean enabled;
        @Setter(AccessLevel.PUBLIC)
        @SerializedName(RecallProperty.NUMBER)
        private Integer number;
        @Setter(AccessLevel.PUBLIC)
        @SerializedName(RecallProperty.SIZE)
        private Integer size;
        @Setter(AccessLevel.PUBLIC)
        @SerializedName(RecallProperty.TOTAL_PAGES)
        private Integer totalPages;
        @Setter(AccessLevel.PUBLIC)
        @SerializedName(RecallProperty.NUMBER_OF_ELEMENTS)
        private Integer numOfElements;
        @Setter(AccessLevel.PUBLIC)
        @SerializedName(RecallProperty.TOTAL_ELEMENTS)
        private Integer totalElements;
        @Setter(AccessLevel.PUBLIC)
        @SerializedName(RecallProperty.HAS_PREVIOUS_PAGE)
        private Boolean hasPreviousPage;
        @Setter(AccessLevel.PUBLIC)
        @SerializedName(RecallProperty.HAS_NEXT_PAGE)
        private Boolean hasNextPage;
        @Setter(AccessLevel.PUBLIC)
        @SerializedName(RecallProperty.IS_FIRST_PAGE)
        private Boolean isFirstPage;
        @Setter(AccessLevel.PUBLIC)
        @SerializedName(RecallProperty.IS_LAST_PAGE)
        private Boolean isLastPage;
        @Setter(AccessLevel.PUBLIC)
        @SerializedName(RecallProperty.HAS_CONTENTS)
        private Boolean hasContent;
        @Setter(AccessLevel.PUBLIC)
        @SerializedName(RecallProperty.BEGIN_PAGE)
        private Integer beginPage;
        @Setter(AccessLevel.PUBLIC)
        @SerializedName(RecallProperty.END_PAGE)
        private Integer endPage;
        @Setter(AccessLevel.PUBLIC)
        @SerializedName(RecallProperty.PREVIOUS_PAGE)
        private Integer previousPage;
        @Setter(AccessLevel.PUBLIC)
        @SerializedName(RecallProperty.NEXT_PAGE)
        private Integer nextPage;
        @Setter(AccessLevel.PUBLIC)
        @SerializedName(RecallProperty.SORT)
        private String sort;
        @Setter(AccessLevel.PUBLIC)
        @SerializedName(RecallProperty.STATUS)
        private String status;
        @Setter(AccessLevel.PUBLIC)
        @SerializedName(RecallProperty.PAGE_NUMBER)
        private Integer pageNumber;
        @Setter(AccessLevel.PUBLIC)
        @SerializedName(RecallProperty.PAGE_SIZE)
        private Integer pageSize;
        @Setter(AccessLevel.PUBLIC)
        @SerializedName(RecallProperty.FIRST_PAGE)
        private Boolean firstPage;
        @Setter(AccessLevel.PUBLIC)
        @SerializedName(RecallProperty.LAST_PAGE)
        private Boolean lastPage;
        @Setter(AccessLevel.PUBLIC)
        @Getter(AccessLevel.PUBLIC)
        @SerializedName(RecallProperty.CONTENT)
        private ArrayList<RecallData> contents;

        /**
         * Builder pattern method
         * @return
         */
        public RecallAPIPageableResult build() {
            return new RecallAPIPageableResult(this);
        }
    }
}
