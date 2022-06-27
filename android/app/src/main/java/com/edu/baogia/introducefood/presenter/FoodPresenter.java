package com.edu.baogia.introducefood.presenter;

import android.content.Context;

public interface FoodPresenter {
    void loadFoodData(int id);
    void loadDDData(int id, String username);
    void loadRate(int id);
    void loadDesFood(int id);

    void upDanhDau(int id, String taikhoan,Boolean check);
}
