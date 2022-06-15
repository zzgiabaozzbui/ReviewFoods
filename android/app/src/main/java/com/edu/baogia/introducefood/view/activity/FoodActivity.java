package com.edu.baogia.introducefood.view.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.adapter.TabLayoutFoodAdapter;
import com.edu.baogia.introducefood.model.object.AccountRemember;
import com.edu.baogia.introducefood.model.object.Food;
import com.edu.baogia.introducefood.presenter.FoodPresenter;
import com.edu.baogia.introducefood.presenter.FoodPresenterIm;
import com.edu.baogia.introducefood.util.MySharedPreferences;
import com.edu.baogia.introducefood.util.QrCode;

import com.edu.baogia.introducefood.util.idwifi;

import com.google.android.material.checkbox.MaterialCheckBox;

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;


public class FoodActivity extends AppCompatActivity implements FoodView{

    TabLayoutFoodAdapter adapter;
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView txtName,txtCate,txtRate,txtCountRate;
    ImageView imgAnh,imgAnh2,imgim;
    Button btnBinhLuan, btnCreateQr;
    RatingBar ratingFood;
    FoodPresenter foodPresenter;
    LinearLayout lndia;
    private Food fd;
    MaterialCheckBox ckoMonAn_DM;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        Intent intent = getIntent();
        id = intent.getIntExtra("idFood",0);
        innit();

        toolbar();
        addInfor();
        tablayout();
        click();
        foodPresenter = new FoodPresenterIm(this);
        loadFood(id);
        loadrate(id);
    }

    private void loadFood(int id) {
        foodPresenter.loadFoodData(id);

    }


    private void loadrate(int id) {
        foodPresenter.loadRate(id);

    }


    private void click() {
        btnBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodActivity.this,ReviewActivity.class);
                intent.putExtra("idfood",id);
                startActivity(intent);
            }
        });
        btnCreateQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(FoodActivity.this);
                dialog.setContentView(R.layout.activity_dialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                imgim = dialog.findViewById(R.id.imgim);
                lndia = dialog.findViewById(R.id.lndia);
                imgim.setImageBitmap(new QrCode().createQrcode(String.valueOf(getIntent().getIntExtra("idFood",0))));
                lndia.startAnimation(AnimationUtils.loadAnimation(FoodActivity.this,R.anim.movebtnface));
                dialog.show();
            }
        });

        AccountRemember accountRemember = new MySharedPreferences().getRememberAcc(this);
        if (accountRemember.getUsername()==null || accountRemember.getUsername().equals("null")|| accountRemember.getUsername().equals("")){
            Toast.makeText(this, "Bạn phải đăng nhập để đánh dấu món ăn", Toast.LENGTH_SHORT).show();
        }else {
            ckoMonAn_DM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        ckoMonAn_DM.setButtonDrawable(R.drawable.ic_tim);
                        foodPresenter.upDanhDau(id,accountRemember.getUsername(),b);
                    }else{
                        ckoMonAn_DM.setButtonDrawable(R.drawable.ic_kh_tim);
                        foodPresenter.upDanhDau(id,accountRemember.getUsername(),b);
                    }
                }
            });
        }
    }
    private void tablayout() {
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        adapter = new TabLayoutFoodAdapter(getSupportFragmentManager(),this,tabLayout.getTabCount(),id);
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
        btnBinhLuan = findViewById(R.id.btnBinhLuan);
        btnCreateQr = findViewById(R.id.btnCreateQr);

        ratingFood = findViewById(R.id.ratingFood);
        txtRate = findViewById(R.id.txtRate);
        txtCountRate = findViewById(R.id.txtCountRate);

    }

    private void addInfor() {



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

        Picasso.get().load(new idwifi().urlThang+food.getImg()).into(imgAnh);
        Picasso.get().load(new idwifi().urlThang+food.getImg()).into(imgAnh2);
    }

    @Override
    public void setRate(Float rate, String countRate) {
        ratingFood.setRating(rate);
        txtRate.setText(String.valueOf(rate));
        txtCountRate.setText("("+countRate+" đánh giá)");
    }

    @Override
    public void ReRate() {
        loadrate(id);
    }


}