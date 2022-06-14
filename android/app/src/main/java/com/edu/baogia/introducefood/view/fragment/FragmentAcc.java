package com.edu.baogia.introducefood.view.fragment;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.model.object.AccountRemember;
import com.edu.baogia.introducefood.presenter.FoodRatingPresenter;
import com.edu.baogia.introducefood.presenter.FoodRatingPresenterIm;
import com.edu.baogia.introducefood.util.MySharedPreferences;
import com.edu.baogia.introducefood.view.activity.LoginActivity;
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


public class FragmentAcc extends Fragment  {

    Button btnSign;
    GoogleSignInClient mGoogleSignInClient;
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

                //đăng xuất google
                signOut();
                //Đăng xuất face book
                signOutFace();

                new MySharedPreferences().rememberPass(getContext(),new AccountRemember());

                Intent intent = new Intent(getActivity(),LoginActivity.class);
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
}
