package com.example.nawalproj.UserPages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nawalproj.Admin.AddProductActivity;
import com.example.nawalproj.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    TextView username,email;
    NavigationView navigationView;
    FirebaseAuth fauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fauth = FirebaseAuth.getInstance();
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FirebaseUser user = fauth.getCurrentUser();
        if (user != null) {
            if(user.getDisplayName().startsWith("admin:")){
                Intent i = new Intent(MainActivity.this,AddProductActivity.class);
                startActivity(i);
            }
            // User is signed in
            View header = navigationView.getHeaderView(0);
            username = header.findViewById(R.id.tvUsername);
            email = header.findViewById(R.id.tvUserEmail);
            username.setText(user.getDisplayName());
            email.setText(user.getEmail());
        } else {
            // No user is signed in
            Intent i = new Intent( MainActivity.this,LoginActivity.class);
            startActivity(i);
        }
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new home()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(R.id.nav_home==item.getItemId()){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new home()).commit();

        }
        else if(R.id.nav_cart==item.getItemId()){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new cart()).commit();
        }
        else if(R.id.nav_info==item.getItemId()){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new info()).commit();
        }
        else if(R.id.nav_product==item.getItemId()){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new product()).commit();
        }
        else if(R.id.nav_logout==item.getItemId()){
            fauth.signOut();
            startActivity(new Intent(this,LoginActivity.class));
            Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}