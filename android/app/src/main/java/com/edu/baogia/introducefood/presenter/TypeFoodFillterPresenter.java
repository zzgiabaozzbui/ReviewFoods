package com.edu.baogia.introducefood.presenter;


import com.edu.baogia.introducefood.interfaces.TypeFoodFillterInterface;
import com.edu.baogia.introducefood.model.object.LoaiMonAn;

public class TypeFoodFillterPresenter {
    TypeFoodFillterInterface foodFillterInterface;

    public TypeFoodFillterPresenter(TypeFoodFillterInterface foodFillterInterface) {
        this.foodFillterInterface = foodFillterInterface;
    }

    public void getListFoodFillter(LoaiMonAn.CallBackTypeFood callBackTypeFood){
         foodFillterInterface.getTypeFoodFillter(callBackTypeFood);
    }
}
