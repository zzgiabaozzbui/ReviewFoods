package com.edu.baogia.introducefood.model.object;

public class Review {
    private int keyReview;
    private String account;
    private String name;
    private String ava;
    private int key;
    private String text;
    private String time;
    private String img;

    public Review(int keyReview, String account, String name, String ava, int key, String text, String time, String img) {
        this.keyReview = keyReview;
        this.account = account;
        this.name = name;
        this.ava = ava;
        this.key = key;
        this.text = text;
        this.time = time;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAva() {
        return ava;
    }

    public void setAva(String ava) {
        this.ava = ava;
    }

    public Review() {
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getKeyReview() {
        return keyReview;
    }

    public void setKeyReview(int keyReview) {
        this.keyReview = keyReview;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
