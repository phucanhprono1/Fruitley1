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
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fruitley.adapter.PlaceYourOrderAdapter;
import com.example.fruitley.adapter.RestaurantAdapter;
import com.example.fruitley.model.Food;
import com.example.fruitley.model.Restaurant;
import com.example.fruitley.model.User;
import com.google.android.material.navigation.NavigationView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerlayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private TextView phoneLabel,usernameLabel;
    private View header;
    private float delivery_charge;



    private RecyclerView cartItemsRecyclerView;
    private PlaceYourOrderAdapter placeYourOrderAdapter;
    private EditText inputAddress,City;
    private TextView tvSubtotalAmount,totalAmount,tvDeliveryChargeAmount;



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
        inputAddress = findViewById(R.id.inputAddress);
        City=findViewById(R.id.inputCity);
        tvSubtotalAmount=findViewById(R.id.tvSubtotalAmount);
        totalAmount=findViewById(R.id.tvTotalAmount);
        tvDeliveryChargeAmount=findViewById(R.id.tvDeliveryChargeAmount);




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
        Restaurant restaurant = getIntent().getParcelableExtra("rest");
        cartItemsRecyclerView=findViewById(R.id.cartItemsRecyclerView);
        cartItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        placeYourOrderAdapter = new PlaceYourOrderAdapter(restaurant.getMenus());
        cartItemsRecyclerView.setAdapter(placeYourOrderAdapter);
        calculateTotalAmount(restaurant);




    }
    private void onPlaceOrderButtonClick(Restaurant restaurant) {
        if(TextUtils.isEmpty(inputAddress.getText().toString())) {
            inputAddress.setError("Please enter address ");
            return;
        }
        //start success activity..
        Intent i = new Intent(Cart.this, OrderSuccessful.class);
        i.putExtra("rest", restaurant);
        startActivityForResult(i, 1000);
    }
    @Override
    public void onBackPressed() {
        if(drawerlayout.isDrawerOpen(GravityCompat.START)){
            drawerlayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
//            setResult(Activity.RESULT_CANCELED);
//            finish();
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
    private void calculateTotalAmount(Restaurant restaurant) {
        float subTotalAmount = 0f;
        delivery_charge=0f;

        for(Food m : restaurant.getMenus()) {
            subTotalAmount += m.getPrice() * m.getTotalInCart();
        }

        tvSubtotalAmount.setText("$"+String.format("%.2f", subTotalAmount));

        if(subTotalAmount<200000) {
            delivery_charge=20000.00f;
            tvDeliveryChargeAmount.setText("$"+String.format("%.2f", delivery_charge));
            subTotalAmount += delivery_charge;
        }else if(subTotalAmount<400000){
            delivery_charge=10000.00f;
            tvDeliveryChargeAmount.setText("$"+String.format("%.2f", delivery_charge));
            subTotalAmount += delivery_charge;
        }
        else{
            delivery_charge=0f;
            tvDeliveryChargeAmount.setText("$"+String.format("%.2f", delivery_charge));
            subTotalAmount += delivery_charge;
        }
        totalAmount.setText("$"+String.format("%.2f", subTotalAmount));
    }
}