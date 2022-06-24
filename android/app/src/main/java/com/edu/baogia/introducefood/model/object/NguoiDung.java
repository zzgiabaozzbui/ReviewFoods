package com.edu.baogia.introducefood.model.object;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
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

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class NguoiDung {
    private int id;
    private String tenDayDu;
    private String anhDaiDien;
    private String ngaySinh;
    private String email;
    private int gioiTinh;
    private String sdt;

    public NguoiDung() {
    }

    public NguoiDung(int id, String tenDayDu, String anhDaiDien, String ngaySinh, String email, int gioiTinh, String sdt) {
        this.id = id;
        this.tenDayDu = tenDayDu;
        this.anhDaiDien = anhDaiDien;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDayDu() {
        return tenDayDu;
    }

    public void setTenDayDu(String tenDayDu) {
        this.tenDayDu = tenDayDu;
    }

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Override
    public String toString() {
        return "NguoiDung{" +
                "id=" + id +
                ", tenDayDu='" + tenDayDu + '\'' +
                ", anhDaiDien='" + anhDaiDien + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", email='" + email + '\'' +
                ", gioiTinh=" + gioiTinh +
                ", sdt=" + sdt +
                '}';
    }
    public void getNguoiDung(Context context,CallBackHoSo callBackHoSo,String tenTK){
        NguoiDung nguoiDung=new NguoiDung();

        String urlGetHoso = new idwifi().urlThang + "LoadHoSo";
        JSONObject praObject = new JSONObject();
        try {

            praObject.put("tentaikhoan",tenTK);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, urlGetHoso, praObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject;
                    JSONArray jsonArray=response.getJSONArray("Data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        nguoiDung.setId(jsonObject.getInt("id"));
                        nguoiDung.setTenDayDu(jsonObject.getString("tendaydu"));
                        nguoiDung.setAnhDaiDien(jsonObject.getString("anhdaidien"));

                        nguoiDung.setNgaySinh(jsonObject.getString("ngaysinh"));
                        nguoiDung.setEmail(jsonObject.getString("email"));
                        nguoiDung.setGioiTinh(jsonObject.getInt("gioitinh"));
                        nguoiDung.setSdt(jsonObject.getString("sdt"));
                    }
                    callBackHoSo.onSuccessHoSo(nguoiDung);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBackHoSo.onErrorHoSo(error.toString());
            }
        });


        requestQueue.add(jsonObjectRequest);
    }
    public void updateHoSo(Context context, NguoiDung nd, Bitmap isCheckBitMap){
        String urlUpdate= new idwifi().urlThang+"updateHoSo";
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        JSONObject praObject = new JSONObject();
        try {
            praObject.put("id",nd.getId()+"");
            praObject.put("tendaydu",nd.getTenDayDu());

            praObject.put("ngaysinh",nd.getNgaySinh());
            praObject.put("email",nd.getEmail());

            praObject.put("gioitinh",nd.getGioiTinh()+"");
            praObject.put("sdt",nd.getSdt());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, urlUpdate, praObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String data=response.getString("Data");
                    if(data.equalsIgnoreCase("true"))
                        Toast.makeText(context, "Bạn đã sửa thông tin thành công", Toast.LENGTH_SHORT).show();
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

    private String imageToString(Bitmap bm){
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        byte[] imageBytes=outputStream.toByteArray();

        String encodeImage= Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodeImage;
    }

    public interface CallBackHoSo{
        void onSuccessHoSo(NguoiDung nguoiDung);
        void onErrorHoSo(String error);
    }
}
