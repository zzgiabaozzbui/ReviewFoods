package com.edu.baogia.introducefood.presenter;


import com.edu.baogia.introducefood.interfaces.HoSoInterface;
import com.edu.baogia.introducefood.model.object.NguoiDung;

public class HoSoPresenter {
    HoSoInterface hoSoInterface;

    public HoSoPresenter(HoSoInterface hoSoInterface) {
        this.hoSoInterface = hoSoInterface;
    }
    public void getDataHoSo(NguoiDung.CallBackHoSo callBackHoSo){
        hoSoInterface.getHoSo(callBackHoSo);
    }
    public void changeDataHoSo(){
        hoSoInterface.updateHoSo();
    }
}
