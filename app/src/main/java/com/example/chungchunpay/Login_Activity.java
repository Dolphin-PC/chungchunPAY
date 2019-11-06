package com.example.chungchunpay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

public class Login_Activity extends AppCompatActivity {

    Button GoogleButton,FacebookButton,FingerprintButton;
    LoginButton KakaoButton;
    SessionCallback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        click();
    }

    void init(){
        GoogleButton = findViewById(R.id.Logingoogle_Button);
        FacebookButton = findViewById(R.id.Loginfacebook_Button);
        KakaoButton = findViewById(R.id.Loginkakao_Button);
        FingerprintButton = findViewById(R.id.Loginfingerprint_Button);

        ImageView gif = findViewById(R.id.gif);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(gif);
        Glide.with(this).load(R.drawable.login).into(gifImage);

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
//        Session.getCurrentSession().checkAndImplicitOpen();
        UserManagement.requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                //카카오톡 로그아웃 성공 후 하고싶은 내용 코딩 ~
            }
        });
    }

    void click(){
        KakaoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback = new SessionCallback();
                Session.getCurrentSession().addCallback(callback);
//                Session session = Session.getCurrentSession();
//                session.addCallback(new SessionCallback());
//                session.open(AuthType.KAKAO_TALK_EXCLUDE_NATIVE_LOGIN,Login_Activity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(Session.getCurrentSession().handleActivityResult(requestCode,resultCode,data)){
            return;
        }

        super.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            redirectSignupActivity();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null){
                Logger.e(exception);
            }
            setContentView(R.layout.activity_login);
        }
    }
    protected void redirectSignupActivity(){
        final Intent intent = new Intent(this,KakaoSignupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
}
