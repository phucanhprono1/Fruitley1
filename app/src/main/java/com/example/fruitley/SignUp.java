package com.example.fruitley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fruitley.db.UserInfoDB;
import com.example.fruitley.model.User;

public class SignUp extends AppCompatActivity {
    EditText username,phoneNumber,password,repassword;
    Button btnRegister,btnLogin;
    UserInfoDB UserDB;
    String UserName;
    public String getUserName(){
        return UserName;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username = findViewById(R.id.username);
        phoneNumber = findViewById(R.id.phoneNumber);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        btnRegister=findViewById(R.id.btnRegisterNow);
        btnLogin = findViewById(R.id.btnLogIn);
        UserDB = new UserInfoDB(this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String user=username.getText().toString();
                String phone=phoneNumber.getText().toString();
                String pass=password.getText().toString();
                String repass=repassword.getText().toString();
                if(user.equals("") || phone.equals("") || pass.equals("") || repass.equals("")){
                    Toast.makeText(SignUp.this,"Please fill all these field",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(repass)){
                        Boolean checkUsername=UserDB.checkUsername(user);
                        Boolean checkPhone= UserDB.checkPhoneNumber(phone);
                        if(checkPhone==false){
                            Boolean insert=UserDB.insertUser(phone,user,pass);
                            if(insert==true){

                                Toast.makeText(SignUp.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),SignIn.class);
                                startActivity(intent);
                            }
                            else Toast.makeText(SignUp.this,"Registered fail",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(SignUp.this,"This Phone Number was used,please use another phone number",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(SignUp.this,"Password not matching",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplicationContext(),SignIn.class));
            }
        });
    }
}