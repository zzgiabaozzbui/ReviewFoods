package com.edu.baogia.introducefood.model.object;

public class News {
    private String title;
    private String img;
    private String des;
    private String link;

    public News() {
    }

    public News(String title, String img, String des, String link) {
        this.title = title;
        this.img = img;
        this.des = des;
        this.link=link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", des='" + des + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
