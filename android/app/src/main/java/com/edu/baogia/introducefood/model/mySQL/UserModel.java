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
public class UserModel {
    private UserInterface listener;
    Context context;

    public UserModel(UserInterface listener, Context context) {
        this.listener = listener;
        this.context = context;
    }

    public void insertUser(Users users, String applk){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://" + ipWifi + "/ReviewFoods/service/bao/addUserFaceBook.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject object = null;
                        try {
                            object = new JSONArray(response).getJSONObject(0);
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
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("id", ""+users.getId());
                params.put("name", ""+users.getTenDayDu());
                params.put("email", ""+users.getEmail());
                params.put("picture", ""+users.getAnhDaiDien());
                params.put("applk", ""+applk);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}
