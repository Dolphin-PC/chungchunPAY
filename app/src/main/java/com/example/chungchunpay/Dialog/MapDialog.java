package com.example.chungchunpay.Dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.chungchunpay.R;

public class MapDialog extends DialogFragment {
    private Context context;
    public TextView TitleText;

    public MapDialog(){ }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_map_dialog, container, false);

        TitleText = view.findViewById(R.id.Dialog_TitleText);

        return view;
    }
}
