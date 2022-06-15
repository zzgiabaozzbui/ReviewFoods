package com.edu.baogia.introducefood.adapter;

import android.content.Context;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.edu.baogia.introducefood.view.fragment.FragmentRating;
import com.edu.baogia.introducefood.view.fragment.HomeFragment;
import com.edu.baogia.introducefood.view.fragment.MakingFragment;
import com.edu.baogia.introducefood.view.fragment.ReviewFragment;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

public class TabLayoutFoodAdapter extends FragmentPagerAdapter {
    Context context;
    int tab;
    int id;
    public TabLayoutFoodAdapter(@NonNull FragmentManager fm, Context context, int tab,int id) {
        super(fm);
        this.context = context;
        this.tab = tab;
        this.id = id;
    }

    public TabLayoutFoodAdapter(@NonNull FragmentManager fm, int behavior, Context context, int tab) {
        super(fm, behavior);
        this.context = context;
        this.tab = tab;
    }

    private SparseArray<YouTubePlayerSupportFragment> fragments = new SparseArray<>(3);
    private SparseArray<YouTubePlayer> players = new SparseArray<>(3);

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                ReviewFragment reviewFragment = new ReviewFragment(id);
                return reviewFragment;
            case 1:

                MakingFragment fragment = new MakingFragment(id);
                return fragment;
            case 2:
                FragmentRating fragmentRating = new FragmentRating(id);
                return fragmentRating;
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
