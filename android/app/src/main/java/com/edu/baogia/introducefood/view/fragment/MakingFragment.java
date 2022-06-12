package com.edu.baogia.introducefood.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.YouTubeActivity;
import com.edu.baogia.introducefood.model.object.Food;
import com.edu.baogia.introducefood.presenter.FoodMakingPresenter;
import com.edu.baogia.introducefood.presenter.FoodMakingPresenterIm;
import com.edu.baogia.introducefood.presenter.FoodPresenter;
import com.edu.baogia.introducefood.presenter.FoodPresenterIm;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class MakingFragment extends Fragment implements MakingFragmentView {
    TextView txtReview;

    Button btnyoutube;
    Food food;

    int id ;
    public MakingFragment(int id) {
        this.id = id;
    }

    FoodMakingPresenter foodMakingPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_making,container,false);
        innit(root);
        foodMakingPresenter = new FoodMakingPresenterIm(this);
        foodMakingPresenter.loadDesFood(id);
        btnyoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodMakingPresenter.loadYouTobe(id);
            }
        });
        return root;
    }



    private void innit(ViewGroup root) {
        txtReview = root.findViewById(R.id.txtReview);
        btnyoutube = root.findViewById(R.id.btnyoutube);
    }


    @Override
    public void getDesFood(Food food) {
        txtReview.setText(""+ food);
        this.food = food;
    }

    @Override
    public void goVideo(String vide,String des) {
        Intent intent = new Intent(getActivity(), YouTubeActivity.class);
        intent.putExtra("linkyt",""+vide);
        intent.putExtra("desmaking",""+des);
        Log.d("AAA", "onClick: "+vide);
        startActivity(intent);
    }
}
