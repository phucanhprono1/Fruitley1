package com.example.fruitley;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.fruitley.adapter.RestaurantAdapter;
import com.example.fruitley.model.Food;
import com.example.fruitley.model.Restaurant;
import com.example.fruitley.model.User;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerlayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView phoneLabel,usernameLabel;
    View header;
    RecyclerView cartItemsRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        drawerlayout=findViewById(R.id.drawer_layout);
        navigationView =  findViewById(R.id.nav_view);
        header=navigationView.getHeaderView(0);
        toolbar= findViewById(R.id.toolbar);
        phoneLabel = header.findViewById(R.id.phonenumberLabel);
        usernameLabel =  header.findViewById(R.id.usernameLabel);



        //-------toolbar
        setSupportActionBar(toolbar);
        //---------Navigation drawer menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerlayout ,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        if(getIntent().getExtras()!=null) {
            User user = (User) getIntent().getExtras().get("object_user");
            usernameLabel.setText(user.getUsername());
            phoneLabel.setText(user.getPhoneNumber());
        }
        //---------main_menu(receiver_view)
        cartItemsRecyclerView=findViewById(R.id.cartItemsRecyclerView);


    }

    @Override
    public void onBackPressed() {
        if(drawerlayout.isDrawerOpen(GravityCompat.START)){
            drawerlayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.home:
                Intent intent1=new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent1);
                break;
            case R.id.cart:
                break;
            case R.id.log_out:
                Intent intent2=new Intent(Cart.this,MainActivity.class);
                startActivity(intent2);
                break;

        }

        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == 1000) {
            setResult(Activity.RESULT_OK);
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}