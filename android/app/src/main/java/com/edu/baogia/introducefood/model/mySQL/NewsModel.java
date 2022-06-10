package com.edu.baogia.introducefood.model.mySQL;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.edu.baogia.introducefood.interfaces.ListNewsCallback;
import com.edu.baogia.introducefood.interfaces.NewsMVP;
import com.edu.baogia.introducefood.model.object.News;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class NewsModel implements NewsMVP.Model {
    Context mContext;

    public NewsModel(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void getNews(ListNewsCallback callback) {
        RequestQueue requestQueue= Volley.newRequestQueue(mContext);
        String rss="https://thanhnien.vn/rss/doi-song/am-thuc.rss";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, rss, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Document document = Jsoup.parse(response, "", Parser.xmlParser());
                    Elements elements=document.select("item");
                    List<News> list=new ArrayList<>();
                    for (Element element:elements) {
                        News news=new News();
                        Element eTitle=element.select("title").first();
                        Element eImage=element.select("image").first();
                        Element eDescription=element.select("description").first();
                        Element eLink=element.getElementsByTag("link").first();
                        if(eTitle!=null)
                        {
                            news.setTitle(eTitle.text());
                        }
                        if(eImage!=null)
                        {
                            news.setImg(eImage.text());
                        }
                        if(eLink!=null)
                        {
                            news.setLink(eLink.text());
                        }
                        if(eDescription!=null)
                        {
                            Document dDesc=Jsoup.parse(eDescription.text());
                            news.setDes(dDesc.text());
                        }
                        list.add(news);
                        Log.d("AAA",news.toString());
                    }
                    callback.getList(list);
                }
                catch (Exception e)
                {
                    callback.getList(null);
                    Log.d("AAA",e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("AAA",error.toString());
                callback.getList(null);
            }
        });
        requestQueue.add(stringRequest);
    }

}
