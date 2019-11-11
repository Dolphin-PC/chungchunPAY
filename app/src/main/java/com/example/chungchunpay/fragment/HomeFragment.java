package com.example.chungchunpay.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.chungchunpay.R;
import com.example.chungchunpay.activity.MainActivity;
import com.example.chungchunpay.activity.MapActivity;
import com.example.chungchunpay.activity.PayActivity;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    ImageView MainImageView;
    TextView NicknameText, AgeText, SexText, HobbyText;
    Button TourButton, PayButton;
    Boolean isActive = false;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainImageView = view.findViewById(R.id.MainimageView);
        NicknameText = view.findViewById(R.id.NicknameText);
        AgeText = view.findViewById(R.id.AgeText);
        SexText = view.findViewById(R.id.SexText);
        HobbyText = view.findViewById(R.id.HobbyText);
        TourButton = view.findViewById(R.id.TourButton);
        PayButton = view.findViewById(R.id.PayButton);

        MainImageView.setOnClickListener(this);
        NicknameText.setOnClickListener(this);
        AgeText.setOnClickListener(this);
        SexText.setOnClickListener(this);
        HobbyText.setOnClickListener(this);
        TourButton.setOnClickListener(this);
        PayButton.setOnClickListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.MainimageView :
                GIF(isActive);
                break;

            case R.id.NicknameText :
            case R.id.AgeText :
            case R.id.SexText :
            case R.id.HobbyText :
                ((MainActivity)getActivity()).showFragment(new SettingFragment());
                break;

            case R.id.TourButton :
                Go_Intent(new Intent(getActivity(), MapActivity.class));
                break;

            case R.id.PayButton :
                Go_Intent(new Intent(getActivity(), PayActivity.class));
                break;

        }
    }

    void Go_Intent(Intent intent){
        startActivity(intent);
    }

    void GIF(Boolean isActive){
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(MainImageView);

        if(isActive) {
            Random random = new Random();
            int num = random.nextInt(3);
            switch (num) {
                case 0:
                    Glide.with(getActivity()).load(R.drawable.main1).into(gifImage);
                    break;
                case 1:
                    Glide.with(getActivity()).load(R.drawable.main2).into(gifImage);
                    break;
                case 2:
                    Glide.with(getActivity()).load(R.drawable.main3).into(gifImage);
                    break;

            }
            this.isActive = false;
        }else{
            Glide.with(getActivity()).load(R.drawable.main).into(gifImage);
            this.isActive = true;
        }

    }

}
