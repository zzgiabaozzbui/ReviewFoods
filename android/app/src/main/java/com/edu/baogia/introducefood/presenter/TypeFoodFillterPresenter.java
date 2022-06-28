package com.edu.baogia.introducefood.presenter;


import android.content.Context;

import com.edu.baogia.introducefood.interfaces.TypeFoodFillterInterface;
import com.edu.baogia.introducefood.model.object.LoaiMonAn;
import com.edu.baogia.introducefood.model.object.MonAn;
import com.edu.baogia.introducefood.view.activity.DanhMucMonAnView;

public class TypeFoodFillterPresenter {
    TypeFoodFillterInterface foodFillterInterface;

    public TypeFoodFillterPresenter(TypeFoodFillterInterface foodFillterInterface) {
        this.foodFillterInterface = foodFillterInterface;
    }

    public void getListFoodFillter(LoaiMonAn.CallBackTypeFood callBackTypeFood, LoaiMonAn loaiMonAn, Context context){
        loaiMonAn.getTypeFood(callBackTypeFood, context);
    }
}
