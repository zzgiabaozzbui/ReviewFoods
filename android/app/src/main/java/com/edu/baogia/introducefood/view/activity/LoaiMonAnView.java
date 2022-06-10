package com.edu.baogia.introducefood.view.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.view.fragment.Fragment_LoaiMonAn;

public class LoaiMonAnView extends AppCompatActivity {
    DrawerLayout drrDoanhMuc;
    Toolbar tbrDoanhMuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_an);
        mapping();
        setMyToolbar();
    }

    private void setMyToolbar() {
        setSupportActionBar(tbrDoanhMuc);
//        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(DoanhMucView.this,drrDoanhMuc,tbrDoanhMuc,R.string.nav_open,R.string.nav_close);
//        drrDoanhMuc.addDrawerListener(toggle);
//        toggle.syncState();
    }

    private void mapping() {
        drrDoanhMuc=findViewById(R.id.drrDoanhMuc);
        tbrDoanhMuc=findViewById(R.id.tbrDoanhMuc);

        replaceFragment(new Fragment_LoaiMonAn(LoaiMonAnView.this));
    }

    public void test(View view){
        Toast.makeText(LoaiMonAnView.this, "abc", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_doanh_muc,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.fillter_doanhMuc:
                if(drrDoanhMuc.isDrawerOpen(GravityCompat.END)){
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
    public void onBackPressed() {
        if(drrDoanhMuc.isDrawerOpen(GravityCompat.END))
            drrDoanhMuc.closeDrawer(GravityCompat.END);
        else
        super.onBackPressed();
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flDoanhMuc,fragment);
        transaction.commit();
    }
}