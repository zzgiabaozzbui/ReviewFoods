package com.edu.baogia.introducefood.presenter;

import androidx.fragment.app.Fragment;

import com.edu.baogia.introducefood.model.mySQL.FoodInterator;
import com.edu.baogia.introducefood.model.mySQL.LoadFoodListener;
import com.edu.baogia.introducefood.model.object.Food;
import com.edu.baogia.introducefood.model.object.LoaiMonAn;
import com.edu.baogia.introducefood.view.fragment.HomeFragmentView;

import java.util.List;

public class HomePresenterIm implements LoadFoodListener, HomePresenter {

    private FoodInterator foodInterator;
    private HomeFragmentView homeFragmentView;
    public HomePresenterIm(HomeFragmentView homeFragmentView) {
        this.homeFragmentView = homeFragmentView;
        foodInterator = new FoodInterator(this, (Fragment) homeFragmentView);
    }

    @Override
    public void loadData() {
        foodInterator.createListData();

    }

    @Override
    public void loadCate() {
        foodInterator.createListCate();
    }

    @Override
    public void onLoadFoodSuccess(List<Food> listFood) {
        homeFragmentView.listRcy(listFood);
    }

    @Override
    public void onLoadListCate(List<LoaiMonAn> listFood) {
        homeFragmentView.listRcyCate(listFood);
    }

    @Override
    public void onLoadFoodSuccess(Food food) {
    }

    @Override
    public void onLoadDesFoodSuccess(Food food) {

    }

    @Override
    public void onLoadFoodFailure(String message) {

    }

    @Override
    public void onLoadRateSuccess(Float rate, String countRate) {

    }
}
