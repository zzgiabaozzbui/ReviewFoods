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
import com.edu.baogia.introducefood.model.object.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//lớp này là lớp xử lý, dữ liệu đẩy vào callback để đưa qua lớp presenter
public class AccModel {
    private AccInterface listener;
    Context context;
    Fragment fragment;

    public AccModel(AccInterface listener, Fragment fragment) {
        this.listener = listener;
        this.fragment = fragment;
    }


    //Xử lý tạo dữ liệu
    public void getAcc(String account){
        RequestQueue requestQueue = Volley.newRequestQueue(fragment.getContext());
        String url = "http://" + ipWifi + "/ReviewFoods/service/bao/selectUser.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONArray(response).getJSONObject(0);
//                            Log.d("AAA", "onResponse: "+object.getString("id"));
//                            Log.d("AAA", "onResponse: "+object.getString("tenDayDu"));
//                            Log.d("AAA", "onResponse: "+object.getString("anhDaiDien"));
//                            Log.d("AAA", "onResponse: "+object.getString("ngaySinh"));
//                            Log.d("AAA", "onResponse: "+object.getString("email"));
//                            Log.d("AAA", "onResponse: "+object.getString("gioiTinh"));
//                            Log.d("AAA", "onResponse: "+object.getString("sdt"));
                            Users users =new Users(object.getString("id"),
                                                    object.getString("tenDayDu"),
                                                    object.getString("anhDaiDien"),
                                                    object.getString("ngaySinh"),
                                                    object.getString("email"),
                                                    object.getInt("gioiTinh"),
                                                    object.getString("sdt")
                                                    );
                            listener.getAccount(users);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAA", "onResponse: "+error.toString());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("username", ""+account);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

}
