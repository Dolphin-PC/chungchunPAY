package com.example.chungchunpay.cart;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chungchunpay.R;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    Context context;
    String url,Name;
    SharedPreferences.Editor editor;
    private ArrayList<Item> data = new ArrayList<>();

    public ShopAdapter(Context context, SharedPreferences positionDATA){
        this.context = context;
        editor = positionDATA.edit();
    }
    public ShopAdapter(Context context){
        this.context = context;
    }

    public void setUrlName(String url,String Name){
        this.url = url;
        this.Name = Name;
    }
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
        private TextView UrlText;

        ViewHolder(View itemView) {

            super(itemView);
            imageview = (ImageView) itemView.findViewById(R.id.image);
            NameText = (TextView) itemView.findViewById(R.id.NameText);

            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                    dialog.setTitle(Name).setMessage("이 멍무이를 메인으로 설정하시겠습니까?")
                            .setPositiveButton("예", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int p) {
                                    Toast.makeText(context,"<" + Name + ">를(을) 메인화면으로 설정하였습니다.",Toast.LENGTH_SHORT).show();
                                    editor.putString("Active_Mungmui",url).apply();
                                }
                            })
                            .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).show();
                }
            });
        }
    }
}
