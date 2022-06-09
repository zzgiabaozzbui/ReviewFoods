package com.edu.baogia.introducefood.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.model.object.AccountRemember;
import com.edu.baogia.introducefood.presenter.EnterOTPPresenter;
import com.edu.baogia.introducefood.presenter.EnterOTPPresenterIml;
import com.edu.baogia.introducefood.util.MySharedPreferences;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class EnterOTPActivity extends AppCompatActivity implements EnterOTPView{
    EditText edtOTP;
    Button btnOTP;
    FirebaseAuth mAuth;
    EnterOTPPresenter enterOTPPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otpactivity);
        edtOTP = findViewById(R.id.edtOTP);
        btnOTP = findViewById(R.id.btnOTP);
        mAuth = FirebaseAuth.getInstance();
        enterOTPPresenter = new EnterOTPPresenterIml(this,this,this);

        String numberPhone = getIntent().getStringExtra("numberPhone");
        String pass = getIntent().getStringExtra("pass");
        String idOTP = getIntent().getStringExtra("idOTP");
        String repass = getIntent().getStringExtra("repass");



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