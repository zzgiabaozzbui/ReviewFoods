package com.edu.baogia.introducefood.adapter;

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
import com.edu.baogia.introducefood.view.activity.FoodActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    Context context;
    List<Food> list;
    int layout;
    public HomeAdapter(Context context, List<Food> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
        Log.d("AAA", "ada: ");
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food book = list.get(position);
        holder.txtName.setText("Món ăn: "+book.getName());

        holder.txtCate.setText("Loại: "+book.getCate());
        holder.txtDes.setText("Mô tả: "+book.getDes());

        Picasso.with(context).load(""+book.getImg()).into(holder.imgAnh);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick){
                    Toast.makeText(context, "Bạn chỉ nên nhấn thôi!!!", Toast.LENGTH_SHORT).show();

                }
                else{
//                    Toast.makeText(context, "Bạn vừa bấm "+list.get(position).getId(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, FoodActivity.class);
                    intent.putExtra("idFood",list.get(position).getId());
                    context.startActivity(intent);

                }


            }
        });
        Animation animation = AnimationUtils.loadAnimation(context.getApplicationContext(),R.anim.move);
        holder.lnfoood.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        TextView  txtName,txtCate,txtDes;
        ImageView imgAnh;
        LinearLayout lnfoood;
//        public TextView txt_description;

        private ItemClickListener itemClickListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtCate = itemView.findViewById(R.id.txtCate);
            txtDes = itemView.findViewById(R.id.txtDes);
            imgAnh = itemView.findViewById(R.id.imgAnh);
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
