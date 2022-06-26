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
import com.edu.baogia.introducefood.model.object.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//lớp này là lớp xử lý, dữ liệu đẩy vào callback để đưa qua lớp presenter
public class UserModel {
    private UserInterface listener;
    Context context;

    public UserModel(UserInterface listener, Context context) {
        this.listener = listener;
        this.context = context;
    }

    public void insertUser(Users users, String applk){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = urlAPI+"InsertUserFacebook";
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("id", ""+users.getId());
            jsonRequest.put("name", ""+users.getTenDayDu());
            jsonRequest.put("email", ""+users.getEmail());
//            jsonRequest.put("picture", ""+users.getAnhDaiDien());
            jsonRequest.put("applk", ""+applk);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject object = response.getJSONArray("Data").getJSONObject(0);
                            AccountRemember accountRemember = new AccountRemember();
                            accountRemember.setUsername(object.getString("tentaikhoan"));
                            accountRemember.setPassword(object.getString("matkhau"));
                            accountRemember.setIduser(object.getInt("idnguoidung"));
                            listener.checkUserSuces(accountRemember);
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
