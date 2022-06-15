package com.edu.baogia.introducefood.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.model.object.AccountRemember;
import com.edu.baogia.introducefood.presenter.ForgetPresenter;
import com.edu.baogia.introducefood.presenter.ForgetPresenterIml;

public class FogetActivity extends AppCompatActivity implements  ForgetView {

    EditText edtNumberPhone;
    Button btnForget;
    ForgetPresenter forgetPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foget);
        innit();
        click();
    }

    private void click() {
        btnForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtNumberPhone.getText().toString().equals("")){
                    Toast.makeText(FogetActivity.this, "Số điện thoại không được để trống", Toast.LENGTH_SHORT).show();
                }else{
                    forgetPresenter.checkAcc(edtNumberPhone.getText().toString());
                }
            }
        });
    }

    private void innit() {
        edtNumberPhone= findViewById(R.id.edtNumberPhone);
        btnForget = findViewById(R.id.btnForget);
        forgetPresenter = new ForgetPresenterIml(this,this);
    }

    @Override
    public void enterOTP(String e, String pas, String s) {
        Intent intent = new Intent(FogetActivity.this, EnterOTPActivity.class);
        intent.putExtra("numberPhone",edtNumberPhone.getText().toString());
        intent.putExtra("repass","ok");
        startActivity(intent);
    }

    @Override
    public void checkSuces(Boolean b) {
        if(b==false){
            Toast.makeText(FogetActivity.this, "Số điện thoại này chưa được đăng ký!", Toast.LENGTH_SHORT).show();
        }else {
            forgetPresenter.sendOTP(edtNumberPhone.getText().toString());
        }
    }


}