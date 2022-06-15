package com.edu.baogia.introducefood.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.edu.baogia.introducefood.R;
import com.edu.baogia.introducefood.presenter.FoodPresenter;
import com.edu.baogia.introducefood.presenter.FoodPresenterIm;


public class ReviewFragment extends Fragment implements ReviewFragmentView {
    TextView txtReview;
    private int idmonAn;
    FoodPresenter foodPresenter;

    public ReviewFragment(int idmonAn) {
        this.idmonAn = idmonAn;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_review,container,false);
        innit(root);
        foodPresenter = new FoodPresenterIm(this);
        foodPresenter.loadDesFood(idmonAn);
        return root;
    }



    private void innit(ViewGroup root) {
        txtReview = root.findViewById(R.id.txtReview);

    }


    @Override
    public void getDesFood(String food) {
        txtReview.setText(""+ food);
    }
}
