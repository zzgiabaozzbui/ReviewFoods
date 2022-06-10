package com.edu.baogia.introducefood.model.mySQL;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.edu.baogia.introducefood.interfaces.BooleanCallback;
import com.edu.baogia.introducefood.interfaces.ListReviewCallback;
import com.edu.baogia.introducefood.interfaces.ReviewMVP;
import com.edu.baogia.introducefood.model.object.Review;

import org.json.JSONArray;
import org.json.JSONObject;

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
        String url= QuestModel.IP+"/Webservice/KTPM/insertReview.php";
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
                return map;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void updateReview(Review review, BooleanCallback booleanCallback) {
        String url= QuestModel.IP+"/Webservice/KTPM/updateReview.php";
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
                map.put("KeyReview",review.getKeyReview()+"");
                return map;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void deleteReview(Review review, BooleanCallback booleanCallback) {
        String url= QuestModel.IP+"/Webservice/KTPM/deleteReview.php";
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
        return null;
    }

    @Override
    public void getListReview(int key, ListReviewCallback callback) {
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        String url=QuestModel.IP+"/Webservice/KTPM/selectReview.php";
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
                        review.setName("Manhhlunn");
                        review.setAva("https://static-images.vnncdn.net/files/publish/nhan-sac-elizabeth-olsen-phu-thuy-sieu-quyen-ru-trong-doctor-strange-2-d0a203b613204da1bb467af5df0dcd5c.jpg");
                        review.setKey(jsonObject.getInt("keyFood"));
                        review.setText(jsonObject.getString("content"));
                        review.setTime(jsonObject.getString("timeRV"));
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
}
