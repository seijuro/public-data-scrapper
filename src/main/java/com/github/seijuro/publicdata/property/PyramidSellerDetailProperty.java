package com.github.seijuro.publicdata.property;

public class PyramidSellerDetailProperty extends PyramidSellerProperty {
    public static class Item extends PyramidSellerProperty.Item {
        public static final String STATUS = "gasGbn";
        public static final String ADDRESS = "wrkrBupSpecAddr";
        public static final String CAPITTAL = "captScale";
    }

    public static class ItemCode extends PyramidSellerProperty.ItemCode {
        public static final int IC_STATUS = CodePrefix.IC_PREFIX | 0x06;
        public static final int IC_ADDRESS = CodePrefix.IC_PREFIX | 0x07;
        public static final int IC_CAPITTAL = CodePrefix.IC_PREFIX | 0x08;
    }
}
