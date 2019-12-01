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
import com.example.chungchunpay.cart.CardData;
import com.example.chungchunpay.cart.Item;
import com.example.chungchunpay.cart.ShopAdapter;
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
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.List;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;


public class MapDialogImageTest extends AppCompatActivity {

    //TODO : firebase storage
    private StorageReference mStorageRef;
    FirebaseFirestore firebaseFirestore;
    List<Item> data;
    Uri DBuri;

    ShopAdapter shopAdapter = new ShopAdapter();
    CardData cardData = new CardData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_dialog_image_test);

        DiscreteScrollView scrollView = findViewById(R.id.picker);


        cardData.setItems("test","test","https://firebasestorage.googleapis.com/v0/b/chungchunpay.appspot.com/o/Soyang_River_Virgin_Award.gif?alt=media&token=29af9daa-c15e-47f6-aa6c-531ea1e2931e");
        cardData.setItems("test","test","https://firebasestorage.googleapis.com/v0/b/chungchunpay.appspot.com/o/Soyang_River_Virgin_Award.gif?alt=media&token=29af9daa-c15e-47f6-aa6c-531ea1e2931e");

        scrollView.setAdapter(shopAdapter);
        shopAdapter.setItems(cardData.getItems());

//        storageTest();
//        CelebrateTest();
//        DatabaseTest();
//        DiscreteTest();
    }
    void storageTest(){
        mStorageRef = FirebaseStorage.getInstance().getReference();

        StorageReference imagesRef = mStorageRef.child("Soyang_River_Virgin_Award.gif");

        ImageView imageView = findViewById(R.id.testImage);

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

    /*void DiscreteTest(){
        mStorageRef = FirebaseStorage.getInstance().getReference();

        StorageReference imagesRef = mStorageRef.child("Soyang_River_Virgin_Award.gif");

        imagesRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.e("test",uri+"");
                DBuri = uri;
//                data.add(new Item(4,"test","Test",uri));
            }
        });
        data = Arrays.asList(
//                new Item(1, "Everyday Candle", "$12.00 USD", R.drawable.main1),
//                new Item(2, "Small Porcelain Bowl", "$50.00 USD", R.drawable.main2),
//                new Item(3, "Favourite Board", "$265.00 USD", R.drawable.main3),
                new Item(1,"test","test",DBuri));


        DiscreteScrollView scrollView = findViewById(R.id.picker);
        scrollView.setAdapter(shopAdapter);
        shopAdapter.setItems(new CardData(DBuri).getItems());
    }*/

}


