package com.edu.baogia.introducefood.view.fragment;

import static com.edu.baogia.introducefood.util.idwifi.ipWifi;
import static com.edu.baogia.introducefood.util.idwifi.urlImage;
import static com.facebook.FacebookSdk.getApplicationContext;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.model.object.Account;
import com.edu.baogia.introducefood.model.object.AccountRemember;
import com.edu.baogia.introducefood.model.object.Users;
import com.edu.baogia.introducefood.presenter.AccPresenter;
import com.edu.baogia.introducefood.presenter.AccPresenterIm;
import com.edu.baogia.introducefood.presenter.FoodRatingPresenter;
import com.edu.baogia.introducefood.presenter.FoodRatingPresenterIm;
import com.edu.baogia.introducefood.util.MyInternet;
import com.edu.baogia.introducefood.util.MySharedPreferences;
import com.edu.baogia.introducefood.util.idwifi;
import com.edu.baogia.introducefood.view.activity.DanhDauView;
import com.edu.baogia.introducefood.view.activity.HoSoView;
import com.edu.baogia.introducefood.view.activity.LoginActivity;
import com.edu.baogia.introducefood.view.activity.QuestActivity;
import com.edu.baogia.introducefood.view.activity.ReportActivity;
import com.edu.baogia.introducefood.view.activity.SplashActivity;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import java.io.IOException;


public class FragmentAcc extends Fragment  implements FragmentAccView{
    ImageView imgBottom, imgTop;
    TextView txtHoten;
    Button btnSign, btnSuaTk, btnCauHoi, btnLh,btnDanhDau;
    GoogleSignInClient mGoogleSignInClient;
    AccPresenter accPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_acc, container, false);
        innit(root);


        click();

        return root;
    }

    private void click() {
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccountRemember accountRemember = new MySharedPreferences().getRememberAcc(getContext());
                if (accountRemember.getUsername()==null||accountRemember.getUsername().equals("null")){
                    Intent intent = new Intent(getActivity(),LoginActivity.class);
                    startActivity(intent);
                }else {
                    //đăng xuất google
                    signOut();
                    //Đăng xuất face book
                    signOutFace();

                    if (new MySharedPreferences().getRememberAcc(getActivity()).getCheck()==false){
                        new MySharedPreferences().rememberPass(getContext(),new AccountRemember());
                    }

                    Intent intent = new Intent(getActivity(),LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
        btnSuaTk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), HoSoView.class);
                startActivity(intent);
            }
        });
        btnDanhDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhDauView.class);
                startActivity(intent);
            }
        });
        btnLh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ReportActivity.class);
                startActivity(intent);
            }
        });
        btnCauHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), QuestActivity.class);
                startActivity(intent);
            }
        });
    }


    private void innit(ViewGroup root) {

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
        btnSign = root.findViewById(R.id.btnSign);
        btnSuaTk= root.findViewById(R.id.btnSuaTk);
        btnCauHoi= root.findViewById(R.id.btnCauHoi);
        btnDanhDau=root.findViewById(R.id.btnDanhDau);
        btnLh = root.findViewById(R.id.btnLh);
        txtHoten = root.findViewById(R.id.txtHoten);
        imgBottom = root.findViewById(R.id.imgBottom);
        imgTop = root.findViewById(R.id.imgTop);
        accPresenter = new AccPresenterIm(this);
        AccountRemember accountRemember = new MySharedPreferences().getRememberAcc(getContext());
        if (accountRemember.getUsername()==null||accountRemember.getUsername().equals("null")){
            btnSign.setText("Đăng nhập");
            btnSuaTk.setVisibility(View.GONE);
        }else {
            btnSign.setText("Đăng xuất");
            btnSuaTk.setVisibility(View.VISIBLE);

            Log.d("AAA", "innit: "+accountRemember.getUsername());
            accPresenter.getAcc(accountRemember.getUsername());
        }
    }



    private void signOutFace() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {
                AccessToken.setCurrentAccessToken(null);
                LoginManager.getInstance().logOut();
                Log.d("AAA", "out: ");
            }
        }).executeAsync();
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Log.w("AAA", "signout    " );
                    }
                });
    }

    @Override
    public void setacc(Users users) {
        Log.d("AAA", "setacc: "+users.toString());
        if(users.getAnhDaiDien()==null||users.getAnhDaiDien().equals("null")){
            imgTop.setImageResource(R.drawable.food);
            imgBottom.setImageResource(R.drawable.food);
        }else {
            Picasso.get().load(urlImage+users.getAnhDaiDien()).into(imgTop);
            Picasso.get().load(urlImage+users.getAnhDaiDien()).into(imgBottom);
        }
        if(users.getTenDayDu()==null||users.getTenDayDu().equals("null")){

        }else {
            txtHoten.setText(users.getTenDayDu());
        }

    }
}
