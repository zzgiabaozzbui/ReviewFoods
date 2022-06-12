package com.edu.baogia.introducefood.presenter;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.edu.baogia.introducefood.model.mySQL.FoodInterator;
import com.edu.baogia.introducefood.model.mySQL.LoadFoodListener;
import com.edu.baogia.introducefood.model.mySQL.LoadVideoModel;
import com.edu.baogia.introducefood.model.mySQL.VideoInterator;
import com.edu.baogia.introducefood.model.object.Food;
import com.edu.baogia.introducefood.view.activity.FoodView;
import com.edu.baogia.introducefood.view.fragment.MakingFragmentView;
import com.edu.baogia.introducefood.view.fragment.ReviewFragmentView;

import java.util.List;

public class FoodMakingPresenterIm implements FoodMakingPresenter, LoadVideoModel {
    private VideoInterator videoInterator;
    private MakingFragmentView makingFragmentView;


    public FoodMakingPresenterIm(MakingFragmentView makingFragmentView) {
        this.makingFragmentView = (MakingFragmentView) makingFragmentView;
        videoInterator = new VideoInterator(this,(Fragment) makingFragmentView);
    }


    @Override
    public void loadDesFood(int id) {

        videoInterator.getDesFood(id);
    }

    @Override
    public void loadYouTobe(int id) {
        videoInterator.getVideoFood(id);
    }

    @Override
    public void onLoadVideo(String message,String des) {
        makingFragmentView.goVideo(message,des);
    }

    @Override
    public void onLoadDesFoodSuccess(Food us) {
        makingFragmentView.getDesFood(us);
    }

    @Override
    public void onLoadFoodSuccess(Food us) {

    }
}
