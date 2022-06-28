package com.edu.baogia.introducefood.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.adapter.LoaiMonAnAdapter;
import com.edu.baogia.introducefood.adapter.MonAnAdapter;
import com.edu.baogia.introducefood.interfaces.FoodFillterInterface;
import com.edu.baogia.introducefood.model.object.MonAn;
import com.edu.baogia.introducefood.presenter.FoodFillterPresenter;

import java.util.ArrayList;
import java.util.List;

public class Fragment_MonAn extends Fragment implements FoodFillterInterface {

    Context context;
    Intent i;
    RecyclerView rcvDoanhMuc;
    List<MonAn> list;
    MonAnAdapter adapter;
    View view;
    FoodFillterPresenter foodFillterPresenter=new FoodFillterPresenter(this);
    String TK="";
    LoaiMonAnAdapter loaiMonAnAdapter;
    public MonAnAdapter getAdapter(){
        return adapter;
    }

    public Fragment_MonAn(Context context,Intent i,LoaiMonAnAdapter loaiMonAnAdapter) {
        this.context = context;
        this.i=i;
        this.loaiMonAnAdapter=loaiMonAnAdapter;
    }
    public Fragment_MonAn(Context context,Intent i,String taikhoan,LoaiMonAnAdapter loaiMonAnAdapter) {
        this.context = context;
        this.i=i;
        this.TK=taikhoan;
        this.loaiMonAnAdapter=loaiMonAnAdapter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_loai_mon_an,container,false);
        mapping();
        setRecyclerViewMonAn();
        return view;
    }
// ---------------hàm xét tài khoản ở đây --------------------------


    private void setRecyclerViewMonAn() {
        adapter=new MonAnAdapter();
        adapter.setTK(TK);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),1);
        rcvDoanhMuc.setLayoutManager(gridLayoutManager);
        rcvDoanhMuc.setFocusable(false);
        rcvDoanhMuc.setNestedScrollingEnabled(false);
        rcvDoanhMuc.setAdapter(adapter);

    }

    public void mapping() {
        list=new ArrayList<>();
        foodFillterPresenter.getFoodFillter(new MonAn.CallBackFood() {
            @Override
            public void onSuccessFood(List<MonAn> callBackFood) {
                list.addAll(callBackFood);
                adapter.setData(list);
                setIntent();
            }

            @Override
            public void onFailFood(String callBackFood) {
                Toast.makeText(context, ""+callBackFood, Toast.LENGTH_SHORT).show();
            }
        },new MonAn(),context);
        rcvDoanhMuc=view.findViewById(R.id.rcvDoanhMuc);
    }
    private void setIntent() {
        Intent intent=i;
        if(i.getStringExtra("idLoaiMonAn")!=null){
            String id=i.getStringExtra("idLoaiMonAn");
            List<String> conditions = loaiMonAnAdapter.getCondition();
            conditions.add(id+"");
            MonAnAdapter monAnAdapter = getAdapter();
            int check = monAnAdapter.filter("", conditions);
        }
        // hàm tìm kiếm từ phía trang chủ -----------------------
        if(i.getStringExtra("timkiem")!=null){
            String timkiem=i.getStringExtra("timkiem");
            List<String> conditions = loaiMonAnAdapter.getCondition();
            MonAnAdapter monAnAdapter = getAdapter();
            int check = monAnAdapter.filter(timkiem, conditions);
        }
    }

    @Override
    public void getListFoodFillter(MonAn.CallBackFood callBackFood) {

    }
    @Override
    public void hasNullFoodFillter() {

    }



}
