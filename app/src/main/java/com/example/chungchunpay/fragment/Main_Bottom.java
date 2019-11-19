package com.example.chungchunpay.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chungchunpay.FireCloud_Data.DataUser;
import com.example.chungchunpay.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static android.content.Context.MODE_PRIVATE;


public class Main_Bottom extends Fragment {
    FirebaseFirestore CloudDB = FirebaseFirestore.getInstance();

    TextView NameText,PointText;

    String ID;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NameText = view.findViewById(R.id.NameText);
        PointText = view.findViewById(R.id.PointText);

        DB();

    }

    void DB(){
        SharedPreferences positionDATA = this.getActivity().getSharedPreferences("UserData",MODE_PRIVATE);
        SharedPreferences.Editor editor = positionDATA.edit();

        ID = positionDATA.getString("ID","");

        CloudDB.collection("users").document(ID)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        DataUser dataUser = documentSnapshot.toObject(DataUser.class);
                        PointText.setText(dataUser.getPoint()+"");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(),"데이터를 읽어오는데 실패하였습니다.",Toast.LENGTH_SHORT).show();
                    PointText.setText("error");
                }
            });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    public Main_Bottom() {
        // Required empty public constructor
    }
}
