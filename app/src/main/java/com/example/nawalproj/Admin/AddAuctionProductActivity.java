package com.example.nawalproj.Admin;

import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_DESCRIPTION;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_IMAGE;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_MINPRICE;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_TYPE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nawalproj.Classes.AuctionProduct;
import com.example.nawalproj.DataBase.DBHelper;
import com.example.nawalproj.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddAuctionProductActivity extends AppCompatActivity implements View.OnClickListener {

    private static int RESULT_LOAD_IMAGE = 1;
    EditText etType,etdisc,etminprice;
    ImageButton imageButton;
    Button btauctionadd;
    AuctionProduct p;
    int SelectedId;
    byte[] image;
    Uri selectedImageUri;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_auction_product);
        etType = findViewById(R.id.etAuctionprodType);
        etdisc = findViewById(R.id.etAuctionprodDisc);
        etminprice = findViewById(R.id.etAuctionMinPrice);
        imageButton = findViewById(R.id.auctionprodimg);
        btauctionadd = findViewById(R.id.addToAuctionButton);
        btauctionadd.setOnClickListener(this);
        imageButton.setOnClickListener(this);
        dbHelper = new DBHelper(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.addToAuctionButton){
            dbHelper.OpenWriteAble();

            byte[] data  = imageViewToByte();
            p=new AuctionProduct(etType.getText().toString(), data,
                    Double.parseDouble(etminprice.getText().toString()),
                    etdisc.getText().toString());
            dbHelper.OpenWriteAble();
            if(p.Add(dbHelper.getDb())>-1){
                Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show();
            }
            dbHelper.Close();
        }

        if(view.getId()==R.id.auctionprodimg){
            Intent gallery = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery, RESULT_LOAD_IMAGE);
        }
    }
    public byte[] imageViewToByte() {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver() ,selectedImageUri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1){
            selectedImageUri = data.getData();
            imageButton.setImageURI(selectedImageUri);

        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}