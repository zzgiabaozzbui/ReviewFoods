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
import com.edu.baogia.introducefood.model.object.Danhgia;

import java.util.HashMap;
import java.util.Map;

public class RatingModel {
    private RatignInterface listener;
    Context context;
    Fragment fragment;

    public RatingModel(RatignInterface listener, Fragment fragment) {
        this.listener = listener;
        this.fragment = fragment;
    }
    public void insertRate(Danhgia danhgia){
        RequestQueue requestQueue = Volley.newRequestQueue(fragment.getContext());
        String url = "http://" + ipWifi + "/ReviewFoods/service/bao/insertRating.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onRateMessage(response);
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
                params.put("username", danhgia.getTenTaiKhoan());
                params.put("idfood", ""+danhgia.getIdMonAn());
                params.put("rate", ""+danhgia.getRating());
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void getRating(int idFood, String taikhoan){
        RequestQueue requestQueue = Volley.newRequestQueue(fragment.getContext());
        String url = "http://" + ipWifi + "/ReviewFoods/service/bao/selectRating.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onGetRateMessage(response);
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
                params.put("username", ""+taikhoan);
                params.put("idfood", ""+idFood);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

}
