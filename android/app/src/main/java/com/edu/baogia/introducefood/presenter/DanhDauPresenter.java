package com.edu.baogia.introducefood.presenter;

import com.edu.baogia.introducefood.interfaces.DanhDauInterface;
import com.edu.baogia.introducefood.model.object.DanhDau;

public class DanhDauPresenter {
    DanhDauInterface danhDauInterface;

    public DanhDauPresenter(DanhDauInterface danhDauInterface) {
        this.danhDauInterface = danhDauInterface;
    }
    public void getFoodDanhDau(DanhDau.CallBackGetFoodDanhDau callBackGetFoodDanhDau){
        danhDauInterface.getFoodDanhDau(callBackGetFoodDanhDau);
    }
}
