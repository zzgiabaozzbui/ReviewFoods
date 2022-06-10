package com.edu.baogia.introducefood.presenter;

import android.util.Log;

import com.edu.baogia.introducefood.interfaces.BooleanCallback;
import com.edu.baogia.introducefood.interfaces.ListQuestCallback;
import com.edu.baogia.introducefood.interfaces.QuestMVP;
import com.edu.baogia.introducefood.model.object.Quest;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class QuestPresenter implements QuestMVP.Presenter {
    QuestMVP.View view;
    QuestMVP.Model model;

    public QuestPresenter(QuestMVP.View view, QuestMVP.Model model) {
        this.view = view;
        this.model = model;
    }


    @Override
    public String getKeyUser() {
        return "null";
    }

    @Override
    public void getListQuest() {
        Log.d("AAA",getKeyUser());
        if(getKeyUser()==null)
        {
            view.onFail();
        }
        else
        {
            model.getListQuest(getKeyUser(), new ListQuestCallback() {
                @Override
                public void getList(List<Quest> list) {
                    if(list==null)
                    {
                        view.onFail();
                    }
                    else
                    {
                        view.setList(list);
                    }
                }
            });
        }
    }

    @Override
    public void addQuest( String title) {
        String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        Quest quest=new Quest(-1,getKeyUser(),title,null,timeStamp,false);
        model.addQuest(quest, new BooleanCallback() {
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

    @Override
    public void deleteQuest(Quest quest) {
        model.deleteQuest(quest, new BooleanCallback() {
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
}
