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
import com.edu.baogia.introducefood.interfaces.ListReviewCallback;
import com.edu.baogia.introducefood.interfaces.ReviewMVP;
import com.edu.baogia.introducefood.interfaces.StringCallback;
import com.edu.baogia.introducefood.model.object.AccountRemember;
import com.edu.baogia.introducefood.model.object.Review;
import com.edu.baogia.introducefood.util.MySharedPreferences;
import com.edu.baogia.introducefood.util.VolleyMultipartRequest;
import com.edu.baogia.introducefood.util.idwifi;
import com.edu.baogia.introducefood.view.activity.HoSoView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReviewModel implements ReviewMVP.Model {
    Context context;

    public ReviewModel(Context context) {
        this.context = context;
    }


    @Override
    public void addReview(Review review, BooleanCallback callback) {
        String url= QuestModel.IP+QuestModel.FOLDER+"insertReview.php";
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
                map.put("Account",review.getAccount());
                map.put("Desc",review.getText());
                map.put("Key",review.getKey()+"");
                map.put("Time",review.getTime());
                if(review.getImg()!=null)
                {
                    map.put("Img",review.getImg());
                }
                return map;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void updateReview(Review review, BooleanCallback booleanCallback) {
        String url= QuestModel.IP+QuestModel.FOLDER+"updateReview.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("Success"))
                {
                    booleanCallback.getBool(true);
                }
                else
                {
                    booleanCallback.getBool(false);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("BBB",error.toString());
                booleanCallback.getBool(false);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("Desc",review.getText());
                map.put("Img",review.getImg());
                map.put("KeyReview",review.getKeyReview()+"");
                return map;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void deleteReview(Review review, BooleanCallback booleanCallback) {
        String url= QuestModel.IP+QuestModel.FOLDER+"deleteReview.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("Success"))
                {
                    booleanCallback.getBool(true);
                }
                else
                {
                    booleanCallback.getBool(false);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("BBB",error.toString());
                booleanCallback.getBool(false);
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("KeyReview",review.getKeyReview()+"");
                return map;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public String getUid() {
         AccountRemember accountRemember=new MySharedPreferences().getRememberAcc(context);
         return  accountRemember.getUsername();
    }

    @Override
    public void getListReview(int key, ListReviewCallback callback) {
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        String url=QuestModel.IP+QuestModel.FOLDER+"selectReview.php";
        Log.d("AAA",url);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public  void onResponse(String response) {
                Log.d("AAA",response.toString());
                try {
                JSONArray jsonArr = new JSONArray(response.trim());
                List<Review> reviewList=new ArrayList<>();
                for (int i = 0; i < jsonArr.length(); i++) {
                    Review review;
                        JSONObject jsonObject=jsonArr.getJSONObject(i);
                        review=new Review();
                        review.setKeyReview(jsonObject.getInt("keyRV"));
                        review.setAccount(jsonObject.getString("account"));
                        review.setName(jsonObject.getString("name"));
                        review.setAva(new idwifi().urlThang+jsonObject.getString("avatar"));
                        review.setKey(jsonObject.getInt("keyFood"));
                        review.setText(jsonObject.getString("content"));
                        review.setTime(jsonObject.getString("timeRV"));
                        review.setImg(jsonObject.getString("img"));
                        reviewList.add(review);

                    }
                    callback.getList(reviewList);
                }
                catch (Exception e)
                {
                    callback.getList(null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("BBB",error.toString());
                callback.getList(null);
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("Key",key+"");
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void uploadImg(Bitmap filePath, StringCallback callback) {
        String url=QuestModel.IP+QuestModel.FOLDER+"fileUpload.php";
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        Log.d("AAA",response.toString());
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));
                            String s=new String(response.data);
                            String filename=""+obj.getString("file_name");
                            callback.getString(filename);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            String s=new String(response.data);
                            callback.getString(null);
                            Log.d("AAA",s);
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
