package com.example.nawalproj.UserPages;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nawalproj.Classes.AuctionProduct;
import com.example.nawalproj.Classes.Product;
import com.example.nawalproj.DataBase.DBHelper;
import com.example.nawalproj.MainPages.SignupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import com.example.nawalproj.R;


import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_DESCRIPTION;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_IMAGE;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_MINPRICE;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_TIME;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_TYPE;
public class PlaceBidActivity extends AppCompatActivity {
    String selectedid;
    DBHelper dbHelper;
    CircleImageView imageView;
    TextView producttype;
    TextView productprice;
    TextView description,auctiontime;
    EditText enterbid;
    Button placebidbtn;
    AuctionProduct p;
    int SelectedId;
    double price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_bid);

        imageView = findViewById(R.id.iv_auctionImage);
        producttype = findViewById(R.id.tv_jewelName);
        productprice = findViewById(R.id.tv_Price);
        description = findViewById(R.id.tv_Desc);
        enterbid = findViewById(R.id.et_enterBid);
        placebidbtn = findViewById(R.id.btn_placeBid);
        placebidbtn.setOnClickListener(this::onClick);
        auctiontime = findViewById(R.id.tv_hour);
        dbHelper = new DBHelper(this);
        selectedid = getIntent().getExtras().getString("id");
        setAProduct();
        new CountDownTimer(50000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Used for formatting digit to be in 2 digits only
                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                auctiontime.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
            }
            // When the task is over it will print 00:00:00 there
            public void onFinish() {
                auctiontime.setText("00:00:00");
            }
        }.start();
        }
    public void onClick(View view) {
        if(view.getId()==R.id.btn_placeBid){
            if(Double.parseDouble(enterbid.getText().toString()) > price){
            p=new AuctionProduct();
            p.setMinprice(Double.parseDouble(enterbid.getText().toString()));
            dbHelper.OpenWriteAble();
            if(p.Update(dbHelper.getDb(),SelectedId)>-1)
                Toast.makeText(this, "Bid Added Successfully", Toast.LENGTH_SHORT).show();
            dbHelper.Close();
            price = p.getMinprice();
            productprice.setText(enterbid.getText().toString()+"₪");
            enterbid.setText("");

            }
            else
                Toast.makeText(this, "You have to give a price more than minmum price", Toast.LENGTH_SHORT).show();
        }

        }

        private void setAProduct() {
        dbHelper.OpenReadAble();
        AuctionProduct p=new AuctionProduct();
        Cursor c = p.SelectById(dbHelper.getDb(),Integer.parseInt(selectedid));
        if(c!=null){
            c.moveToFirst();
            producttype.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_AUCTIONPRODUCT_TYPE)));
            description.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_AUCTIONPRODUCT_DESCRIPTION)));
            price = c.getDouble(c.getColumnIndexOrThrow(COLUMN_AUCTIONPRODUCT_MINPRICE));
            productprice.setText(c.getDouble(c.getColumnIndexOrThrow(COLUMN_AUCTIONPRODUCT_MINPRICE))+"₪");

            byte[] image = c.getBlob(c.getColumnIndexOrThrow(COLUMN_AUCTIONPRODUCT_IMAGE));
            Bitmap bm = BitmapFactory.decodeByteArray(image, 0 ,image.length);
            imageView.setImageBitmap(bm);
        }
        dbHelper.Close();

    }

    }

