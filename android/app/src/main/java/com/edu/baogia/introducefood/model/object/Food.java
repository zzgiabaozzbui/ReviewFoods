package com.edu.baogia.introducefood.model.object;

public class Food {
    private int id;
    private String name;
    private String img;
    private String video;
    private String des;
    private String making;
    private String location;
    private int cate;

    public Food() {
    }

    public Food(int id, String name, String img, String video, String des, String making, String location, int cate) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.video = video;
        this.des = des;
        this.making = making;
        this.location = location;
        this.cate = cate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String date) {
        this.des = date;
    }

    public String getMaking() {
        return making;
    }

    public void setMaking(String making) {
        this.making = making;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCate() {
        return cate;
    }

    public void setCate(int cate) {
        this.cate = cate;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", video='" + video + '\'' +
                ", des='" + des + '\'' +
                ", making='" + making + '\'' +
                ", location='" + location + '\'' +
                ", cate='" + cate + '\'' +
                '}';
    }
}
