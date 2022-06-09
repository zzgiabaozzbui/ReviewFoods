package com.edu.baogia.introducefood.view.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.adapter.LoginAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton btnF, btnG;
    //public static LoginAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        innit();
        animation();
        tablayout();
        click();

    }

    private void click() {

    }

    private void tablayout() {
        tabLayout.addTab(tabLayout.newTab().setText("                 Login                 "));
        tabLayout.addTab(tabLayout.newTab().setText("                 Sign Up                 "));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // không ở đâu trong mã, nó được xác định điều gì sẽ xảy ra khi người dùng nhấn / chọn tab
                // đây là lý do tại sao dòng sau là cần thiết
                // chúng ta cần đặt phân đoạn chính xác theo cách thủ công khi một tab được chọn / khai thác
                // và đây là vấn đề trong mã của bạn
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

                // Reload your recyclerView here
            }
        });
    }

    private void animation() {
        tabLayout.startAnimation(AnimationUtils.loadAnimation(this, R.anim.movetablayout));

        btnF.startAnimation(AnimationUtils.loadAnimation(this,R.anim.movebtnface));
        btnG.startAnimation(AnimationUtils.loadAnimation(this,R.anim.movebtngg));
    }

    private void innit() {
        //Màu thanh pin
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.argb(100,50,153,255));
        }
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        btnF = findViewById(R.id.btnF);
        btnG = findViewById(R.id.btnG);
    }


}