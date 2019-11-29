package com.example.chungchunpay.test;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.chungchunpay.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;


public class MapDialogImageTest extends AppCompatActivity {
    //TODO : firebase storage
    private StorageReference mStorageRef;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_dialog_image_test);

//        storageTest();
//        CelebrateTest();
        DatabaseTest();
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
        KonfettiView konfettiView = findViewById(R.id.viewKonfetti);
        konfettiView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                konfettiView.build()
                        .addColors(Color.YELLOW,Color.GREEN,Color.MAGENTA)
                        .setDirection(0.0,359.0)
                        .setSpeed(1f,5f)
                        .setFadeOutEnabled(true)
                        .setTimeToLive(1200L)
                        .addShapes(Shape.RECT,Shape.CIRCLE)
                        .addSizes(new Size(12, 5f))
                        .setPosition(-50f,konfettiView.getWidth() + 50f,-50f,-50f)
                        .streamFor(300,5000L);
            }
        });
    }
    void DatabaseTest(){
        firebaseFirestore = FirebaseFirestore.getInstance();

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        firebaseFirestore.setFirestoreSettings(settings);

        /*firebaseFirestore.collection("user_have").document("1207357213").collection("have_mungmui")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                        Log.e("test",queryDocumentSnapshot.getId());
                        Log.e("test",queryDocumentSnapshot.getData()+"");

                        DataTourMungMui  dataTourMungMui = queryDocumentSnapshot.toObject(DataTourMungMui.class);
                        Log.e("test",dataTourMungMui.getMungMuiName());
                        Log.e("test",dataTourMungMui.getPointName());

                    }
                }
            }
        });*/

        firebaseFirestore.collection("user_have").document("_1207357213").collection("mungmui")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                                Log.e("test",queryDocumentSnapshot.getId()+","+queryDocumentSnapshot.getData());
                            }
                        }else{

                        }
                    }
                });
    }

}

