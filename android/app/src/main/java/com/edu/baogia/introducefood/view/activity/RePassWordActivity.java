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
import com.edu.baogia.introducefood.presenter.RePassWordPresenter;
import com.edu.baogia.introducefood.presenter.RePassWordPresenterIml;
import com.edu.baogia.introducefood.util.MySharedPreferences;

public class RePassWordActivity extends AppCompatActivity implements RePassWordView{
    EditText edtPs,edtRePs;
    Button btnRegister;
    RePassWordPresenter rePassWordPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_pass_word);
        innit();
        click();
    }

    private void click() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtPs.getText().toString().equals("")){
                    Toast.makeText(RePassWordActivity.this, "Mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
                }else if(edtRePs.getText().toString().equals("")){
                    Toast.makeText(RePassWordActivity.this, "Mật khẩu nhập lại không được để trống", Toast.LENGTH_SHORT).show();
                }else if(edtPs.getText().toString().equals(edtRePs.getText().toString())){
                    String numberPhone = new MySharedPreferences().getRememberAcc(RePassWordActivity.this).getUsername();
                    AccountRemember accountRemember = new AccountRemember(numberPhone,
                                                                    edtPs.getText().toString(),
                                                                    -1,
                                                                    false);

                    rePassWordPresenter.updatePass(numberPhone,edtPs.getText().toString());
                }else {
                    Toast.makeText(RePassWordActivity.this, "Mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void innit() {
        edtPs = findViewById(R.id.edtPs);
        edtRePs = findViewById(R.id.edtRePs);
        btnRegister = findViewById(R.id.btnRegister);
        rePassWordPresenter = new RePassWordPresenterIml(this,this);
    }

    @Override
    public void goMain() {
        Intent intent = new Intent(RePassWordActivity.this, MainActivity.class);
        startActivity(intent);

    }
}