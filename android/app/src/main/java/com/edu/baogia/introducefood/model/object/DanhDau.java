package com.edu.baogia.introducefood.model.object;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.edu.baogia.introducefood.util.UrlVolley;
import com.edu.baogia.introducefood.util.idwifi;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DanhDau {
    private int idMonAn;
    private String tenTaiKhoan;

    public DanhDau(int idMonAn, String tenTaiKhoan) {
        this.idMonAn = idMonAn;
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public DanhDau() {
    }

    public int getIdMonAn() {
        return idMonAn;
    }

    public void setIdMonAn(int idMonAn) {
        this.idMonAn = idMonAn;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public void themDanhDau(Context context){
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

    public void xoaDanhdau(Context context) {
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
    public void getFoodDanhDau(CallBackGetFoodDanhDau callBackGetFoodDanhDau,Context context){
        List<MonAn> danhDauList = new ArrayList<>();
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
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Log.d("bb", "onResponse: "+jsonArray.length() );
                        jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String tenMonAn = jsonObject.getString("tenmonan");
                        String anh = jsonObject.getString("anh");
                        danhDauList.add(new MonAn(id, tenMonAn, anh));
                    }
                    callBackGetFoodDanhDau.onSuccess(danhDauList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackGetFoodDanhDau.onFail(error.toString());
            }
        });


        requestQueue.add(jsonObjectRequest);
    }
    public void getCheckedFood(CallBackCheckedFood callBackCheckedFood, Context context){
        List<DanhDau> danhDauList = new ArrayList<>();

        String urlGetCheckedFood = new idwifi().urlThang + "LoadCheckedDanhDau";
        JSONObject praObject = new JSONObject();
        try {
            praObject.put("idFood",idMonAn+"" );
            praObject.put("tentaikhoan",tenTaiKhoan);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, urlGetCheckedFood, praObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArray=null;
                try {
                    jsonArray=response.getJSONArray("Data");
                    JSONObject jsonObject;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("idmonan");
                        String tenTaiKhoan = jsonObject.getString("tentaikhoan");
                        danhDauList.add(new DanhDau(id, tenTaiKhoan));
                    }
                    callBackCheckedFood.onSuccessCheckedFood(danhDauList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackCheckedFood.onErrorCheckedFood(error.toString());
            }
        });


        requestQueue.add(jsonObjectRequest);
    }
    public interface CallBackCheckedFood{
        void onSuccessCheckedFood(List<DanhDau> danhDauList);
        void onErrorCheckedFood(String error);
    }
    public interface CallBackGetFoodDanhDau{
        void onSuccess(List<MonAn> danhDauList);
        void onFail(String error);
    }
}
