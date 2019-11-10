package com.example.chungchunpay;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;


public class KakaoSignupActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestMe();
    }

    // 사용자 정보 요청
    public void requestMe() {
        // 사용자정보 요청 결과에 대한 Callback
        UserManagement.requestMe(new MeResponseCallback() {
            // 세션 오픈 실패. 세션이 삭제된 경우,
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                redirectLoginActivity();
            }

            // 회원이 아닌 경우,
            @Override
            public void onNotSignedUp() {
//                Log.e("KakaoSignupActivity :: ", "onNotSignedUp");
            }

            // 사용자정보 요청에 성공한 경우,
            @Override
            public void onSuccess(UserProfile userProfile) {
//                Log.e("KakaoSignupActivity :: ", "onSuccess");
                String nickname = userProfile.getNickname();
                String profileImagePath = userProfile.getProfileImagePath();
                String thumnailPath = userProfile.getThumbnailImagePath();
                String UUID = userProfile.getUUID();
                long id = userProfile.getId();

                Log.e("Profile : ", nickname + "");
                Log.e("Profile : ", profileImagePath + "");
                Log.e("Profile : ", thumnailPath + "");
                Log.e("Profile : ", UUID + "");
                Log.e("Profile : ", id + "");

                redirectMainActivity(nickname,id,profileImagePath);
                //TODO : redirect to [mainActivity]
            }

            // 사용자 정보 요청 실패
            @Override
            public void onFailure(ErrorResult errorResult) {
                Log.e("KakaoSignupActivity :: ", "onFailure : " + errorResult.getErrorMessage());
                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if (result == ErrorCode.CLIENT_ERROR_CODE) {
                    finish();
                } else {
                    redirectLoginActivity();
                }
            }
        });
    }
    protected void redirectLoginActivity() {
        final Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
    void redirectMainActivity(String nickname,long id,String profile){
        //첫 로그인
        SharedPreferences positionDATA = getSharedPreferences("LoginDATA",MODE_PRIVATE);
        SharedPreferences.Editor editor = positionDATA.edit();
//        Intent intent = new Intent(this,Permission.class);
        editor.putString("USERNAME",nickname);
        editor.putString("ID",id+"");
        if(profile=="") {
//            editor.putString("PROFILE","");
        }
        else
            editor.putString("PROFILE",profile);
//        editor.putString("GENDER","남자");
//        editor.putBoolean("Guide",false);
        editor.apply();

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}