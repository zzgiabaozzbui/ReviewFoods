package com.edu.baogia.introducefood.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.adapter.LoaiMonAnAdapter;
import com.edu.baogia.introducefood.model.object.LoaiMonAn;

import java.util.ArrayList;
import java.util.List;


public class Fragment_LoaiMonAn extends Fragment {
    Context context;
    RecyclerView rcvDoanhMuc;
    List<LoaiMonAn> list;
    LoaiMonAnAdapter adapter;
    View view;
    public Fragment_LoaiMonAn(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_loai_mon_an,container,false);
        mapping();
        return view;
    }

    public void mapping() {
        list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
//            list.add(new LoaiMonAn(i,"Loại đồ ăn "+i,R.drawable.meo_meo));

        }

        rcvDoanhMuc=view.findViewById(R.id.rcvDoanhMuc);
        adapter=new LoaiMonAnAdapter(getContext());
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),5);
        rcvDoanhMuc.setLayoutManager(gridLayoutManager);
        rcvDoanhMuc.setFocusable(false);
        rcvDoanhMuc.setNestedScrollingEnabled(false);
        adapter.setData(list);
        rcvDoanhMuc.setAdapter(adapter);
    }
}
