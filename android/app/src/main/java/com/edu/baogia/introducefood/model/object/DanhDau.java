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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.edu.baogia.introducefood.util.UrlVolley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        String urlThem= new UrlVolley().url+"themDanhDau.php";
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlThem, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("Thêm thành công")){
                    Toast.makeText(context, "Bạn đã thêm sản phẩm vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "error thêm đánh dấu: "+error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("idFood",idMonAn+"");
                map.put("tentaikhoan",tenTaiKhoan);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void xoaDanhdau(Context context) {
            if(idMonAn<0 || tenTaiKhoan==null){
                return;
            }
        String urlXoa= new UrlVolley().url+"xoaDanhDau.php";
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlXoa, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("Xóa thành công")){
                    Toast.makeText(context, "Bạn đã xóa sản phẩm trong danh sách yêu thích", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "error xóa đánh dấu: "+error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("idFood",idMonAn+"");
                map.put("tentaikhoan",tenTaiKhoan);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void getCheckedFood(CallBackCheckedFood callBackCheckedFood, Context context){
        List<DanhDau> danhDauList = new ArrayList<>();
        UrlVolley urlVolley = new UrlVolley();
        String urlGetCheckedFood = urlVolley.url + "getCheckedDanhDau.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlGetCheckedFood, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject;
                for (int i = 0; i <= response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("idmonan");
                        String tenTaiKhoan =jsonObject.getString("tentaikhoan");
                        danhDauList.add(new DanhDau(id,tenTaiKhoan));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                callBackCheckedFood.onSuccessCheckedFood(danhDauList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("pdt", "onErrorResponse: " + error.toString());
                callBackCheckedFood.onErrorCheckedFood(error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("tentaikhoan",tenTaiKhoan);
                return map;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }
    public interface CallBackCheckedFood{
        void onSuccessCheckedFood(List<DanhDau> danhDauList);
        void onErrorCheckedFood(String error);
    }
}
