package com.example.nawalproj.DataBase;

import android.provider.BaseColumns;

public class TablesString {

    public TablesString() {
    }
    //region Product Table
    public static class ProductTable implements BaseColumns {
        public static final String TABLE_PRODUCT = "Product";
        public static final String COLUMN_PRODUCT_TYPE = "ProductType";
        public static final String COLUMN_PRODUCT_YOP = "YearOfProduction";
        public static final String COLUMN_PRODUCT_DESCRIPTION = "Description";
        public static final String COLUMN_PRODUCT_CATEGORY = "Category";
        public static final String COLUMN_PRODUCT_IMAGE = "ProductImage";
        public static final String COLUMN_PRODUCT_STOCK = "Stock";
        public static final String COLUMN_PRODUCT_SALEPRICE = "SalePrice";
        public static final String COLUMN_PRODUCT_BUYPRICE = "BuyPrice";
        public static final String COLUMN_PRODUCT_KARAT = "Karat";
    }
    //endregion

    //region Auction Product Table
    public static class AuctionProductTable implements BaseColumns {
        public static final String TABLE_AUCTIONPRODUCT = "AuctionProduct";
        public static final String COLUMN_AUCTIONPRODUCT_TYPE = "ProductType";
        public static final String COLUMN_AUCTIONPRODUCT_DESCRIPTION = "Description";
        public static final String COLUMN_AUCTIONPRODUCT_IMAGE = "ProductImage";
        public static final String COLUMN_AUCTIONPRODUCT_MINPRICE = "MinPrice";
    }
    //endregion

    //region Cart Table
    public static class CartTable implements BaseColumns {
        public static final String TABLE_CART = "Cart";
        public static final String COLUMN_PRODUCT_ID = "PID";
        public static final String COLUMN_USER_ID = "UID";
        public static final String COLUMN_AMOUNT = "amount";

    }
    //endregion

    //region Favorite Table
    public static class FavoriteTable implements BaseColumns {
        public static final String TABLE_FAVORITE = "Favorite";
        public static final String COLUMN_PRODUCT_ID = "PID";
        public static final String COLUMN_USER_ID = "UID";

    }

    //region Sale Table
    public static class SaleTable implements BaseColumns {
        public static final String TABLE_SALE = "SALE";
        public static final String COLUMN_SALE_PROD_ID = "PID";
        public static final String COLUMN_SALE_USER_ID = "UID";

    }

}

