package com.edu.baogia.introducefood.view.activity;

import com.edu.baogia.introducefood.model.object.Food;

public interface FoodView {

    void getFood(Food food);
    void setRate(Float rate, String countRate);
    void ReRate();
    void setDD(boolean a);

}
