package com.edu.baogia.introducefood.model.mySQL;


import com.edu.baogia.introducefood.model.object.Food;

import java.util.List;

//làm callback cho lớp Model
public interface LoadVideoModel {
    void onLoadVideo(String message,String des);
    void onLoadDesFoodSuccess(Food us);
    void onLoadFoodSuccess(Food us);
}