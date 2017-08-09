package com.monetapp.in.beans;


public class BarDataBeans {

    private String barTitle;
    private String barValue;

    public BarDataBeans(String barTitle, String barValue) {
        this.barTitle = barTitle;
        this.barValue = barValue;
    }

    public String getBarTitle() {
        return barTitle;
    }

    public void setBarTitle(String barTitle) {
        this.barTitle = barTitle;
    }

    public String getBarValue() {
        return barValue;
    }

    public void setBarValue(String barValue) {
        this.barValue = barValue;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BarDataBeans{");
        sb.append("barTitle='").append(barTitle).append('\'');
        sb.append(", barValue='").append(barValue).append('\'');
        sb.append('}');
        return sb.toString();
    }
}


