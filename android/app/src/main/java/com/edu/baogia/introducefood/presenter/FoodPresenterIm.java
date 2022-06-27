package com.edu.baogia.introducefood.presenter;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.edu.baogia.introducefood.model.mySQL.FoodInterator;
import com.edu.baogia.introducefood.model.mySQL.LoadFoodListener;
import com.edu.baogia.introducefood.model.mySQL.RatignInterface;
import com.edu.baogia.introducefood.model.object.Food;
import com.edu.baogia.introducefood.model.object.LoaiMonAn;
import com.edu.baogia.introducefood.view.activity.FoodView;
import com.edu.baogia.introducefood.view.fragment.ReviewFragmentView;

import java.util.List;

public class FoodPresenterIm implements FoodPresenter, LoadFoodListener {
    private FoodInterator foodInterator;
    private FoodView foodView;
    private ReviewFragmentView reviewFragmentView;


    public FoodPresenterIm(ReviewFragmentView reviewFragmentView) {
        this.reviewFragmentView = (ReviewFragmentView) reviewFragmentView;
        foodInterator = new FoodInterator(this,(Fragment) reviewFragmentView);
    }
    public FoodPresenterIm(FoodView foodView) {
        this.foodView = foodView;
        foodInterator = new FoodInterator(this, (Context) foodView);
    }
    @Override
    public void loadFoodData(int id) {
        foodInterator.getFood(id);
    }

    @Override
    public void loadDDData(int id,String username) {
        foodInterator.getFoodDanhDau((Context) foodView,id,username);
    }

    @Override
    public void loadRate(int id) {
        foodInterator.getRate(id);
    }

    @Override
    public void loadDesFood(int id) {
        foodInterator.getDesFood(id);
    }

    @Override
    public void upDanhDau(int id, String taikhoan,Boolean check) {
        if(check==true){
            foodInterator.insertDanhdau(id,taikhoan);
        }else if(check==false){
            foodInterator.deleteDanhdau(id,taikhoan);
        }
    }


    @Override
    public void onLoadFoodSuccess(List<Food> listFood) {

    }

    @Override
    public void onLoadListCate(List<LoaiMonAn> listFood) {

    }

    @Override
    public void onLoadFoodSuccess(Food food) {
        foodView.getFood(food);
    }

    @Override
    public void onLoadDesFoodSuccess(Food food) {
        reviewFragmentView.getDesFood(food.getDes());
    }

    @Override
    public void onLoadFoodFailure(String message) {

    }

    @Override
    public void onLoadRateSuccess(Float rate, String countRate) {
        foodView.setRate(rate, countRate);
    }

    @Override
    public void onLoadDDSuccess(boolean a) {
        foodView.setDD(a);
    }


}
