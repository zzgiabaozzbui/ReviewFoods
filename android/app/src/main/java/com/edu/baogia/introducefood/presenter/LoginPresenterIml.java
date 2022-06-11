package com.edu.baogia.introducefood.presenter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.edu.baogia.introducefood.model.mySQL.AccountInterface;
import com.edu.baogia.introducefood.model.mySQL.AccountModel;
import com.edu.baogia.introducefood.model.mySQL.UserInterface;
import com.edu.baogia.introducefood.model.mySQL.UserModel;
import com.edu.baogia.introducefood.model.object.Account;
import com.edu.baogia.introducefood.model.object.AccountRemember;
import com.edu.baogia.introducefood.model.object.NguoiDung;
import com.edu.baogia.introducefood.model.object.Users;
import com.edu.baogia.introducefood.util.MySharedPreferences;
import com.edu.baogia.introducefood.view.activity.LoginActivity;
import com.edu.baogia.introducefood.view.activity.LoginView;
import com.edu.baogia.introducefood.view.fragment.SignInView;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


public class LoginPresenterIml implements LoginPresenter, UserInterface {
    LoginView loginView;
    UserModel userModel;
    Context context;

    public LoginPresenterIml(LoginView loginView) {
        this.context = (Context) loginView;
        this.loginView = loginView;
        this.userModel = new UserModel(this, (Context) loginView);
    }



    @Override
    public void signInface(Users users, String applk) {
        Log.d("AAA", "signInface: ");
        new MySharedPreferences().signInAppLk(context,applk);
        userModel.insertUser(users,applk);
    }

    @Override
    public void checkUserSuces(AccountRemember accountRemember) {
        accountRemember.setCheck(false);
        new MySharedPreferences().rememberPass(context,accountRemember);
        loginView.goHome();
    }
}
