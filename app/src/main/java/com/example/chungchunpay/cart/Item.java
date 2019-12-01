package com.example.chungchunpay.cart;

public class Item {
    private final int id;
    private final String name;
    private final String price;
    private int image;
    private String imageUri;

    public Item(int id, String name, String price, int image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;

    }
    public Item(int id, String name, String price, String imageUri) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUri = imageUri;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }

    public String getImageUri() {
        return imageUri;
    }
}
