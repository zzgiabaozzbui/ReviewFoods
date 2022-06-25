package com.edu.baogia.introducefood.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.adapter.DanhDauAdapter;
import com.edu.baogia.introducefood.interfaces.DanhDauInterface;
import com.edu.baogia.introducefood.model.object.AccountRemember;
import com.edu.baogia.introducefood.model.object.DanhDau;
import com.edu.baogia.introducefood.model.object.MonAn;
import com.edu.baogia.introducefood.presenter.DanhDauPresenter;
import com.edu.baogia.introducefood.util.MySharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class DanhDauView extends AppCompatActivity implements DanhDauInterface {
    DanhDauPresenter danhDauPresenter=new DanhDauPresenter(this);
    ListView listView ;
    List<MonAn> monAnList;
    DanhDauAdapter danhDauAdapter;
    ImageView imgReturnHome;
    String tenTK="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTenTaiKhoan();
        setContentView(R.layout.activity_danhdauview);
        listView = findViewById(R.id.ListViewDanhDau);
        imgReturnHome=findViewById(R.id.imgReturnHome);
        monAnList = new ArrayList<>();
        danhDauAdapter = new DanhDauAdapter(DanhDauView.this,R.layout.linedanhdauview,monAnList,tenTK);
        listView.setAdapter(danhDauAdapter);
        addData();
        setReturnHome();
        setOnclick();

    }

    private void setOnclick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(view.getContext(), FoodActivity.class);
                intent.putExtra("idFood",monAnList.get(i).getId());
                view.getContext().startActivity(intent);
            }
        });
    }

    private void setReturnHome() {
        imgReturnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setTenTaiKhoan() {
        try {
            AccountRemember accountRemember=new MySharedPreferences().getRememberAcc(DanhDauView.this);
            Log.d("pdt", "setTenTaiKhoan: "+accountRemember.getUsername());
            tenTK=accountRemember.getUsername();
        }catch (Exception e){

        }
    }

    private void addData() {
        danhDauPresenter.getFoodDanhDau(new DanhDau.CallBackGetFoodDanhDau() {
            @Override
            public void onSuccess(List<MonAn> danhDauList) {
                monAnList.addAll(danhDauList);
                danhDauAdapter.notifyDataSetChanged();;
            }

            @Override
            public void onFail(String error) {

            }
        });
    }

    @Override
    public void getFoodDanhDau(DanhDau.CallBackGetFoodDanhDau callBackGetFoodDanhDau) {
        DanhDau danhDau=new DanhDau();
        danhDau.setTenTaiKhoan(tenTK);
        danhDau.getFoodDanhDau(callBackGetFoodDanhDau,DanhDauView.this);
    }
}
