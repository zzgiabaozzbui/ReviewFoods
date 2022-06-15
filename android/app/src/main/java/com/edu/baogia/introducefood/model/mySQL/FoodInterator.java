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
import com.edu.baogia.introducefood.model.object.LoaiMonAn;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//lớp này là lớp xử lý, dữ liệu đẩy vào callback để đưa qua lớp presenter
public class FoodInterator {
    private LoadFoodListener listener;
    Context context;
    Fragment fragment;

    public FoodInterator(LoadFoodListener listener, Context context) {
        this.listener = listener;
        this.context = context;
    }
    public FoodInterator(LoadFoodListener listener, Fragment fragment) {
        this.listener = listener;
        this.fragment = fragment;
    }

    //Xử lý tạo dữ liệu
    public void createListData(){
        List<Food> listFood = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(fragment.getContext());
        String url = "http://" + ipWifi + "/ReviewFoods/service/bao/select.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject object;
                            String name = "";
                            Food us;
                            int id;
                            String nam;
                            String avatar;
                            String video;
                            String des;
                            String permiss;
                            String location;
                            int cateid;
                            for (int i = 0; i < response.length(); i++) {
                                object = response.getJSONObject(i);
                                id = object.getInt("id");
                                name = object.getString("name");
                                avatar = object.getString("img");
                                video = object.getString("video");
                                des = object.getString("des");
                                permiss = object.getString("permiss");
                                location = object.getString("location");
                                cateid = object.getInt("idcate");

                                us = new Food(id, name, avatar,video, des,permiss,location, cateid);
                                listFood.add(us);
                            }

                            listener.onLoadFoodSuccess(listFood);
                        } catch (Exception e) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAA", "onErrorResponse65: "+error.toString());

                        listener.onLoadFoodFailure(error.toString());
                    }
                });

        requestQueue.add(jsonArrayRequest);
    }

    //Xử lý tạo dữ liệu
    public void createListCate(){
        List<LoaiMonAn> listFood = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(fragment.getContext());
        String url = "http://" + ipWifi + "/ReviewFoods/service/bao/select.php";
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
                                tenloai = object.getString("tenloai");
                                anh = object.getString("anh");

                                us = new LoaiMonAn(id,tenloai,anh);
                                listFood.add(us);
                            }

                            listener.onLoadListCate(listFood);
                        } catch (Exception e) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAA", "onErrorResponse65: "+error.toString());

                        listener.onLoadFoodFailure(error.toString());
                    }
                });

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

                        listener.onLoadFoodFailure(error.toString());
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

    public void getRate(int id){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://" + ipWifi + "/ReviewFoods/service/bao/selectAllRate.php";
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);

                            Float rate = Float.parseFloat(object.getString("rate"));
                            String count = object.getString("count");
                            listener.onLoadRateSuccess(rate,count);
                        } catch (Exception e) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAA", "onErrorResponse65: "+error.toString());

                        listener.onLoadFoodFailure(error.toString());
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
    public void getDesFood(int id){
        List<Food> listFood = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(fragment.getContext());
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


                            listener.onLoadDesFoodSuccess(us);
                        } catch (Exception e) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAA", "onErrorResponse65: "+error.toString());

                        listener.onLoadFoodFailure(error.toString());
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
