package com.example.nawalproj.MainPages;

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

import com.example.nawalproj.Admin.AddAuctionProductActivity;
import com.example.nawalproj.Admin.ShowProduct;
import com.example.nawalproj.Classes.AuctionProduct;
import com.example.nawalproj.DataBase.DBHelper;
import com.example.nawalproj.R;
import com.example.nawalproj.UserPages.CareTips;
import com.example.nawalproj.UserPages.CartFragment;
import com.example.nawalproj.UserPages.ContactUs;
import com.example.nawalproj.UserPages.HomeFragment;
import com.example.nawalproj.UserPages.InfoApp;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.nawalproj.DataBase.QueryString.SQL_CREATE_AUCTIONPRODUCT;
import static com.example.nawalproj.DataBase.QueryString.SQL_CREATE_FAVORITE;
import static com.example.nawalproj.DataBase.QueryString.SQL_DELETE_AUCTIONPRODUCT;
import static com.example.nawalproj.DataBase.QueryString.SQL_DELETE_FAVORITE;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.TABLE_AUCTIONPRODUCT;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    TextView username,email;
    NavigationView navigationView;
    FirebaseAuth fauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* DBHelper dbHelper = new DBHelper(this);
        dbHelper.OpenWriteAble();
        dbHelper.getDb().execSQL(SQL_DELETE_AUCTIONPRODUCT);
        dbHelper.getDb().execSQL(SQL_CREATE_AUCTIONPRODUCT);
        //dbHelper.getDb().execSQL("ALTER TABLE "+TABLE_AUCTIONPRODUCT+" ADD CurrentTime INTEGER;");
       // new AuctionProduct().Delete(dbHelper.getDb(),1);
        dbHelper.Close();*/
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fauth = FirebaseAuth.getInstance();
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FirebaseUser user = fauth.getCurrentUser();
        if (user != null) {
            if(user.getDisplayName().startsWith("admin:")){
                Intent i = new Intent(MainActivity.this, ShowProduct.class);
                startActivity(i);
            }
            // Auction
            else if(user.getDisplayName().startsWith("bidder:")){
                    Intent i = new Intent(MainActivity.this, AddAuctionProductActivity.class);
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
            Intent i = new Intent( MainActivity.this, LoginActivity.class);
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
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(R.id.nav_home==item.getItemId()){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        }
        else if(R.id.nav_cart==item.getItemId()){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CartFragment()).commit();
        }
        else if(R.id.nav_info==item.getItemId()){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InfoApp()).commit();
        }
        else if(R.id.nav_contactus==item.getItemId()){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContactUs()).commit();
        }
        else if(R.id.nav_tips==item.getItemId()){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CareTips()).commit();
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