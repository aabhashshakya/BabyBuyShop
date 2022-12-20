package com.babybuy.Models;

import android.net.Uri;

import androidx.annotation.Nullable;

public class Item {
    private String name, description;
    private String imageURL;
    private String price;

    public Item(String name, String description, String imageURL, String price) {
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.price = price;
    }

    public Item() {
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
