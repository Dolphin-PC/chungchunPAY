package com.example.chungchunpay.cart;

import android.content.SharedPreferences;

public class Shop {
    private static final String STORAGE = "Shop";

    public static Shop get() {
        return new Shop();
    }

    private SharedPreferences storage;

    private Shop() {
//        storage = App.getInstance().getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
    }

//    public List<Item> getData() {
//        return Arrays.asList(
//                new Item(1, "Everyday Candle", "$12.00 USD", R.drawable.main),
//                new Item(2, "Small Porcelain Bowl", "$50.00 USD", R.drawable.main2),
//                new Item(3, "Favourite Board", "$265.00 USD", R.drawable.main3));
//    }

    public boolean isRated(int itemId) {
        return storage.getBoolean(String.valueOf(itemId), false);
    }

    public void setRated(int itemId, boolean isRated) {
        storage.edit().putBoolean(String.valueOf(itemId), isRated).apply();
    }
}
