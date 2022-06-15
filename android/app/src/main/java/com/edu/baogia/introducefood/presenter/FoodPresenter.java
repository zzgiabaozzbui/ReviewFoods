package com.edu.baogia.introducefood.presenter;

public interface FoodPresenter {
    void loadFoodData(int id);
    void loadRate(int id);
    void loadDesFood(int id);

    void upDanhDau(int id, String taikhoan,Boolean check);
}
