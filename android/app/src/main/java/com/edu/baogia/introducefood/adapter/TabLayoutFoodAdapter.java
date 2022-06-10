package com.edu.baogia.introducefood.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.edu.baogia.introducefood.view.fragment.HomeFragment;
import com.edu.baogia.introducefood.view.fragment.ReviewFragment;

public class TabLayoutFoodAdapter extends FragmentPagerAdapter {
    Context context;
    int tab;
    public TabLayoutFoodAdapter(@NonNull FragmentManager fm, Context context, int tab) {
        super(fm);
        this.context = context;
        this.tab = tab;
    }

    public TabLayoutFoodAdapter(@NonNull FragmentManager fm, int behavior, Context context, int tab) {
        super(fm, behavior);
        this.context = context;
        this.tab = tab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                ReviewFragment reviewFragment = new ReviewFragment();
                return reviewFragment;
            case 1:
                HomeFragment reviewFragment2 = new HomeFragment();
                return reviewFragment2;
            case 2:
                HomeFragment reviewFragment3 = new HomeFragment();
                return reviewFragment3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tab;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Giới thiệu";
            case 1:
                return "Công thức";
            case 2:
                return "Đánh giá";
        }
        return null;
    }

}
