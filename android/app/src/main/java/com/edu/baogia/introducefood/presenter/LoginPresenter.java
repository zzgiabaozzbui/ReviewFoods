package com.edu.baogia.introducefood.presenter;


import com.edu.baogia.introducefood.model.object.Account;
import com.edu.baogia.introducefood.model.object.Users;
import com.facebook.CallbackManager;

public interface LoginPresenter {

    //applki:
    //0: face
    //1: google
    void signInface(Users users, String applk);
}
