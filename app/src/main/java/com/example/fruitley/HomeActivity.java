package com.example.fruitley;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fruitley.adapter.RestaurantAdapter;
import com.example.fruitley.model.Food;
import com.example.fruitley.model.Restaurant;
import com.example.fruitley.model.User;
import com.google.android.material.navigation.NavigationView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, RestaurantAdapter.RestaurantClickListener {
    DrawerLayout drawerlayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView phoneLabel,usernameLabel;
    View header;
    RecyclerView rcv_rest;
    ImageView img;
    private RestaurantAdapter restaurantAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //------Hook

        drawerlayout=findViewById(R.id.drawer_layout);
        navigationView =  findViewById(R.id.nav_view);
        header=navigationView.getHeaderView(0);
        toolbar= findViewById(R.id.toolbar);
        phoneLabel = header.findViewById(R.id.phonenumberLabel);
        usernameLabel =  header.findViewById(R.id.usernameLabel);
        img=findViewById(R.id.imageView);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,MainActivity2.class));
            }
        });



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
        rcv_rest=findViewById(R.id.rcv_view);
        restaurantAdapter=new RestaurantAdapter(getListRestaurant(),this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcv_rest.setLayoutManager(linearLayoutManager);
        restaurantAdapter.setData(getListRestaurant());
        rcv_rest.setAdapter(restaurantAdapter);


    }
    public List<Food> getCole(){
        List<Food> list = new ArrayList<>();
        list.add(new Food("C??t l???n x??o me", R.drawable.cole_cutlonxaome,30000.00f));
        list.add(new Food("Nem n?????ng size S", R.drawable.nem_nuong,35000.00f));
        list.add(new Food("Nem n?????ng size M", R.drawable.nem_nuong,40000.00f));
        list.add(new Food("Nem n?????ng size L", R.drawable.nem_nuong,55000.00f));
        list.add(new Food("Ph???ng t??m", R.drawable.phong_tom,20000.00f));
        list.add(new Food("Tr?? t???c",R.drawable.tra_tac,10000.00f));
        return list;
    }
    public List<Food> getBone(){
        List<Food> list = new ArrayList<>();
        list.add(new Food("B??nh m?? ch???o", R.drawable.banh_mi_chao, 30000.00f));
        list.add(new Food("C??m t???m s?????n b??", R.drawable.com_tam_suon_bi,35000.00f));
        list.add(new Food("B?? n?? 1", R.drawable.bo_ne_1,40000.00f));
        list.add(new Food("B?? n?? 2", R.drawable.bo_ne_1,50000.00f));
        list.add(new Food("Ph??? b?? kho", R.drawable.pho_bo_kho,40000.00f));
        list.add(new Food("B??nh m?? b?? kho", R.drawable.banh_mi_bo_kho,40000.00f));
        return list;
    }
    public List<Food> getBimbim(){
        List<Food> list = new ArrayList<>();
        list.add(new Food("Kimbab", R.drawable.kimbab_thuong,20000.00f));
        list.add(new Food("G?? vi??n chi??n s???t cay", R.drawable.ga_vien_chien,60000.00f));
        list.add(new Food("Mi???n tr???n th?????ng", R.drawable.mien_tron_thuong,10000.00f));
        list.add(new Food("C??m g?? chi??n BBQ", R.drawable.com_ga_chien,45000.00f));

        list.add(new Food("L???u Teobbokki", R.drawable.lau_teobbokki,150000.00f));
        list.add(new Food("L???u xi??n que", R.drawable.lau_xien,100000.00f));
        return list;
    }
    private List<Restaurant> getListRestaurant(){
        List<Restaurant> list=new ArrayList<>();

        list.add(new Restaurant(R.drawable.quan_cole,R.drawable.quan_cole_bg,"Qu??n C?? L??","16B Ng?? 3 Ao Sen, Qu???n H?? ????ng, H?? N???i","Hotline: "+"0974 315 130",getCole()));
        list.add(new Restaurant(R.drawable.bo_ne_mien, R.drawable.bo_nemien_bg,"B?? N?? Mi??n","2 Ng?? 2 Ao Sen, Qu???n H?? ????ng, H?? N???i","Hotline: "+"0869 378 707",getBone()));
        list.add(new Restaurant(R.drawable.bimbim_bap,R.drawable.bim_bim_bap_bg,"Bim Bim Bap","12B Ng?? 4 Ao Sen, Qu???n H?? ????ng, H?? N???i","Hotline: "+"0389 639 669",getBimbim()));


        return list;
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
                break;
            case R.id.cart:
                Intent intent1=new Intent(getApplicationContext(),Cart.class);
                startActivity(intent1);
                break;
            case R.id.log_out:
                Intent intent2=new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent2);
                break;

        }

        return true;
    }
    @Override
    public void onItemClick(Restaurant restaurant) {
        Intent intent = new Intent(HomeActivity.this, RestaurantMenuActivity.class);

        intent.putExtra("rest",restaurant);
        startActivity(intent);

    }
}