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

        String urlGetTypeFood = new idwifi().urlThang + "getHoSo.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, urlGetTypeFood, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        nguoiDung.setId(jsonObject.getInt("id"));
                        nguoiDung.setTenDayDu(jsonObject.getString("tendaydu"));
                        nguoiDung.setAnhDaiDien(jsonObject.getString("anhdaidien"));

                        nguoiDung.setNgaySinh(jsonObject.getString("ngaysinh"));
                        nguoiDung.setEmail(jsonObject.getString("email"));
                        nguoiDung.setGioiTinh(jsonObject.getInt("gioitinh"));
                        nguoiDung.setSdt(jsonObject.getString("sdt"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                callBackHoSo.onSuccessHoSo(nguoiDung);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("pdt", "onErrorResponse: " + error.toString());
                callBackHoSo.onErrorHoSo(error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("tentaikhoan",tenTK);
                return map;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }
    public void updateHoSo(Context context, NguoiDung nd, Bitmap isCheckBitMap){
        String urlUpdate= new idwifi().urlThang+"updateHoSo.php";
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlUpdate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("Sửa thành công")){
                    Toast.makeText(context, "Bạn đã sửa thông tin thành công", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "error sửa thông tin: "+error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("id",nd.getId()+"");
                map.put("tendaydu",nd.getTenDayDu());

                map.put("ngaysinh",nd.getNgaySinh());
                map.put("email",nd.getEmail());
                if(isCheckBitMap!=null){
                    map.put("anhdaidien",imageToString(isCheckBitMap));
                }
                map.put("gioitinh",nd.getGioiTinh()+"");
                map.put("sdt",nd.getSdt());
                return map;
            }
        };
        requestQueue.add(stringRequest);
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
