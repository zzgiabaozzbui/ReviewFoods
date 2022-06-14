package com.edu.baogia.introducefood.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.model.object.AccountRemember;
import com.edu.baogia.introducefood.presenter.EnterOTPPresenter;
import com.edu.baogia.introducefood.presenter.EnterOTPPresenterIml;
import com.edu.baogia.introducefood.presenter.SignUpPresenter;
import com.edu.baogia.introducefood.util.MySharedPreferences;
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

public class EnterOTPActivity extends AppCompatActivity implements EnterOTPView{
    EditText edtOTP;
    Button btnOTP;
    FirebaseAuth mAuth;
    TextView txtReOTP;
    EnterOTPPresenter enterOTPPresenter;
    ProgressBar progressBar1;
    private Handler handler = new Handler();
    String numberPhone;
    String pass;
    String idOTP;
    String repass;
    int i;
    int progressre =0;
    PhoneAuthProvider.ForceResendingToken mforceResendingToken;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks  mCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otpactivity);
        edtOTP = findViewById(R.id.edtOTP);
        btnOTP = findViewById(R.id.btnOTP);
        progressBar1 = findViewById(R.id.progressBar1);
        txtReOTP = findViewById(R.id.txtReOTP);
        mAuth = FirebaseAuth.getInstance();
        enterOTPPresenter = new EnterOTPPresenterIml(this,this,this);
        //gọi mcallback
        callBack();
        numberPhone = getIntent().getStringExtra("numberPhone");
        pass = getIntent().getStringExtra("pass");
        new MySharedPreferences().accSave(this,new AccountRemember(numberPhone,pass,-1,false));
        idOTP = getIntent().getStringExtra("idOTP");
        repass = getIntent().getStringExtra("repass");
        startSendOtp();
        click();
    }

    private void click() {
        btnOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String OTP = edtOTP.getText().toString();
                if(repass.equals("ok")){
                    enterOTPPresenter.verify2(idOTP,OTP );
                }else {
                    enterOTPPresenter.verify(idOTP,OTP );
                }
            }
        });

        txtReOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressre = 200;
                reSend();
            }
        });
    }

    private void startSendOtp() {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(numberPhone)       // Phone number to verify
                        .setTimeout(10L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }


    private void reSend() {
//        FirebaseAuth.getInstance().signOut();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(numberPhone)       // Phone number to verify
                        .setTimeout(10L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)          // OnVerificationStateChangedCallbacks
                        .setForceResendingToken(mforceResendingToken)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    private void callBack() {
        mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                //xác thực thành công ngay lập tức mà không cần OTP
                enterOTPPresenter.signInWithPhoneAuthCredential(phoneAuthCredential);
                Log.d("AAA", "onCodeSent: oknoqw");
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
                    Toast.makeText(EnterOTPActivity.this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // Đã vượt quá hạn ngạch SMS cho dự án
                    Toast.makeText(EnterOTPActivity.this, "Bạn đăng ký quá số lần cho phép trong 1 ngày", Toast.LENGTH_SHORT).show();
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
                idOTP = s;
                mforceResendingToken = forceResendingToken;

                txtReOTP.setVisibility(View.GONE);
                doStartProgressBar1();
                Log.d("AAA", "onCodeSent: re");
            }
        };
    }


    private void success() {
        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);
    }

    private void doStartProgressBar1()  {
        final int MAX = 10;
        progressBar1.setMax(MAX);

        Thread thread = new Thread(new Runnable()  {
            @Override
            public void run() {
                for( i = 0; i < MAX; i++) {
                    int progress = progressre + i + 1;
                    // Do something (Download, Upload, Update database,..)
                    SystemClock.sleep(1000); // Sleep 1000 milliseconds.

                    // Update interface.
                    handler.post(new Runnable() {
                        public void run() {
                            if(progress > MAX)  {
                                i=0;
                                progressre=0;
                                progressBar1.setProgress(0);
                            }else {
                                //Hoàn tất progressbar
                                progressBar1.setProgress(progress);
                                if(progress == MAX)  {
                                    Toast.makeText(EnterOTPActivity.this, "OTP của bạn hết hạn!!!", Toast.LENGTH_SHORT).show();
                                    txtReOTP.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    });
                }
            }
        });
        thread.start();

    }


    @Override
    public void goMain() {
        Intent intent = new Intent(EnterOTPActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void goRePass() {
        Intent intent = new Intent(EnterOTPActivity.this, RePassWordActivity.class);
        startActivity(intent);

    }
}