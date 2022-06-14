package com.edu.baogia.introducefood.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.edu.baogia.introducefood.model.mySQL.AccountInterface;
import com.edu.baogia.introducefood.model.mySQL.AccountModel;
import com.edu.baogia.introducefood.model.object.Account;
import com.edu.baogia.introducefood.model.object.AccountRemember;
import com.edu.baogia.introducefood.util.MySharedPreferences;
import com.edu.baogia.introducefood.view.activity.EnterOTPActivity;
import com.edu.baogia.introducefood.view.activity.MainActivity;
import com.edu.baogia.introducefood.view.fragment.SignInView;
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


public class SignUpPresenterIml implements SignUpPresenter,AccountInterface {
    FirebaseAuth mAuth;
    SignUpView signUpView;
    AccountModel accountModel;
    Context context;
    Activity activity;
    AccountRemember account;
    private PhoneAuthProvider.ForceResendingToken mforceResendingToken;

    public SignUpPresenterIml(SignUpView signUpView, Context context, Activity activity) {
        this.signUpView = signUpView;
        this.context = context;
        accountModel = new AccountModel(this, (Fragment) signUpView);
        this.activity = activity;
    }


    @Override
    public void checkTK(String e) {
        accountModel.isAcc(e);
    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("AAA", "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            accountModel.registerAcc(account);
                            String phonenumber = user.getPhoneNumber();
                            // Xác thực thành công
                            success();
                        } else {
                            // Sign in failed, display a message and update the UI
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                //OTP không khả dụng
                                Log.d("AAA", "OTP không khả dụng");
                            }
                        }
                    }
                });
    }

    private void success() {
        Intent intent = new Intent( context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onAccountMessage(String message, Account account, Boolean check) {

    }

    @Override
    public void getAccount(Context context, AccountRemember account) {

    }

    @Override
    public void registerAccSuces(AccountRemember account) {
//        new MySharedPreferences().rememberPass(context,account);
        signUpView.goMain();
    }

    @Override
    public void updatePassSuces(AccountRemember account) {

    }

    @Override
    public void checkAccSuces(Boolean b) {
        signUpView.checkSuces(b);
    }

    @Override
    public void checkAccSuces2(Boolean b) {

    }
}
