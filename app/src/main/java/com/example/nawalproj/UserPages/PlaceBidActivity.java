package com.example.nawalproj.UserPages;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nawalproj.Classes.AuctionProduct;
import com.example.nawalproj.Classes.Product;
import com.example.nawalproj.DataBase.DBHelper;
import com.example.nawalproj.DataBase.MyBroadcastReceiver;
import com.example.nawalproj.MainPages.SignupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;

import com.example.nawalproj.R;


import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_DESCRIPTION;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_ENDTIME;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_IMAGE;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_MINPRICE;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_TYPE;
public class PlaceBidActivity extends AppCompatActivity implements View.OnClickListener {
    String selectedid;
    DBHelper dbHelper;
    CircleImageView imageView;
    TextView producttype;
    TextView productprice;
    TextView description,auctiontime,auctionisoverTV, whowon, wonprice;
    EditText enterbid;
    Button placebidbtn;
    AuctionProduct p;
    int SelectedId;
    double price;
    private long mTimeLeftInMillis;
    private long mEndTime;
    private boolean timeRunning;
    private CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_bid);
        auctionisoverTV = findViewById(R.id.tv_bidisover);
        whowon = findViewById(R.id.tv_whowon);
        wonprice = findViewById(R.id.tv_wonprice);
        imageView = findViewById(R.id.iv_auctionImage);
        producttype = findViewById(R.id.tv_jewelName);
        productprice = findViewById(R.id.tv_Price);
        description = findViewById(R.id.tv_Desc);
        enterbid = findViewById(R.id.et_enterBid);
        placebidbtn = findViewById(R.id.btn_placeBid);
        placebidbtn.setOnClickListener(this);
        auctiontime = findViewById(R.id.tv_hour);
        dbHelper = new DBHelper(this);
        selectedid = getIntent().getExtras().getString("id");
        setAProduct();
        startTimer();


    }
    private void startTimer(){
        mEndTime = System.currentTimeMillis()+mTimeLeftInMillis;
        countDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Used for formatting digit to be in 2 digits only
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            // When the task is over it will print 00:00:00 there
            @Override
            public void onFinish() {
                timeRunning=false;
                auctiontime.setText("00:00:00");
                enterbid.setVisibility(View.GONE);
                auctionisoverTV.setVisibility(View.VISIBLE);
                whowon.setVisibility(View.VISIBLE);
                wonprice.setText(productprice.getText());
                wonprice.setVisibility(View.VISIBLE);
                placebidbtn.setClickable(false);
                double highestBidPrice = Double.parseDouble(productprice.getText().toString());
                Intent auctionHourOverIntent = new Intent(MyBroadcastReceiver.AUCTION_HOUR_OVER_ACTION);
                auctionHourOverIntent.putExtra(MyBroadcastReceiver.EXTRA_HIGHEST_BID_PRICE, highestBidPrice);
                sendBroadcast(auctionHourOverIntent);

            }
        }.start();
        timeRunning=true;

    }
    private void updateCountDownText(){
        int hour =(int) (mTimeLeftInMillis / 3600000) % 24;
        int min = (int)(mTimeLeftInMillis / 1000) / 60;
        int sec = (int)(mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d", hour, min, sec);
        auctiontime.setText(timeLeftFormatted);
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
            mTimeLeftInMillis = c.getLong(c.getColumnIndexOrThrow(COLUMN_AUCTIONPRODUCT_ENDTIME))-System.currentTimeMillis();
            byte[] image = c.getBlob(c.getColumnIndexOrThrow(COLUMN_AUCTIONPRODUCT_IMAGE));
            Bitmap bm = BitmapFactory.decodeByteArray(image, 0 ,image.length);
            imageView.setImageBitmap(bm);
        }
        dbHelper.Close();

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
    @Override
    protected void onStop(){
        super.onStop();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor= prefs.edit();
        editor.putLong("millisLeft", mTimeLeftInMillis);
        editor.putBoolean("timerRunning", timeRunning);
        editor.putLong("endTime", mEndTime);
        editor.apply();
        if (countDownTimer!=null){
            countDownTimer.cancel();
        }
}
    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        timeRunning = prefs.getBoolean("timerRunning", false);
        updateCountDownText();
        if (timeRunning) {
            mEndTime = prefs.getLong("endTime", 0);
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();
            if (mTimeLeftInMillis < 0) {
                mTimeLeftInMillis = 0;
                timeRunning = false;
                updateCountDownText();
            } else {
                startTimer();
            }
        }
    }
}



