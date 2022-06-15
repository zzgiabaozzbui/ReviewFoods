package com.edu.baogia.introducefood.view.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.adapter.LoginAdapter;
import com.edu.baogia.introducefood.model.object.AccountRemember;
import com.edu.baogia.introducefood.model.object.NguoiDung;
import com.edu.baogia.introducefood.model.object.Users;
import com.edu.baogia.introducefood.presenter.LoginPresenter;
import com.edu.baogia.introducefood.presenter.LoginPresenterIml;
import com.edu.baogia.introducefood.util.MyInternet;
import com.edu.baogia.introducefood.util.MySharedPreferences;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements LoginView{
    private static final int RC_SIGN_IN = 1;
    GoogleSignInClient mGoogleSignInClient;

    TabLayout tabLayout;
    ViewPager viewPager;
    private CallbackManager callbackManager;
    Button btnF, btnG;
    TextView txtBoqua;
    LoginPresenter loginPresenter;
    //public static LoginAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        innit();
        animation();
        tablayout();
        face();
        google();
        click();




    }


    private void google() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void face() {
        //trình trợ kicks hoạt SDK
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();


    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Log.w("AAA", "signout    " );
                    }
                });
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Log.w("AAA", "signout revokeAccess   " );
                    }
                });
    }
    private void click() {
        //Đăng nhập bằng facebook
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInface();
            }
        });
        //Đăng nhập bằng ggoogle
        btnG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIngoogle();
            }
        });
        txtBoqua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //đăng xuất google
                signOut();
                //Đăng xuất face book
                signOutFace();

                new MySharedPreferences().rememberPass(LoginActivity.this,new AccountRemember());

                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
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

    private void signIngoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void signInface() {
        //Cấp quyền lấy các data
        LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this,
                Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d("Success", "Login");
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
//                                            graphObject có dạng:
//                                            {"id":"1728713757468587",
//                                            "name":"Nguyễn Quang Bảo",
//                                            "email":"nguyenquangbao2409@gmail.com",
//                                            "picture":
//                                                  {"data":
//                                                          {"height":200,
//                                                          "is_silhouette":false,
//                                                          "url":"https:\/\/platform-lookaside.fbsbx.com\/platform\/profilepic\/?asid=1728713757468587&height=200&width=200&ext=1657534191&hash=AeQ_tPy_iwrUIEOpM6Q",
//                                                          "width":201}
//                                                          }
//                                                   },
//                                            error: null}
                                        String id = null;
                                        String name = null;
                                        String email = null;
                                        String image_url = null;
                                        NguoiDung nguoiDung;

                                        try {
                                            id = object.getString("id");
                                            name = object.getString("name");
                                            email = object.getString("email");
                                            image_url = object.getJSONObject("picture")
                                                    .getJSONObject("data")
                                                    .getString("url");
//                                                public NguoiDung(int id, String tenDayDu, String anhDaiDien, String ngaySinh, String email, int gioiTinh, String sdt) {
                                            Users users = new Users(id,name,image_url,"",email,0,"");
                                            Log.d("AAA", "onCompleted: "+users.toString());
                                            loginPresenter.signInface(users,"0");

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,picture.type(large)");
                        request.setParameters(parameters);
                        request.executeAsync();

                    }

                    @Override
                    public void onCancel() {
//                                Nếu người sử dụng huỷ đăng nhập thì phương thức onCancel được gọi
                        Toast.makeText(LoginActivity.this, "Login Cancel", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
//                                Nếu quá trình đăng nhập phát sinh lỗi thì phương thức onError được gọi
                        Toast.makeText(LoginActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);

        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
//        GoogleSignInAccount chứa thông tin về người dùng đã đăng nhập, chẳng hạn như tên của người dùng.
        try {
            GoogleSignInAccount acct = completedTask.getResult(ApiException.class);

            if (acct != null) {
                String personName = acct.getDisplayName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                String personPhoto = acct.getPhotoUrl().toString();
                Log.d("AAA", "handleSignInResult: "+personPhoto);

                Users users = new Users(personId,personName,personPhoto,"",personEmail,0,"");
                Log.d("AAA", "onCompleted: "+users.toString());
                loginPresenter.signInface(users,"1");

            }

            Log.w("AAA", "signInResultok    " + acct.getAccount().toString());
            // Signed in successfully, show authenticated UI.
            updateUI(acct);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("AAA", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    private void updateUI(Object o) {
    }
    private void tablayout() {
        tabLayout.addTab(tabLayout.newTab().setText("                 Login                 "));
        tabLayout.addTab(tabLayout.newTab().setText("                 Sign Up                 "));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // không ở đâu trong mã, nó được xác định điều gì sẽ xảy ra khi người dùng nhấn / chọn tab
                // đây là lý do tại sao dòng sau là cần thiết
                // chúng ta cần đặt phân đoạn chính xác theo cách thủ công khi một tab được chọn / khai thác
                // và đây là vấn đề trong mã của bạn
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

                // Reload your recyclerView here
            }
        });
    }

    private void animation() {
        tabLayout.startAnimation(AnimationUtils.loadAnimation(this, R.anim.movetablayout));

        btnF.startAnimation(AnimationUtils.loadAnimation(this,R.anim.movebtnface));
        btnG.startAnimation(AnimationUtils.loadAnimation(this,R.anim.movebtngg));
    }

    private void innit() {
        //Màu thanh pin
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.argb(100,50,153,255));
        }
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        txtBoqua = findViewById(R.id.txtBoqua);
        btnF = findViewById(R.id.btnF);
        btnG = findViewById(R.id.btnG);
        loginPresenter = new LoginPresenterIml(this);
    }


    @Override
    public void goHome() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}