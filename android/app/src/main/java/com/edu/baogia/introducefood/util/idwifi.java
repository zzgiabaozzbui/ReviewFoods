package com.edu.baogia.introducefood.util;

import static com.edu.baogia.introducefood.model.mySQL.FoodInterator.monAnList;

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

    public static String ipWifi = "192.168.0.106";
    public static String urlImage="http://"+ipWifi+":81";
    public static String urlAPI="http://"+ipWifi+"/api/Dictionary/";

    public String urlThang="http://"+ipWifi+"/api/Dictionary/";
    public String getUrlThangImage="http://"+ipWifi+":81";
    public String urlNinh="http://"+ipWifi+"/ReviewFoods/service/ninh/";
    public  void getnamecate(TextView txt, int id1, Context context){
        String name ="Đang cập nhật";
        for (int i =0;i < monAnList.size();i++){
            if (id1==monAnList.get(i).getId()){
                name= monAnList.get(i).getNameCategory().toString();
            }
        }
        txt.setText(name);
    }
    public  void getnamecate2(TextView txt, int id1, Context context){
        String name ="Loại: "+"Đang cập nhật";
        for (int i =0;i < monAnList.size();i++){
            if (id1==monAnList.get(i).getId()){
                name= "Loại: "+monAnList.get(i).getNameCategory().toString();
            }
        }
        txt.setText(name);

    }
}
