package com.edu.baogia.introducefood.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.edu.baogia.introducefood.model.mySQL.AccountInterface;
import com.edu.baogia.introducefood.model.mySQL.AccountModel;
import com.edu.baogia.introducefood.model.object.Account;
import com.edu.baogia.introducefood.model.object.AccountRemember;
import com.edu.baogia.introducefood.util.MySharedPreferences;
import com.edu.baogia.introducefood.view.activity.MainActivity;
import com.edu.baogia.introducefood.view.activity.RePassWordActivity;
import com.edu.baogia.introducefood.view.activity.RePassWordView;
import com.edu.baogia.introducefood.view.fragment.SignUpView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class RePassWordPresenterIml implements RePassWordPresenter,AccountInterface {
    RePassWordView rePassWordView;
    AccountModel accountModel;
    Context context;

    public RePassWordPresenterIml(RePassWordView rePassWordView, Context context) {
        this.rePassWordView = rePassWordView;
        this.context = context;
        accountModel = new AccountModel(this, (Context) rePassWordView);
    }

    @Override
    public void onAccountMessage(String message, Account account, Boolean check) {

    }

    @Override
    public void getAccount(Context context, AccountRemember account) {

    }

    @Override
    public void registerAccSuces(AccountRemember account) {

    }

    @Override
    public void updatePassSuces(AccountRemember account) {
        new MySharedPreferences().rememberPass(context,account);

    }

    @Override
    public void checkAccSuces(Boolean b) {

    }

    @Override
    public void checkAccSuces2(Boolean b) {

    }


    @Override
    public void updatePass(String username, String pass) {
        accountModel.upPass(username,pass);
        rePassWordView.goMain();
    }
}
