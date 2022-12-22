package com.babybuy.Models;

import android.net.Uri;

import androidx.annotation.Nullable;

public class Item {
    private String userID;
    private String name, description;
    private String imageURL;
    private String price;
    private Boolean isPurchased;

    public Item(String name, String description, String imageURL, String price) {
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.price = price;
    }

    public Item() {
    }

    public Item(String name, String description, String imageURL, String price, Boolean isPurchased) {
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.price = price;
        this.isPurchased = isPurchased;
    }

    public Item(String userID, String name, String description, String imageURL, String price, Boolean isPurchased) {
        this.userID = userID;
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.price = price;
        this.isPurchased = isPurchased;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public Boolean getIsPurchased() {
        return isPurchased;
    }

    public void setIsPurchased(Boolean isPurchased) {
        this.isPurchased = isPurchased;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
