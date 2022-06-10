package com.edu.baogia.introducefood.interfaces;

import android.graphics.Bitmap;

import com.edu.baogia.introducefood.model.object.Review;


import java.util.List;

public interface ReviewMVP {
    interface View
    {
        void setList(List<Review> list);
        void onFail();

        void onAddFail();
        void onAddSuccess();

        void onUpdateFail();
        void onUpdateSuccess();

        void onDeleteFail();
        void onDeleteSuccess();

    }
    interface Presenter
    {
        void updateReview(Review review,Bitmap bitmap);
        void deleteReview(Review review);
        String getAccount();
        void getListReview(int key);
        void addReview(String account, int key, String text, Bitmap bitmap);
    }
    interface Model
    {
        void addReview(Review review,BooleanCallback callback);
        void updateReview(Review review,BooleanCallback booleanCallback);
        void deleteReview(Review review,BooleanCallback booleanCallback);
        String getUid();
        void uploadImg(Bitmap bitmap,StringCallback stringCallback);
        void getListReview(int key, ListReviewCallback callback);

    }
}
