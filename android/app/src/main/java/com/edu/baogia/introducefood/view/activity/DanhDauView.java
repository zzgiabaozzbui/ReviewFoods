package com.edu.baogia.introducefood.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ListView;

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
    String tenTK="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTenTaiKhoan();
        setContentView(R.layout.activity_danhdauview);
        listView = findViewById(R.id.ListViewDanhDau);
        monAnList = new ArrayList<>();
        danhDauAdapter = new DanhDauAdapter(DanhDauView.this,R.layout.linedanhdauview,monAnList,tenTK);
        listView.setAdapter(danhDauAdapter);
        addData();


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
        danhDau.getFoodDanhDau(callBackGetFoodDanhDau,DanhDauView.this);
    }
}
