package com.edu.baogia.introducefood.model.mySQL;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.edu.baogia.introducefood.interfaces.BooleanCallback;
import com.edu.baogia.introducefood.interfaces.ListQuestCallback;
import com.edu.baogia.introducefood.interfaces.QuestMVP;
import com.edu.baogia.introducefood.model.object.Quest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestModel implements QuestMVP.Model {
    Context context;
    public static final String IP="http://192.168.1.7";
    public static final String FOLDER="/Webservice/KTPM/";
    public QuestModel(Context context) {
        this.context = context;
    }

    @Override
    public void addQuest(Quest quest, BooleanCallback callback) {

        String url=IP+FOLDER+"insertQuest.php";
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
                Log.d("BBB",response);
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
                map.put("Account",quest.getAccount());
                map.put("Quest",quest.getQuest());
                map.put("Time",quest.getTime());
                return map;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void deleteQuest(Quest quest, BooleanCallback callback) {
        String url=IP+FOLDER+"deleteQuest.php";
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
                Log.d("BBB",response);
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
                map.put("KeyQuest",quest.getKey()+"");
                return map;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    @Override
    public void getListQuest(String keyUser, ListQuestCallback callback) {
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        String url=IP+FOLDER+"selectQuest.php";
        Log.d("AAA",url);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public  void onResponse(JSONArray response) {
               List<Quest> questList=new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    Quest quest;
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        quest=new Quest();
                        quest.setKey(jsonObject.getInt("keyQuest"));
                        quest.setAccount(jsonObject.getString("account"));
                        quest.setQuest(jsonObject.getString("quest"));
                        quest.setAnswer(jsonObject.getString("answer"));
                        quest.setTime(jsonObject.getString("timeQ"));
                        if(jsonObject.getInt("checkQ")==1)
                        {
                            quest.setCheck(true);
                        }
                        else
                        {
                            quest.setCheck(false);
                        }
                        questList.add(quest);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("AAA",e.toString());
                    }
                }
                callback.getList(questList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("AAA",error.toString());
                callback.getList(null);
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("Account",keyUser+"");
                return map;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }
}
