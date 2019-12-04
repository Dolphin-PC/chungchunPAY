package com.example.chungchunpay.cart;

import java.util.ArrayList;

public class CardData {

    ArrayList<Item> items = new ArrayList<>();
    private String name,text,imageurl;

    void CardData(){}

    void CardData(String name,String text, String imageurl){
        this.name = name;
        this.text = text;
        this.imageurl = imageurl;
    }



    public void setItems(String name, String text, String imageurl){
        try{
            if(!name.equals("Active_Mungmui") || !name.isEmpty()){
                items.add(new Item(1,name,text,imageurl));
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }
    public ArrayList<Item> getItems() {
        return items;
    }
    public String getName(int position){
        return items.get(position).getName();
    }

    public String getImageurl() {
        return imageurl;
    }
    public String getImageurl(int position) {
        return items.get(position).getImageUri();
    }

    public String getText() {
        return text;
    }
}
