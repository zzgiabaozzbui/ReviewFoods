package com.edu.baogia.introducefood.model.mySQL;

import static com.edu.baogia.introducefood.util.idwifi.ipWifi;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.edu.baogia.introducefood.model.object.Food;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//lớp này là lớp xử lý, dữ liệu đẩy vào callback để đưa qua lớp presenter
public class VideoInterator {
    private LoadVideoModel listener;
    Context context;
    Fragment fragment;

    public VideoInterator(LoadVideoModel listener, Fragment fragment) {
        this.listener = listener;
        this.fragment = fragment;
    }

    public void getVideoFood(int id) {
        List<Food> listFood = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(fragment.getContext());
        String url = "http://" + ipWifi + "/ReviewFoods/service/bao/selectFood.php";
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            String des = object.getString("des");
                            String video;
                            video = object.getString("video");

                            listener.onLoadVideo(video,des);
                        } catch (Exception e) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAA", "onErrorResponse65: " + error.toString());

                    }
                }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", "" + id);
                return params;
            }
        };

        requestQueue.add(jsonArrayRequest);
    }

    public void getDesFood(int id) {
        List<Food> listFood = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(fragment.getContext());
        String url = "http://" + ipWifi + "/ReviewFoods/service/bao/selectFood.php";
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            String name = "";
                            Food us = null;
                            int id;
                            String nam;
                            String avatar;
                            String video;
                            String des;
                            String permiss;
                            String location;
                            int cateid;

                            id = object.getInt("id");
                            name = object.getString("name");
                            avatar = object.getString("img");
                            video = object.getString("video");
                            des = object.getString("des");
                            permiss = object.getString("permiss");
                            location = object.getString("location");
                            cateid = object.getInt("idcate");

                            us = new Food(id, name, avatar, video, des, permiss, location, cateid);


                            listener.onLoadDesFoodSuccess(us);
                        } catch (Exception e) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAA", "onErrorResponse65: " + error.toString());


                    }
                }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", "" + id);
                return params;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }

    public void getFood(int id){
        List<Food> listFood = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://" + ipWifi + "/ReviewFoods/service/bao/selectFood.php";
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            String name = "";
                            Food us = null;
                            int id;
                            String nam;
                            String avatar;
                            String video;
                            String des;
                            String permiss;
                            String location;
                            int cateid;

                            id = object.getInt("id");
                            name = object.getString("name");
                            avatar = object.getString("img");
                            video = object.getString("video");
                            des = object.getString("des");
                            permiss = object.getString("permiss");
                            location = object.getString("location");
                            cateid = object.getInt("idcate");

                            us = new Food(id, name, avatar,video, des,permiss,location, cateid);


                            listener.onLoadFoodSuccess(us);
                        } catch (Exception e) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAA", "onErrorResponse65: "+error.toString());


                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("id", ""+id);
                return params;
            }
        };

        requestQueue.add(jsonArrayRequest);
    }
}
