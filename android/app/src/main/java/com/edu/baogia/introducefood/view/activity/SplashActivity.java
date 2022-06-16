package com.edu.baogia.introducefood.view.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.model.object.AccountRemember;
import com.edu.baogia.introducefood.util.MyInternet;
import com.edu.baogia.introducefood.util.MySharedPreferences;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.io.IOException;

public class SplashActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    Boolean precheck=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        serviceInternet();

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


    private void serviceInternet() {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    SystemClock.sleep(2000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                boolean check=new MyInternet().isInternetAvailable();
                                Log.d("pdt", "run: "+check);
                                if(check!=precheck){
                                    if(check==false){
                                        Intent intent=new Intent(SplashActivity.this,ActivityNoInternet.class);
                                        startActivity(intent);
                                    }else if(check==true){
                                        Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                                        startActivity(intent);
                                    }

                                    precheck = check;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }


            }
        });
        thread.start();
    }

    private void nextActivity() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//
////        kiểm tra đăng nhập bằng facebook
//        AccessToken accessToken = AccessToken.getCurrentAccessToken();
//        boolean isLoggedFace = accessToken != null && !accessToken.isExpired();
        AccountRemember accountRemember = new MySharedPreferences().getRememberAcc(this);
        Log.d("AAA", "nextActivity: "+accountRemember.toString());
        if (accountRemember.getUsername()==null || accountRemember.getUsername().equals("null")|| accountRemember.getUsername().equals("")){
            Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(intent);
        }else if (accountRemember.getCheck()==false){
            new MySharedPreferences().rememberPass(this,new AccountRemember());
            Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }
}