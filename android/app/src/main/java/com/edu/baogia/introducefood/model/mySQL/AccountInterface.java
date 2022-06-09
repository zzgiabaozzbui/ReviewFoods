package com.edu.baogia.introducefood.model.mySQL;


import android.content.Context;

import com.edu.baogia.introducefood.model.object.Account;
import com.edu.baogia.introducefood.model.object.AccountRemember;

//làm callback cho lớp Model
public interface AccountInterface {
    void onAccountMessage(String message, Account account, Boolean check);
    void getAccount(Context context, AccountRemember account);
    void registerAccSuces(AccountRemember account);
    void updatePassSuces(AccountRemember account);
    void checkAccSuces(Boolean b);
    void checkAccSuces2(Boolean b);
}