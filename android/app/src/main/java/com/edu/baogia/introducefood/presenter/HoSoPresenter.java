package com.edu.baogia.introducefood.presenter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import com.edu.baogia.introducefood.interfaces.HoSoInterface;
import com.edu.baogia.introducefood.model.object.NguoiDung;
import com.edu.baogia.introducefood.view.activity.HoSoView;
import com.edu.baogia.introducefood.view.activity.LoginActivity;

public class HoSoPresenter {
    HoSoInterface hoSoInterface;

    public HoSoPresenter(HoSoInterface hoSoInterface) {
        this.hoSoInterface = hoSoInterface;
    }
    public void getDataHoSo(NguoiDung.CallBackHoSo callBackHoSo,Context context, String tenTK){
        NguoiDung nguoiDung=new NguoiDung();
        if(tenTK.equalsIgnoreCase("")){
            Intent intent=new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        }
        else
            nguoiDung.getNguoiDung(context,callBackHoSo,tenTK);
        hoSoInterface.getHoSo(callBackHoSo);
    }
    public void changeDataHoSo(Context context, NguoiDung nguoiDung, Bitmap bitmap){
        if(nguoiDung.isValidEmail() && nguoiDung.getSdt().length()==10){
            nguoiDung.updateHoSo(context,nguoiDung,bitmap);
            hoSoInterface.updateHoSoSucess();
        }else
            hoSoInterface.updateHoSoError();
    }
}
