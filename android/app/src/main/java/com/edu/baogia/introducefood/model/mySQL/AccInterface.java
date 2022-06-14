package com.edu.baogia.introducefood.model.mySQL;


import android.content.Context;

import com.edu.baogia.introducefood.model.object.Account;
import com.edu.baogia.introducefood.model.object.AccountRemember;
import com.edu.baogia.introducefood.model.object.Users;

//làm callback cho lớp Model
public interface AccInterface {
    void getAccount(Users users);
}