package com.edu.baogia.introducefood.interfaces;



import com.edu.baogia.introducefood.model.object.News;

import java.util.List;

public interface ListNewsCallback {
    void getList(List<News> list);
}
