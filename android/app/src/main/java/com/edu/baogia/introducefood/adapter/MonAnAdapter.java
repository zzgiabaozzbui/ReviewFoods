package com.edu.baogia.introducefood.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.interfaces.ItemClickRecycleListener;
import com.edu.baogia.introducefood.model.object.DanhDau;
import com.edu.baogia.introducefood.model.object.MonAn;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MonAnAdapter extends RecyclerView.Adapter<MonAnAdapter.MonAnViewHolder> {
    private List<MonAn> list;
    //    Khởi tạo list phụ chứa dữ liệu list
    private List<MonAn> listPhu;
    List<DanhDau> danhDaus=new ArrayList<>();
    View view;
    public String tenTK="thangyb27";

    public void setData(List<MonAn> listMonAn) {
        this.list = listMonAn;
        this.listPhu = new ArrayList<>();
        this.listPhu.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MonAnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_line_mon_an, parent, false);
        return new MonAnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonAnViewHolder holder, int position) {
        MonAn monAn = list.get(position);
        if (monAn == null) {
            return;
        }
        Picasso.get().load(monAn.getAnh()).into(holder.imgMonAn_DM);
        holder.txtTenMonAn_DM.setText(monAn.getTenMonAn());
        holder.txtMoTaMonAn_DM.setText(monAn.getMoTa());
        new DanhDau(0,tenTK).getCheckedFood(new DanhDau.CallBackCheckedFood() {
            @Override
            public void onSuccessCheckedFood(List<DanhDau> danhDauList) {
                if (danhDaus.size()==0)
                danhDaus.addAll(danhDauList);
                Boolean check=false;
                for (DanhDau danhDau:danhDaus) {

                    if(monAn.getId()==danhDau.getIdMonAn()){
                        check=true;
                        break;
                    }else {
                        check=false;
                    }
                }
                if(check){
                    holder.ckoMonAn_DM.setChecked(true);
                    holder.ckoMonAn_DM.setButtonDrawable(R.drawable.ic_tim);
                }

            }

            @Override
            public void onErrorCheckedFood(String error) {
                Log.d("error", "onErrorCheckedFood: "+error);
            }
        }, view.getContext());
        holder.setClickRecycleListener(new ItemClickRecycleListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick){
                    return;
                }else {
                    Toast.makeText(view.getContext(), "bạn đã click chuột "+list.get(position).getTenMonAn(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        setTim(holder.ckoMonAn_DM,list.get(position).getId());
    }



    private void setTim(MaterialCheckBox ckoMonAn_DM, int id) {
        ckoMonAn_DM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ckoMonAn_DM.setButtonDrawable(R.drawable.ic_tim);
                    new DanhDau(id,tenTK).themDanhDau(view.getContext());
                }else{
                    ckoMonAn_DM.setButtonDrawable(R.drawable.ic_kh_tim);
                    new DanhDau(id,tenTK).xoaDanhdau(view.getContext());
                }
            }
        });
    }

    public int filter(String s, List<String> condition) {
        s = s.toLowerCase(Locale.getDefault());
        list.clear();
        if (condition.size() == 0) {
            if (s.equalsIgnoreCase("")) {
                list.addAll(listPhu);
            } else {
                for (MonAn monAn : listPhu) {
                    if (monAn.getTenMonAn().toLowerCase(Locale.getDefault()).contains(s)) {
                        list.add(monAn);
                    }
                }
            }
        }
        else {
            if (s.equalsIgnoreCase("")) {
                for (MonAn monAn : listPhu) {
                    for (String id : condition) {
                        if (monAn.getIdLoaiMonAn() == Integer.parseInt(id)) {
                            list.add(monAn);
                        }
                    }
                }
            } else {
                for (MonAn monAn : listPhu) {
                    if (monAn.getTenMonAn().toLowerCase(Locale.getDefault()).contains(s)) {
                        for (String id : condition) {
                            if (monAn.getId() == Integer.parseInt(id)) {
                                list.add(monAn);
                            }
                        }
                    }
                }
            }
        }



        notifyDataSetChanged();
        if (list.size() == 0)
            return 0;
        else return 1;
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class MonAnViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener  {
        private ItemClickRecycleListener clickRecycleListener;

        private ImageView imgMonAn_DM;
        private TextView txtTenMonAn_DM, txtMoTaMonAn_DM;
        MaterialCheckBox ckoMonAn_DM;

        public MonAnViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMonAn_DM = itemView.findViewById(R.id.imgMonAn_DM);
            txtTenMonAn_DM = itemView.findViewById(R.id.txtTenMonAn_DM);
            txtMoTaMonAn_DM = itemView.findViewById(R.id.txtMoTaMonAn_DM);
            ckoMonAn_DM = itemView.findViewById(R.id.ckoMonAn_DM);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View view) {
            clickRecycleListener.onClick(view,getAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickRecycleListener.onClick(view,getAdapterPosition(),true);
            return true;
        }

        public void setClickRecycleListener(ItemClickRecycleListener clickRecycleListener) {
            this.clickRecycleListener = clickRecycleListener;
        }
    }

}
