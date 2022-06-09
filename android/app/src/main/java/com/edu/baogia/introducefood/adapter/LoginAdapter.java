package com.edu.baogia.introducefood.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.edu.baogia.introducefood.view.fragment.SignInFragment;
import com.edu.baogia.introducefood.view.fragment.SignUpFragment;


public class LoginAdapter extends FragmentPagerAdapter {
    Context context;
    int tab;

    public LoginAdapter(@NonNull FragmentManager fm, Context context, int tab) {
        super(fm);
        this.context = context;
        this.tab = tab;
    }

    public LoginAdapter(@NonNull FragmentManager fm, int behavior, Context context, int tab) {
        super(fm, behavior);
        this.context = context;
        this.tab = tab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                SignInFragment signInFragment = new SignInFragment();
                return signInFragment;
            case 1:
                SignUpFragment signUpFragment = new SignUpFragment();
                return signUpFragment;
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
                return "                   Login                   ";
            case 1:
                return "                   Sign Up                   ";
        }
        return null;
    }
}
