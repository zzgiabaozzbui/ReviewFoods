package com.edu.baogia.introducefood.presenter;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.edu.baogia.introducefood.model.mySQL.AccInterface;
import com.edu.baogia.introducefood.model.mySQL.AccModel;
import com.edu.baogia.introducefood.model.mySQL.AccountInterface;
import com.edu.baogia.introducefood.model.mySQL.AccountModel;
import com.edu.baogia.introducefood.model.mySQL.RatignInterface;
import com.edu.baogia.introducefood.model.mySQL.RatingModel;
import com.edu.baogia.introducefood.model.object.Account;
import com.edu.baogia.introducefood.model.object.AccountRemember;
import com.edu.baogia.introducefood.model.object.Danhgia;
import com.edu.baogia.introducefood.model.object.Users;
import com.edu.baogia.introducefood.view.activity.FoodView;
import com.edu.baogia.introducefood.view.fragment.FragmentAccView;
import com.edu.baogia.introducefood.view.fragment.FragmentRating;
import com.edu.baogia.introducefood.view.fragment.FragmentRatingView;

public class AccPresenterIm implements AccInterface, AccPresenter {
    private AccModel accModel;
    private FragmentAccView fragmentAccView;
    FragmentAccView accView;


    public AccPresenterIm(FragmentAccView fragmentAccView) {
        this.fragmentAccView = fragmentAccView;
        accModel = new AccModel(this, (Fragment) fragmentAccView);
    }


    @Override
    public void getAcc(String taikhoan) {
        accModel.getAcc(taikhoan);
    }

    @Override
    public void getAccount(Users users) {
        fragmentAccView.setacc(users);
    }
}
