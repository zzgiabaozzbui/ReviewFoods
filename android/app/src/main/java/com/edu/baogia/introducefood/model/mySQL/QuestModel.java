package com.edu.baogia.introducefood.model.mySQL;

import static com.edu.baogia.introducefood.util.idwifi.ipWifi;

import android.content.Context;
import android.util.Log;

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
import com.edu.baogia.introducefood.interfaces.BooleanCallback;
import com.edu.baogia.introducefood.interfaces.ListQuestCallback;
import com.edu.baogia.introducefood.interfaces.QuestMVP;
import com.edu.baogia.introducefood.model.object.AccountRemember;
import com.edu.baogia.introducefood.model.object.Quest;
import com.edu.baogia.introducefood.util.MySharedPreferences;
import com.edu.baogia.introducefood.util.idwifi;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestModel implements QuestMVP.Model {
    Context context;
    public static final String IP="http://"+ipWifi;
    public static final String FOLDER="/ReviewFoods/service/manh/KTPM/";
    public QuestModel(Context context) {
        this.context = context;
    }

    @Override
    public String getUid() {
        AccountRemember accountRemember=new MySharedPreferences().getRememberAcc(context);
        return  accountRemember.getUsername();
    }

    @Override
    public void addQuest(Quest quest, BooleanCallback callback) {

        String url= idwifi.urlAPI+"InsertQuest";

        JSONObject praObject = new JSONObject();
        try {
            praObject.put("Account",quest.getAccount());
            praObject.put("Quest",quest.getQuest());
            praObject.put("Time",quest.getTime());
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
    public void deleteQuest(Quest quest, BooleanCallback callback) {
        String url=idwifi.urlAPI+"DeleteQuest";
        JSONObject praObject = new JSONObject();
        try {
            praObject.put("KeyQuest",quest.getKey()+"");
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
    public void getListQuest(String keyUser, ListQuestCallback callback) {
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        String url=idwifi.urlAPI+"LoadQuestID";
        JSONObject praObject = new JSONObject();
        try {

            praObject.put("Account",keyUser+"");
            Log.d("AAA",keyUser);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("AAA",url);
        JsonObjectRequest jsonArrayRequest=new JsonObjectRequest(Request.Method.POST, url, praObject, new Response.Listener<JSONObject>() {
            @Override
            public  void onResponse(JSONObject response) {
                List<Quest> questList = new ArrayList<>();
                try {
                    JSONArray jsonArray = response.getJSONArray("Data");
                    Log.d("AAA",jsonArray.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Quest quest;
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        quest = new Quest();
                        quest.setKey(jsonObject.getInt("keyQuest"));
                        quest.setAccount(jsonObject.getString("account"));
                        quest.setQuest(jsonObject.getString("quest"));
                        quest.setAnswer(jsonObject.getString("answer"));
                        quest.setTime(jsonObject.getString("timeQ"));
                        if (jsonObject.getInt("checkQ") == 1) {
                            quest.setCheck(true);
                        } else {
                            quest.setCheck(false);
                        }
                        questList.add(quest);
                    }
                }
                catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("AAA",e.toString());
                    }
                    callback.getList(questList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("AAA",error.toString());
                callback.getList(null);
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}
