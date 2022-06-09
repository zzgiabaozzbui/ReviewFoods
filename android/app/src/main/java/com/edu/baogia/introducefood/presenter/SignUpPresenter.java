package com.edu.baogia.introducefood.presenter;


import com.edu.baogia.introducefood.model.object.Account;
import com.edu.baogia.introducefood.model.object.AccountRemember;

public interface SignUpPresenter {
    void sendOTP(String e,String pas);
    void checkTK(String e);
}
