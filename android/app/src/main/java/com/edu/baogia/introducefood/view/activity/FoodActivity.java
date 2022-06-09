package com.edu.baogia.introducefood.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.adapter.TabLayoutFoodAdapter;
import com.edu.baogia.introducefood.model.object.Food;
import com.edu.baogia.introducefood.presenter.FoodPresenter;
import com.edu.baogia.introducefood.presenter.FoodPresenterIm;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;


public class FoodActivity extends AppCompatActivity implements FoodView{

    TabLayoutFoodAdapter adapter;
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView txtName,txtCate;
    ImageView imgAnh,imgAnh2;
    Button btnThemGH;
    FoodPresenter foodPresenter;
    private Food fd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        innit();

        toolbar();
        addInfor();
        tablayout();
        click();
        Intent intent = getIntent();
        int id=intent.getIntExtra("idFood",0);
        loadFood(id);
    }

    private void loadFood(int id) {
        foodPresenter = new FoodPresenterIm(this);
        foodPresenter.loadFoodData(id);

    }


    private void click() {
        btnThemGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    private void tablayout() {
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        adapter = new TabLayoutFoodAdapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
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

    private void innit() {
        //Màu thanh pin
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.argb(100,0,125,175));
        }

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        txtName = findViewById(R.id.txtName);
        txtCate = findViewById(R.id.txtCate);
        imgAnh = findViewById(R.id.imgAnh);
        imgAnh2 = findViewById(R.id.imgAnh2);
        btnThemGH = findViewById(R.id.btnThemGH);
    }

    private void addInfor() {

        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);

    }

    private void toolbar() {
        //Thay actionbar bằng toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Tạo nút navbutton
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //thay icon nút
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left);
        getSupportActionBar().setTitle("Chi tiết món ăn");
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getFood(Food food) {
        fd = food;
        txtName.setText(""+food.getName());
        txtCate.setText(""+food.getCate());

        Picasso.with(this).load(""+food.getImg()).into(imgAnh);
        Picasso.with(this).load(""+food.getImg()).into(imgAnh2);
        Log.d("AAA", "getFood: "+food.toString());
    }


    public Food getFd() {
        return fd;
    }
}