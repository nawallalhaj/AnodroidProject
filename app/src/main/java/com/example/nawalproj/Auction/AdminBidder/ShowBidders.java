package com.example.nawalproj.Auction.AdminBidder;

import static com.example.nawalproj.DataBase.TablesString.AuctionBiddersTable.COLUMN_AUCTIONBIDDERPRODUCT_IMAGE;
import static com.example.nawalproj.DataBase.TablesString.AuctionBiddersTable.COLUMN_AUCTIONBIDDER_NAME;
import static com.example.nawalproj.DataBase.TablesString.AuctionBiddersTable.COLUMN_AUCTIONBIDDER_PRICE;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_TYPE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.ImageView;

import com.example.nawalproj.Auction.AuctionAdapters.ShowAllBiddersAdapter;
import com.example.nawalproj.Auction.AuctionBidders;

import com.example.nawalproj.DataBase.DBHelper;
import com.example.nawalproj.R;

import java.util.ArrayList;
import java.util.List;

public class ShowBidders extends AppCompatActivity {

    List<AuctionBidders> biddersList;
    RecyclerView recyclerView;
    ShowAllBiddersAdapter mAdapter;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bidders);
        Bundle extras = getIntent().getExtras();
        recyclerView = findViewById(R.id.showBiddersRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        biddersList = new ArrayList<>();
        dbHelper = new DBHelper(this);
        dbHelper = dbHelper.OpenReadAble();
        AuctionBidders p = new AuctionBidders(), p2;
        Cursor c = p.Select(dbHelper.getDb());
        c.moveToFirst();
        while (!c.isAfterLast()) {
            p2 = new AuctionBidders(c.getInt(c.getColumnIndexOrThrow(BaseColumns._ID)),
                    c.getString(c.getColumnIndexOrThrow(COLUMN_AUCTIONPRODUCT_TYPE)),
                    c.getString(c.getColumnIndexOrThrow(COLUMN_AUCTIONBIDDER_NAME)),
                    c.getBlob(c.getColumnIndexOrThrow(COLUMN_AUCTIONBIDDERPRODUCT_IMAGE)),
                    c.getDouble(c.getColumnIndexOrThrow(COLUMN_AUCTIONBIDDER_PRICE)));
            biddersList.add(p2);
            c.moveToNext();
        }
        dbHelper.Close();
        // adapter
        mAdapter = new ShowAllBiddersAdapter(this, biddersList);
        recyclerView.setAdapter(mAdapter)
        ;
    }
}