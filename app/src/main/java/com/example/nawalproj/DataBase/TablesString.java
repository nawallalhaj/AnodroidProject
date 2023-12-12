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

    public static class AuctionTable implements BaseColumns{
        public static final String TABLE_AUCTION = "AUCTION";
        public static final String COLUMN_AUCTION_PROD_ID = "PID";
        public static final String COLUMN_AUCTION_MINIMALPRICE = "MINIMALPRICE";
        public static final String COLUMN_AUCTION_RUNNIGTIME = "RUNNINGTIME";
        public static final String COLUMN_AUCTION_STARTINGHOUR = "STARTINGHOUR";
        public static final String COLUMN_AUCTION_STARTINGDAY = "STARTINGDAY";

    }
    public static class AuctionParticipantsTable implements BaseColumns{
        public static final String TABLE_AUCTIONP = "AUCTIONPARTICIPANTS";
        public static final String COLUMN_AUCTIONP_USER_ID = "UID";
        public static final String COLUMN_AUCTIONP_PAIDPRICE = "PAIDPRICE";


    }
    //endregion
}

