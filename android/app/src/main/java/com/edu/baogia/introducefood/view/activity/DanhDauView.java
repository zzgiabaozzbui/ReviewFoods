package com.edu.baogia.introducefood.view.activity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.adapter.DanhDauAdapter;
import com.edu.baogia.introducefood.model.object.MonAn;

import java.util.ArrayList;
import java.util.List;

public class DanhDauView extends AppCompatActivity {

    ListView listView ;
    List<MonAn> monAnList;
    DanhDauAdapter danhDauAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhdauview);
        listView = findViewById(R.id.ListViewDanhDau);
        monAnList = new ArrayList<>();
        monAnList.add(new MonAn(1,"Cá 1","https://cdn.pixabay.com/photo/2016/03/05/19/02/salmon-1238248_960_720.jpg","","ấdasd","dd","dd",2));
        monAnList.add(new MonAn(1,"Cá 2","https://cdn.pixabay.com/photo/2016/03/05/19/02/salmon-1238248_960_720.jpg","","ấdasd","dd","dd",2));
        monAnList.add(new MonAn(1,"Cá 3","https://cdn.pixabay.com/photo/2016/03/05/19/02/salmon-1238248_960_720.jpg","","ấdasd","dd","dd",2));
        monAnList.add(new MonAn(1,"Cá 4","https://cdn.pixabay.com/photo/2016/03/05/19/02/salmon-1238248_960_720.jpg","","ấdasd","dd","dd",2));
        monAnList.add(new MonAn(1,"Cá","https://cdn.pixabay.com/photo/2016/03/05/19/02/salmon-1238248_960_720.jpg","","ấdasd","dd","dd",2));
        monAnList.add(new MonAn(1,"Cá","https://cdn.pixabay.com/photo/2016/03/05/19/02/salmon-1238248_960_720.jpg","","ấdasd","dd","dd",2));
        monAnList.add(new MonAn(1,"Cá","https://cdn.pixabay.com/photo/2016/03/05/19/02/salmon-1238248_960_720.jpg","","ấdasd","dd","dd",2));
        monAnList.add(new MonAn(1,"Cá","https://cdn.pixabay.com/photo/2016/03/05/19/02/salmon-1238248_960_720.jpg","","ấdasd","dd","dd",2));
        monAnList.add(new MonAn(1,"Cá","https://cdn.pixabay.com/photo/2016/03/05/19/02/salmon-1238248_960_720.jpg","","ấdasd","dd","dd",2));
        monAnList.add(new MonAn(1,"Cá","https://cdn.pixabay.com/photo/2016/03/05/19/02/salmon-1238248_960_720.jpg","","ấdasd","dd","dd",2));
        monAnList.add(new MonAn(1,"Cá","https://cdn.pixabay.com/photo/2016/03/05/19/02/salmon-1238248_960_720.jpg","","ấdasd","dd","dd",2));

        danhDauAdapter = new DanhDauAdapter(DanhDauView.this,R.layout.linedanhdauview,monAnList);
        listView.setAdapter(danhDauAdapter);






    }
}
