package com.example.chungchunpay.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chungchunpay.R;
import com.example.chungchunpay.cart.CardData;
import com.example.chungchunpay.cart.Item;
import com.example.chungchunpay.cart.Shop;
import com.example.chungchunpay.cart.ShopAdapter;
import com.google.firebase.storage.FirebaseStorage;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;

import java.util.ArrayList;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment implements DiscreteScrollView.OnItemChangedListener{
    FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    ShopAdapter shopAdapter = new ShopAdapter();
    CardData cardData = new CardData();

    private ArrayList<Item> data;

    private Shop shop;
    private int position = 4;

    private TextView currentItemName;
    private TextView currentItemPrice;
    private ImageView rateItemButton;
    private DiscreteScrollView itemPicker;
    private InfiniteScrollAdapter infiniteAdapter;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences positionDATA = getActivity().getSharedPreferences("MungMuiData",MODE_PRIVATE);
        Map<String,?> ImageUrls = positionDATA.getAll();

        currentItemName = view.findViewById(R.id.NameText);

        for(Map.Entry<String,?> entry : ImageUrls.entrySet()){
            cardData.setItems(
                    entry.getKey(),
                    "test",
                    entry.getValue()+""
            );
        }

        itemPicker = view.findViewById(R.id.picker);

        itemPicker.setAdapter(shopAdapter);
        shopAdapter.setItems(cardData.getItems());

    }

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int i) {
        onItemChanged(cardData);
    }
    private void onItemChanged(CardData cardData) {
        currentItemName.setText(cardData.getName());
    }
}
