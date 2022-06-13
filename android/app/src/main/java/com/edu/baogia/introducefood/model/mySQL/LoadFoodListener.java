package com.edu.baogia.introducefood.model.mySQL;


import com.edu.baogia.introducefood.model.object.Food;

import java.util.List;

//làm callback cho lớp Model
public interface LoadFoodListener {
    void onLoadFoodSuccess(List<Food> listFood);
    void onLoadFoodSuccess(Food food);
    void onLoadDesFoodSuccess(Food food);
    void onLoadFoodFailure(String message);
    void onLoadRateSuccess(Float rate,String countRate);
}