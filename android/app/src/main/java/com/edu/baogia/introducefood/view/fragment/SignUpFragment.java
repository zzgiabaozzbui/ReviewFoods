package com.edu.baogia.introducefood.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.presenter.SignUpPresenter;
import com.edu.baogia.introducefood.presenter.SignUpPresenterIml;
import com.edu.baogia.introducefood.view.activity.EnterOTPActivity;
import com.edu.baogia.introducefood.view.activity.MainActivity;
import com.google.firebase.auth.PhoneAuthProvider;


public class SignUpFragment extends Fragment implements SignUpView{
    EditText edtNumberPhone, edtPs, edtRePs;
    Button btnRegister;
    SignUpPresenter signUpPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_sign_up,container,false);
        innit(root);
        click();
        return root;
    }

    private void innit(ViewGroup root) {
        edtNumberPhone = root.findViewById(R.id.edtNumberPhone);
        btnRegister = root.findViewById(R.id.btnRegister);
        edtPs = root.findViewById(R.id.edtPs);
        edtRePs  = root.findViewById(R.id.edtRePs);
        signUpPresenter = new SignUpPresenterIml(this,getContext(),this.getActivity());
    }

    private void click() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtNumberPhone.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Số điện thoại không được để trống", Toast.LENGTH_SHORT).show();
                }else{
                    signUpPresenter.checkTK(edtNumberPhone.getText().toString());
                }


            }
        });
    }



    @Override
    public void enterOTP(String e, String pas) {
        Intent intent = new Intent(this.getActivity(), EnterOTPActivity.class);
        intent.putExtra("numberPhone",e);
        intent.putExtra("pass",pas);
        startActivity(intent);

    }

    @Override
    public void goMain() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void checkSuces(Boolean b) {
        if(b==true){
            Toast.makeText(getActivity(), "Số điện thoại đã có tài khoản", Toast.LENGTH_SHORT).show();
        }else{
            if(edtPs.getText().toString().equals("")){
                Toast.makeText(getActivity(), "Mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
            }else if(edtRePs.getText().toString().equals("")){
                Toast.makeText(getActivity(), "Mật khẩu nhập lại không được để trống", Toast.LENGTH_SHORT).show();
            }else if(edtPs.getText().toString().equals(edtRePs.getText().toString())){
                enterOTP(edtNumberPhone.getText().toString(),edtPs.getText().toString());
            }else {
                Toast.makeText(getActivity(), "Mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
