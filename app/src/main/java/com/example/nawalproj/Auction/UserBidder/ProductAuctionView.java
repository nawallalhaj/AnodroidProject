package com.example.nawalproj.Auction.UserBidder;

import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_IMAGE;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_MINPRICE;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_TYPE;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_YOP;
import static com.example.nawalproj.DataBase.TablesString.ProductTable.COLUMN_PRODUCT_IMAGE;
import static com.example.nawalproj.DataBase.TablesString.ProductTable.COLUMN_PRODUCT_SALEPRICE;
import static com.example.nawalproj.DataBase.TablesString.ProductTable.COLUMN_PRODUCT_TYPE;
import static com.example.nawalproj.DataBase.TablesString.ProductTable.COLUMN_PRODUCT_YOP;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.widget.ImageView;

import com.example.nawalproj.Auction.AuctionProduct;
import com.example.nawalproj.Auction.AuctionAdapters.ProductAdapterAuction;
import com.example.nawalproj.Classes.ProductAdapter;
import com.example.nawalproj.DataBase.DBHelper;
import com.example.nawalproj.R;

import java.util.ArrayList;
import java.util.List;

public class ProductAuctionView extends AppCompatActivity {

    List<AuctionProduct> productList;
    RecyclerView recyclerView;
    ProductAdapterAuction mAdapter;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_auction_view);
        Bundle extras = getIntent().getExtras();
        recyclerView = findViewById(R.id.auctionMainRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        productList = new ArrayList<>();
        dbHelper = new DBHelper(this);
        dbHelper = dbHelper.OpenReadAble();
        AuctionProduct p = new AuctionProduct(), p2;
        Cursor c = p.Select(dbHelper.getDb());
        c.moveToFirst();
        while (!c.isAfterLast()) {
            p2 = new AuctionProduct(c.getInt(c.getColumnIndexOrThrow(BaseColumns._ID)),
                    c.getString(c.getColumnIndexOrThrow(COLUMN_AUCTIONPRODUCT_TYPE)),
                    c.getInt(c.getColumnIndexOrThrow(COLUMN_AUCTIONPRODUCT_YOP)),
                    c.getBlob(c.getColumnIndexOrThrow(COLUMN_AUCTIONPRODUCT_IMAGE)),
                    c.getDouble(c.getColumnIndexOrThrow(COLUMN_AUCTIONPRODUCT_MINPRICE)));
            productList.add(p2);
            c.moveToNext();
        }
        dbHelper.Close();
        // adapter
        mAdapter = new ProductAdapterAuction(this, productList);
        recyclerView.setAdapter(mAdapter)
        ;
    }
}