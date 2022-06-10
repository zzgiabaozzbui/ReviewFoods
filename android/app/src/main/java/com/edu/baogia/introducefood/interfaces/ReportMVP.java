package com.edu.baogia.introducefood.interfaces;

import android.graphics.Bitmap;

import com.edu.baogia.introducefood.model.object.Report;



public interface ReportMVP {
    interface View
    {
        void onSuccess();
        void onFail();
    }
    interface Presenter
    {
        void addReport(String title, String text, Bitmap uri);
    }
    interface Model
    {
        void addReport(Report report,BooleanCallback callback);
        void uploadImg(Bitmap filePath, StringCallback callback);
    }

}
