package com.edu.baogia.introducefood.interfaces;


import com.edu.baogia.introducefood.model.object.NguoiDung;

public interface HoSoInterface {
    void getHoSo(NguoiDung.CallBackHoSo callBackHoSo);
    void updateHoSo();
}
