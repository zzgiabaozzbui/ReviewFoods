package com.edu.baogia.introducefood.presenter;

public interface FoodRatingPresenter {
    void setRating(float rating,int idFood,String taikhoan);
    void getRating(int idFood,String taikhoan);
}
