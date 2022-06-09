package com.edu.baogia.introducefood.model.mySQL;



import static com.edu.baogia.introducefood.util.idwifi.ipWifi;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.edu.baogia.introducefood.model.object.Account;
import com.edu.baogia.introducefood.model.object.AccountRemember;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//lớp này là lớp xử lý, dữ liệu đẩy vào callback để đưa qua lớp presenter
public class AccountModel {
    private AccountInterface listener;
    Context context;
    Fragment fragment;

    public AccountModel(AccountInterface listener, Fragment fragment) {
        this.listener = listener;
        this.fragment = fragment;
    }

    public AccountModel(AccountInterface listener, Context context) {
        this.listener = listener;
        this.context = context;
    }
    //Xử lý tạo dữ liệu
    public void isAccountName(Account account, Boolean check){
        RequestQueue requestQueue = Volley.newRequestQueue(fragment.getContext());
        String url = "http://" + ipWifi + "/ReviewFoods/service/bao/checkAccount.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onAccountMessage(response,account,check);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("username", account.getUsername());
                params.put("pass", account.getPassword());
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    //Xử lý tạo dữ liệu
    public void getAcc(Account account, Boolean check){
        RequestQueue requestQueue = Volley.newRequestQueue(fragment.getContext());
        String url = "http://" + ipWifi + "/ReviewFoods/service/bao/selectAcc.php";
        AccountRemember accountRemember = new AccountRemember();
        accountRemember.setCheck(check);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONArray(response).getJSONObject(0);
                            accountRemember.setUsername(object.getString("tentaikhoan"));
                            accountRemember.setPassword(object.getString("matkhau"));
                            accountRemember.setIduser(object.getInt("idnguoidung"));
                            listener.getAccount(fragment.getContext().getApplicationContext(), accountRemember);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("username", ""+account.getUsername());
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    //Xử lý tạo dữ liệu
    public void isAcc(String u){
        RequestQueue requestQueue = Volley.newRequestQueue(fragment.getContext());
        String url = "http://" + ipWifi + "/ReviewFoods/service/bao/checkAcc.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equals("0")){
                            //khoong co tai khoan
                            listener.checkAccSuces(false);
                        }else {
                            listener.checkAccSuces(true);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("username", ""+u);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
    //Xử lý tạo dữ liệu
    public void isAcc2(String u){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://" + ipWifi + "/ReviewFoods/service/bao/checkAcc.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equals("0")){
                            //khoong co tai khoan
                            listener.checkAccSuces2(false);
                        }else {
                            listener.checkAccSuces2(true);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("username", ""+u);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    //Xử lý tạo dữ liệu
    public void registerAcc(AccountRemember account){
        RequestQueue requestQueue = Volley.newRequestQueue(fragment.getContext());
        String url = "http://" + ipWifi + "/ReviewFoods/service/bao/registerAcc.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONArray(response).getJSONObject(0);
                            account.setUsername(object.getString("tentaikhoan"));
                            account.setPassword(object.getString("matkhau"));
                            account.setIduser(object.getInt("idnguoidung"));
                            listener.registerAccSuces(account);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("username", ""+account.getUsername());
                params.put("pass", ""+account.getPassword());
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    //Xử lý tạo dữ liệu
    public void registerAccContext(AccountRemember account){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://" + ipWifi + "/ReviewFoods/service/bao/registerAcc.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONArray(response).getJSONObject(0);
                            account.setUsername(object.getString("tentaikhoan"));
                            account.setPassword(object.getString("matkhau"));
                            account.setIduser(object.getInt("idnguoidung"));
                            listener.registerAccSuces(account);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("username", ""+account.getUsername());
                params.put("pass", ""+account.getPassword());
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    //Xử lý tạo dữ liệu
    public void upPass(String u, String p){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://" + ipWifi + "/ReviewFoods/service/bao/upPassWord.php";
        AccountRemember account = new AccountRemember();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONArray(response).getJSONObject(0);
                            account.setUsername(object.getString("tentaikhoan"));
                            account.setPassword(object.getString("matkhau"));
                            account.setIduser(object.getInt("idnguoidung"));
                            account.setCheck(false);
                            listener.updatePassSuces(account);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("username", ""+u);
                params.put("pass", ""+p);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}
