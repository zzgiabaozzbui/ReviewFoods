package com.edu.baogia.introducefood.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.interfaces.ItemClickListenerReview;
import com.edu.baogia.introducefood.model.mySQL.QuestModel;
import com.edu.baogia.introducefood.model.object.Review;
import com.edu.baogia.introducefood.util.idwifi;


import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    Context mContext;
    List<Review> mList;
    int mLayout;
    ItemClickListenerReview itemClickListener;
    String account;

    public ReviewAdapter(Context mContext, List<Review> mList, int mLayout, ItemClickListenerReview itemClickListener, String keyUid) {
        this.mContext = mContext;
        this.mList = mList;
        this.mLayout = mLayout;
        this.itemClickListener = itemClickListener;
        this.account = keyUid;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(mLayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(account!=null&&account.equals(mList.get(position).getAccount()))
        {
            holder.moreImg.setVisibility(View.VISIBLE);
            holder.moreImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        itemClickListener.itemClickListener(mList.get(holder.getAdapterPosition()),view);
                }
            });
        }
        Glide.with(this.mContext).load(mList.get(position).getAva()).into(holder.imageView);
        if(mList.get(position).getImg()!=null&&!mList.get(position).getImg().trim().equals(""))
        {
            Log.d("AAA",idwifi.urlImage+QuestModel.FOLDER+"Images/"+mList.get(position).getImg());
            Glide.with(this.mContext).load(idwifi.urlImage+QuestModel.FOLDER+"Images/"+mList.get(position).getImg()).into(holder.imgRV);
        }
        else
        {
            holder.imgRV.setVisibility(View.GONE);
        }
        holder.tvName.setText(mList.get(position).getName());
        holder.tvTime.setText(mList.get(position).getTime());
        holder.tvDesc.setText(mList.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return mList!=null?mList.size():0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {

        ImageView imageView,moreImg,imgRV;
        TextView tvName;
        TextView tvTime;
        TextView tvDesc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imgAvatar);
            tvName=itemView.findViewById(R.id.tvName);
            tvTime=itemView.findViewById(R.id.tvTime);
            tvDesc=itemView.findViewById(R.id.tvDesc);
            moreImg=itemView.findViewById(R.id.moreImg);
            imgRV=itemView.findViewById(R.id.imgRV);
        }
    }
}
