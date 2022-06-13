package com.edu.baogia.introducefood.presenter;

import androidx.fragment.app.Fragment;

import com.edu.baogia.introducefood.model.mySQL.LoadVideoModel;
import com.edu.baogia.introducefood.model.mySQL.RatignInterface;
import com.edu.baogia.introducefood.model.mySQL.RatingModel;
import com.edu.baogia.introducefood.model.mySQL.VideoInterator;
import com.edu.baogia.introducefood.model.object.Danhgia;
import com.edu.baogia.introducefood.model.object.Food;
import com.edu.baogia.introducefood.view.fragment.FragmentRating;
import com.edu.baogia.introducefood.view.fragment.FragmentRatingView;
import com.edu.baogia.introducefood.view.fragment.MakingFragmentView;

public class FoodRatingPresenterIm implements FoodRatingPresenter, RatignInterface {
    private RatingModel ratingModel;
    private FragmentRatingView fragmentRating;


    public FoodRatingPresenterIm(FragmentRating fragmentRating) {
        this.fragmentRating = (FragmentRating) fragmentRating;
        ratingModel = new RatingModel(this,(Fragment) fragmentRating);
    }




    @Override
    public void setRating(float rating, int idFood, String taikhoan) {
        Danhgia danhgia = new Danhgia(idFood,taikhoan,rating);
        ratingModel.insertRate(danhgia);
    }

    @Override
    public void getRating(int idFood, String taikhoan) {
        ratingModel.getRating(idFood, taikhoan);
    }

    @Override
    public void onRateMessage(String mess) {
            fragmentRating.rate(mess);
    }

    @Override
    public void onGetRateMessage(String mess) {
        if (mess.equals("-1")){
            fragmentRating.ratenow(5);
        }else {
            fragmentRating.ratenow(Float.parseFloat(mess));
        }
    }
}
