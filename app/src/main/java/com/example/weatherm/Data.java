package com.example.weatherm;

public class Data {
    private String weekly;
    private String date;
    private int img;
    private String hight;
    private String low;

    public Data(String weekly, String date, int img, String hight, String low) {
        this.weekly = weekly;
        this.date = date;
        this.img = img;
        this.hight = hight;
        this.low = low;
    }

    public String getWeekly() {
        return weekly;
    }

    public void setWeekly(String weekly) {
        this.weekly = weekly;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getHight() {
        return hight;
    }

    public void setHight(String hight) {
        this.hight = hight;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }
}