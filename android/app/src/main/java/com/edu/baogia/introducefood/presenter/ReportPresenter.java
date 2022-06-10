package com.edu.baogia.introducefood.presenter;

import android.graphics.Bitmap;

import com.edu.baogia.introducefood.interfaces.BooleanCallback;
import com.edu.baogia.introducefood.interfaces.ReportMVP;
import com.edu.baogia.introducefood.interfaces.StringCallback;
import com.edu.baogia.introducefood.model.object.Report;


import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ReportPresenter implements ReportMVP.Presenter {
    ReportMVP.View view;
    ReportMVP.Model model;

    public ReportPresenter(ReportMVP.View view, ReportMVP.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void addReport(String title, String text, Bitmap bitmap) {
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        if(bitmap==null)
        {
            Report report=new Report(-1,title,text,null,timeStamp,false);
            model.addReport(report, new BooleanCallback() {
                @Override
                public void getBool(Boolean b) {
                    if(b)
                    {
                        view.onSuccess();
                    }
                    else
                    {
                        view.onFail();
                    }
                }
            });
        }
        else
        {
                model.uploadImg(bitmap, new StringCallback() {
                    @Override
                    public void getString(String s) {
                        if(s!=null||!s.equals(""))
                        {
                            model.addReport(new Report(-1, title, text, s,timeStamp,false), new BooleanCallback() {
                                @Override
                                public void getBool(Boolean b) {
                                    if(b)
                                    {
                                        view.onSuccess();
                                    }
                                    else
                                    {
                                        view.onFail();
                                    }
                                }
                            });
                        }
                        else
                        {
                            view.onFail();
                        }
                    }
                });
            }
    }

}
