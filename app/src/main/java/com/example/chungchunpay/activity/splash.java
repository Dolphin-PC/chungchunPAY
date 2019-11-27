package com.example.chungchunpay.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.chungchunpay.R;

public class splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ImageView gif = findViewById(R.id.loading_gif);
        Glide.with(this).load(R.drawable.loading).into(gif);

        Loading();
    }

    void Loading(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(getBaseContext(), Login_Activity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }

}
