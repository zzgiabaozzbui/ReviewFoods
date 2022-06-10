package com.edu.baogia.introducefood.model.object;

public class Report {
    private int key;
    private String title;
    private String desc;
    private String img;
    private String time;
    private boolean fix;

    public Report(int key, String title, String desc, String img, String time, boolean fix) {
        this.key = key;
        this.title = title;
        this.desc = desc;
        this.img = img;
        this.time = time;
        this.fix = fix;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isFix() {
        return fix;
    }

    public void setFix(boolean fix) {
        this.fix = fix;
    }

    public Report() {
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
