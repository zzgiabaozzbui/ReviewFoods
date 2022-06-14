package com.edu.baogia.introducefood.presenter;


import com.google.firebase.auth.PhoneAuthCredential;

public interface EnterOTPPresenter {
    void verify(String verificationId,String OTP);
    void verify2(String verificationId,String OTP);
    void signInWithPhoneAuthCredential(PhoneAuthCredential credential);
}
