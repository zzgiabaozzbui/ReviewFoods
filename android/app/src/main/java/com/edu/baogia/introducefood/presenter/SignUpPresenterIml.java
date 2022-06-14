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
    public void sendOTP(String e, String pas) {
        mAuth = FirebaseAuth.getInstance();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(e)       // Phone number to verify
                        .setTimeout(10L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(activity)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                //xác thực thành công ngay lập tức mà không cần OTP
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                //xác thực thất bại
                                Log.d("AAA", "Xác thực thất b ại");
                                // Lệnh gọi lại này được gọi trong một yêu cầu xác minh không hợp lệ được thực hiện,
                                // chẳng hạn nếu định dạng số điện thoại không hợp lệ.

                                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                                    // Yêu cầu không hợp lệ
                                    //Số điện thoại không hợp lệ
                                    Log.d("AAA", "sdt ko hợp lệ: ");
                                    Toast.makeText(context, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                                } else if (e instanceof FirebaseTooManyRequestsException) {
                                    // Đã vượt quá hạn ngạch SMS cho dự án
                                    Log.d("AAA", "Bạn đăng ký quá số lần cho phép trong 1 tiếng");
                                }

                                // Hiển thị thông báo và cập nhật giao diện người dùng


                            }

                            //Nhận được 1 OTP
                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                //Khi điện thoại không tự động xác thực đucợ mà cần nhập OTP
                                //khi gửi cho bạn 1 OTP nó sẽ nhảy vào đây
                                //s là id của lượt gửi OTP đó

                                mforceResendingToken = forceResendingToken;

                                signUpView.enterOTP(e,pas,s);

                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
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
