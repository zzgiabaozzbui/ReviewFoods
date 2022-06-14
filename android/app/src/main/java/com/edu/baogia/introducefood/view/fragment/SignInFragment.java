package com.edu.baogia.introducefood.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.edu.baogia.introducefood.view.activity.FogetActivity;
import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.model.object.Account;
import com.edu.baogia.introducefood.model.object.AccountRemember;
import com.edu.baogia.introducefood.presenter.SignInPresenter;
import com.edu.baogia.introducefood.presenter.SignInPresenterIml;
import com.edu.baogia.introducefood.util.MySharedPreferences;
import com.edu.baogia.introducefood.view.activity.MainActivity;



public class SignInFragment extends Fragment  implements SignInView {

    EditText edtNumberPhone,edtPass;
    Button btnSignIn;
    TextView forget;
    SignInPresenter loginPresenter;
    CheckBox remember;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_sign_in,container,false);
        innit(root);
        checkRemember(root);
        animation(root);

        click();
        return root;
    }

    private void checkRemember(ViewGroup root) {
        AccountRemember accountRemember2 = new MySharedPreferences().getRememberAcc(getContext());
        if (accountRemember2.getCheck()==true){
            edtNumberPhone.setText(""+accountRemember2.getUsername());
            edtPass.setText(""+accountRemember2.getPassword());
            remember.setChecked(true);
        }else {
            edtNumberPhone.setText("");
            edtPass.setText("");
            remember.setChecked(false);
        }
    }

    private void animation(ViewGroup root) {
        edtNumberPhone.startAnimation(AnimationUtils.loadAnimation(root.getContext().getApplicationContext(),R.anim.moveedtnumber));
        edtPass.startAnimation(AnimationUtils.loadAnimation(root.getContext().getApplicationContext(),R.anim.moveedtpas));
        remember.startAnimation(AnimationUtils.loadAnimation(root.getContext().getApplicationContext(),R.anim.movefoget));
        forget.startAnimation(AnimationUtils.loadAnimation(root.getContext().getApplicationContext(),R.anim.movefoget));
        btnSignIn.startAnimation(AnimationUtils.loadAnimation(root.getContext().getApplicationContext(),R.anim.movebtnlogin));
    }

    private void click() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtNumberPhone.getText().toString().equals("")){
                    if(edtPass.getText().toString().equals("")){
                        Toast.makeText(getContext(), "Mật khẩu không được để trống!",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Tài khoản không được để trống!",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Account account = new Account(edtNumberPhone.getText().toString(),edtPass.getText().toString(),0,0);
                    loginPresenter.checkAccount(account,remember.isChecked());
                }

            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FogetActivity.class);
                startActivity(intent);
            }
        });
    }

    private void innit(ViewGroup root) {
        edtNumberPhone = root.findViewById(R.id.edtNumberPhone);
        edtPass = root.findViewById(R.id.edtPass);
        btnSignIn = root.findViewById(R.id.btnSignIn);
        remember = root.findViewById(R.id.remember);
        forget = root.findViewById(R.id.forget);
        loginPresenter = new SignInPresenterIml(this);
    }

    @Override
    public void userNameFalse() {
        Toast.makeText(getContext(), "Tên tài khoản không tồn tại!!",Toast.LENGTH_SHORT).show();
        edtNumberPhone.requestFocus();
    }

    @Override
    public void passNameFalse() {
        Toast.makeText(getContext(), "Sai mật khẩu!",Toast.LENGTH_SHORT).show();
        edtPass.requestFocus();

    }



    @Override
    public void loginFail() {
        Toast.makeText(getContext(), "Đăng nhập thất bại!!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goHome() {
        Toast.makeText(getContext(), "Đăng nhập thành công!!",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getContext(), MainActivity.class));
    }

}
