package com.edu.baogia.introducefood.model.mySQL;

import static com.edu.baogia.introducefood.util.idwifi.ipWifi;
import static com.edu.baogia.introducefood.util.idwifi.urlAPI;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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
import com.edu.baogia.introducefood.model.object.DanhDau;
import com.edu.baogia.introducefood.model.object.Food;
import com.edu.baogia.introducefood.model.object.LoaiMonAn;
import com.edu.baogia.introducefood.model.object.MonAn;
import com.edu.baogia.introducefood.util.idwifi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//lớp này là lớp xử lý, dữ liệu đẩy vào callback để đưa qua lớp presenter
public class FoodInterator {
    public static List<LoaiMonAn> monAnList = new ArrayList<>();
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
        String url = urlAPI+"LoadMonAn";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Data");
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
                            for (int i = 0; i < jsonArray.length(); i++) {
                                object = jsonArray.getJSONObject(i);
                                id = object.getInt("id");
                                name = object.getString("tenmonan");
                                avatar = object.getString("anh");
                                video = object.getString("video");
                                des = object.getString("mota");
                                permiss = object.getString("cachlam");
                                location = object.getString("noiban");
                                cateid = object.getInt("idloaimonan");

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
                        Log.d("AAA", "onErrorResponse95: "+error.toString());

                        listener.onLoadFoodFailure(error.toString());
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }

    //Xử lý tạo dữ liệu
    public void createListCate(){
        List<LoaiMonAn> listFood = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(fragment.getContext());
        String url = urlAPI+"LoadLoaiMonAn";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Data");
                            JSONObject object;
                            LoaiMonAn us;
                            int id;
                            String tenloai;
                            String anh;
                            for (int i = 0; i < jsonArray.length(); i++) {
                                object = jsonArray.getJSONObject(i);
                                id = object.getInt("idloaimonan");
                                tenloai = object.getString("tenloai");
                                anh = object.getString("anh");

                                us = new LoaiMonAn(id,tenloai,anh);
                                listFood.add(us);
                            }

                            listener.onLoadListCate(listFood);
                            monAnList = listFood;
                        } catch (Exception e) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAA", "onErrorResponse138: "+error.toString());

                        listener.onLoadFoodFailure(error.toString());
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }
    public void getFoodDanhDau( Context context,int id, String tenTaiKhoan){
        String urlGetDanhDau= new idwifi().urlThang+"LoadAllDanhDau";
        JSONObject praObject = new JSONObject();
        try {
            praObject.put("tentaikhoan",tenTaiKhoan);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, urlGetDanhDau, praObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArray=null;
                try {
                    jsonArray=response.getJSONArray("Data");
                    JSONObject jsonObject;
                    boolean b = false;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Log.d("bb", "onResponse: "+jsonArray.length() );
                        jsonObject = jsonArray.getJSONObject(i);
                        int id1 = jsonObject.getInt("id");
                        if (id1 ==id){
                            b=true;
                        }else {
                            b=false;
                        }

                    }
                    listener.onLoadDDSuccess(b);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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
                        Log.d("AAA", "onErrorResponse192: "+error.toString());

                        listener.onLoadFoodFailure(error.toString());
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }

    public void getRate(int id){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = urlAPI+"GetAllRate";
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

                            String count = object.getString("count");

                            Float rate;
                            if (count.equals("0")){
                                rate = Float.parseFloat("5.0");
                            }else {
                                rate = Float.parseFloat(object.getString("rates"));
                            }
                            Log.d("AAA", "onResponsefood226: "+rate);
                            listener.onLoadRateSuccess(rate,count);
                        } catch (Exception e) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("AAA", "onErrorResponse234: "+error.toString());

                        listener.onLoadFoodFailure(error.toString());
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }
    public void getDesFood(int id){
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
                        Log.d("AAA", "onErrorResponse288: "+error.toString());

                        listener.onLoadFoodFailure(error.toString());
                    }
                });

        requestQueue.add(jsonObjectRequest);

    }

    public void insertDanhdau(int idMonAn,String tenTaiKhoan){
        if(idMonAn<0 || tenTaiKhoan==null){
            return;
        }
        String urlThem= new idwifi().urlThang+"InsertDanhDau";
        JSONObject praObject = new JSONObject();
        try {
            praObject.put("idFood",idMonAn+"" );
            praObject.put("tentaikhoan",tenTaiKhoan);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, urlThem, praObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String data=response.getString("Data");
                    if(data.equalsIgnoreCase("true"))
                        Toast.makeText(context, "Bạn đã thêm sản phẩm vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue.add(jsonObjectRequest);
    }

    public void deleteDanhdau(int idMonAn,String tenTaiKhoan) {
        if(idMonAn<0 || tenTaiKhoan==null){
            return;
        }
        String urlXoa= new idwifi().urlThang+"DeleteDanhDau";
        JSONObject praObject = new JSONObject();
        try {
            praObject.put("idFood",idMonAn+"" );
            praObject.put("tentaikhoan",tenTaiKhoan);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, urlXoa, praObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("Data").equalsIgnoreCase("true")){
                        Toast.makeText(context, "Bạn đã xóa sản phẩm vào danh sách yêu thích", Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });



        requestQueue.add(jsonObjectRequest);
    }
}
