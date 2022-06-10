package com.edu.baogia.introducefood.model.object;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.edu.baogia.introducefood.util.UrlVolley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MonAn {
    private int id;
    private String tenMonAn;
    private String anh;
    private String video;
    private String moTa;
    private String cachLam;
    private String noiBan;
    private int idLoaiMonAn;

    public MonAn() {
    }

    public MonAn(int id, String tenMonAn, String anh, String video, String moTa, String cachLam, String noiBan, int idLoaiMonAn) {
        this.id = id;
        this.tenMonAn = tenMonAn;
        this.anh = anh;
        this.video = video;
        this.moTa = moTa;
        this.cachLam = cachLam;
        this.noiBan = noiBan;
        this.idLoaiMonAn = idLoaiMonAn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLoaiMonAn() {
        return idLoaiMonAn;
    }

    public void setIdLoaiMonAn(int idLoaiMonAn) {
        this.idLoaiMonAn = idLoaiMonAn;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getCachLam() {
        return cachLam;
    }

    public void setCachLam(String cachLam) {
        this.cachLam = cachLam;
    }

    public String getNoiBan() {
        return noiBan;
    }

    public void setNoiBan(String noiBan) {
        this.noiBan = noiBan;
    }

    public interface CallBackFood{
        void onSuccessFood(List<MonAn> callBackFood);
        void onFailFood(String callBackFood);
    }

    public void getListFoodDanhMuc(CallBackFood callBackFood, Context context){
        List<MonAn> monAnList = new ArrayList<>();
        UrlVolley urlVolley = new UrlVolley();
        String urlGetFood = urlVolley.url + "getAllFood.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(Request.Method.GET, urlGetFood, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        int idTypeFood = jsonObject.getInt("idloaimonan");
                        String tenMonAn = jsonObject.getString("tenmonan");
                        String anh = jsonObject.getString("anh");
                        String moTa = jsonObject.getString("mota");
                        monAnList.add(new MonAn(id, tenMonAn, anh,"",moTa,"","",idTypeFood));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                callBackFood.onSuccessFood(monAnList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackFood.onFailFood(error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}
