package com.example.nawalproj.UserPages;

import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_IMAGE;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_MINPRICE;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_TYPE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.nawalproj.Classes.AuctionProduct;
import com.example.nawalproj.Classes.ProductAdapterAuction;
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
                    c.getBlob(c.getColumnIndexOrThrow(COLUMN_AUCTIONPRODUCT_IMAGE)),
                    c.getDouble(c.getColumnIndexOrThrow(COLUMN_AUCTIONPRODUCT_MINPRICE)));
            productList.add(p2);
            c.moveToNext();
        }
        dbHelper.Close();
        // adapter
        mAdapter = new ProductAdapterAuction(this, productList);
        recyclerView.setAdapter(mAdapter);
    }
}