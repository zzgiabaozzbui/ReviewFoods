package com.edu.baogia.introducefood.model.object;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.edu.baogia.introducefood.util.UrlVolley;
import com.edu.baogia.introducefood.util.idwifi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoaiMonAn {
    private int id;
    private String nameCategory;
    private String Image;


    public LoaiMonAn(int id, String nameCategory, String image) {
        this.id = id;
        this.nameCategory = nameCategory;
        Image = image;

    }

    public LoaiMonAn() {
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }



    @Override
    public String toString() {
        return "DoanhMuc{" +
                "id=" + id +
                ", nameCategory='" + nameCategory + '\'' +
                ", Image=" + Image +
                '}';
    }
    public void getTypeFood(CallBackTypeFood callBackTypeFood, Context context){
        List<LoaiMonAn> loaiMonAns = new ArrayList<>();


        String urlGetTypeFood = new idwifi().urlThang + "LoadLoaiMonAn";


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,urlGetTypeFood, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArray=null;
                try {
                    jsonArray=response.getJSONArray("Data");
                    JSONObject jsonObject;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("idloaimonan");
                        String tenLoaiMonAn = jsonObject.getString("tenloai");
                        String anh = jsonObject.getString("anh");
                        loaiMonAns.add(new LoaiMonAn(id, tenLoaiMonAn, anh));
                    }
                    callBackTypeFood.onSuccessTypeFood(loaiMonAns);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackTypeFood.onFailTypeFood(error.toString());
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
    public interface CallBackTypeFood {
        void onSuccessTypeFood(List<LoaiMonAn> callBackTypeFood);
        void onFailTypeFood(String callBackTypeFood);
    }
}
