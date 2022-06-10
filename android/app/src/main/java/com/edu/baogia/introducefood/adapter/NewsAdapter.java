package com.edu.baogia.introducefood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.interfaces.ItemClickListenerNews;
import com.edu.baogia.introducefood.model.object.News;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> implements Filterable {
    Context myContext;
    List<News> myList;
    List<News> myListOld;
    int myLayout;
    ItemClickListenerNews itemClickListener;


    public NewsAdapter(Context myContext, List<News> myList, int myLayout, ItemClickListenerNews itemClickListener) {
        this.myContext = myContext;
        this.myList = myList;
        this.myListOld=myList;
        this.myLayout = myLayout;
        this.itemClickListener=itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(myContext).inflate(myLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.edtTitle.setText(myList.get(position).getTitle());
        holder.edtDes.setText(myList.get(position).getDes());
        try {
            Glide.with(myContext)
                    .load(myList.get(position).getImg())
                    .apply(new RequestOptions().placeholder(R.drawable.loading).error(R.drawable.erro))
                    .into(holder.img);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.itemClickListener(myList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String searh=charSequence.toString();
                if(searh.isEmpty())
                {
                    myList=myListOld;
                }

                else
                {
                    List<News> mListFilter=new ArrayList<>();
                    for (News news:myListOld) {
                        if(news.getTitle().toLowerCase().contains(searh.toLowerCase()))
                        {
                            mListFilter.add(news);
                        }
                    }
                    myList=mListFilter;
                }
                FilterResults results=new FilterResults();
                results.values=myList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                myList= (List<News>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    class ViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout layout;
        TextView edtTitle;
        TextView edtDes;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            edtTitle=itemView.findViewById(R.id.tvTitle);
            edtDes=itemView.findViewById(R.id.tvDesc);
            img=itemView.findViewById(R.id.imgNews);
            layout=itemView.findViewById(R.id.layoutItem);
        }
    }

}
