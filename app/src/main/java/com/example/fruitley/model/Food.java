package com.example.fruitley.model;

import static android.os.UserHandle.readFromParcel;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.math.BigDecimal;

public class Food  implements Parcelable {
    private String name,description;
    private int picId,totalInCart;
    private float price;

    public Food(String name, int picId, float price) {

        this.name = name;

        this.picId = picId;
        this.price = price;

    }


    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };
    public Food(Parcel in) {

        name=in.readString();
        totalInCart=in.readInt();
        picId=in.readInt();
        price= in.readFloat();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public int getTotalInCart() {
        return totalInCart;
    }

    public void setTotalInCart(int totalInCart) {
        this.totalInCart = totalInCart;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(totalInCart);
        dest.writeInt(picId);
        dest.writeFloat(price);
    }
}
