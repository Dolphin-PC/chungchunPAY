package com.example.chungchunpay.fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.danielstone.materialaboutlibrary.ConvenienceBuilder;
import com.danielstone.materialaboutlibrary.MaterialAboutFragment;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutItemOnClickAction;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.example.chungchunpay.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */

//TODO : Create a Setting Fragment
public class SettingFragment extends MaterialAboutFragment{

    String Name, ID, Gender, Age, Hobby;

    static AlertDialog.Builder SettingDialog;

    static TextView TitleText, SubTitleText,SubTitleText1,SubTitleText2;
    static EditText SettingEditText,SettingEditText1;
    static LinearLayout layout;
    static Button ManButton, WomanButton;

    MaterialAboutActionItem.Builder AgeBuilder = new MaterialAboutActionItem.Builder();
    MaterialAboutActionItem.Builder GenderBuilder = new MaterialAboutActionItem.Builder();
    MaterialAboutActionItem.Builder HobbyBuilder = new MaterialAboutActionItem.Builder();

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected MaterialAboutList getMaterialAboutList(final Context c) {
        SharedPreferences positionDATA = c.getSharedPreferences("UserData",MODE_PRIVATE);
        ID = positionDATA.getString("ID","설정해주세요.");
        Name = positionDATA.getString("USERNAME","설정해주세요.");
        Gender = positionDATA.getString("GENDER","설정해주세요.");
        Age = positionDATA.getString("AGE","설정해주세요.");
        Hobby = positionDATA.getString("HOBBY","설정해주세요.");

        MaterialAboutCard.Builder appCardBuilder = new MaterialAboutCard.Builder();

        // Add items to card

        appCardBuilder.addItem(new MaterialAboutTitleItem.Builder()
                .text("팔도강산 멍무이")
                .desc("© EC&H")
                .icon(R.drawable.logo)
                .build());

        appCardBuilder.addItem(ConvenienceBuilder.createVersionActionItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_information_outline)
                        .color(ContextCompat.getColor(c, R.color.mal_color_icon_dark_theme))
                        .sizeDp(18),
                "Version",
                false));

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Changelog")
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_history)
                        .color(ContextCompat.getColor(c, R.color.mal_color_icon_dark_theme))
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebViewDialogOnClickAction(c, "Releases", "https://github.com/DorPhin-pc/chungchunPAY", true, false))
                .build());


        MaterialAboutCard.Builder UserInfoCardBuilder = new MaterialAboutCard.Builder();
        UserInfoCardBuilder.title("User Info");
//        authorCardBuilder.titleColor(ContextCompat.getColor(c, R.color.colorAccent));

        UserInfoCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(Name)
                .subText(ID)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_account)
                        .color(ContextCompat.getColor(c, R.color.mal_color_icon_dark_theme))
                        .sizeDp(18))
                .build());

        //TODO : UserInfo 설정 클릭 시, 설정 다이얼로그 창 표시
        UserInfoCardBuilder.addItem(AgeBuilder
                .text("나이")
                .subText(Age)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_cake)
                        .color(ContextCompat.getColor(c, R.color.mal_color_icon_dark_theme))
                        .sizeDp(18))
                .setOnClickAction(createDialog(c,"Age",ID))
                .build());

        UserInfoCardBuilder.addItem(GenderBuilder
                .text("성별")
                .subText(Gender)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_gender_male_female)
                        .color(ContextCompat.getColor(c, R.color.mal_color_icon_dark_theme))
                        .sizeDp(18))
                .setOnClickAction(createDialog(c,"Gender",ID))
                .build());

        UserInfoCardBuilder.addItem(HobbyBuilder
                .text("취미")
                .subText(Hobby)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_history)
                        .color(ContextCompat.getColor(c, R.color.mal_color_icon_dark_theme))
                        .sizeDp(18))
                .setOnClickAction(createDialog(c,"Hobby",ID))
                .build());


        MaterialAboutCard.Builder convenienceCardBuilder = new MaterialAboutCard.Builder();

        convenienceCardBuilder.title("Developer Info");

        convenienceCardBuilder.addItem(ConvenienceBuilder.createVersionActionItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_information_outline)
                        .color(ContextCompat.getColor(c, R.color.mal_color_icon_dark_theme))
                        .sizeDp(18),
                "Version",
                false));

        //WebSite 리디렉
        /*convenienceCardBuilder.addItem(ConvenienceBuilder.createWebsiteActionItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_earth)
                        .color(ContextCompat.getColor(c, colorIcon))
                        .sizeDp(18),
                "Visit Website",
                true,
                Uri.parse("http://daniel-stone.uk")));*/

        convenienceCardBuilder.addItem(ConvenienceBuilder.createRateActionItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_star)
                        .color(ContextCompat.getColor(c, R.color.mal_color_icon_dark_theme))
                        .sizeDp(18),
                "Rate this app",
                null
        ));

        convenienceCardBuilder.addItem(ConvenienceBuilder.createEmailItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_email)
                        .color(ContextCompat.getColor(c, R.color.mal_color_icon_dark_theme))
                        .sizeDp(18),
                "Send an email",
                true,
                "pcx474@gmail.com",
                "Question about 팔도강산 멍무이"));

//        convenienceCardBuilder.addItem(ConvenienceBuilder.createPhoneItem(c,
//                new IconicsDrawable(c)
//                        .icon(CommunityMaterial.Icon.cmd_phone)
//                        .color(ContextCompat.getColor(c, R.color.mal_color_icon_dark_theme))
//                        .sizeDp(18),
//                "Call me",
//                true,
//                "+82 10 1234 5678"));

        convenienceCardBuilder.addItem(ConvenienceBuilder.createMapItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_map)
                        .color(ContextCompat.getColor(c, R.color.mal_color_icon_dark_theme))
                        .sizeDp(18),
                "Visit 춘천",
                null,
                "춘천"));

        MaterialAboutCard.Builder LicenseCardBuilder = new MaterialAboutCard.Builder();
        LicenseCardBuilder.title("License");

        LicenseCardBuilder.cardColor(Color.parseColor("#7986CB"));

        LicenseCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_check)
                        .color(ContextCompat.getColor(c, R.color.mal_color_icon_dark_theme))
                        .sizeDp(18))
                .text("Copyright 2019 by EC&H")
                .subTextHtml("본 서비스의 저작권은 EC&H에게 있으며,<br>" +
                        "서비스 내의 캐릭터, 디자인은 <a href=http://www.brosco.co.kr/>BROSCO</a>에게 귀속되어 있습니다.<br><br>" +
                        "본 서비스의 모든 저작물에 대한 무단 복사를 금합니다.")
                .setIconGravity(MaterialAboutActionItem.GRAVITY_TOP)
                .build()
        );

        return new MaterialAboutList(appCardBuilder.build(), UserInfoCardBuilder.build(), convenienceCardBuilder.build(), LicenseCardBuilder.build());
    }


    @Override
    protected int getTheme() {
        return R.style.AppTheme_MaterialAboutActivity_Fragment;
    }

    /*MaterialAboutItemOnClickAction DialogEvent(String Category){
        Handler mHandler = new Handler(Looper.getMainLooper());
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 사용하고자 하는 코드
                SettingDialog = new Dialog(getActivity());
                SettingDialog.setContentView(R.layout.dialog_setting);


                TitleText = SettingDialog.findViewById(R.id.TitleText);
                SubTitleText = SettingDialog.findViewById(R.id.SubTitleText);
                SettingEditText = SettingDialog.findViewById(R.id.SettingEditText);
                layout = SettingDialog.findViewById(R.id.GenderLayout);
                ManButton = SettingDialog.findViewById(R.id.ManButton);
                WomanButton = SettingDialog.findViewById(R.id.WomanButton);


            }
        }, 0);

        return new MaterialAboutItemOnClickAction() {
            @Override
            public void onClick() {
                switch (Category){
                    case "Age" :
                        SubTitleText.setText("나이");
                        layout.setVisibility(View.INVISIBLE);
                        SettingDialog.show();
                        break;

                    case "Gender" :
                        SubTitleText.setText("성별");
                        SettingEditText.setVisibility(View.INVISIBLE);
                        layout.setVisibility(View.VISIBLE);
                        ManButton.setOnClickListener(SettingFragment.this::onClick);
                        WomanButton.setOnClickListener(SettingFragment.this::onClick);
                        SettingDialog.show();
                        break;

                    case "Hobby" :
                        SubTitleText.setText("취미");
                        layout.setVisibility(View.INVISIBLE);
                        SettingDialog.show();
                        break;
                }
            }
        };*/
    public static MaterialAboutItemOnClickAction createDialog(Context c, String Category, String id) {
        FirebaseFirestore DB = FirebaseFirestore.getInstance();
        Map<String, Object> user  = new HashMap<>();
        SharedPreferences positionDATA = c.getSharedPreferences("UserData",MODE_PRIVATE);
        SharedPreferences.Editor editor = positionDATA.edit();

        String Age = positionDATA.getString("AGE","20");
        String Hobby = positionDATA.getString("HOBBY","춘천");
        String Gender = positionDATA.getString("GENDER","남/여");

        return new MaterialAboutItemOnClickAction() {
            public void onClick() {
                SettingDialog = new AlertDialog.Builder(c);
                SettingDialog.setView(R.layout.dialog_setting);

                SettingDialog.setPositiveButton("설정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        user.put("Hobby",SettingEditText1.getText().toString());
                        user.put("Age",SettingEditText.getText().toString());
                        editor.putString("AGE",SettingEditText.getText().toString());
                        editor.putString("HOBBY",SettingEditText1.getText().toString());

                        DB.collection("users").document(id)
                                .update(user)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        editor.apply();
                                        Toast.makeText(c,"데이터 변경이 완료되었습니다.",Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                Dialog dialog = SettingDialog.create();
                dialog.show();

                TitleText = dialog.findViewById(R.id.TitleText);
                SubTitleText = dialog.findViewById(R.id.SubTitleText);
                SubTitleText1 = dialog.findViewById(R.id.SubTitleText1);
                SubTitleText2 = dialog.findViewById(R.id.SubTitleText2);
                SettingEditText = dialog.findViewById(R.id.SettingEditText);
                SettingEditText1 = dialog.findViewById(R.id.SettingEditText1);
                layout = dialog.findViewById(R.id.GenderLayout);
                ManButton = dialog.findViewById(R.id.ManButton);
                WomanButton = dialog.findViewById(R.id.WomanButton);

                SettingEditText.setText(Age);
                SettingEditText1.setText(Hobby);

                if(Gender.equals("남")){
                    ManButton.setZ(10f);
                    WomanButton.setZ(0f);
                }else{
                    ManButton.setZ(0f);
                    WomanButton.setZ(10f);
                }
                ManButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ManButton.setZ(10f);
                        WomanButton.setZ(0f);
                        user.put("Gender","남");
                        editor.putString("GENDER","남");
                    }
                });

                WomanButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ManButton.setZ(0f);
                        WomanButton.setZ(10f);
                        user.put("Gender","여");
                        editor.putString("GENDER","여");
                    }
                });
/*
                switch (Category){
                    case "Age" :
                        SubTitleText.setText("나이");
                        layout.setVisibility(View.INVISIBLE);
                        dialog.show();
                        break;

                    case "Gender" :
                        SubTitleText.setText("성별");
                        SettingEditText.setVisibility(View.INVISIBLE);
                        layout.setVisibility(View.VISIBLE);
                        ManButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                        WomanButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        });
                        dialog.show();
                        break;

                    case "Hobby" :
                        SubTitleText.setText("취미");
                        layout.setVisibility(View.INVISIBLE);
                        dialog.show();
                        break;
                }*/
            }
        };
    }
}
