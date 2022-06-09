package com.edu.baogia.introducefood.view.fragment;

import com.edu.baogia.introducefood.model.object.AccountRemember;

public interface SignUpView {
    void enterOTP(String e,String pas, String s);
    void goMain();
    void checkSuces(Boolean b);
}
