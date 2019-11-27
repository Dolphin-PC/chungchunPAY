package com.example.chungchunpay.fragment;


import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.danielstone.materialaboutlibrary.ConvenienceBuilder;
import com.danielstone.materialaboutlibrary.MaterialAboutFragment;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.example.chungchunpay.R;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

/**
 * A simple {@link Fragment} subclass.
 */

//TODO : Create a Setting Fragment
public class SettingFragment extends MaterialAboutFragment {

    String Name,ID,Gender,Age,Hobby;
//    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    public SettingFragment(String ID, String UserName, String Age,String Gender, String Hobby) {
        this.ID = ID;
        this.Name = UserName;
        this.Age = Age;
        this.Gender = Gender;
        this.Hobby = Hobby;
        // Required empty public constructor
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected MaterialAboutList getMaterialAboutList(final Context c) {

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
        UserInfoCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("나이")
                .subText(Age)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_cake)
                        .color(ContextCompat.getColor(c, R.color.mal_color_icon_dark_theme))
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse("https://github.com/DorPhin-pc/chungchunPAY")))
                .build());

        UserInfoCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("성별")
                .subText(Gender)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_gender_male_female)
                        .color(ContextCompat.getColor(c, R.color.mal_color_icon_dark_theme))
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse("https://github.com/DorPhin-pc/chungchunPAY")))
                .build());

        UserInfoCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("취미")
                .subText(Hobby)
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_history)
                        .color(ContextCompat.getColor(c, R.color.mal_color_icon_dark_theme))
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse("https://github.com/DorPhin-pc/chungchunPAY")))
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
}
