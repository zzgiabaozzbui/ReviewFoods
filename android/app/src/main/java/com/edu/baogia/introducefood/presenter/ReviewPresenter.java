package com.edu.baogia.introducefood.presenter;

import android.graphics.Bitmap;

import com.edu.baogia.introducefood.interfaces.BooleanCallback;
import com.edu.baogia.introducefood.interfaces.ListReviewCallback;
import com.edu.baogia.introducefood.interfaces.ReviewMVP;
import com.edu.baogia.introducefood.interfaces.StringCallback;
import com.edu.baogia.introducefood.model.object.Review;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ReviewPresenter implements ReviewMVP.Presenter {
    ReviewMVP.View view;
    ReviewMVP.Model model;

    public ReviewPresenter(ReviewMVP.View view, ReviewMVP.Model model) {
        this.view = view;
        this.model = model;
    }


    @Override
    public void updateReview(Review review, Bitmap bitmap) {
        if(bitmap==null)
        {
            model.updateReview(review, new BooleanCallback() {
                @Override
                public void getBool(Boolean b) {
                    if(b)
                    {
                        view.onUpdateSuccess();
                    }
                    else
                    {
                        view.onUpdateFail();
                    }
                }
            });
        }
        else
        {
            model.uploadImg(bitmap, new StringCallback() {
                @Override
                public void getString(String s) {
                    if (s != null) {
                        review.setImg(s);
                    }
                    model.updateReview(review, new BooleanCallback() {
                        @Override
                        public void getBool(Boolean b) {
                            if(b)
                            {
                                view.onUpdateSuccess();
                            }
                            else
                            {
                                view.onUpdateFail();
                            }
                        }
                    });
                }
            });
        }

    }

    @Override
    public void deleteReview(Review review) {
        model.deleteReview(review, new BooleanCallback() {
            @Override
            public void getBool(Boolean b) {
                if(b)
                {
                    view.onDeleteSuccess();
                }
                else
                {
                    view.onDeleteFail();
                }
            }
        });
    }

    @Override
    public String getAccount() {
        return "TEST";
    }

    @Override
    public void getListReview(int key) {
            model.getListReview(key, new ListReviewCallback() {
                @Override
                public void getList(List<Review> list) {
                    if(list!=null)
                    {
                        view.setList(list);
                    }
                    else
                    {
                        view.onFail();
                    }
                }
            });
    }

    @Override
    public void addReview(String account,int key,String text,Bitmap bitmap) {
        Review review=new Review();
        review.setAccount(account);
        review.setText(text);
        review.setKey(key);
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        review.setTime(timeStamp);
        if(bitmap==null)
        {
            model.addReview(review, new BooleanCallback() {
                @Override
                public void getBool(Boolean b) {
                    if(b)
                    {
                        view.onAddSuccess();
                    }
                    else
                    {
                        view.onAddFail();
                    }
                }
            });
        }
        else
        {
                model.uploadImg(bitmap, new StringCallback() {
                    @Override
                    public void getString(String s) {
                        if(s!=null)
                        {
                            review.setImg(s);
                        }
                        model.addReview(review, new BooleanCallback() {
                            @Override
                            public void getBool(Boolean b) {
                                if(b)
                                {
                                    view.onAddSuccess();
                                }
                                else
                                {
                                    view.onAddFail();
                                }
                            }
                        });
                    }
                });
        }

    }
}
