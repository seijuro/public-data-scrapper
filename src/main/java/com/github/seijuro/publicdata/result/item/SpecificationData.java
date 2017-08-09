package com.github.seijuro.publicdata.result.item;

import lombok.*;

import java.util.function.Consumer;

@ToString
@EqualsAndHashCode(callSuper = true)
public class SpecificationData extends PublicData {
    /**
     * Instance Properties
     */
    @Getter(AccessLevel.PUBLIC)
    private final String businessDivisionName;
    @Getter(AccessLevel.PUBLIC)
    private final String refNo;
    @Getter(AccessLevel.PUBLIC)
    private final String productName;
    @Getter(AccessLevel.PUBLIC)
    private final String orderingInstName;
    @Getter(AccessLevel.PUBLIC)
    private final String demandInstName;
    @Getter(AccessLevel.PUBLIC)
    private final String assignBudgetAmount;
    @Getter(AccessLevel.PUBLIC)
    private final String receiptDate;
    @Getter(AccessLevel.PUBLIC)
    private final String regOptionDueDate;
    @Getter(AccessLevel.PUBLIC)
    private final String officalTel;
    @Getter(AccessLevel.PUBLIC)
    private final String officalName;
    @Getter(AccessLevel.PUBLIC)
    private final String isSWBusiness;
    @Getter(AccessLevel.PUBLIC)
    private final String deliveryDueDate;
    @Getter(AccessLevel.PUBLIC)
    private final String deliveryDayNum;
    @Getter(AccessLevel.PUBLIC)
    private final String specificationRegNo;
    @Getter(AccessLevel.PUBLIC)
    private final String specificationDocFileURL1;
    @Getter(AccessLevel.PUBLIC)
    private final String specificationDocFileURL2;
    @Getter(AccessLevel.PUBLIC)
    private final String specificationDocFileURL3;
    @Getter(AccessLevel.PUBLIC)
    private final String specificationDocFileURL4;
    @Getter(AccessLevel.PUBLIC)
    private final String specificationDocFileURL5;
    @Getter(AccessLevel.PUBLIC)
    private final String productDetails;
    @Getter(AccessLevel.PUBLIC)
    private final String regDate;
    @Getter(AccessLevel.PUBLIC)
    private final String changedDate;
    @Getter(AccessLevel.PUBLIC)
    private final String bidNoticeNoList;

    /**
     * C'tor for Builder
     *
     * @param builder
     */
    protected SpecificationData(Builder builder) {
        super(builder);

        this.businessDivisionName = builder.businessDivisionName;
        this.refNo = builder.refNo;
        this.productName = builder.productName;
        this.orderingInstName = builder.orderingInstName;
        this.demandInstName = builder.demandInstName;
        this.assignBudgetAmount = builder.assignBudgetAmount;
        this.receiptDate = builder.receiptDate;
        this.regOptionDueDate = builder.regOptionDueDate;
        this.officalTel = builder.officalTel;
        this.officalName = builder.officalName;
        this.isSWBusiness = builder.isSWBusiness;
        this.deliveryDueDate = builder.deliveryDueDate;
        this.deliveryDayNum = builder.deliveryDayNum;
        this.specificationRegNo = builder.specificationRegNo;
        this.specificationDocFileURL1 = builder.specificationDocFileURL1;
        this.specificationDocFileURL2 = builder.specificationDocFileURL2;
        this.specificationDocFileURL3 = builder.specificationDocFileURL3;
        this.specificationDocFileURL4 = builder.specificationDocFileURL4;
        this.specificationDocFileURL5 = builder.specificationDocFileURL5;
        this.productDetails = builder.productDetails;
        this.regDate = builder.regDate;
        this.changedDate = builder.changedDate;
        this.bidNoticeNoList = builder.bidNoticeNoList;
    }

    @Override
    public void prettyPrint(Consumer<String> consumer) {
        StringBuffer sb = new StringBuffer();

        consumer.accept(sb.toString());
    }

    /**
     * Builder Pattern class
     */
    public static class Builder extends PublicData.Builder {
        /**
         * Instance Properties
         */
        @Setter(AccessLevel.PUBLIC)
        private String businessDivisionName;
        @Setter(AccessLevel.PUBLIC)
        private String refNo;
        @Setter(AccessLevel.PUBLIC)
        private String productName;
        @Setter(AccessLevel.PUBLIC)
        private String orderingInstName;
        @Setter(AccessLevel.PUBLIC)
        private String demandInstName;
        @Setter(AccessLevel.PUBLIC)
        private String assignBudgetAmount;
        @Setter(AccessLevel.PUBLIC)
        private String receiptDate;
        @Setter(AccessLevel.PUBLIC)
        private String regOptionDueDate;
        @Setter(AccessLevel.PUBLIC)
        private String officalTel;
        @Setter(AccessLevel.PUBLIC)
        private String officalName;
        @Setter(AccessLevel.PUBLIC)
        private String isSWBusiness;
        @Setter(AccessLevel.PUBLIC)
        private String deliveryDueDate;
        @Setter(AccessLevel.PUBLIC)
        private String deliveryDayNum;
        @Setter(AccessLevel.PUBLIC)
        private String specificationRegNo;
        @Setter(AccessLevel.PUBLIC)
        private String specificationDocFileURL1;
        @Setter(AccessLevel.PUBLIC)
        private String specificationDocFileURL2;
        @Setter(AccessLevel.PUBLIC)
        private String specificationDocFileURL3;
        @Setter(AccessLevel.PUBLIC)
        private String specificationDocFileURL4;
        @Setter(AccessLevel.PUBLIC)
        private String specificationDocFileURL5;
        @Setter(AccessLevel.PUBLIC)
        private String productDetails;
        @Setter(AccessLevel.PUBLIC)
        private String regDate;
        @Setter(AccessLevel.PUBLIC)
        private String changedDate;
        @Setter(AccessLevel.PUBLIC)
        private String bidNoticeNoList;

        /**
         * build : Builder patthern method
         *
         * @return
         */
        @Override
        public SpecificationData build() {
            return new SpecificationData(this);
        }
    }
}
