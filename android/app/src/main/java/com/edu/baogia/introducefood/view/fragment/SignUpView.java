package com.edu.baogia.introducefood.view.fragment;

import com.edu.baogia.introducefood.model.object.AccountRemember;
import com.google.firebase.auth.PhoneAuthProvider;

public interface SignUpView {
    void enterOTP(String e, String pas);
    void goMain();
    void checkSuces(Boolean b);
}
