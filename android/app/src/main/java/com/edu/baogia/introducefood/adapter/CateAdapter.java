package com.edu.baogia.introducefood.adapter;

import static com.edu.baogia.introducefood.util.idwifi.ipWifi;
import static com.edu.baogia.introducefood.util.idwifi.urlImage;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.model.object.Food;
import com.edu.baogia.introducefood.model.object.LoaiMonAn;
import com.edu.baogia.introducefood.util.idwifi;
import com.edu.baogia.introducefood.view.activity.DanhMucMonAnView;
import com.edu.baogia.introducefood.view.activity.FoodActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CateAdapter extends RecyclerView.Adapter<CateAdapter.ViewHolder> {
    Context context;
    List<LoaiMonAn> list;
    int layout;
    public CateAdapter(Context context, List<LoaiMonAn> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LoaiMonAn book = list.get(position);
        holder.txtid.setText(""+book.getId());

        holder.txtNameCate.setText(""+book.getNameCategory());

        if (book.getImage().equals("null")){

        }else {
            Picasso.get().load(urlImage+book.getImage()).into(holder.imgcAnh);
        }

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick){
                    Toast.makeText(context, "Bạn chỉ nên nhấn thôi!!!", Toast.LENGTH_SHORT).show();
                }
                else{
//                    Toast.makeText(context, "Bạn vừa bấm "+list.get(position).getId(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, DanhMucMonAnView.class);
                    intent.putExtra("idLoaiMonAn",list.get(position).getId()+"");
                    context.startActivity(intent);
                }
            }
        });
        Animation animation = AnimationUtils.loadAnimation(context.getApplicationContext(),R.anim.move3);
        holder.lnfoood.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

        TextView  txtNameCate,txtid;
        ImageView imgcAnh;
        LinearLayout lnfoood;
//        public TextView txt_description;

        private ItemClickListener itemClickListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtid = itemView.findViewById(R.id.txtid);
            txtNameCate = itemView.findViewById(R.id.txtNameCate);
            imgcAnh = itemView.findViewById(R.id.imgcAnh);
            lnfoood = itemView.findViewById(R.id.lnfoood);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),true);
            return true;
        }
    }
}
