package com.monetapp.in.beans;

import android.graphics.drawable.Drawable;


public class SpendIndividualMonthBeans {


    private Drawable thumb;
    private String title;
    private String subtitle;
    private String amount;
    private String categoryId;
    private String messageId;

    private String merchantId;
    private String date;
    private String accountId;
    private boolean isSpent;

    public SpendIndividualMonthBeans(Drawable thumb, String title, String subtitle, String amount, String categoryId, String messageId) {
        this.thumb = thumb;
        this.title = title;
        this.subtitle = subtitle;
        this.amount = amount;
        this.categoryId = categoryId;
        this.messageId = messageId;
    }

    public SpendIndividualMonthBeans(Drawable thumb, String title, String subtitle, String amount, String categoryId,
                                     String messageId, String merchantId, String date, String accountId, boolean isSpent) {
        this.thumb = thumb;
        this.title = title;
        this.subtitle = subtitle;
        this.amount = amount;
        this.categoryId = categoryId;
        this.messageId = messageId;
        this.merchantId = merchantId;
        this.date = date;
        this.accountId = accountId;
        this.isSpent = isSpent;
    }

    public boolean isSpent() {
        return isSpent;
    }

    public void setSpent(boolean spent) {
        isSpent = spent;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("IncomeBeans{");
        sb.append("thumb=").append(thumb);
        sb.append(", title='").append(title).append('\'');
        sb.append(", subtitle='").append(subtitle).append('\'');
        sb.append(", amount='").append(amount).append('\'');
        sb.append(", categoryId='").append(categoryId).append('\'');
        sb.append(", messageId='").append(messageId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
