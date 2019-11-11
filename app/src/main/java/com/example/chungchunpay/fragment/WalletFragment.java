package com.example.chungchunpay.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.chungchunpay.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WalletFragment extends Fragment {


    public WalletFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wallet, container, false);
    }

}
