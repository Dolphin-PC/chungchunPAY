package com.example.chungchunpay.test;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.chungchunpay.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MapDialogImageTest extends AppCompatActivity {
    //TODO : firebase storage
    FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_dialog_image_test);

        StorageReference storageReference = firebaseStorage.getReference();
        StorageReference imagesRef = storageReference.child("map/소양강처녀상.jpg");
        ImageView imageView = findViewById(R.id.imageView3);

        Glide.with(this).load(imagesRef).placeholder(R.drawable.main).into(imageView);
    }


}

