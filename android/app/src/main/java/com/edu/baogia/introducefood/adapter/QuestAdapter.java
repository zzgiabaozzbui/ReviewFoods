package com.edu.baogia.introducefood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.interfaces.ItemClickListenerQuest;
import com.edu.baogia.introducefood.model.object.Quest;

import java.util.List;

public class QuestAdapter extends RecyclerView.Adapter<QuestAdapter.ViewHolder>  {
    Context myContext;
    List<Quest> myList;
    int myLayout;
    ItemClickListenerQuest itemClickListener;
    ItemClickListenerQuest itemClickListener2;


    public QuestAdapter(Context myContext, List<Quest> myList, int myLayout, ItemClickListenerQuest itemClickListener, ItemClickListenerQuest itemClickListener2) {
        this.myContext = myContext;
        this.myList = myList;
        this.myLayout = myLayout;
        this.itemClickListener = itemClickListener;
        this.itemClickListener2 = itemClickListener2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(myContext).inflate(myLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText("Câu hỏi : "+myList.get(position).getQuest());
        holder.tvTime.setText(myList.get(position).getTime());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.itemClickListener(myList.get(holder.getAdapterPosition()));
            }
        });
        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                itemClickListener2.itemClickListener(myList.get(holder.getAdapterPosition()));
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout layout;
        TextView tvTitle;
        TextView tvTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvTime=itemView.findViewById(R.id.tvTime);
            layout=itemView.findViewById(R.id.layoutItem);
        }
    }

}
