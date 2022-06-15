package com.edu.baogia.introducefood.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.edu.baogia.introducefood.model.mySQL.AccountInterface;
import com.edu.baogia.introducefood.model.mySQL.AccountModel;
import com.edu.baogia.introducefood.model.object.Account;
import com.edu.baogia.introducefood.model.object.AccountRemember;
import com.edu.baogia.introducefood.util.MySharedPreferences;
import com.edu.baogia.introducefood.view.activity.EnterOTPActivity;
import com.edu.baogia.introducefood.view.activity.EnterOTPView;
import com.edu.baogia.introducefood.view.activity.MainActivity;
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

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;


public class EnterOTPPresenterIml implements EnterOTPPresenter,AccountInterface {
    FirebaseAuth mAuth;
    EnterOTPView enterOTPView;
    AccountModel accountModel;
    Context context;
    Activity activity;

    public EnterOTPPresenterIml(EnterOTPView enterOTPView, Context context, Activity activity) {
        this.enterOTPView = enterOTPView;
        this.context = context;
        this.activity = activity;
        accountModel = new AccountModel(this,context);
    }

    @Override
    public void verify(String verificationId, String OTP) {
        //gửi OTP
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, OTP);
        signInWithPhoneAuthCredential(credential);
    }

    @Override
    public void verify2(String verificationId, String OTP) {
        //gửi OTP
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, OTP);
        signInWithPhoneAuthCredential2(credential);
    }

    @Override
    public void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("AAA", "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            String phonenumber = user.getPhoneNumber();
                            // Xác thực thành công
                            AccountRemember account = new MySharedPreferences().getAccSave(context);
                            accountModel.registerAccContext(account);

                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.d("AAA", "signInWithCredential:failure");
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                //OTP không khả dụng
                                Toast.makeText(context, "OTP của bạn nhập bị sai!!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
    private void signInWithPhoneAuthCredential2(PhoneAuthCredential credential) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("AAA", "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            String phonenumber = user.getPhoneNumber();
                            // Xác thực thành công
                            enterOTPView.goRePass();

                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.d("AAA", "signInWithCredential:failure");
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                //OTP không khả dụng
                                Toast.makeText(context, "OTP của bạn nhập bị sai!!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    @Override
    public void onAccountMessage(String message, Account account, Boolean check) {

    }

    @Override
    public void getAccount(Context context, AccountRemember account) {

    }

    @Override
    public void registerAccSuces(AccountRemember account) {
        new MySharedPreferences().rememberPass(context,account);
        enterOTPView.goMain();
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
