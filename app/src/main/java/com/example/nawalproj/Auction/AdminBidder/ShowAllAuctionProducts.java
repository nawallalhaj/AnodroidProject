package com.example.nawalproj.Auction.AdminBidder;

import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_DESCRIPTION;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_IMAGE;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_MINPRICE;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_TYPE;
import static com.example.nawalproj.DataBase.TablesString.ProductTable.COLUMN_PRODUCT_DESCRIPTION;
import static com.example.nawalproj.DataBase.TablesString.ProductTable.COLUMN_PRODUCT_IMAGE;
import static com.example.nawalproj.DataBase.TablesString.ProductTable.COLUMN_PRODUCT_SALEPRICE;
import static com.example.nawalproj.DataBase.TablesString.ProductTable.COLUMN_PRODUCT_TYPE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.nawalproj.Auction.AuctionProduct;
import com.example.nawalproj.Auction.AuctionAdapters.ListAuctionAdapter;
import com.example.nawalproj.DataBase.DBHelper;
import com.example.nawalproj.R;
import com.google.firebase.auth.FirebaseAuth;

public class ShowAllAuctionProducts extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    ListView productListview;
    Button addnew, bidder;
    String [] product_string;
    DBHelper db;
    AuctionProduct p;
    AuctionProduct[] product_info;
    AuctionProduct selected_product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_auction_products);
        productListview = findViewById(R.id.lvauctionproduct);
        productListview.setOnItemClickListener(this);
        addnew = findViewById(R.id.btAddToAuctionProd);
        addnew.setOnClickListener(this);
        bidder = findViewById(R.id.btShowAllBidders);
        bidder.setOnClickListener(this);
        db = new DBHelper(getApplicationContext());
        p = new AuctionProduct();
        getProductToArray();
        ListAuctionAdapter adapter = new ListAuctionAdapter(this,product_info);
        productListview.setAdapter(adapter);

    }
    public void getProductToArray(){
        db.OpenReadAble();
        Cursor c = p.Select(db.getDb());
        if(c.getCount()>0){
            product_string = new String[c.getCount()];
            product_info =  new AuctionProduct[c.getCount()];
            int i =0;
            c.moveToFirst();
            while(!c.isAfterLast()){
                p.setPid(c.getInt(c.getColumnIndexOrThrow(BaseColumns._ID)));
                p.setProdType(c.getString(c.getColumnIndexOrThrow(COLUMN_AUCTIONPRODUCT_TYPE)));
                p.setProdDisc(c.getString(c.getColumnIndexOrThrow(COLUMN_AUCTIONPRODUCT_DESCRIPTION)));
                p.setMinprice(c.getDouble(c.getColumnIndexOrThrow(COLUMN_AUCTIONPRODUCT_MINPRICE)));
                p.setProdimg(c.getBlob(c.getColumnIndexOrThrow(COLUMN_AUCTIONPRODUCT_IMAGE)));
                product_info[i]=new AuctionProduct(p);
                product_string[i++] = p.toString();
                c.moveToNext();
            }
        }
        db.Close();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        selected_product = product_info[i];
        Intent in = new Intent(this, AddAuctionProductActivity.class);
        in.putExtra("Selected_Id", selected_product.getPid()+"");
        startActivity(in);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btAddToAuctionProd) {
            Intent in = new Intent(this, AddAuctionProductActivity.class);
            startActivity(in);
        } else if (view.getId() == R.id.btShowAllBidders) {
            Intent in = new Intent(this, ShowBidders.class);
            startActivity(in);
        }
    }

    @Override
    public void onStop() {
        FirebaseAuth fauth = FirebaseAuth.getInstance();
        fauth.signOut();
        super.onStop();
    }
}