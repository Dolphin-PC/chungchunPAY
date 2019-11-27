package com.example.chungchunpay.Dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.example.chungchunpay.R;
import com.google.firebase.storage.StorageReference;

public class MapDialog extends DialogFragment {
    private Context context;
    public TextView TitleText;
    public ImageView imageView;
    StorageReference ImageRef;

    public MapDialog(StorageReference ImageRef){
        this.ImageRef = ImageRef;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_map_dialog, container, false);

        TitleText = view.findViewById(R.id.Dialog_TitleText);
        imageView = view.findViewById(R.id.Dialog_ImageView);

        Glide.with(this).load(ImageRef).into(imageView);

        return view;
    }
}
