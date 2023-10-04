package com.example.nawalproj.Admin;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.nawalproj.Classes.Product;
import com.example.nawalproj.DataBase.DBHelper;
import com.example.nawalproj.R;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener {

    private static int RESULT_LOAD_IMAGE = 1;
    EditText etType,etdisc,etstock,etsaleprice,etbuyprice, etprodYOP, etkarat;
    ImageButton imageButton;
    Button btadd;
    Product p;
    Uri selectedImageUri;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        etType = findViewById(R.id.etprodType);
        etdisc = findViewById(R.id.etprodDisc);
        etprodYOP = findViewById(R.id.etprodYOP);
        etstock = findViewById(R.id.etStock);
        etsaleprice = findViewById(R.id.etSalePrice);
        etbuyprice = findViewById(R.id.etBuyPrice);
        etkarat= findViewById(R.id.etkarat);
        imageButton = findViewById(R.id.prodimg);
        btadd = findViewById(R.id.addButton);
        btadd.setOnClickListener(this);
        imageButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.addButton){
            dbHelper = new DBHelper(this);

            byte[] data  = imageViewToByte();
            p=new Product(etType.getText().toString(),
                    Integer.parseInt(etprodYOP.getText().toString()), imageViewToByte(),
                    Integer.parseInt(etstock.getText().toString()),
                    Double.parseDouble(etsaleprice.getText().toString()),
                    Double.parseDouble(etbuyprice.getText().toString()),
                    etdisc.getText().toString(), Integer.parseInt(etkarat.getText().toString()));
            dbHelper.OpenWriteAble();
            if(p.Add(dbHelper.getDb())>-1){
                Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show();
                dbHelper.Close();
            }

        }
        if(view.getId()==R.id.prodimg){
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
}