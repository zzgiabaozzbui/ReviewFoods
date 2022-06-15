package com.edu.baogia.introducefood.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.model.object.LoaiMonAn;
import com.edu.baogia.introducefood.adapter.MonAnAdapter;
import com.edu.baogia.introducefood.view.activity.DanhMucMonAnView;
import com.google.android.material.checkbox.MaterialCheckBox;


import java.util.ArrayList;
import java.util.List;

public class LoaiMonAnAdapter extends RecyclerView.Adapter<LoaiMonAnAdapter.DoanhMucViewHolder> {
    private List<LoaiMonAn> list;
    View view;
    Context context;
    List<MaterialCheckBox> listCheck = new ArrayList<>();
    List<LinearLayout> listLinner = new ArrayList<>();
    List<String> idFillter = new ArrayList<>();
    DoanhMucViewHolder holderDanhMuc;
    Intent intent;

    public LoaiMonAnAdapter(Context context, Intent intent) {
        this.context = context;
        this.intent=intent;
    }
    public LoaiMonAnAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<LoaiMonAn> listLoaiMonAn) {
        this.list = listLoaiMonAn;
        notifyDataSetChanged();
    }
    public void addCondition(String id){
        idFillter.add(id);
        notifyDataSetChanged();
    }

    public List<String> getCondition() {
        return idFillter;
    }

    @NonNull
    @Override
    public DoanhMucViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_line_fillter, parent, false);
        return new DoanhMucViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoanhMucViewHolder holder, int position) {
        LoaiMonAn loaiMonAn = list.get(position);
        if (loaiMonAn == null) {
            return;
        }
        holder.ckoFillter.setText(loaiMonAn.getNameCategory());
        holder.txtIdLoaiMonAnFillter.setText(loaiMonAn.getId() + "");
        setOnclickChecked(holder, position);
        resetFillter(holder);
        addFillter(holder, position);
        holderDanhMuc=holder;
        if(intent!=null)
            getCurrentFillter(loaiMonAn.getId()+"");
    }

    private void getCurrentFillter(String idLoaiMonAn) {
        try{
            String id=intent.getStringExtra("idLoaiMonAn");
            if(id.equalsIgnoreCase(idLoaiMonAn)){
                holderDanhMuc.ckoFillter.setBackgroundResource(R.drawable.checked_fillter);
                holderDanhMuc.ckoFillter.setChecked(true);
                holderDanhMuc.lnlChecked.setVisibility(View.VISIBLE);
            }
        }catch (Exception e){

        }
    }


    private void addFillter(DoanhMucViewHolder holder, int position) {
        DanhMucMonAnView monAnView = (DanhMucMonAnView) context;
        Button btnApDungFillter = ((DanhMucMonAnView) context).getBtnApDungFillter();
        btnApDungFillter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idAll = "";
                for (String id : idFillter) {
                    idAll += id + ",";
                }
                List<String> conditions = new ArrayList<>();
                conditions.addAll(getCondition());
                MonAnAdapter monAnAdapter = monAnView.getMonAnAdapter();
                String s = monAnView.getTextSearchFillter();
                int check = monAnAdapter.filter(s, conditions);
            }
        });
    }

    private void resetFillter(DoanhMucViewHolder holder) {
        DanhMucMonAnView monAnView = (DanhMucMonAnView) context;
        Button btnResetFillter = ((DanhMucMonAnView) context).getBtnResetFillter();
        btnResetFillter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (MaterialCheckBox box : listCheck) {
                    box.setBackgroundResource(R.drawable.dont_checked_fillter);
                }
                for (LinearLayout layout : listLinner) {
                    layout.setVisibility(View.INVISIBLE);
                }
                listLinner.clear();
                listCheck.clear();
                idFillter.clear();

                List<String> conditions = new ArrayList<>();
                MonAnAdapter monAnAdapter = monAnView.getMonAnAdapter();
                String s = monAnView.getTextSearchFillter();
                int check = monAnAdapter.filter(s, conditions);
            }
        });
    }

    private void setOnclickChecked(DoanhMucViewHolder holder, int position) {
        holder.ckoFillter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    holder.ckoFillter.setBackgroundResource(R.drawable.checked_fillter);
                    holder.lnlChecked.setVisibility(View.VISIBLE);
                    listCheck.add(holder.ckoFillter);
                    listLinner.add(holder.lnlChecked);
                    idFillter.add(holder.txtIdLoaiMonAnFillter.getText().toString());
                } else {
                    listCheck.remove(holder.ckoFillter);
                    listLinner.remove(holder.lnlChecked);
                    idFillter.remove(holder.txtIdLoaiMonAnFillter.getText().toString());
                    holder.ckoFillter.setBackgroundResource(R.drawable.dont_checked_fillter);
                    holder.lnlChecked.setVisibility(View.INVISIBLE);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class DoanhMucViewHolder extends RecyclerView.ViewHolder {
        private MaterialCheckBox ckoFillter;
        private LinearLayout lnlChecked;
        private TextView txtIdLoaiMonAnFillter;

        public DoanhMucViewHolder(@NonNull View itemView) {
            super(itemView);
            ckoFillter = itemView.findViewById(R.id.ckoFillter);
            lnlChecked = itemView.findViewById(R.id.lnlChecked);
            txtIdLoaiMonAnFillter = itemView.findViewById(R.id.txtIdLoaiMonAnFillter);

        }
    }
}
