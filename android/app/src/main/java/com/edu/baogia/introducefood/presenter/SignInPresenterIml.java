package com.edu.baogia.introducefood.presenter;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.edu.baogia.introducefood.model.mySQL.AccountInterface;
import com.edu.baogia.introducefood.model.mySQL.AccountModel;
import com.edu.baogia.introducefood.model.object.Account;
import com.edu.baogia.introducefood.model.object.AccountRemember;
import com.edu.baogia.introducefood.util.MySharedPreferences;
import com.edu.baogia.introducefood.view.fragment.SignInView;



public class SignInPresenterIml implements SignInPresenter, AccountInterface {
    SignInView loginView;
    AccountModel accountModel;

    public SignInPresenterIml(SignInView loginView) {
        this.loginView = loginView;
        this.accountModel = new AccountModel(this, (Fragment) loginView);
    }

    @Override
    public void checkAccount(Account account, Boolean check) {
        accountModel.isAccountName(account, check);
    }


    @Override
    public void onAccountMessage(String message,Account account,Boolean check) {
//        0: không tồn tại tài khoản
//        1: có tài khoản nhưng sai mật khẩu
//        2: đúng tài khoản và mật khẩu
//        Còn lại đăng nhập thất bại
        Log.d("AAA", "onAccountMessage: "+message);
        if(message.equals("0")){
            loginView.userNameFalse();
        }else if(message.equals("1")){
            loginView.passNameFalse();
        }else if(message.equals("2")){
            accountModel.getAcc(account,check);
        }else {
            loginView.loginFail();
        }
    }

    @Override
    public void getAccount(Context context, AccountRemember account) {
        new MySharedPreferences().rememberPass(context,account);
        loginView.goHome();
    }

    @Override
    public void registerAccSuces(AccountRemember account) {

    }

    @Override
    public void updatePassSuces(AccountRemember account) {

    }

    @Override
    public void checkAccSuces(Boolean b) {

    }

    @Override
    public void checkAccSuces2(Boolean b) {

    }
}
