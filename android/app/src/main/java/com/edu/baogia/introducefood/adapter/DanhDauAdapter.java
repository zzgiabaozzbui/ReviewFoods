package com.edu.baogia.introducefood.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.model.object.DanhDau;
import com.edu.baogia.introducefood.model.object.MonAn;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DanhDauAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<MonAn> dsDanhDauMonAn;


    public DanhDauAdapter(Context context, int layout, List<MonAn> dsDanhDauMonAn) {
        this.context = context;
        this.layout = layout;
        this.dsDanhDauMonAn = dsDanhDauMonAn;
    }


    @Override
    public int getCount() {
        return dsDanhDauMonAn.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(layout,null);
        ImageView imgFood = convertView.findViewById(R.id.imgFood);
        TextView txtNameFood = convertView.findViewById(R.id.txtNameFood);
        MonAn monandanhdau = dsDanhDauMonAn.get(i);

        ImageView imgDelete =  convertView.findViewById(R.id.btnDelete);
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dsDanhDauMonAn.remove(monandanhdau);
//                notifyDataSetChanged();
            }
        });

        Picasso.get().load(monandanhdau.getAnh()).into(imgFood);
        txtNameFood.setText(monandanhdau.getTenMonAn());
        return convertView;
    }
}
