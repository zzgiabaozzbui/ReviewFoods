package com.edu.baogia.introducefood.util;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.edu.baogia.introducefood.model.object.LoaiMonAn;

import org.json.JSONArray;
import org.json.JSONObject;

public class idwifi {

    public static String ipWifi = "192.168.0.106";


    public String urlThang="http://"+ipWifi+"/api/Dictionary/";
    public String getUrlThangImage="http://"+ipWifi+":81";
    public String urlNinh="http://"+ipWifi+"/ReviewFoods/service/ninh/";
    public  void getnamecate(TextView txt, int id1, Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://" + ipWifi + "/ReviewFoods/service/bao/selectCate.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject object;
                            LoaiMonAn us;
                            int id;
                            String tenloai;
                            String anh;
                            for (int i = 0; i < response.length(); i++) {
                                object = response.getJSONObject(i);
                                id = object.getInt("id");
                                if(id == id1){
                                    txt.setText(object.getString("tenloai"));
                                }

                            }

                        } catch (Exception e) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAA", "onErrorResponse65: "+error.toString());

                    }
                });
        requestQueue.add(jsonArrayRequest);
    }
}
