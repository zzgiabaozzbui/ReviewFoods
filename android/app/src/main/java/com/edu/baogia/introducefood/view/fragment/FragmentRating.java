package com.edu.baogia.introducefood.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.model.object.AccountRemember;
import com.edu.baogia.introducefood.presenter.FoodPresenter;
import com.edu.baogia.introducefood.presenter.FoodPresenterIm;
import com.edu.baogia.introducefood.presenter.FoodRatingPresenter;
import com.edu.baogia.introducefood.presenter.FoodRatingPresenterIm;
import com.edu.baogia.introducefood.util.MySharedPreferences;


public class FragmentRating extends Fragment  implements FragmentRatingView{
    RatingBar rateFood;
    Button  btnrate;
    FoodRatingPresenter foodRatingPresenter;
    int id;

    public FragmentRating(int id) {
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_rating, container, false);
        innit(root);


        AccountRemember accountRemember = new MySharedPreferences().getRememberAcc(getContext());
        foodRatingPresenter.getRating(id,accountRemember.getUsername());
        btnrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float getrating = rateFood.getRating();
                Log.d("AAA", "onCreateView: " + getrating );
                foodRatingPresenter.setRating(getrating,id,accountRemember.getUsername());
            }
        });
        return root;
    }


    private void innit(ViewGroup root) {
        rateFood = root.findViewById(R.id.rateFood);
        btnrate = root.findViewById(R.id.btnrate);
        foodRatingPresenter = new FoodRatingPresenterIm(this);
    }

    @Override
    public void rate(String mess) {
        if (mess.equals("1")){
            Toast.makeText(getActivity(), "Đánh giá hoàn tất", Toast.LENGTH_SHORT).show();
        }else if (mess.equals("0")){
            Toast.makeText(getActivity(), "Đánh giá thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void ratenow(float rate) {
        Log.d("AAA", "ratenow: "+rate);
        rateFood.setRating(rate);
    }

}
