package com.example.chungchunpay.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.danielstone.materialaboutlibrary.MaterialAboutFragment;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.example.chungchunpay.R;
import com.example.chungchunpay.menu.SettingMenu;
/**
 * A simple {@link Fragment} subclass.
 */

//TODO : Create a Setting Fragment
public class SettingFragment extends MaterialAboutFragment {
    public static final int THEME_LIGHT_DARKBAR = 1;

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    protected void setMaterialAboutList(MaterialAboutList materialAboutList) {

        super.setMaterialAboutList(getMaterialAboutList(getContext()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    protected MaterialAboutList getMaterialAboutList(final Context c) {
        MaterialAboutList list = SettingMenu.createMaterialAboutList(c, R.color.mal_color_icon_dark_theme, THEME_LIGHT_DARKBAR);

        return list;
    }

    @Override
    protected int getTheme() {
        return R.style.AppTheme_MaterialAboutActivity_Fragment;
    }

}
