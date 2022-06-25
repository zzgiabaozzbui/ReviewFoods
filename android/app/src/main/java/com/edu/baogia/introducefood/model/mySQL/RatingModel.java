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
import com.edu.baogia.introducefood.model.object.Danhgia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        String url = urlAPI+"insertRating";
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("username", danhgia.getTenTaiKhoan());
            jsonRequest.put("idfood", ""+danhgia.getIdMonAn());
            jsonRequest.put("rate", ""+danhgia.getRating());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            listener.onRateMessage(String.valueOf(response.get("Data")));
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

    public void getRating(int idFood, String taikhoan){
        RequestQueue requestQueue = Volley.newRequestQueue(fragment.getContext());

        String url = urlAPI + "GetRating";
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("username", ""+taikhoan);
            jsonRequest.put("idfood", ""+idFood);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Data");
                            if (jsonArray.length() == 0){
                                listener.onGetRateMessage("-1");
                            }else {
                                listener.onGetRateMessage(response.getJSONArray("Data").getJSONObject(0).get("danhgia").toString());
                            }
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
