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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.edu.baogia.introducefood.interfaces.BooleanCallback;
import com.edu.baogia.introducefood.interfaces.ReportMVP;
import com.edu.baogia.introducefood.interfaces.StringCallback;
import com.edu.baogia.introducefood.model.object.Report;
import com.edu.baogia.introducefood.util.VolleyMultipartRequest;
import com.edu.baogia.introducefood.util.idwifi;


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
        String url= idwifi.urlAPI+"InsertRepost";
        JSONObject praObject = new JSONObject();
        try {

            praObject.put("Title",report.getTitle());
            praObject.put("Desc",report.getDesc());
            praObject.put("Time",report.getTime());
            praObject.put("Img",report.getImg());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest stringRequest=new JsonObjectRequest(Request.Method.POST, url,praObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String data=response.getString("Data");
                    if(data.trim().equals("true"))
                    {
                        callback.getBool(true);
                    }
                    else
                    {
                        callback.getBool(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("BBB",error.toString());
                callback.getBool(false);
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void uploadImg(Bitmap filePath, StringCallback callback) {
        String url="http://"+idwifi.ipWifi+":8080"+QuestModel.FOLDER+"fileUpload.php";
        Log.d("AAA",url);
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
