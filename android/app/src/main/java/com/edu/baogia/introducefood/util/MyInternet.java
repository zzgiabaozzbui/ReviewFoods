package com.edu.baogia.introducefood.util;


import android.util.Log;

import java.io.IOException;

public class MyInternet {
    public boolean isInternetAvailable() throws IOException, InterruptedException {
//        try {
//            Log.d("AAA", "onCreateView: "+new MyInternet().isInternetAvailable());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        String command = "ping -c 1 google.com";
        return Runtime.getRuntime().exec(command).waitFor() == 0;
    }
}
