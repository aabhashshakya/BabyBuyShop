package com.babybuy;

public class Lists {
    private int itemId;
    private String name;
    private String price;
    private String Dis;

    public Lists(int itemId, String name, String price, String dis) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        Dis = dis;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDis() {
        return Dis;
    }

    public void setDis(String dis) {
        Dis = dis;
    }
}
