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
        //g???i mcallback
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
                //x??c th???c th??nh c??ng ngay l???p t???c m?? kh??ng c???n OTP
                enterOTPPresenter.signInWithPhoneAuthCredential(phoneAuthCredential);
                Log.d("AAA", "onCodeSent: oknoqw");
            }
            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                //x??c th???c th???t b???i
                Log.d("AAA", "X??c th???c th???t b ???i");
                // L???nh g???i l???i n??y ???????c g???i trong m???t y??u c???u x??c minh kh??ng h???p l??? ???????c th???c hi???n,
                // ch???ng h???n n???u ?????nh d???ng s??? ??i???n tho???i kh??ng h???p l???.
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Y??u c???u kh??ng h???p l???
                    //S??? ??i???n tho???i kh??ng h???p l???
                    Toast.makeText(EnterOTPActivity.this, "S??? ??i???n tho???i kh??ng h???p l???", Toast.LENGTH_SHORT).show();
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // ???? v?????t qu?? h???n ng???ch SMS cho d??? ??n
                    Toast.makeText(EnterOTPActivity.this, "B???n ????ng k?? qu?? s??? l???n cho ph??p trong 1 ng??y", Toast.LENGTH_SHORT).show();
                }
                // Hi???n th??? th??ng b??o v?? c???p nh???t giao di???n ng?????i d??ng

            }
            //Nh???n ???????c 1 OTP
            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                //Khi ??i???n tho???i kh??ng t??? ?????ng x??c th???c ??uc??? m?? c???n nh???p OTP
                //khi g???i cho b???n 1 OTP n?? s??? nh???y v??o ????y
                //s l?? id c???a l?????t g???i OTP ????
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
                                //Ho??n t???t progressbar
                                progressBar1.setProgress(progress);
                                if(progress == MAX)  {
                                    Toast.makeText(EnterOTPActivity.this, "OTP c???a b???n h???t h???n!!!", Toast.LENGTH_SHORT).show();
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