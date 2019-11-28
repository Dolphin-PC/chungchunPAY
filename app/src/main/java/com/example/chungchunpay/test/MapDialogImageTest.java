package com.example.chungchunpay.test;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.chungchunpay.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class MapDialogImageTest extends AppCompatActivity {
    //TODO : firebase storage
    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_dialog_image_test);

//        storageTest();
        CelebrateTest();
    }
    void storageTest(){
        mStorageRef = FirebaseStorage.getInstance().getReference().child("멍무이");

        StorageReference imagesRef = mStorageRef.child("test1.gif");


        ImageView imageView = findViewById(R.id.imageView3);

//        Glide.with(this).load(imagesRef).placeholder(R.drawable.main).into(imageView);
        imagesRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.e("URI Test",uri+"");
                Glide.with(MapDialogImageTest.this)
                        .load(uri)
                        .placeholder(R.drawable.main)
                        .into(imageView);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("URI Test","Download Fail");
            }
        });

        //FirebaseStorage UI로 바로 안되는 이유가 토큰이 없어서 인가???

    }
    void CelebrateTest(){

    }

}

