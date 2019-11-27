package com.example.chungchunpay;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;

import androidx.core.content.ContextCompat;

import com.danielstone.materialaboutlibrary.ConvenienceBuilder;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.danielstone.materialaboutlibrary.util.OpenSourceLicense;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

public class Demo {

    public static MaterialAboutList createMaterialAboutList(final Context c, final int colorIcon, final int theme,String ID) {

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
                        .color(ContextCompat.getColor(c, colorIcon))
                        .sizeDp(18),
                "Version",
                false));

        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Changelog")
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_history)
                        .color(ContextCompat.getColor(c, colorIcon))
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebViewDialogOnClickAction(c, "Releases", "https://github.com/DorPhin-pc/chungchunPAY", true, false))
                .build());


        MaterialAboutCard.Builder UserInfoCardBuilder = new MaterialAboutCard.Builder();
        UserInfoCardBuilder.title("User Info");
//        authorCardBuilder.titleColor(ContextCompat.getColor(c, R.color.colorAccent));

        UserInfoCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Park chan young")
                .subText("EC&H S/W Dev.")
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_account)
                        .color(ContextCompat.getColor(c, colorIcon))
                        .sizeDp(18))
                .build());

        UserInfoCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("Fork on GitHub")
                .icon(new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_github_circle)
                        .color(ContextCompat.getColor(c, colorIcon))
                        .sizeDp(18))
                .setOnClickAction(ConvenienceBuilder.createWebsiteOnClickAction(c, Uri.parse("https://github.com/DorPhin-pc/chungchunPAY")))
                .build());

        MaterialAboutCard.Builder convenienceCardBuilder = new MaterialAboutCard.Builder();

        convenienceCardBuilder.title("Developer Info");

        convenienceCardBuilder.addItem(ConvenienceBuilder.createVersionActionItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_information_outline)
                        .color(ContextCompat.getColor(c, colorIcon))
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
                        .color(ContextCompat.getColor(c, colorIcon))
                        .sizeDp(18),
                "Rate this app",
                null
        ));

        convenienceCardBuilder.addItem(ConvenienceBuilder.createEmailItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_email)
                        .color(ContextCompat.getColor(c, colorIcon))
                        .sizeDp(18),
                "Send an email",
                true,
                "pcx474@gmail.com",
                "Question about 팔도강산 멍무이"));

        convenienceCardBuilder.addItem(ConvenienceBuilder.createPhoneItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_phone)
                        .color(ContextCompat.getColor(c, colorIcon))
                        .sizeDp(18),
                "Call me",
                true,
                "+82 10 1234 5678"));

        convenienceCardBuilder.addItem(ConvenienceBuilder.createMapItem(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_map)
                        .color(ContextCompat.getColor(c, colorIcon))
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
                        .color(ContextCompat.getColor(c, colorIcon))
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

    public static MaterialAboutList createMaterialAboutLicenseList(final Context c, int colorIcon) {

        MaterialAboutCard materialAboutLIbraryLicenseCard = ConvenienceBuilder.createLicenseCard(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_book)
                        .color(ContextCompat.getColor(c, colorIcon))
                        .sizeDp(18),
                "material-about-library", "2016", "Daniel Stone",
                OpenSourceLicense.APACHE_2);

        MaterialAboutCard androidIconicsLicenseCard = ConvenienceBuilder.createLicenseCard(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_book)
                        .color(ContextCompat.getColor(c, colorIcon))
                        .sizeDp(18),
                "Android Iconics", "2016", "Mike Penz",
                OpenSourceLicense.APACHE_2);

        MaterialAboutCard leakCanaryLicenseCard = ConvenienceBuilder.createLicenseCard(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_book)
                        .color(ContextCompat.getColor(c, colorIcon))
                        .sizeDp(18),
                "LeakCanary", "2015", "Square, Inc",
                OpenSourceLicense.APACHE_2);

        MaterialAboutCard mitLicenseCard = ConvenienceBuilder.createLicenseCard(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_book)
                        .color(ContextCompat.getColor(c, colorIcon))
                        .sizeDp(18),
                "MIT Example", "2017", "Matthew Ian Thomson",
                OpenSourceLicense.MIT);

        MaterialAboutCard gplLicenseCard = ConvenienceBuilder.createLicenseCard(c,
                new IconicsDrawable(c)
                        .icon(CommunityMaterial.Icon.cmd_book)
                        .color(ContextCompat.getColor(c, colorIcon))
                        .sizeDp(18),
                "GPL Example", "2017", "George Perry Lindsay",
                OpenSourceLicense.GNU_GPL_3);

        return new MaterialAboutList(materialAboutLIbraryLicenseCard,
                androidIconicsLicenseCard,
                leakCanaryLicenseCard,
                mitLicenseCard,
                gplLicenseCard);
    }
}