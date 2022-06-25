package com.edu.baogia.introducefood.model.mySQL;

import static com.edu.baogia.introducefood.util.idwifi.ipWifi;
import static com.edu.baogia.introducefood.util.idwifi.urlAPI;

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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.edu.baogia.introducefood.model.object.Food;

import org.json.JSONArray;
import org.json.JSONException;
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
        RequestQueue requestQueue = Volley.newRequestQueue(fragment.getContext());
        String url =urlAPI+ "GetFoodID";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", ""+id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject object = (JSONObject) response.getJSONArray("Data").get(0);
                            String video;
                            String des;
                            video = object.getString("video");
                            des = object.getString("mota");

                            listener.onLoadVideo(video,des);
                        } catch (Exception e) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAA", "onErrorResponsevideo71: "+error.toString());

                    }
                });

        requestQueue.add(jsonObjectRequest);

    }

    public void getDesFood(int id) {
        RequestQueue requestQueue = Volley.newRequestQueue(fragment.getContext());
        String url =urlAPI+ "GetFoodID";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", ""+id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject object = (JSONObject) response.getJSONArray("Data").get(0);
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
                            name = object.getString("tenmonan");
                            avatar = object.getString("anh");
                            video = object.getString("video");
                            des = object.getString("mota");
                            permiss = object.getString("cachlam");
                            location = object.getString("noiban");
                            cateid = object.getInt("idloaimonan");

                            us = new Food(id, name, avatar,video, des,permiss,location, cateid);


                            listener.onLoadDesFoodSuccess(us);
                        } catch (Exception e) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAA", "onErrorResponsevideo126: "+error.toString());
                    }
                });

        requestQueue.add(jsonObjectRequest);

    }

    public void getFood(int id){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url =urlAPI+ "GetFoodID";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", ""+id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject object = (JSONObject) response.getJSONArray("Data").get(0);
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
                            name = object.getString("tenmonan");
                            avatar = object.getString("anh");
                            video = object.getString("video");
                            des = object.getString("mota");
                            permiss = object.getString("cachlam");
                            location = object.getString("noiban");
                            cateid = object.getInt("idloaimonan");

                            us = new Food(id, name, avatar,video, des,permiss,location, cateid);


                            listener.onLoadFoodSuccess(us);
                        } catch (Exception e) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAA", "onErrorResponsevideo180: "+error.toString());

                    }
                });

        requestQueue.add(jsonObjectRequest);
    }
}
