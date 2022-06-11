package com.edu.baogia.introducefood.model.mySQL;


import android.content.Context;

import com.edu.baogia.introducefood.model.object.Account;
import com.edu.baogia.introducefood.model.object.AccountRemember;

//làm callback cho lớp Model
public interface UserInterface {
    void checkUserSuces(AccountRemember accountRemember);
}