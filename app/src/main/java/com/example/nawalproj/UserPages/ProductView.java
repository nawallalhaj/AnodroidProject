package com.example.nawalproj.UserPages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.nawalproj.R;

public class ProductView extends AppCompatActivity {

    String selctedCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        Bundle extras = getIntent().getExtras();
        selctedCategory= extras.getString("Category");

    }
}