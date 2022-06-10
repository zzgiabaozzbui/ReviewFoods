package com.edu.baogia.introducefood.model.object;

import java.io.Serializable;

public class Quest implements Serializable {
    private int key;
    private String account;
    private String quest;
    private String answer;
    private String time;
    private boolean check;

    public Quest(int key, String account, String quest, String answer, String time, boolean check) {
        this.key = key;
        this.account = account;
        this.quest = quest;
        this.answer = answer;
        this.time = time;
        this.check = check;
    }

    public Quest() {
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getQuest() {
        return quest;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
