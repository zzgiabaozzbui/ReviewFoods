package com.edu.baogia.introducefood.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.adapter.LoaiMonAnAdapter;
import com.edu.baogia.introducefood.adapter.MonAnAdapter;
import com.edu.baogia.introducefood.interfaces.TypeFoodFillterInterface;
import com.edu.baogia.introducefood.model.object.LoaiMonAn;
import com.edu.baogia.introducefood.presenter.TypeFoodFillterPresenter;
import com.edu.baogia.introducefood.view.fragment.Fragment_MonAn;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class DanhMucMonAnView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, TypeFoodFillterInterface {
    SearchView searchFillter;
    Fragment_MonAn fragment_monAn;
    RecyclerView rcvDanhMucFillter;
    List<LoaiMonAn> list;
    LoaiMonAnAdapter adapter;
    DrawerLayout drrDoanhMuc;
    Toolbar tbrDoanhMuc;
    Button btnResetFillter, btnApDungFillter;

    TypeFoodFillterPresenter typeFoodFillterPresenter = new TypeFoodFillterPresenter(this);

    public String getTextSearchFillter() {
        String s = searchFillter.getQuery().toString();
        return s;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_an);
        mapping();
        setMyToolbar();
        setRecyclerViewLoaiMonAn();

    }




    public MonAnAdapter getMonAnAdapter() {
        return fragment_monAn.getAdapter();
    }

    public Button getBtnResetFillter() {
        return btnResetFillter;
    }

    public Button getBtnApDungFillter() {
        return btnApDungFillter;
    }

    private View getNav(int id) {
        NavigationView navDanhMuc = (NavigationView) findViewById(id);
        View headerView = navDanhMuc.getHeaderView(0);
        return headerView;
    }

    private void setRecyclerViewLoaiMonAn() {
        rcvDanhMucFillter = getNav(R.id.navDanhMuc).findViewById(R.id.rcvDanhMucFillter);

        list = new ArrayList<>();
        typeFoodFillterPresenter.getListFoodFillter(new LoaiMonAn.CallBackTypeFood() {
            @Override
            public void onSuccessTypeFood(List<LoaiMonAn> callBackTypeFood) {
                list.addAll(callBackTypeFood);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailTypeFood(String callBackTypeFood) {

            }
        });


        adapter = new LoaiMonAnAdapter(DanhMucMonAnView.this,getIntent());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(DanhMucMonAnView.this, 2);
        rcvDanhMucFillter.setLayoutManager(gridLayoutManager);
        rcvDanhMucFillter.setFocusable(false);
        adapter.setData(list);
        rcvDanhMucFillter.setAdapter(adapter);
    }

    private void mapping() {
        btnResetFillter = getNav(R.id.navDanhMuc).findViewById(R.id.btnResetFillter);
        btnApDungFillter = getNav(R.id.navDanhMuc).findViewById(R.id.btnApDungFillter);
        drrDoanhMuc = findViewById(R.id.drrDoanhMuc);
        tbrDoanhMuc = findViewById(R.id.tbrDoanhMuc);
//        Thêm tên tài khoản vào thuộc tính Fragment_MonAn
        fragment_monAn = new Fragment_MonAn(DanhMucMonAnView.this,getIntent());
        replaceFragment(fragment_monAn);

    }

    private void setMyToolbar() {
        setSupportActionBar(tbrDoanhMuc);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Custom Toolbar");
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flDoanhMuc, fragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_doanh_muc, menu);
        onChangeSearchView(menu);


        return super.onCreateOptionsMenu(menu);
    }

    private void onChangeSearchView(Menu menu) {
        MenuItem search = menu.findItem(R.id.fillter_search_danhMuc);
        searchFillter = (SearchView) MenuItemCompat.getActionView(search);
        searchFillter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<String> conditions = adapter.getCondition();
                MonAnAdapter monAnAdapter = fragment_monAn.getAdapter();
                int check = monAnAdapter.filter(newText, conditions);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fillter_doanhMuc:
                if (drrDoanhMuc.isDrawerOpen(GravityCompat.END)) {
                    drrDoanhMuc.closeDrawer(GravityCompat.END);
                }
                drrDoanhMuc.openDrawer(GravityCompat.END);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        return false;
    }


    @Override
    public void getTypeFoodFillter(LoaiMonAn.CallBackTypeFood callBackTypeFood) {
        new LoaiMonAn().getTypeFood(callBackTypeFood,DanhMucMonAnView.this);
    }
}
