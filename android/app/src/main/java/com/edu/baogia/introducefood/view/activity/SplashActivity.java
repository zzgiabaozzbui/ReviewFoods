package com.edu.baogia.introducefood.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.model.object.AccountRemember;
import com.edu.baogia.introducefood.util.MySharedPreferences;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        //Màu thanh pin
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.argb(100,233,30,99));
        }

        //trình trợ kicks hoạt SDK

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                nextActivity();
            }
        },2000);
    }

    private void nextActivity() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//
////        kiểm tra đăng nhập bằng facebook
//        AccessToken accessToken = AccessToken.getCurrentAccessToken();
//        boolean isLoggedFace = accessToken != null && !accessToken.isExpired();
        AccountRemember accountRemember = new MySharedPreferences().getRememberAcc(this);
        Log.d("AAA", "nextActivity: "+accountRemember.toString());
        if (accountRemember.getUsername()==null || accountRemember.getUsername().equals("null")){
            Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }
}