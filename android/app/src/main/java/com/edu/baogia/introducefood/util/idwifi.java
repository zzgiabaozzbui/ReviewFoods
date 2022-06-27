package com.edu.baogia.introducefood.util;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.edu.baogia.introducefood.model.object.LoaiMonAn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class idwifi {

    public static String ipWifi = "192.168.43.202";
    public static String urlImage="http://"+ipWifi+":81";
    public static String urlAPI="http://"+ipWifi+"/api/Dictionary/";

    public String urlThang="http://"+ipWifi+"/api/Dictionary/";
    public String getUrlThangImage="http://"+ipWifi+":81";
    public String urlNinh="http://"+ipWifi+"/ReviewFoods/service/ninh/";
    public  void getnamecate(TextView txt, int id1, Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = urlAPI + "LoadMonAnID";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", ""+id1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject object = (JSONObject) response.getJSONArray("Data").get(0);

                            txt.setText(object.getString("tenloai"));

                        } catch (Exception e) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAA", "onErrorResponseidwifi56: "+error.toString());

                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
    public  void getnamecate2(TextView txt, int id1, Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = urlAPI + "LoadMonAnID";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", ""+id1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject object = (JSONObject) response.getJSONArray("Data").get(0);
                            txt.setText("Loại: "+object.getString("tenloai"));

                        } catch (Exception e) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAA", "onErrorResponseidwifi56: "+error.toString());

                    }
                });
        requestQueue.add(jsonObjectRequest);

    }
}
