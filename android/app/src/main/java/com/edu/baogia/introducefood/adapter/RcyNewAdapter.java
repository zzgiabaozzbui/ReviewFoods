package com.edu.baogia.introducefood.adapter;

import static com.edu.baogia.introducefood.util.idwifi.urlImage;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.model.object.Food;
import com.edu.baogia.introducefood.util.idwifi;
import com.edu.baogia.introducefood.view.fragment.HomeFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RcyNewAdapter extends RecyclerView.Adapter<RcyNewAdapter.ViewHolder> {
    Context context;
    List<Food> list;
    int layout;
    HomeFragment homeFragment;
    public RcyNewAdapter(Context context, List<Food> list, int layout,HomeFragment homeFragment) {
        this.context = context;
        this.list = list;
        this.layout = layout;
        this.homeFragment = homeFragment;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Picasso.get().load(urlImage+list.get(position).getImg()).into(holder.imgNew);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick){
                    Toast.makeText(context, "Bạn chỉ nên nhấn thôi!!!", Toast.LENGTH_SHORT).show();

                }
                else{
//                    Toast.makeText(context, "Bạn vừa bấm "+list.get(position), Toast.LENGTH_SHORT).show();
//                    homeFragment.getTxtCateNew().setText(""+list.get(position).getCate());

                    new idwifi().getnamecate(homeFragment.getTxtCateNew(),list.get(position).getCate(),context);
                    homeFragment.getTxtNameNew().setText(""+list.get(position).getName());
                    homeFragment.getTxtDesNew().setText(""+list.get(position).getDes());
                    Log.d("AAA", "onClick: "+list.get(position).getCate());


                    homeFragment.getTxtidNew().setText(""+list.get(position).getId());
                    Log.d("AAA", "onClick: "+list.get(position).getCate());

                    Picasso.get().load(urlImage+list.get(position).getImg()).into(homeFragment.getImgNew());
                    Picasso.get().load(urlImage+list.get(position).getImg()).into(homeFragment.getImageAlpha());

                }


            }
        });
        Animation animation = AnimationUtils.loadAnimation(context.getApplicationContext(),R.anim.move2);
        holder.lnNew.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

        ImageView imgNew;
        CardView lnNew;
//        public TextView txt_description;

        private ItemClickListener itemClickListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgNew = itemView.findViewById(R.id.imgNew);
            lnNew = itemView.findViewById(R.id.lnNew);

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
