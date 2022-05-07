
package com.example.fruitley;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fruitley.db.UserInfoDB;
import com.example.fruitley.model.User;

public class SignIn extends AppCompatActivity {
//    private static final int MY_REQUEST_CODE = 10;
    EditText phoneNumber,password1;
    Button login;
    UserInfoDB DB;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        phoneNumber = findViewById(R.id.phoneNumber1);
        password1 = findViewById(R.id.password1);
        login = findViewById(R.id.btnLogin);
        register=findViewById(R.id.register);
        DB = new UserInfoDB(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneNumber.getText().toString();
                String pass1 = password1.getText().toString();
                if (phone.equals("") || pass1.equals("")) {
                    Toast.makeText(SignIn.this, "Please fill all these field", Toast.LENGTH_SHORT).show();

                } else {
                    Boolean checkValid = DB.checkPassword(phone, pass1);
                    if (checkValid == true) {
                        String username = DB.getUsername(phone);
                        User us = new User(username, phone, pass1);
                        Toast.makeText(SignIn.this, "Log In successfully", Toast.LENGTH_SHORT).show();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("object_user", us);

                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else
                        Toast.makeText(SignIn.this, "Invalid phone number or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });
    }
}