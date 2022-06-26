package com.edu.baogia.introducefood.interfaces;

import com.edu.baogia.introducefood.model.object.Quest;

import java.util.List;

public interface QuestMVP {
    interface View
    {
        void setList(List<Quest> list);
        void onFail();
        void onAddSuccess();
        void onAddFail();
        void onDeleteSuccess();
        void onDeleteFail();

    }
    interface Presenter
    {
        String getKeyUser();
        void getListQuest();
        void addQuest(String quest);
        void deleteQuest(Quest quest);
    }
    interface Model
    {
        String getUid();
        void addQuest(Quest quest, BooleanCallback callback);
        void deleteQuest(Quest quest, BooleanCallback callback);
        void getListQuest(String keyUser, ListQuestCallback callback);
    }
}
