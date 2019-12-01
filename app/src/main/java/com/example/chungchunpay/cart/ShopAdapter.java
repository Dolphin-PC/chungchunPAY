package com.example.chungchunpay.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chungchunpay.R;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    private ArrayList<Item> data = new ArrayList<>();

    @Override
    public ShopAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop_card,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ShopAdapter.ViewHolder holder, int position) {
        Item item = data.get(position);

        Glide.with(holder.itemView.getContext())
                .load(item.getImageUri())
                .into(holder.imageview);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setItems(ArrayList<Item> items) {
        this.data = items;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageview;
        private TextView NameText;

        ViewHolder(View itemView) {
            super(itemView);
            imageview = (ImageView) itemView.findViewById(R.id.image);
            NameText = (TextView) itemView.findViewById(R.id.NameText);
        }
    }
}
