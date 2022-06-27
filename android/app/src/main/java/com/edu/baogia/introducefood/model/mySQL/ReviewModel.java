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
        String url= idwifi.urlAPI+"InsertReview";
        JSONObject praObject = new JSONObject();
        try {
            praObject.put("Account",review.getAccount());
            praObject.put("Desc",review.getText());
            praObject.put("Time",review.getTime());
            praObject.put("Key",review.getKey()+"");
            praObject.put("Img",review.getImg());

            Log.d("AAA",praObject.toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest stringRequest=new JsonObjectRequest(Request.Method.POST, url,praObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("AAA",response.toString());
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
                    Log.d("AAA",e.toString());
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
    public void updateReview(Review review, BooleanCallback booleanCallback) {
        String url= idwifi.urlAPI+"UpdateReview";

        JSONObject praObject = new JSONObject();
        try {
            praObject.put("KeyReview",review.getKeyReview()+"");
            praObject.put("Content",review.getText());
            praObject.put("Img",review.getImg());


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
                        booleanCallback.getBool(true);
                    }
                    else
                    {
                        booleanCallback.getBool(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("BBB",error.toString());
                booleanCallback.getBool(false);
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void deleteReview(Review review, BooleanCallback booleanCallback) {
        String url= idwifi.urlAPI+"DeleteReview";

        JSONObject praObject = new JSONObject();
        try {
            praObject.put("KeyReview",review.getKeyReview()+"");
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
                        booleanCallback.getBool(true);
                    }
                    else
                    {
                        booleanCallback.getBool(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("BBB",error.toString());
                booleanCallback.getBool(false);
            }
        });
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
        String url=idwifi.urlAPI+"LoadAllReview";
        Log.d("AAA",url);

        JSONObject praObject = new JSONObject();
        try {

            praObject.put("Key",key);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest stringRequest=new JsonObjectRequest(Request.Method.POST, url,praObject, new Response.Listener<JSONObject>() {
            @Override
            public  void onResponse(JSONObject response) {
                Log.d("AAA",response.toString());
                try {
                JSONArray jsonArr = response.getJSONArray("Data");
                    Log.d("AAA",jsonArr.toString());
                List<Review> reviewList=new ArrayList<>();
                for (int i = 0; i < jsonArr.length(); i++) {
                    Review review;
                        JSONObject jsonObject=jsonArr.getJSONObject(i);
                        review=new Review();
                        review.setKeyReview(jsonObject.getInt("keyRV"));
                        review.setAccount(jsonObject.getString("account"));
                        review.setName(jsonObject.getString("tendaydu"));
                        review.setAva(idwifi.urlImage+jsonObject.getString("anhdaidien"));
                        Log.d("AAA",review.getAva());
                        review.setKey(jsonObject.getInt("keyFood"));
                        review.setText(jsonObject.getString("content"));
                        review.setTime(jsonObject.getString("timeRV"));
                        review.setImg(jsonObject.getString("img"));
                        reviewList.add(review);
                    Log.d("AAA",review.toString());

                    }
                    callback.getList(reviewList);
                }
                catch (Exception e)
                {
                    Log.d("AAA",e.toString());
                    callback.getList(null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("BBB",error.toString());
                callback.getList(null);
            }
        });
        requestQueue.add(stringRequest);
    }

    @Override
    public void uploadImg(Bitmap filePath, StringCallback callback) {
        String url=idwifi.urlImage+QuestModel.FOLDER+"fileUpload.php";
        Log.d("AAA",url);
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
