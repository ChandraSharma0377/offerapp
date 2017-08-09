package com.monetapp.in.beans;


import android.graphics.drawable.Drawable;

public class OfferBeans {

    private Drawable thumb;
    private String merchantName;
    private String offerDetails;

    public OfferBeans(Drawable thumb, String merchantName, String offerDetails) {
        this.thumb = thumb;
        this.merchantName = merchantName;
        this.offerDetails = offerDetails;
    }

    public Drawable getThumb() {
        return thumb;
    }

    public void setThumb(Drawable thumb) {
        this.thumb = thumb;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getOfferDetails() {
        return offerDetails;
    }

    public void setOfferDetails(String offerDetails) {
        this.offerDetails = offerDetails;
    }

    @Override
    public String toString() {
        return "OfferBeans{" +
                "thumb=" + thumb +
                ", merchantName='" + merchantName + '\'' +
                ", offerDetails='" + offerDetails + '\'' +
                '}';
    }
}
