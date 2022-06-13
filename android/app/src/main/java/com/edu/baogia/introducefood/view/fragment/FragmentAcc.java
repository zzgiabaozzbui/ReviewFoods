package com.edu.baogia.introducefood.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.model.object.AccountRemember;
import com.edu.baogia.introducefood.presenter.FoodRatingPresenter;
import com.edu.baogia.introducefood.presenter.FoodRatingPresenterIm;
import com.edu.baogia.introducefood.util.MySharedPreferences;


public class FragmentAcc extends Fragment  {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_acc, container, false);
        innit(root);


        return root;
    }


    private void innit(ViewGroup root) {
    }


}
