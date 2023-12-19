package com.example.nawalproj.Auction.AdminBidder;

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

import com.example.nawalproj.Auction.AuctionProduct;
import com.example.nawalproj.DataBase.DBHelper;
import com.example.nawalproj.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddAuctionProductActivity extends AppCompatActivity implements View.OnClickListener {

    private static int RESULT_LOAD_IMAGE = 1;
    EditText etType,etdisc,etminprice;
    ImageButton imageButton;
    Button btauctionadd,btdelete;
    AuctionProduct p;
    boolean SelectedNewImage;
    int SelectedId;
    byte[] image;
    Uri selectedImageUri;
    DBHelper dbHelper;
    ProgressBar addItemProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_auction_product);
        etType = findViewById(R.id.etAuctionprodType);
        etdisc = findViewById(R.id.etAuctionprodDisc);
        etminprice = findViewById(R.id.etAuctionMinPrice);
        addItemProgressBar=findViewById(R.id.addItemProgressBar);
        imageButton = findViewById(R.id.auctionprodimg);
        btauctionadd = findViewById(R.id.addToAuctionButton);
        btauctionadd.setOnClickListener(this);
        imageButton.setOnClickListener(this);
        btdelete = findViewById(R.id.btAuctionDelete);
        btdelete.setOnClickListener(this);
        dbHelper = new DBHelper(this);
        Intent i = getIntent();
        if(i.getStringExtra("Selected_Id")==null) {
            btdelete.setVisibility(View.GONE);
        }else {
            btauctionadd.setVisibility(View.GONE);
            SelectedId = Integer.parseInt(i.getStringExtra("Selected_Id"));
            SelectedNewImage=false;
            setAuctionProduct();
        }
    }

    private void setAuctionProduct() {
        dbHelper.OpenReadAble();
        p=new AuctionProduct();
        Cursor c = p.SelectById(dbHelper.getDb(),SelectedId);
        if(c!=null){
            c.moveToFirst();
            etType.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_AUCTIONPRODUCT_TYPE)));
            etdisc.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_AUCTIONPRODUCT_DESCRIPTION)));
            image = c.getBlob(c.getColumnIndexOrThrow(COLUMN_AUCTIONPRODUCT_IMAGE));
            etminprice.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_AUCTIONPRODUCT_MINPRICE)));
            Bitmap bm = BitmapFactory.decodeByteArray(image, 0 ,image.length);
            imageButton.setImageBitmap(bm);
        }
        dbHelper.Close();

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.addToAuctionButton){
            dbHelper.OpenWriteAble();
            addItemProgressBar.setVisibility(View.VISIBLE);
            byte[] data  = imageViewToByte();
            p=new AuctionProduct(etType.getText().toString(),
                    Double.parseDouble(etminprice.getText().toString()),
                    etdisc.getText().toString());
            dbHelper.OpenWriteAble();
            if(p.Add(dbHelper.getDb())>-1){
                Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show();
                dbHelper.Close();
                Intent i = new Intent(this, ShowAllAuctionProducts.class);
                startActivity(i);
            }
        }

        if(view.getId()==R.id.btAuctionDelete){
            dbHelper.OpenWriteAble();
            p.Delete(dbHelper.getDb(),SelectedId);
            dbHelper.Close();
            Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,ShowAllAuctionProducts.class);
            startActivity(i);
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
            SelectedNewImage = true;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}