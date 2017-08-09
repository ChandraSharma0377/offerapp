package com.monetapp.in.beans;


import com.monetapp.in.utility.Commons;
import com.monetapp.in.utility.Helper;

public class GenericBeans {

    private String message_id;
    private String customer_id;
    private String account_provider;
    private String account_balance;
    private String merchant_id;
    private String industry_id;
    private String industry_details;
    private String category_id;
    private String category_details;
    private String processed;
    private String transaction_type;
    private String amount;
    private String account_number;
    private String merchant;
    private String date;
    private String account_type;

    private String date_week;
    private String date_month;
    private String date_year;

    public String getDate_week() {
        return date_week;
    }

    public void setDate_week(String date_week) {
        this.date_week = date_week;
    }

    public String getDate_month() {
        return date_month;
    }

    public void setDate_month(String date_month) {
        this.date_month = date_month;
    }

    public String getDate_year() {
        return date_year;
    }

    public void setDate_year(String date_year) {
        this.date_year = date_year;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getAccount_provider() {
        return account_provider;
    }

    public void setAccount_provider(String account_provider) {
        this.account_provider = account_provider;
    }

    public String getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(String account_balance) {
        this.account_balance = account_balance;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getIndustry_id() {
        return industry_id;
    }

    public void setIndustry_id(String industry_id) {
        this.industry_id = industry_id;
    }

    public String getIndustry_details() {
        return industry_details;
    }

    public void setIndustry_details(String industry_details) {
        this.industry_details = industry_details;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_details() {
        return category_details;
    }

    public void setCategory_details(String category_details) {
        this.category_details = category_details;
    }

    public String getProcessed() {
        return processed;
    }

    public void setProcessed(String processed) {
        this.processed = processed;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        initDateInfo();
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }


    private void initDateInfo(){
        try {
            String dates[] = date.split("/");
            date_year = dates[2];
            date_month = dates[1];
            date_week = Helper.getWeek(date_month,dates[0],date_year) ;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GenericBeans{");
        sb.append("message_id='").append(message_id).append('\'');
        sb.append(", customer_id='").append(customer_id).append('\'');
        sb.append(", account_provider='").append(account_provider).append('\'');
        sb.append(", account_balance='").append(account_balance).append('\'');
        sb.append(", merchant_id='").append(merchant_id).append('\'');
        sb.append(", industry_id='").append(industry_id).append('\'');
        sb.append(", industry_details='").append(industry_details).append('\'');
        sb.append(", category_id='").append(category_id).append('\'');
        sb.append(", category_details='").append(category_details).append('\'');
        sb.append(", processed='").append(processed).append('\'');
        sb.append(", transaction_type='").append(transaction_type).append('\'');
        sb.append(", amount='").append(amount).append('\'');
        sb.append(", account_number='").append(account_number).append('\'');
        sb.append(", merchant='").append(merchant).append('\'');
        sb.append(", date='").append(date).append('\'');
        sb.append(", account_type='").append(account_type).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
