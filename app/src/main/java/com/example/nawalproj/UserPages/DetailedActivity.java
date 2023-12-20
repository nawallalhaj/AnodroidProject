package com.example.nawalproj.UserPages;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nawalproj.Classes.Cart;
import com.example.nawalproj.Classes.Product;
import com.example.nawalproj.DataBase.DBHelper;
import com.example.nawalproj.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.nawalproj.DataBase.TablesString.ProductTable.COLUMN_PRODUCT_BUYPRICE;
import static com.example.nawalproj.DataBase.TablesString.ProductTable.COLUMN_PRODUCT_DESCRIPTION;
import static com.example.nawalproj.DataBase.TablesString.ProductTable.COLUMN_PRODUCT_IMAGE;
import static com.example.nawalproj.DataBase.TablesString.ProductTable.COLUMN_PRODUCT_KARAT;
import static com.example.nawalproj.DataBase.TablesString.ProductTable.COLUMN_PRODUCT_SALEPRICE;
import static com.example.nawalproj.DataBase.TablesString.ProductTable.COLUMN_PRODUCT_STOCK;
import static com.example.nawalproj.DataBase.TablesString.ProductTable.COLUMN_PRODUCT_TYPE;

public class DetailedActivity extends AppCompatActivity {
    ImageView imageView;
    ImageButton plusquantity, minusquantity;
    TextView quantitynumber;
    TextView producttype;
    TextView productprice;
    TextView description;
    TextView karat;
    Button addtoCart;
    int quantity,stock;
    double basePrice = 0;
    DBHelper dbHelper;
    String selectedid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        imageView = findViewById(R.id.detailActivityJewelryIV);
        plusquantity = findViewById(R.id.addquantity);
        minusquantity = findViewById(R.id.subquantity);
        quantitynumber = findViewById(R.id.quantity);
        producttype = findViewById(R.id.detailActivityJewelryTypeTv);
        productprice =findViewById(R.id.detailActivityJewelryPriceTv);
        karat = findViewById(R.id.detailActivityJewelryKaratTv);
        addtoCart = findViewById(R.id.detailActivityAddToCartBtn);
        description = findViewById(R.id.descriptionTV);
        dbHelper = new DBHelper(this);
        selectedid = getIntent().getExtras().getString("id");
        setProduct();

        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity==0) {
                    quantity++;
                    displayQuantity();
                    double prodPrice = basePrice * quantity;
                    prodPrice = basePrice;
                    productprice.setText(prodPrice + "₪");
                    SaveCart();
                }else
                    SaveCart();

            }
        });

        /*
         * plus button for quaninty of product that buy him
         */
        plusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // coffee price
                if (quantity == stock)
                    Toast.makeText(getBaseContext(), "We dont have more in stock", Toast.LENGTH_SHORT).show();
                else {
                    quantity++;
                    displayQuantity();
                    double prodPrice = basePrice * quantity;
                    if(quantity==0)
                        prodPrice = basePrice;
                    productprice.setText(prodPrice +"₪" );
                }

            }
        });

        minusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // because we dont want the quantity go less than 0
                if (quantity == 0) {
                    Toast.makeText(getBaseContext(), "Cant decrease quantity < 0", Toast.LENGTH_SHORT).show();
                } else {
                    quantity--;
                    displayQuantity();
                    double prodPrice = basePrice * quantity;
                    if(quantity==0)
                        prodPrice = basePrice;
                    productprice.setText(prodPrice +"₪" );
                }
            }
        });

    }
    private void setProduct() {

        dbHelper.OpenReadAble();
        Product p=new Product();
        Cursor c = p.SelectById(dbHelper.getDb(),Integer.parseInt(selectedid));
        if(c!=null){
            c.moveToFirst();
            producttype.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_TYPE)));
            description.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_DESCRIPTION)));
            productprice.setText(c.getDouble(c.getColumnIndexOrThrow(COLUMN_PRODUCT_SALEPRICE))+"₪");
            basePrice=c.getDouble(c.getColumnIndexOrThrow(COLUMN_PRODUCT_SALEPRICE));
            karat.setText(c.getString(c.getColumnIndexOrThrow(COLUMN_PRODUCT_KARAT))+"K");
            stock = c.getInt(c.getColumnIndexOrThrow(COLUMN_PRODUCT_STOCK));
            byte[] image = c.getBlob(c.getColumnIndexOrThrow(COLUMN_PRODUCT_IMAGE));
            Bitmap bm = BitmapFactory.decodeByteArray(image, 0 ,image.length);
            imageView.setImageBitmap(bm);
        }
        dbHelper.Close();

    }
    private void SaveCart() {
        FirebaseAuth fauth = FirebaseAuth.getInstance();
        FirebaseUser curruser = fauth.getCurrentUser();
        // getting the values from our views
        dbHelper.OpenWriteAble();
        Cart cart = new Cart(curruser.getUid(), Integer.parseInt(selectedid),quantity);
        cart.Add(dbHelper.getDb());
        dbHelper.Close();
        Toast.makeText(getBaseContext(), "Added To Cart", Toast.LENGTH_SHORT).show();


    }
    private void displayQuantity() {
        quantitynumber.setText(String.valueOf(quantity));
    }

}