package com.edu.baogia.introducefood.presenter;

import com.edu.baogia.introducefood.interfaces.ListNewsCallback;
import com.edu.baogia.introducefood.interfaces.NewsMVP;
import com.edu.baogia.introducefood.model.object.News;


import java.util.List;

public class NewsPresenter implements NewsMVP.Presenter {
    NewsMVP.View view;
    NewsMVP.Model model;

    public NewsPresenter(NewsMVP.View view, NewsMVP.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void getList() {
        model.getNews(new ListNewsCallback() {
            @Override
            public void getList(List<News> list) {
                if(list==null)
                {
                    view.onFail();
                }
                else
                {
                    view.setList(list);
                }
            }
        });
    }
}
