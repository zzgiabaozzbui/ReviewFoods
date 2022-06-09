package com.edu.baogia.introducefood.presenter;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.edu.baogia.introducefood.model.mySQL.FoodInterator;
import com.edu.baogia.introducefood.model.mySQL.LoadFoodListener;
import com.edu.baogia.introducefood.model.object.Food;
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
        Log.d("AAA", "loadData: ");
    }

    @Override
    public void loadDesFood(int id) {
        foodInterator.getDesFood(id);
    }

    @Override
    public void onLoadFoodSuccess(List<Food> listFood) {

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
}
