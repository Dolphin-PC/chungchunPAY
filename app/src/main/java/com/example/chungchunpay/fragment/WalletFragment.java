package com.example.chungchunpay.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chungchunpay.R;
import com.example.chungchunpay.TestStackAdapter;
import com.loopeer.cardstack.AllMoveDownAnimatorAdapter;
import com.loopeer.cardstack.CardStackView;
import com.loopeer.cardstack.UpDownAnimatorAdapter;
import com.loopeer.cardstack.UpDownStackAnimatorAdapter;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class WalletFragment extends Fragment implements CardStackView.ItemExpendListener{
    public static Integer[] TEST_DATAS = new Integer[]{
            R.color.color_1,
            R.color.color_2,
            R.color.color_3,
            R.color.color_4,
            R.color.color_5
    };

    CardStackView cardStackView;
    LinearLayout mActionButtonContainer;
    TestStackAdapter mTestStackAdapter;
    Button PreButton,NextButton;

    public WalletFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cardStackView = view.findViewById(R.id.CardStackView);
        mActionButtonContainer = view.findViewById(R.id.button_container);
        PreButton = view.findViewById(R.id.PreButton);
        NextButton = view.findViewById(R.id.NextButton);

        cardStackView.setItemExpendListener(this);
        mTestStackAdapter = new TestStackAdapter(getContext());
        cardStackView.setAdapter(mTestStackAdapter);

        PreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardStackView.pre();
            }
        });
        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardStackView.next();
            }
        });


        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        mTestStackAdapter.updateData(Arrays.asList(TEST_DATAS));
                    }
                }
                , 200
        );


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_actions, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_all_down:
                cardStackView.setAnimatorAdapter(new AllMoveDownAnimatorAdapter(cardStackView));
                break;
            case R.id.menu_up_down:
                cardStackView.setAnimatorAdapter(new UpDownAnimatorAdapter(cardStackView));
                break;
            case R.id.menu_up_down_stack:
                cardStackView.setAnimatorAdapter(new UpDownStackAnimatorAdapter(cardStackView));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wallet, container, false);
    }

    @Override
    public void onItemExpend(boolean expend) {
        mActionButtonContainer.setVisibility(expend ? View.VISIBLE : View.GONE);
    }
}

