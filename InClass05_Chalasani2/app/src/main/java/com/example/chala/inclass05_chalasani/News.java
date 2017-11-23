package com.example.chala.inclass05_chalasani;

/**
 * Created by chala on 2/13/2017.
 */

public class News {
    String title,pub,description,sqr,img;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPub() {
        return pub;
    }

    public void setPub(String pub) {
        this.pub = pub;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSqr() {
        return sqr;
    }

    public void setSqr(String sqr) {
        this.sqr = sqr;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", pub='" + pub + '\'' +
                ", description='" + description + '\'' +
                ", sqr='" + sqr + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
