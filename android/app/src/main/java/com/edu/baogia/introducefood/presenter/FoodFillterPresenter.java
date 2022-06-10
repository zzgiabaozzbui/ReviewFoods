package com.edu.baogia.introducefood.presenter;


import com.edu.baogia.introducefood.interfaces.FoodFillterInterface;
import com.edu.baogia.introducefood.model.object.MonAn;

public class FoodFillterPresenter {
    private FoodFillterInterface foodFillterInterface;

    public FoodFillterPresenter(FoodFillterInterface foodFillterInterface) {
        this.foodFillterInterface = foodFillterInterface;
    }
    public void getFoodFillter(MonAn.CallBackFood callBackFood){
        foodFillterInterface.getListFoodFillter(callBackFood);
    }
}
