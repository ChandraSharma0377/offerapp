package com.in.monetapp.beans;

import android.graphics.drawable.Drawable;



public class IncomeBeans {


    private Drawable thumb;
    private String title;
    private String subtitle;
    private String amount;


    public IncomeBeans(Drawable thumb, String title, String subtitle, String amount) {
        this.thumb = thumb;
        this.title = title;
        this.subtitle = subtitle;
        this.amount = amount;
    }

    public Drawable getThumb() {
        return thumb;
    }

    public void setThumb(Drawable thumb) {
        this.thumb = thumb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "IncomeBeans{" +
                "thumb=" + thumb +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
