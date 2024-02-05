package com.example.nawalproj.Admin;

import static com.example.nawalproj.DataBase.TablesString.ProductTable.COLUMN_PRODUCT_BUYPRICE;
import static com.example.nawalproj.DataBase.TablesString.ProductTable.COLUMN_PRODUCT_CATEGORY;
import static com.example.nawalproj.DataBase.TablesString.ProductTable.COLUMN_PRODUCT_DESCRIPTION;
import static com.example.nawalproj.DataBase.TablesString.ProductTable.COLUMN_PRODUCT_IMAGE;
import static com.example.nawalproj.DataBase.TablesString.ProductTable.COLUMN_PRODUCT_KARAT;
import static com.example.nawalproj.DataBase.TablesString.ProductTable.COLUMN_PRODUCT_SALEPRICE;
import static com.example.nawalproj.DataBase.TablesString.ProductTable.COLUMN_PRODUCT_STOCK;
import static com.example.nawalproj.DataBase.TablesString.ProductTable.COLUMN_PRODUCT_TYPE;
import static com.example.nawalproj.DataBase.TablesString.ProductTable.COLUMN_PRODUCT_YOP;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.nawalproj.Classes.Product;
import com.example.nawalproj.DataBase.DBHelper;
import com.example.nawalproj.R;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import android.widget.ProgressBar;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private static int RESULT_LOAD_IMAGE = 1;
    EditText etType,etdisc,etstock,etsaleprice,etbuyprice, etprodYOP, etkarat;
    ImageButton imageButton;
    Button btadd,btupdate,btdelete;
    Product p;
    boolean SelectedNewImage;
    int SelectedId;
    byte[] image;
    Uri selectedImageUri;
    DBHelper dbHelper;
    ProgressBar addItemProgressBar;
    Spinner catspinner;
    ArrayAdapter ad;
    String selectedcategory="";
    String[] Category={"Choose Category ...","Ring","Bracelet","Earring","Necklace"};
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
        addItemProgressBar=findViewById(R.id.addItemProgressBar);
        imageButton = findViewById(R.id.prodimg);
        btadd = findViewById(R.id.addButton);
        btadd.setOnClickListener(this);
        imageButton.setOnClickListener(this);
        btupdate = findViewById(R.id.btUpdate);
        btupdate.setOnClickListener(this);
        btdelete = findViewById(R.id.btDelete);
        btdelete.setOnClickListener(this);
        catspinner = findViewById(R.id.SpCategory);
        ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Category);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catspinner.setAdapter(ad);
        catspinner.setOnItemSelectedListener(this);
        dbHelper = new DBHelper(this);
        Intent i = getIntent();
        if(i.getStringExtra("Selected_Id")==null) {
            btdelete.setVisibility(View.GONE);
            btupdate.setVisibility(View.GONE);
        }else {
                btadd.setVisibility(View.GONE);
                SelectedId = Integer.parseInt(i.getStringExtra("Selected_Id"));
                 SelectedNewImage=false;
                setProduct();
            }
        }

    private void setProduct() {
        dbHelper.OpenReadAble();
        p=new Product();
        Cursor c = p.SelectById(dbHelper.getDb(),SelectedId);
        if(c!=null){
            c.moveToFirst();
            etType.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_TYPE)));
            etprodYOP.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_YOP)));
            etdisc.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_DESCRIPTION)));
            image = c.getBlob(c.getColumnIndexOrThrow(COLUMN_PRODUCT_IMAGE));
            etbuyprice.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_BUYPRICE)));
            etsaleprice.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_SALEPRICE)));
            etstock.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_STOCK)));
            etkarat.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_KARAT)));
            selectedcategory = (c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_CATEGORY)));
            catspinner.setSelection(ad.getPosition(selectedcategory));
            Bitmap bm = BitmapFactory.decodeByteArray(image, 0 ,image.length);
            imageButton.setImageBitmap(bm);
        }
        dbHelper.Close();

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.addButton){
            dbHelper.OpenWriteAble();
            addItemProgressBar.setVisibility(View.VISIBLE);
            byte[] data  = imageViewToByte();
            p=new Product(etType.getText().toString(),
                    Integer.parseInt(etprodYOP.getText().toString()), data,
                    Integer.parseInt(etstock.getText().toString()),
                    Double.parseDouble(etsaleprice.getText().toString()),
                    Double.parseDouble(etbuyprice.getText().toString()),
                    etdisc.getText().toString(), Integer.parseInt(etkarat.getText().toString()),selectedcategory);
            dbHelper.OpenWriteAble();
            if(p.Add(dbHelper.getDb())>-1){
                Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show();
                dbHelper.Close();
                Intent i = new Intent(this, ShowProduct.class);
                startActivity(i);
            }
        }
        if(view.getId()==R.id.btUpdate){
            p=new Product();
        p.setPid(SelectedId);
        p.setProdType(etType.getText().toString());
        p.setProdDisc(etdisc.getText().toString());
        p.setBuyprice(Double.parseDouble(etbuyprice.getText().toString()));
        p.setSalesprice(Double.parseDouble(etsaleprice.getText().toString()));
        p.setStock(Integer.parseInt(etstock.getText().toString()));
        p.setKarat(Integer.parseInt(etkarat.getText().toString()));
        p.setProdYOP(Integer.parseInt(etprodYOP.getText().toString()));
        p.setCategory(selectedcategory);
        if(SelectedNewImage)
            p.setProdimg(imageViewToByte());
        else{
            p.setProdimg(image);
        }
        dbHelper.OpenWriteAble();
        if(p.Update(dbHelper.getDb(),SelectedId)>0){
            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,ShowProduct.class);
            startActivity(i);
        }
        dbHelper.Close();

       }
        if(view.getId()==R.id.btDelete){
        dbHelper.OpenWriteAble();
        p.Delete(dbHelper.getDb(),SelectedId);
        dbHelper.Close();
        Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,ShowProduct.class);
        startActivity(i);
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
            SelectedNewImage = true;
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedcategory = Category[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}