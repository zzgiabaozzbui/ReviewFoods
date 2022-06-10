package com.edu.baogia.introducefood.interfaces;
import com.edu.baogia.introducefood.model.object.News;


import java.util.List;

public interface NewsMVP {
    interface View
    {
        void setList(List<News> list);
        void onFail();
    }
    interface Presenter
    {
        void getList();
    }
    interface Model
    {
        void getNews(ListNewsCallback callback);
    }
}
