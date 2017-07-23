package com.in.monetapp.beans;

import android.graphics.drawable.Drawable;


public class ReminderBeans {


    private Drawable thumb;
    private String bankname;
    private String transactionNo;
    private String transactionDate;
    private String amount;


    public ReminderBeans(Drawable thumb, String bankname, String transactionNo, String transactionDate, String amount) {
        this.thumb = thumb;
        this.bankname = bankname;
        this.transactionNo = transactionNo;
        this.transactionDate = transactionDate;
        this.amount = amount;
    }

    public Drawable getThumb() {
        return thumb;
    }

    public void setThumb(Drawable thumb) {
        this.thumb = thumb;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ReminderBeans{");
        sb.append("thumb=").append(thumb);
        sb.append(", bankname='").append(bankname).append('\'');
        sb.append(", transactionNo='").append(transactionNo).append('\'');
        sb.append(", transactionDate='").append(transactionDate).append('\'');
        sb.append(", amount='").append(amount).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
