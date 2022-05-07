package com.example.fruitley.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserInfoDB extends SQLiteOpenHelper {
    public static String DBNAME="Login.db",DB_PATH="";
    public UserInfoDB(Context context){

        super(context,DBNAME,null,1);
//        if (android.os.Build.VERSION.SDK_INT >= 17) {
//            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
//        } else {
//            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
//        }
    }
    @Override
    public void onCreate(SQLiteDatabase MyDB){
        MyDB.execSQL("CREATE TABLE users(phoneNumber TEXT primary key," +
                "password TEXT," +
                "username TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("DROP TABLE IF EXISTS users");
    }
    public boolean changePassword(String phone,String pass){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("password",pass);
        int row=MyDB.update("users",values,"phoneNumber=?",new String[]{phone});
        if(row<=0)return false;
        return true;
    }
    public Boolean insertUser(String phoneNumber,String username,String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("phoneNumber",phoneNumber);
        values.put("username",username);
        values.put("password",password);
        long result = MyDB.insert("users",null,values);
        if (result==-1)return false;
        else return true;
    }
    public Boolean checkUsername(String username){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if(cursor.getCount()>0)return true;
        else return false;
    }

    public Boolean checkPhoneNumber(String phoneNumber){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where phoneNumber = ?", new String[]{phoneNumber});
        if(cursor.getCount()>0)return true;
        else return false;
    }
    public Boolean checkPassword(String phoneNumber,String password){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where phoneNumber = ? and password = ?", new String[]{phoneNumber,password});
        if(cursor.getCount()>0)return true;
        else return false;
    }
    public String getUsername(String phone){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where phoneNumber=?",new String[]{phone});
        cursor.moveToFirst();
        String username = cursor.getString(2).toString();
        return username;
    }
}