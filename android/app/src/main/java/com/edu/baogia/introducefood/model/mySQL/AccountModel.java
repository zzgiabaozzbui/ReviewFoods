package com.edu.baogia.introducefood.model.mySQL;



import static com.edu.baogia.introducefood.util.idwifi.ipWifi;
import static com.edu.baogia.introducefood.util.idwifi.urlAPI;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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
        String url = urlAPI+"checkAccount";
        JSONObject jsonRequest = new JSONObject();
        try {

            jsonRequest.put("username", ""+account.getUsername());
            jsonRequest.put("pass", ""+account.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            listener.onAccountMessage(String.valueOf(response.get("Data")),account,check);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }

    //Xử lý tạo dữ liệu
    public void getAcc(Account account, Boolean check){
        RequestQueue requestQueue = Volley.newRequestQueue(fragment.getContext());
        String url = urlAPI+"GetUserID";
        AccountRemember accountRemember = new AccountRemember();
        accountRemember.setCheck(check);
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("username", ""+account.getUsername());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
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
                        Log.d("AAA", "onErrorResponse: "+error.toString());
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }

    //Xử lý tạo dữ liệu
    public void isAcc(String u){
        RequestQueue requestQueue = Volley.newRequestQueue(fragment.getContext());
        String url = urlAPI + "checkAcc";
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("username", ""+u);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean a = (boolean) response.get("Data");
                            listener.checkAccSuces(a);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }
    //Xử lý tạo dữ liệu
    public void isAcc2(String u){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = urlAPI + "checkAcc";
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("username", ""+u);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            boolean a = (boolean) response.get("Data");
                            listener.checkAccSuces2(a);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }

    //Xử lý tạo dữ liệu
    public void registerAcc(AccountRemember account){
        RequestQueue requestQueue = Volley.newRequestQueue(fragment.getContext());
        String url = urlAPI+"RegisterAcc";

        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("username", ""+account.getUsername());
            jsonRequest.put("pass", ""+account.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject object = response.getJSONObject("Data");
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
                });

        requestQueue.add(jsonObjectRequest);
    }

    //Xử lý tạo dữ liệu
    public void registerAccContext(AccountRemember account){
        RequestQueue requestQueue = Volley.newRequestQueue(fragment.getContext());
        String url = urlAPI+"RegisterAcc";

        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("username", ""+account.getUsername());
            jsonRequest.put("pass", ""+account.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject object = response.getJSONObject("Data");
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
                });

        requestQueue.add(jsonObjectRequest);
    }

    //Xử lý tạo dữ liệu
    public void upPass(String u, String p){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = urlAPI+"UpdatePassWord";
        AccountRemember account = new AccountRemember();
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("username", ""+u);
            jsonRequest.put("pass", ""+p);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject object = response.getJSONArray("Data").getJSONObject(0);
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
                });

        requestQueue.add(jsonObjectRequest);
    }
}
