package com.example.chungchunpay.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.chungchunpay.R;
import com.example.chungchunpay.activity.MainActivity;
import com.example.chungchunpay.activity.MapActivity;
import com.example.chungchunpay.activity.PayActivity;

import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    ImageView MainImageView;
    TextView NicknameText, AgeText, GenderText, HobbyText;
    Button TourButton, PayButton;
    Boolean isActive = false;
    String Nickname, Age,Gender, Hobby,ID;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences positionDATA = getActivity().getSharedPreferences("UserData",MODE_PRIVATE);
        ID = positionDATA.getString("ID","");
        Nickname = positionDATA.getString("USERNAME","설정해주세요.");
        Age = positionDATA.getString("AGE","설정해주세요.");
        Gender = positionDATA.getString("GENDER","설정해주세요.");
        Hobby = positionDATA.getString("HOBBY","설정해주세요.");

        MainImageView = view.findViewById(R.id.MainimageView);
        NicknameText = view.findViewById(R.id.NicknameText);
        AgeText = view.findViewById(R.id.AgeText);
        GenderText = view.findViewById(R.id.GenderText);
        HobbyText = view.findViewById(R.id.HobbyText);
        TourButton = view.findViewById(R.id.TourButton);
        PayButton = view.findViewById(R.id.PayButton);

        NicknameText.setText(Nickname);
        AgeText.setText(Age);
        GenderText.setText(Gender);
        HobbyText.setText(Hobby);


        MainImageView.setOnClickListener(this);
        NicknameText.setOnClickListener(this);
        AgeText.setOnClickListener(this);
        GenderText.setOnClickListener(this);
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

            case R.id.NicknameText : if(!Nickname.equals("설정해주세요.")) break;
            case R.id.AgeText :      if(!Age.equals("설정해주세요.")) break;
            case R.id.GenderText:    if(!Gender.equals("설정해주세요.")) break;
            case R.id.HobbyText :    if(!Hobby.equals("설정해주세요.")) break;
                ((MainActivity)getActivity()).showFragment(new SettingFragment());
                //TODO : 설정으로 넘어갔을 때, 사이드 메뉴 Active시키기
                break;

            case R.id.TourButton :
                Go_Intent(new Intent(getActivity(), MapActivity.class));
                break;

            case R.id.PayButton :
                //TODO : 보안 비밀번호 or 지문 인증 구현
                Go_Intent(new Intent(getActivity(), PayActivity.class));
                break;

        }
    }

    void Go_Intent(Intent intent){
        startActivity(intent);
    }

    void GIF(Boolean isActive){

        if(isActive) {
            Random random = new Random();
            int num = random.nextInt(3);
            switch (num) {
                case 0:
                    Glide.with(getActivity()).load(R.drawable.main1).into(MainImageView);
                    break;
                case 1:
                    Glide.with(getActivity()).load(R.drawable.main2).into(MainImageView);
                    break;
                case 2:
                    Glide.with(getActivity()).load(R.drawable.main3).into(MainImageView);
                    break;

            }
            this.isActive = false;
        }else{
            Glide.with(getActivity()).load(R.drawable.main).into(MainImageView);
            this.isActive = true;
        }

    }

}
