package com.monetapp.in.beans;

import android.graphics.drawable.Drawable;


public class SpendIndividualCategoryBeans {


    private Drawable thumb;
    private String title;
    private String subtitle;
    private String amount;
    private String accontId;

    public SpendIndividualCategoryBeans(Drawable thumb, String title, String subtitle, String amount, String accontId) {
        this.thumb = thumb;
        this.title = title;
        this.subtitle = subtitle;
        this.amount = amount;
        this.accontId = accontId;
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

    public String getAccontId() {
        return accontId;
    }

    public void setAccontId(String accontId) {
        this.accontId = accontId;
    }
}
