package com.edu.baogia.introducefood.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.edu.baogia.introducefood.model.object.AccountRemember;


public class MySharedPreferences {
    public void rememberPass(Context context, AccountRemember account){
//      sharedPref được lưu tại DATA/data/[application package name]/shared_prefs/shared_preferences_name.xml
        SharedPreferences sharedPref =  context.getSharedPreferences("MyPreferences",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username", ""+account.getUsername());
        editor.putString("password", ""+ account.getPassword());
        editor.putInt("idnguoidung", account.getIduser());
        editor.putBoolean("remember", account.getCheck());
        editor.commit();
//        commit()trả về true nếu giá trị được lưu thành công khác false. Nó lưu các giá trị vào
//        SharedPreferences một cách đồng bộ.
//        apply()đã được thêm vào 2.3 và không trả lại bất kỳ giá trị nào dù thành công hay thất bại.
//        Nó lưu các giá trị vào SharedPreferences ngay lập tức nhưng bắt đầu một cam kết không đồng bộ .
    }

    public AccountRemember getRememberAcc(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String username = sharedPref.getString("username", "");
        String password = sharedPref.getString("password", "");
        int idnguoidung = sharedPref.getInt("idnguoidung", -1);
        boolean remember = sharedPref.getBoolean("remember", false);
        AccountRemember accountRemember = new AccountRemember(username,password,idnguoidung,remember);
        return accountRemember;
    }
}
