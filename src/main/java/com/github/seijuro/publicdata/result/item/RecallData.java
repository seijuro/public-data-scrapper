package com.github.seijuro.publicdata.result.item;

import com.google.gson.annotations.SerializedName;
import com.github.seijuro.publicdata.property.RecallProperty;
import lombok.*;
import org.joda.time.DateTime;

import java.util.function.Consumer;

@ToString
@EqualsAndHashCode(callSuper = true)
public class RecallData extends PublicData {
    /**
     * Instance Properties
     */
    @Getter(AccessLevel.PUBLIC)
    @SerializedName(RecallProperty.Content.FieldName.IDX)
    private final Integer idx;
    @Getter(AccessLevel.PUBLIC)
    @SerializedName(RecallProperty.Content.FieldName.COUNTRY_OF_MANUFACTURE)
    private final String countryOfManufacture;
    @Getter(AccessLevel.PUBLIC)
    @SerializedName(RecallProperty.Content.FieldName.TRADEMARK)
    private final String trademark;
    @Getter(AccessLevel.PUBLIC)
    @SerializedName(RecallProperty.Content.FieldName.PRODUCT_NAME)
    private final String productName;
    @Getter(AccessLevel.PUBLIC)
    @SerializedName(RecallProperty.Content.FieldName.MODEL)
    private final String model;
    @Getter(AccessLevel.PUBLIC)
    @SerializedName(RecallProperty.Content.FieldName.SERIAL_NUMBER)
    private final String serialNumber;
    @Getter(AccessLevel.PUBLIC)
    @SerializedName(RecallProperty.Content.FieldName.TYPE)
    private final String type;
    @Getter(AccessLevel.PUBLIC)
    @SerializedName(RecallProperty.Content.FieldName.COMPANY)
    private final String company;
    @Getter(AccessLevel.PUBLIC)
    @SerializedName(RecallProperty.Content.FieldName.DATE_OF_ISSUE)
    private final String dateOfIssue;
    @Getter(AccessLevel.PUBLIC)
    @SerializedName(RecallProperty.Content.FieldName.DIMENSION_TYPE)
    private final String dimensionType;
    @Getter(AccessLevel.PUBLIC)
    @SerializedName(RecallProperty.Content.FieldName.Extra)
    private final RecallExtraData extra;

    /**
     * C'tor
     *
     * @param builder
     */
    protected RecallData(Builder builder) {
        super(builder);

        this.idx = builder.idx;
        this.countryOfManufacture = builder.countryOfManufacture;
        this.productName = builder.productName;
        this.trademark = builder.trademark;
        this.model = builder.model;
        this.serialNumber = builder.serialNumber;
        this.type = builder.type;
        this.company = builder.company;
        this.dateOfIssue = builder.dateOfIssue;
        this.dimensionType = builder.dimensionType;
        this.extra = builder.extra;
    }

    @Override
    public void prettyPrint(Consumer<String> consumer) {
        StringBuffer sb = new StringBuffer();

        sb.append("recall :=\n")
                .append("\tidx : [").append(this.idx).append("]\n")
                .append("\tcountry of manufacture : [").append(this.countryOfManufacture).append("]\n")
                .append("\tproduct name : [").append(this.productName).append("]\n")
                .append("\ttrademark : [").append(this.trademark).append("]\n")
                .append("\tmodel : [").append(this.model).append("]\n")
                .append("\tserial# : [").append(this.serialNumber).append("]\n")
                .append("\ttype : [").append(this.type).append("]\n")
                .append("\tcompany : [").append(this.company).append("]\n")
                .append("\tdate of issue : [").append(this.dateOfIssue).append("]\n")
                .append("\tdimension of nation type : [").append(this.dimensionType).append("]\n")
                .append("\textra : [").append(this.extra).append("]\n");

        consumer.accept(sb.toString());
    }

    /**
     * Builder pattern class
     */
    public static class Builder extends PublicData.Builder {
        @Setter(AccessLevel.PUBLIC)
        private int idx = Integer.MIN_VALUE;
        @Setter(AccessLevel.PUBLIC)
        private String countryOfManufacture = null;
        @Setter(AccessLevel.PUBLIC)
        private String productName = null;
        @Setter(AccessLevel.PUBLIC)
        private String trademark = null;
        @Setter(AccessLevel.PUBLIC)
        private String model = null;
        @Setter(AccessLevel.PUBLIC)
        private String serialNumber = null;
        @Setter(AccessLevel.PUBLIC)
        private String type = null;
        @Setter(AccessLevel.PUBLIC)
        private String company = null;
        @Setter(AccessLevel.PUBLIC)
        private String dateOfIssue = null;
        @Setter(AccessLevel.PUBLIC)
        private String dimensionType = null;
        @Setter(AccessLevel.PUBLIC)
        private RecallExtraData extra = null;

        @Override
        public RecallData build() {
            return new RecallData(this);
        }
    }
}
