package com.edu.baogia.introducefood.model.mySQL;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.edu.baogia.introducefood.interfaces.BooleanCallback;
import com.edu.baogia.introducefood.interfaces.ReportMVP;
import com.edu.baogia.introducefood.interfaces.StringCallback;
import com.edu.baogia.introducefood.model.object.Report;
import com.edu.baogia.introducefood.util.VolleyMultipartRequest;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;


public class ReportModel implements ReportMVP.Model {
    Context context;

    public ReportModel(Context context) {
        this.context = context;
    }

    @Override
    public void addReport(Report report, BooleanCallback callback) {
        String url= QuestModel.IP+QuestModel.FOLDER+"insertReport.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("Success"))
                {
                    callback.getBool(true);
                }
                else
                {
                    callback.getBool(false);
                }
                Log.d("BBB",response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("BBB",error.toString());
                callback.getBool(false);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("Title",report.getTitle());
                map.put("Desc",report.getDesc());
                if(report.getImg()!=null)
                {
                    map.put("Img",report.getImg());
                }
                map.put("Time",report.getTime());
                return map;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void uploadImg(Bitmap filePath, StringCallback callback) {
        String url=QuestModel.IP+QuestModel.FOLDER+"fileUpload.php";
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));
                            String filename=""+obj.getString("file_name");
                            callback.getString(filename);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       callback.getString(null);
                    }
                }) {
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("image", new DataPart(imagename + ".png", getFileDataFromDrawable(filePath)));
                return params;
            }
        };
        //adding the request to volley
        Volley.newRequestQueue(context).add(volleyMultipartRequest);
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

}
