package com.example.nawalproj.MainPages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import com.example.nawalproj.R;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i=new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(i);
            }
        }, 3000);
    }

}