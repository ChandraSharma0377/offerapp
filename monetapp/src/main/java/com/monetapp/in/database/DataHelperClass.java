package com.monetapp.in.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.dummy.R;
import com.monetapp.in.activities.MyApplication;
import com.monetapp.in.beans.BarDataBeans;
import com.monetapp.in.beans.GenericBeans;
import com.monetapp.in.beans.IncomeBeans;
import com.monetapp.in.beans.SmsBeans;
import com.monetapp.in.beans.SpendIndividualMonthBeans;
import com.monetapp.in.utility.Commons;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class DataHelperClass {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ", ";
    private static final String INT_TYPE = " INT";
    private static final String DOUBLE_TYPE = " REAL";
    private static final String PRIMARY_KEY = " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ";

    // Table
    private static final String TABLE_MERCHANT_SUMMARY = "merchant_summary";
    private static final String TABLE_TRANSACTIONAL_SUMMARY = "transactional_summary";
    private static final String TABLE_ACCOUNTS = "accounts";
    private static final String TABLE_TRANSACTIONS = "transactions";
    private static final String TABLE_MERCHANT = "merchant";
    private static final String TABLE_OFFERS = "offers";
    private static final String TABLE_GOALS = "goals";
    private static final String TABLE_REMINDER = "reminders";
    private static final String TABLE_CATEGORY_SUMMARY = "category_summary";
    private static final String TABLE_ORIGINAL_MSG = "original_messages";
    private static final String TABLE_CATEGORY = "category";
    private static final String TABLE_ACCOUNT_TYPE = "account_Type";
    private static final String TABLE_INDUSTRY = "industry";

    private static final String TABLE_RESPONSE= "response";

    // Column
    private static final String COLUMN_RESPONSE_ID = "response_id";
    private static final String COLUMN_RESPONSE = "response_value";

    private static final String COLUMN_CATEGORY_DETAIL = "category_detail";
    private static final String COLUMN_CATEGORY_ICON = "category_icon";

    private static final String COLUMN_MESSAGE_ID = "message_id";
    private static final String COLUMN_MESSAGE_SENDER = "message_sender";
    private static final String COLUMN_MESSAGE_DATE = "message_date";
    private static final String COLUMN_MESSAGE_CONTENT = "message_content";
    private static final String COLUMN_IS_PROCESSED = "is_processed";
    private static final String COLUMN_IS_DELETED = "is_deleted";


    private static final String COLUMN_CATEGORY__SUMMARY_ID = "category_summary_id";

    private static final String COLUMN_REMINDER_ID = "reminder_id";
    private static final String COLUMN_REMINDER_DUE_DATE = "reminder_due_date";
    private static final String COLUMN_REMINDER_COMPLETED_DATE = "reminder_completed_date";
    private static final String COLUMN_REMINDER_TYPE = "reminder_type";
    private static final String COLUMN_REMINDER_TYPE_ID = "reminder_type_id";
    private static final String COLUMN_REMINDER_DETAILS = "reminder_details";


    private static final String COLUMN_GOAL_ID = "goal_id";
    private static final String COLUMN_GOAL_TYPE = "goal_type";
    private static final String COLUMN_START_DATE = "start_date";
    private static final String COLUMN_END_DATE = "end_date";
    private static final String COLUMN_TARGET_AMOUNT = "target_amount";
    private static final String COLUMN_TARGET_ACHIEVED = "target_achieved";
    private static final String COLUMN_GOAL_COMPLETED = "goal_completed";


    private static final String COLUMN_OFFER_ID = "offer_id";
    private static final String COLUMN_OFFER_DETAILS = "offer_details";
    private static final String COLUMN_OFFER_START_DATE = "offer_start_date";
    private static final String COLUMN_OFFER_END_DATE = "offer_end_date";
    private static final String COLUMN_IS_ACTIVE = "is_active";


    private static final String COLUMN_MERCHANT_SUMMARY_ID = "merchant_summary_id";
    private static final String COLUMN_CUSTOMER_ID = "customer_id";
    private static final String COLUMN_SUMMARY_MONTH = "summary_month";
    private static  final String COLUMN_SUMMARY_YEAR = "summary_year";
    private static  final String COLUMN_SUMMARY_WEEK = "summary_month_week";
    private static  final String COLUMN_SUMMARY_AMOUNT = "summary_amount";
    private static  final String COLUMN_TRANSACTIONAL_SUMMARY_ID = "transactional_summary_id";
    private static  final String COLUMN_MERCHANT_ID = "merchant_id";
    private static  final String COLUMN_IS_SPENT = "is_spent";

    private static  final String COLUMN_ACCOUNT_ID = "account_id";
    private static  final String COLUMN_ACCOUNT_NUMBER = "account_number";
    private static  final String COLUMN_ACCOUNT_PROVIDER = "account_provider";
    private static  final String COLUMN_ACCOUNT_BALANCE = "account_balance";
    private static  final String COLUMN_ACCOUNT_SPENT = "account_spent";
    private static  final String COLUMN_ACCOUNT_TYPE_ID = "account_type_id";
    private static  final String COLUMN_LAST_UPDATED = "last_updated";


    private static  final String COLUMN_TRANSACTION_ID = "transaction_id";
    private static  final String COLUMN_TRANSACTION_TYPE = "transaction_type";
    private static  final String COLUMN_AMOUNT = "amount";
    private static  final String COLUMN_DATE = "date";
    private static  final String COLUMN_INDUSTRY_ID = "industry_id";
    private static  final String COLUMN_CATEGORY_ID = "category_id";
    private static  final String COLUMN_ORIGINAL_MESSAGE_ID = "original_message_id";
    private static  final String COLUMN_IS_EXPENSE = "is_expense";
    private static  final String COLUMN_TRANSACTION_DETAILS = "transactions_details";
    private static  final String COLUMN_NO_OF_TRANSACTIONS = "no_of_transactions";

    private static  final String COLUMN_MERCHANT_NAME = "merchant_name";
    private static  final String COLUMN_MERCHANT_DEATIL = "merchant_detail";


    private static  final String COLUMN_ACCOUNT_TYPE = "account_type";
    private static  final String COLUMN_INDUSTRY_DETAIL = "industry_detail";

    private static  final String CREATE_TABLE_RESPONSE = "CREATE TABLE " + TABLE_RESPONSE+ " ( "
            + COLUMN_RESPONSE_ID + PRIMARY_KEY
            + COLUMN_RESPONSE + TEXT_TYPE + " );";

    private static  final String CREATE_TABLE_INDUSTRY = "CREATE TABLE " + TABLE_INDUSTRY + " ( "
            + COLUMN_INDUSTRY_ID + PRIMARY_KEY
            + COLUMN_INDUSTRY_DETAIL + TEXT_TYPE + " );";


    private static  final String CREATE_TABLE_ACCOUNT_TYPE = "CREATE TABLE " + TABLE_ACCOUNT_TYPE + " ( "
            + COLUMN_ACCOUNT_TYPE_ID + PRIMARY_KEY
            + COLUMN_ACCOUNT_TYPE + TEXT_TYPE + " );";


    private static  final String CREATE_TABLE_TRANSACTIONAL_SUMMARY = "CREATE TABLE " + TABLE_TRANSACTIONAL_SUMMARY + " ( "
            + COLUMN_TRANSACTIONAL_SUMMARY_ID + PRIMARY_KEY
            + COLUMN_CUSTOMER_ID + INT_TYPE + COMMA_SEP
            + COLUMN_SUMMARY_WEEK + TEXT_TYPE + COMMA_SEP
            + COLUMN_SUMMARY_MONTH + TEXT_TYPE + COMMA_SEP
            + COLUMN_SUMMARY_YEAR + TEXT_TYPE + COMMA_SEP
            + COLUMN_SUMMARY_AMOUNT + DOUBLE_TYPE + COMMA_SEP
            + COLUMN_IS_SPENT + INT_TYPE + " );";

    private static final String CREATE_TABLE_MERCHANT_SUMMARY = "CREATE TABLE " + TABLE_MERCHANT_SUMMARY + " ( "
            + COLUMN_MERCHANT_SUMMARY_ID + PRIMARY_KEY
            + COLUMN_CUSTOMER_ID + INT_TYPE + COMMA_SEP
            + COLUMN_SUMMARY_WEEK + TEXT_TYPE + COMMA_SEP
            + COLUMN_SUMMARY_MONTH + TEXT_TYPE + COMMA_SEP
            + COLUMN_SUMMARY_YEAR + TEXT_TYPE + COMMA_SEP
            + COLUMN_SUMMARY_AMOUNT + DOUBLE_TYPE + COMMA_SEP
            + COLUMN_TRANSACTIONAL_SUMMARY_ID + INT_TYPE + COMMA_SEP
            + COLUMN_MERCHANT_ID + INT_TYPE + " );";


    private static final String CREATE_TABLE_ACCOUNTS = "CREATE TABLE " + TABLE_ACCOUNTS + " ( "
            + COLUMN_ACCOUNT_ID + PRIMARY_KEY
            + COLUMN_CUSTOMER_ID + INT_TYPE + COMMA_SEP
            + COLUMN_ACCOUNT_TYPE + TEXT_TYPE + COMMA_SEP
            + COLUMN_ACCOUNT_NUMBER + TEXT_TYPE + COMMA_SEP
            + COLUMN_ACCOUNT_PROVIDER + TEXT_TYPE + COMMA_SEP
            + COLUMN_ACCOUNT_BALANCE + DOUBLE_TYPE + COMMA_SEP
            + COLUMN_ACCOUNT_SPENT + DOUBLE_TYPE + COMMA_SEP
            + COLUMN_LAST_UPDATED + TEXT_TYPE + " );";

    private static final String CREATE_TABLE_TRANSACTIONS = "CREATE TABLE " + TABLE_TRANSACTIONS + " ( "
            + COLUMN_TRANSACTION_ID + PRIMARY_KEY
            + COLUMN_CUSTOMER_ID + INT_TYPE + COMMA_SEP
            + COLUMN_TRANSACTION_TYPE + TEXT_TYPE + COMMA_SEP
            + COLUMN_AMOUNT + DOUBLE_TYPE + COMMA_SEP
            + COLUMN_DATE + TEXT_TYPE + COMMA_SEP
            + COLUMN_ACCOUNT_ID + INT_TYPE + COMMA_SEP
            + COLUMN_MERCHANT_ID + INT_TYPE + COMMA_SEP
            + COLUMN_INDUSTRY_ID + INT_TYPE + COMMA_SEP
            + COLUMN_CATEGORY_ID + INT_TYPE + COMMA_SEP
            + COLUMN_ORIGINAL_MESSAGE_ID + TEXT_TYPE + COMMA_SEP
            + COLUMN_IS_EXPENSE + TEXT_TYPE + COMMA_SEP
            + COLUMN_TRANSACTION_DETAILS + TEXT_TYPE + " );";


    private static  final String CREATE_TABLE_MERCHANT = "CREATE TABLE " + TABLE_MERCHANT + " ( "
            + COLUMN_MERCHANT_ID + PRIMARY_KEY
            + COLUMN_INDUSTRY_ID + INT_TYPE + COMMA_SEP
            + COLUMN_MERCHANT_NAME + TEXT_TYPE + COMMA_SEP
            + COLUMN_MERCHANT_DEATIL + TEXT_TYPE + " );";

    private static  final String CREATE_TABLE_OFFERS = "CREATE TABLE " + TABLE_OFFERS + " ( "
            + COLUMN_OFFER_ID + PRIMARY_KEY
            + COLUMN_CUSTOMER_ID + INT_TYPE + COMMA_SEP
            + COLUMN_OFFER_DETAILS + TEXT_TYPE + COMMA_SEP
            + COLUMN_OFFER_START_DATE + DOUBLE_TYPE + COMMA_SEP
            + COLUMN_OFFER_END_DATE + TEXT_TYPE + COMMA_SEP
            + COLUMN_IS_ACTIVE + INT_TYPE + COMMA_SEP
            + COLUMN_MERCHANT_ID + INT_TYPE + " );";

    private static  final String CREATE_TABLE_GOALS = "CREATE TABLE " + TABLE_GOALS + " ( "
            + COLUMN_GOAL_ID + PRIMARY_KEY
            + COLUMN_CUSTOMER_ID + INT_TYPE + COMMA_SEP
            + COLUMN_GOAL_TYPE + INT_TYPE + COMMA_SEP
            + COLUMN_START_DATE + DOUBLE_TYPE + COMMA_SEP
            + COLUMN_END_DATE + TEXT_TYPE + COMMA_SEP
            + COLUMN_TARGET_AMOUNT + DOUBLE_TYPE + COMMA_SEP
            + COLUMN_TARGET_ACHIEVED + DOUBLE_TYPE + COMMA_SEP
            + COLUMN_GOAL_COMPLETED + INT_TYPE + COMMA_SEP
            + COLUMN_LAST_UPDATED + TEXT_TYPE + " );";

    private static  final String CREATE_TABLE_REMINDER = "CREATE TABLE " + TABLE_REMINDER + " ( "
            + COLUMN_REMINDER_ID + PRIMARY_KEY
            + COLUMN_CUSTOMER_ID + INT_TYPE + COMMA_SEP
            + COLUMN_REMINDER_DUE_DATE + TEXT_TYPE + COMMA_SEP
            + COLUMN_IS_ACTIVE + INT_TYPE + COMMA_SEP
            + COLUMN_REMINDER_COMPLETED_DATE + TEXT_TYPE + COMMA_SEP
            + COLUMN_REMINDER_TYPE + INT_TYPE + COMMA_SEP
            + COLUMN_REMINDER_TYPE_ID + TEXT_TYPE + COMMA_SEP
            + COLUMN_REMINDER_DETAILS + TEXT_TYPE + " );";

    private static  final String CREATE_TABLE_CATEGORY_SUMMARY = "CREATE TABLE " + TABLE_CATEGORY_SUMMARY + " ( "
            + COLUMN_CATEGORY__SUMMARY_ID + PRIMARY_KEY
            + COLUMN_CUSTOMER_ID + INT_TYPE + COMMA_SEP
            + COLUMN_SUMMARY_WEEK + TEXT_TYPE + COMMA_SEP
            + COLUMN_SUMMARY_MONTH + TEXT_TYPE + COMMA_SEP
            + COLUMN_SUMMARY_YEAR + TEXT_TYPE + COMMA_SEP
            + COLUMN_SUMMARY_AMOUNT + DOUBLE_TYPE + COMMA_SEP
            + COLUMN_CATEGORY_ID + INT_TYPE + COMMA_SEP
            + COLUMN_TRANSACTIONAL_SUMMARY_ID + INT_TYPE + COMMA_SEP
            + COLUMN_IS_SPENT + INT_TYPE + COMMA_SEP
            + COLUMN_NO_OF_TRANSACTIONS + INT_TYPE + " );";

    private static  final String CREATE_TABLE_ORIGINAL_MSG = "CREATE TABLE " + TABLE_ORIGINAL_MSG + " ( "
            + COLUMN_MESSAGE_ID + PRIMARY_KEY
            + COLUMN_CUSTOMER_ID + INT_TYPE + COMMA_SEP
            + COLUMN_MESSAGE_SENDER + TEXT_TYPE + COMMA_SEP
            + COLUMN_MESSAGE_DATE + TEXT_TYPE + COMMA_SEP
            + COLUMN_MESSAGE_CONTENT + TEXT_TYPE + COMMA_SEP
            + COLUMN_IS_PROCESSED + INT_TYPE + COMMA_SEP
            + COLUMN_IS_DELETED + INT_TYPE + " );";

    private static  final String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY + " ( "
            + COLUMN_CATEGORY_ID + PRIMARY_KEY
            + COLUMN_CATEGORY_DETAIL + TEXT_TYPE + COMMA_SEP
            + COLUMN_CATEGORY_ICON + INT_TYPE + " );";


    public static void createAllTable(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_MERCHANT_SUMMARY);
            db.execSQL(CREATE_TABLE_TRANSACTIONAL_SUMMARY);
            db.execSQL(CREATE_TABLE_ACCOUNTS);
            db.execSQL(CREATE_TABLE_TRANSACTIONS);
            db.execSQL(CREATE_TABLE_MERCHANT);
            db.execSQL(CREATE_TABLE_OFFERS);
            db.execSQL(CREATE_TABLE_GOALS);
            db.execSQL(CREATE_TABLE_REMINDER);
            db.execSQL(CREATE_TABLE_CATEGORY_SUMMARY);
            db.execSQL(CREATE_TABLE_ORIGINAL_MSG);
            db.execSQL(CREATE_TABLE_CATEGORY);
            db.execSQL(CREATE_TABLE_ACCOUNT_TYPE);
            db.execSQL(CREATE_TABLE_INDUSTRY);
            db.execSQL(CREATE_TABLE_RESPONSE);
        } catch (Exception ex) {
            Log.d("DBException", ex.getMessage());
        }

    }

    public static  boolean insertAllSms(List<SmsBeans> smsBeansList) {

        //DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase SQDB = MyApplication.dbHandler.getWritableDb();
        for (int i = 0; i < smsBeansList.size(); i++) {
            ContentValues values = new ContentValues();
            SmsBeans smsBeans = smsBeansList.get(i);
            if (!isRecordExist(smsBeans.getId(), TABLE_ORIGINAL_MSG, COLUMN_MESSAGE_ID)) {
                values.put(COLUMN_MESSAGE_ID, Integer.parseInt(smsBeans.getId()));
                values.put(COLUMN_CUSTOMER_ID, MyApplication.getMd5Mobile());
                values.put(COLUMN_MESSAGE_SENDER, smsBeans.getAddress());
                values.put(COLUMN_MESSAGE_DATE, Commons.getFormattedDate(smsBeans.getTime()));
                values.put(COLUMN_MESSAGE_CONTENT, smsBeans.getMsg());
                values.put(COLUMN_IS_PROCESSED, 0);
                values.put(COLUMN_IS_DELETED, 0);
                SQDB.beginTransaction();
                try {
                    SQDB.insert(TABLE_ORIGINAL_MSG, null, values);
                   // MainActivity.dbHandler.close();
                    Commons.updateLastTime(smsBeans.getTime());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                SQDB.setTransactionSuccessful();
                SQDB.endTransaction();
            }
        }
        return true;
    }

    private static long insertTransactionSummary(GenericBeans genericBeans) {
        long rowid = -1;

       // DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase SQDB = MyApplication.dbHandler.getWritableDb();
        if (checkTransactionSummaryData(genericBeans.getDate_year(), genericBeans.getDate_week(), genericBeans.getDate_month(), genericBeans.getTransaction_type().equals(Commons.TRANSACTION_TYPE_CREDIT) ? "0" : "1")) {
            updateTransactionSummaryAmount(genericBeans.getDate_year(), genericBeans.getDate_week(), genericBeans.getDate_month(),
                    genericBeans.getTransaction_type().equals(Commons.TRANSACTION_TYPE_CREDIT) ? "0" : "1", genericBeans.getAmount());
        } else {
            ContentValues values = new ContentValues();
            // values.put(COLUMN_TRANSACTIONAL_SUMMARY_ID, Integer.parseInt(genericBeans.getMessage_id()));
            values.put(COLUMN_CUSTOMER_ID, MyApplication.getMd5Mobile());
            values.put(COLUMN_SUMMARY_WEEK, genericBeans.getDate_week());
            values.put(COLUMN_SUMMARY_MONTH, genericBeans.getDate_month());
            values.put(COLUMN_SUMMARY_YEAR, genericBeans.getDate_year());
            values.put(COLUMN_SUMMARY_AMOUNT, genericBeans.getAmount());
            values.put(COLUMN_IS_SPENT, genericBeans.getTransaction_type().equals(Commons.TRANSACTION_TYPE_CREDIT) ? 0 : 1);
            SQDB.beginTransaction();
            try {
                rowid = SQDB.insert(TABLE_TRANSACTIONAL_SUMMARY, null, values);
               // MainActivity.dbHandler.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            SQDB.setTransactionSuccessful();
            SQDB.endTransaction();
        }

        return rowid;
    }

    public static void insertAccount(GenericBeans genericBeans) {

        //DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase SQDB = MyApplication.dbHandler.getWritableDb();;
        if (isAccountRecordExist(genericBeans.getAccount_type(),
                //.equals(Commons.TRANSACTION_TYPE_CREDIT) ? "0" : "1",
                genericBeans.getAccount_number(), genericBeans.getAccount_provider())) {
            updateAccountData(genericBeans.getTransaction_type(),
                    //.equals(Commons.TRANSACTION_TYPE_CREDIT) ? "0" : "1",
                    genericBeans.getAccount_number(), genericBeans.getAccount_provider(), genericBeans.getAmount());
        } else {
            ContentValues values = new ContentValues();
            //values.put(COLUMN_ACCOUNT_ID, Integer.parseInt(genericBeans.acc));
            values.put(COLUMN_CUSTOMER_ID, MyApplication.getMd5Mobile());
            values.put(COLUMN_ACCOUNT_TYPE, genericBeans.getAccount_type());
            values.put(COLUMN_ACCOUNT_NUMBER, genericBeans.getAccount_number());
            values.put(COLUMN_ACCOUNT_PROVIDER, genericBeans.getAccount_provider());
            values.put(COLUMN_ACCOUNT_BALANCE, genericBeans.getAccount_balance());
            values.put(COLUMN_ACCOUNT_SPENT, genericBeans.getAmount());
            values.put(COLUMN_LAST_UPDATED, genericBeans.getDate());
            SQDB.beginTransaction();
            try {
                SQDB.insert(TABLE_ACCOUNTS, null, values);
               // MainActivity.dbHandler.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            SQDB.setTransactionSuccessful();
            SQDB.endTransaction();
        }

    }


    public static void insertIndustry(String industryId, String industryDetails) {

        //DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase SQDB = MyApplication.dbHandler.getWritableDb();;
        if (!isRecordExist(industryId, TABLE_INDUSTRY, COLUMN_INDUSTRY_ID)) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_INDUSTRY_ID, industryId);
            values.put(COLUMN_INDUSTRY_DETAIL, industryDetails);
            SQDB.beginTransaction();
            try {
                SQDB.insert(TABLE_INDUSTRY, null, values);
               // MainActivity.dbHandler.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            SQDB.setTransactionSuccessful();
            SQDB.endTransaction();
        }
    }

    public static void insertMessageResponse(String responseId, String msgResponse) {

        //DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase SQDB = MyApplication.dbHandler.getWritableDb();
            ContentValues values = new ContentValues();
            values.put(COLUMN_RESPONSE_ID, responseId);
            values.put(COLUMN_RESPONSE, msgResponse);

            SQDB.beginTransaction();
            try {
                SQDB.insert(TABLE_RESPONSE, null, values);
                // MainActivity.dbHandler.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            SQDB.setTransactionSuccessful();
            SQDB.endTransaction();

    }
    public static void insertMerchant(String merchantId, String merchantName, String merchantDetails, String industryId) {

        //DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase SQDB = MyApplication.dbHandler.getWritableDb();
        if (!isRecordExist(merchantId, TABLE_MERCHANT, COLUMN_MERCHANT_ID)) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_MERCHANT_ID, merchantId);
            values.put(COLUMN_MERCHANT_NAME, merchantName);
            values.put(COLUMN_MERCHANT_DEATIL, merchantDetails);
            values.put(COLUMN_INDUSTRY_ID, industryId);
            SQDB.beginTransaction();
            try {
                SQDB.insert(TABLE_MERCHANT, null, values);
               // MainActivity.dbHandler.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            SQDB.setTransactionSuccessful();
            SQDB.endTransaction();
        }
    }

    public static void insertCategory(String categoryId, String categoryDetails) {

        //DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase SQDB = MyApplication.dbHandler.getWritableDb();
        if (!isRecordExist(categoryId, TABLE_CATEGORY, COLUMN_CATEGORY_ID)) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_CATEGORY_ID, categoryId);
            values.put(COLUMN_CATEGORY_DETAIL, categoryDetails);
            SQDB.beginTransaction();
            try {
                SQDB.insert(TABLE_CATEGORY, null, values);
               // MainActivity.dbHandler.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            SQDB.setTransactionSuccessful();
            SQDB.endTransaction();
        }
    }

    public static void insertCategorySummary(GenericBeans genericBeans, long transactionSummaryID) {

        //DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase SQDB = MyApplication.dbHandler.getWritableDb();

        if (checkCategorySummaryData(genericBeans.getDate_year(), genericBeans.getDate_week(), genericBeans.getDate_month(),
                genericBeans.getTransaction_type().equals(Commons.TRANSACTION_TYPE_CREDIT) ? "0" : "1", genericBeans.getCategory_id())) {
            updateCategorySummaryAmount(genericBeans.getDate_year(), genericBeans.getDate_week(), genericBeans.getDate_month(),
                    genericBeans.getTransaction_type().equals(Commons.TRANSACTION_TYPE_CREDIT) ? "0" : "1", genericBeans.getAmount(), genericBeans.getCategory_id());
        } else {
            ContentValues values = new ContentValues();
            values.put(COLUMN_CUSTOMER_ID, MyApplication.getMd5Mobile());
            values.put(COLUMN_CATEGORY_ID, genericBeans.getCategory_id());
            values.put(COLUMN_SUMMARY_MONTH, genericBeans.getDate_month());
            values.put(COLUMN_SUMMARY_YEAR, genericBeans.getDate_year());
            values.put(COLUMN_SUMMARY_WEEK, genericBeans.getDate_week());
            values.put(COLUMN_SUMMARY_AMOUNT, genericBeans.getAmount());
            values.put(COLUMN_NO_OF_TRANSACTIONS, "1");
            values.put(COLUMN_IS_SPENT, genericBeans.getTransaction_type().equals(Commons.TRANSACTION_TYPE_CREDIT) ? 0 : 1);
            SQDB.beginTransaction();
            try {
                SQDB.insert(TABLE_CATEGORY_SUMMARY, null, values);
               // MainActivity.dbHandler.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            SQDB.setTransactionSuccessful();
            SQDB.endTransaction();

        }
    }


    public static void insertMerchantSummary(GenericBeans genericBeans, long transactionSummaryID) {

        //DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase SQDB = MyApplication.dbHandler.getWritableDb();
        if (checkMerchantSummaryData(genericBeans.getDate_year(), genericBeans.getDate_week(), genericBeans.getDate_month(),
                genericBeans.getMerchant_id(), transactionSummaryID)) {
            updateMerchantSummaryAmount(genericBeans.getDate_year(), genericBeans.getDate_week(), genericBeans.getDate_month(),
                    genericBeans.getAmount(), genericBeans.getMerchant_id(), transactionSummaryID);
        } else {
            ContentValues values = new ContentValues();

            values.put(COLUMN_CUSTOMER_ID, MyApplication.getMd5Mobile());
            values.put(COLUMN_MERCHANT_ID, genericBeans.getMerchant_id());
            values.put(COLUMN_SUMMARY_MONTH, genericBeans.getDate_month());
            values.put(COLUMN_SUMMARY_YEAR, genericBeans.getDate_year());
            values.put(COLUMN_SUMMARY_WEEK, genericBeans.getDate_week());
            values.put(COLUMN_SUMMARY_AMOUNT, genericBeans.getAmount());
            values.put(COLUMN_TRANSACTIONAL_SUMMARY_ID, transactionSummaryID);
            SQDB.beginTransaction();
            try {
                SQDB.insert(TABLE_MERCHANT_SUMMARY, null, values);
               // MainActivity.dbHandler.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            SQDB.setTransactionSuccessful();
            SQDB.endTransaction();
        }
    }

    public static void insertTransaction(GenericBeans genericBeans) {

        //DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase SQDB = MyApplication.dbHandler.getWritableDb();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CUSTOMER_ID, MyApplication.getMd5Mobile());
        values.put(COLUMN_TRANSACTION_TYPE, genericBeans.getTransaction_type());
        values.put(COLUMN_AMOUNT, genericBeans.getAmount());
        values.put(COLUMN_DATE, genericBeans.getDate());
        values.put(COLUMN_ACCOUNT_ID, getAccountId(genericBeans.getAccount_number()));
        values.put(COLUMN_MERCHANT_ID, genericBeans.getMerchant_id());
        values.put(COLUMN_INDUSTRY_ID, genericBeans.getIndustry_id());
        values.put(COLUMN_CATEGORY_ID, genericBeans.getCategory_id());
        values.put(COLUMN_ORIGINAL_MESSAGE_ID, genericBeans.getMessage_id());
        values.put(COLUMN_IS_EXPENSE, "1");
        values.put(COLUMN_TRANSACTION_DETAILS, "");
        SQDB.beginTransaction();
        try {
            SQDB.insert(TABLE_TRANSACTIONS, null, values);
           // MainActivity.dbHandler.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SQDB.setTransactionSuccessful();
        SQDB.endTransaction();
    }
    public static boolean isAccountRecordExist(String accountType, String accountNumber, String accountProvider) {

//        Cursor cursor = db.query(TABLE_ACCOUNTS, null,
//                COLUMN_ACCOUNT_TYPE + "=? and " + COLUMN_ACCOUNT_NUMBER + "=? and " + COLUMN_ACCOUNT_PROVIDER + "=?  ",
 //               new String[]{accountType, accountNumber, accountProvider}, null, null, null);
        String selectQuery = "SELECT * FROM " + TABLE_ACCOUNTS + " WHERE " + COLUMN_ACCOUNT_TYPE + " = '" + accountType.trim().toString() + "' and "+
                COLUMN_ACCOUNT_NUMBER +" = '" + accountNumber.trim().toString() + "' and "+COLUMN_ACCOUNT_PROVIDER+ "  = '" + accountProvider.trim().toString() + "'";

        // DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase db = MyApplication.dbHandler.getWritableDb();
        Cursor cursor = db.rawQuery(selectQuery, null);
        boolean isexit = false;
        if (cursor != null) {
            try {

                isexit = cursor.getCount() > 0 ? true : false;
            } catch (Exception e) {
                Log.d("W for file", e.getMessage().toString());
            } finally {
                try {
                    if (!cursor.isClosed()) {
                        //   cursor.close();
                    }
                    cursor = null;
                } catch (Exception e) {
                    Log.e("While closing cursor", e.getMessage().toString());
                }
            }
        }
        return isexit;
    }
    public static boolean isRecordExist(String id, String tableName, String columnName) {
        String selectQuery = "SELECT * FROM " + tableName + " WHERE " + columnName + " = " + id;

       // DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase db = MyApplication.dbHandler.getWritableDb();
        Cursor cursor = db.rawQuery(selectQuery, null);
        boolean isexit = false;
        if (cursor != null) {
            try {

                isexit = cursor.getCount() > 0 ? true : false;
            } catch (Exception e) {
                Log.d("W for file", e.getMessage().toString());
            } finally {
                try {
                    if (!cursor.isClosed()) {
                     //   cursor.close();
                    }
                    cursor = null;
                } catch (Exception e) {
                    Log.e("While closing cursor", e.getMessage().toString());
                }
            }
        }
        return isexit;
    }


    public static void insertAllParseDate(GenericBeans genericBeans) {
        updateUploadedSmsStatus(genericBeans.getMessage_id());
        long transactionSummaryID = insertTransactionSummary(genericBeans);
        insertAccount(genericBeans);
        insertIndustry(genericBeans.getIndustry_id(), genericBeans.getIndustry_details());
        insertMerchant(genericBeans.getMerchant_id(), genericBeans.getMerchant(), "", genericBeans.getIndustry_id());
        insertCategory(genericBeans.getCategory_id(), genericBeans.getCategory_details());
        insertCategorySummary(genericBeans, transactionSummaryID);
        insertMerchantSummary(genericBeans, transactionSummaryID);
        insertTransaction(genericBeans);
    }

    public static void updateUploadedSmsStatus(String msgID) {
        try {
            //DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
            SQLiteDatabase db = MyApplication.dbHandler.getWritableDb();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_IS_PROCESSED, "1");
            db.update(TABLE_ORIGINAL_MSG, cv, COLUMN_MESSAGE_ID + "= ?", new String[]{msgID});
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getAccountId(String accountNumber) {

        String accountID = "";
      //  DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase db = MyApplication.dbHandler.getWritableDb();

        try {
            String query = "SELECT " + COLUMN_ACCOUNT_ID + " FROM " + TABLE_ACCOUNTS + " WHERE " + COLUMN_ACCOUNT_NUMBER + " = '" + accountNumber + "'";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        do {
                            if (null != cursor.getString(0))
                                accountID = cursor.getString(0);


                        } while (cursor.moveToNext());
                    }
                } catch (Exception e) {
                    Log.d("W for file", e.getMessage().toString());
                } finally {
                    try {
                        if (!cursor.isClosed()) {
                          //  cursor.close();
                        }
                        cursor = null;
                    } catch (Exception e) {
                        Log.e("While closing cursor", e.getMessage().toString());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountID;
    }

    private static boolean checkTransactionSummaryData(String year, String week, String month, String spend) {
        //DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase db = MyApplication.dbHandler.getWritableDb();
        Cursor cursor = db.query(TABLE_TRANSACTIONAL_SUMMARY, null,
                COLUMN_SUMMARY_MONTH + "=? and " + COLUMN_SUMMARY_WEEK + "=? and " + COLUMN_SUMMARY_YEAR + "=? and " + COLUMN_IS_SPENT + "=? ",
                new String[]{month, week, year, spend}, null, null, null);
        boolean isexit = false;
        if (null != cursor) {
            try {
                cursor.moveToFirst();
                cursor.moveToFirst();
                isexit = cursor.getCount() > 0 ? true : false;
            } catch (Exception e) {
                Log.d("W for file", e.getMessage().toString());
            } finally {
                try {
                    if (!cursor.isClosed()) {
                      //  cursor.close();
                    }
                    cursor = null;
                } catch (Exception e) {
                    Log.e("While closing cursor", e.getMessage().toString());
                }
            }

        }
        return isexit;
    }

    private static double updateTransactionSummaryAmount(String year, String week, String month, String spend, String newAmount) {
        double amount = 0.0;
        int id = -1;
        //DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase db = MyApplication.dbHandler.getWritableDb();
        Cursor cursor = db.query(TABLE_TRANSACTIONAL_SUMMARY, new String[]{COLUMN_SUMMARY_AMOUNT, COLUMN_TRANSACTIONAL_SUMMARY_ID},
                COLUMN_SUMMARY_MONTH + "=? and " + COLUMN_SUMMARY_WEEK + "=? and " + COLUMN_SUMMARY_YEAR + "=? and " + COLUMN_IS_SPENT + "=? ",
                new String[]{month, week, year, spend}, null, null, null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        try {
                            amount = cursor.getDouble(0);
                            id = cursor.getInt(1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } while (cursor.moveToNext());
                }
            } catch (Exception e) {
                Log.d("W for file", e.getMessage().toString());
            } finally {
                try {
                    if (!cursor.isClosed()) {
                     //   cursor.close();
                    }
                    cursor = null;
                } catch (Exception e) {
                    Log.e("While closing cursor", e.getMessage().toString());
                }
            }
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_SUMMARY_AMOUNT, Double.parseDouble(newAmount) + amount);
        long updatecheck = db.update(TABLE_TRANSACTIONAL_SUMMARY, values, COLUMN_TRANSACTIONAL_SUMMARY_ID + " =" + id, null);
        return amount;
    }

    private static boolean checkCategorySummaryData(String year, String week, String month, String spend, String categoryid) {
        //DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase db = MyApplication.dbHandler.getWritableDb();
        Cursor cursor = db.query(TABLE_CATEGORY_SUMMARY, null,
                COLUMN_SUMMARY_MONTH + "=? and " + COLUMN_SUMMARY_WEEK + "=? and " + COLUMN_SUMMARY_YEAR + "=? and " + COLUMN_IS_SPENT + "=? and "
                        + COLUMN_CATEGORY_ID + "=?  ",
                new String[]{month, week, year, spend, categoryid}, null, null, null);
        boolean isexit = false;
        if (null != cursor) {
            try {
                cursor.moveToFirst();
                cursor.moveToFirst();
                isexit = cursor.getCount() > 0 ? true : false;
            } catch (Exception e) {
                Log.d("W for file", e.getMessage().toString());
            } finally {
                try {
                    if (!cursor.isClosed()) {
                      //  cursor.close();
                    }
                    cursor = null;
                } catch (Exception e) {
                    Log.e("While closing cursor", e.getMessage().toString());
                }
            }
        }
        return isexit;
    }

    private static double updateCategorySummaryAmount(String year, String week, String month, String spend, String newAmount, String categoryid) {
        double amount = 0.0;
        int id = -1;
        int transactionNumber = 0;
        //DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase db = MyApplication.dbHandler.getWritableDb();
        Cursor cursor = db.query(TABLE_CATEGORY_SUMMARY, new String[]{COLUMN_SUMMARY_AMOUNT, COLUMN_CATEGORY__SUMMARY_ID, COLUMN_NO_OF_TRANSACTIONS},
                COLUMN_SUMMARY_MONTH + "=? and " + COLUMN_SUMMARY_WEEK + "=? and " + COLUMN_SUMMARY_YEAR + "=? and " + COLUMN_IS_SPENT + "=? and "
                        + COLUMN_CATEGORY_ID + "=?  ",
                new String[]{month, week, year, spend, categoryid}, null, null, null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        try {
                            amount = cursor.getDouble(0);
                            id = cursor.getInt(1);
                            transactionNumber = cursor.getInt(2);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } while (cursor.moveToNext());
                }
            } catch (Exception e) {
                Log.d("W for file", e.getMessage().toString());
            } finally {
                try {
                    if (!cursor.isClosed()) {
                      //  cursor.close();
                    }
                    cursor = null;
                } catch (Exception e) {
                    Log.e("While closing cursor", e.getMessage().toString());
                }
            }

        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_SUMMARY_AMOUNT, Double.parseDouble(newAmount) + amount);
        values.put(COLUMN_NO_OF_TRANSACTIONS, transactionNumber + 1);
        long updatecheck = db.update(TABLE_CATEGORY_SUMMARY, values, COLUMN_CATEGORY__SUMMARY_ID + " =" + id, null);
        return amount;
    }

    private static boolean checkMerchantSummaryData(String year, String week, String month, String merchantID, long transactionSummaryid) {
       // DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase db = MyApplication.dbHandler.getWritableDb();
        Cursor cursor = db.query(TABLE_MERCHANT_SUMMARY, null,
                COLUMN_SUMMARY_MONTH + "=? and " + COLUMN_SUMMARY_WEEK + "=? and " + COLUMN_SUMMARY_YEAR + "=? and " + COLUMN_MERCHANT_ID + "=? and "
                        + COLUMN_TRANSACTIONAL_SUMMARY_ID + "=?  ",
                new String[]{month, week, year, merchantID, "" + transactionSummaryid}, null, null, null);
        boolean isexit = false;
        if (null != cursor) {
            try {
                cursor.moveToFirst();
                cursor.moveToFirst();
                isexit = cursor.getCount() > 0 ? true : false;
            } catch (Exception e) {
                Log.d("W for file", e.getMessage().toString());
            } finally {
                try {
                    if (!cursor.isClosed()) {
                      //  cursor.close();
                    }
                    cursor = null;
                } catch (Exception e) {
                    Log.e("While closing cursor", e.getMessage().toString());
                }
            }
        }
        return isexit;
    }

    private static double updateMerchantSummaryAmount(String year, String week, String month, String newAmount, String merchantID, long transactionSummaryid) {
        double amount = 0.0;
        int id = -1;
        //DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase db = MyApplication.dbHandler.getWritableDb();
        Cursor cursor = db.query(TABLE_MERCHANT_SUMMARY, new String[]{COLUMN_SUMMARY_AMOUNT, COLUMN_MERCHANT_SUMMARY_ID},
                COLUMN_SUMMARY_MONTH + "=? and " + COLUMN_SUMMARY_WEEK + "=? and " + COLUMN_SUMMARY_YEAR + "=? and " + COLUMN_MERCHANT_ID + "=? and "
                        + COLUMN_TRANSACTIONAL_SUMMARY_ID + "=?  ",
                new String[]{month, week, year, merchantID, "" + transactionSummaryid}, null, null, null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        try {
                            amount = cursor.getDouble(0);
                            id = cursor.getInt(1);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } while (cursor.moveToNext());
                }
            } catch (Exception e) {
                Log.d("W for file", e.getMessage().toString());
            } finally {
                try {
                    if (!cursor.isClosed()) {
                     //   cursor.close();
                    }
                    cursor = null;
                } catch (Exception e) {
                    Log.e("While closing cursor", e.getMessage().toString());
                }
            }
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_SUMMARY_AMOUNT, Double.parseDouble(newAmount) + amount);

        long updatecheck = db.update(TABLE_MERCHANT_SUMMARY, values, COLUMN_MERCHANT_SUMMARY_ID + " =" + id, null);
        return amount;
    }

    private static boolean checkAccountData(String accountType, String accountNumber, String accountProvider) {

       // DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase db = MyApplication.dbHandler.getWritableDb();
        Cursor cursor = db.query(TABLE_ACCOUNTS, null,
                COLUMN_ACCOUNT_TYPE + "=? and " + COLUMN_ACCOUNT_NUMBER + "=? and " + COLUMN_ACCOUNT_PROVIDER + "=?  ",
                new String[]{accountType, accountNumber, accountProvider}, null, null, null);
        boolean isexit = false;
        if (null != cursor) {
            try {
                cursor.moveToFirst();
                cursor.moveToFirst();
                isexit = cursor.getCount() > 0 ? true : false;
            } catch (Exception e) {
                Log.d("W for file", e.getMessage().toString());
            } finally {
                try {
                    if (!cursor.isClosed()) {
                      //  cursor.close();
                    }
                    cursor = null;
                } catch (Exception e) {
                    Log.e("While closing cursor", e.getMessage().toString());
                }
            }
        }
        return isexit;
    }

    private static void updateAccountData(String accountType, String accountNumber, String accountProvider, String newAmount) {
        double amountbalance = 0.0;
        double amountspent = 0.0;
        int id = -1;
        //DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase db = MyApplication.dbHandler.getWritableDb();
        Cursor cursor = db.query(TABLE_ACCOUNTS, new String[]{COLUMN_ACCOUNT_BALANCE, COLUMN_ACCOUNT_SPENT, COLUMN_ACCOUNT_ID},
                COLUMN_ACCOUNT_TYPE + "=? and " + COLUMN_ACCOUNT_NUMBER + "=? and " + COLUMN_ACCOUNT_PROVIDER + "=?  ",
                new String[]{accountType, accountNumber, accountProvider}, null, null, null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        try {
                            amountbalance = cursor.getDouble(0);
                            amountspent = cursor.getDouble(1);
                            id = cursor.getInt(2);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } while (cursor.moveToNext());
                }
            } catch (Exception e) {
                Log.d("W for file", e.getMessage().toString());
            } finally {
                try {
                    if (!cursor.isClosed()) {
                      //  cursor.close();
                    }
                    cursor = null;
                } catch (Exception e) {
                    Log.e("While closing cursor", e.getMessage().toString());
                }
            }
        }
        ContentValues values = new ContentValues();
        values.put(COLUMN_ACCOUNT_BALANCE, amountbalance - Double.parseDouble(newAmount));
        values.put(COLUMN_ACCOUNT_SPENT, amountspent + Double.parseDouble(newAmount));

        long updatecheck = db.update(TABLE_ACCOUNTS, values, COLUMN_ACCOUNT_ID + " = " + id, null);
    }

    public static ArrayList<String> getTablews() {
        ArrayList<String> arrTblNames = new ArrayList<String>();
       // DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase db = MyApplication.dbHandler.getWritableDb();
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        if (null != cursor) {
            try {
                if (cursor.moveToFirst()) {
                    while (!cursor.isAfterLast()) {
                        arrTblNames.add(cursor.getString(cursor.getColumnIndex("name")));
                        cursor.moveToNext();
                    }
                }
            } catch (Exception e) {
                Log.d("W for file", e.getMessage().toString());
            } finally {
                try {
                    if (!cursor.isClosed()) {
                      //  cursor.close();
                    }
                    cursor = null;
                } catch (Exception e) {
                    Log.e("While closing cursor", e.getMessage().toString());
                }
            }
        }
        return arrTblNames;
    }

    public static ArrayList<BarDataBeans> getBarChartData(String isSpent) {
        ArrayList<BarDataBeans> barDataBeanses = new ArrayList<>();
        try {
            //DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
            SQLiteDatabase db = MyApplication.dbHandler.getWritableDb();
            String query = "SELECT " + COLUMN_SUMMARY_MONTH + ", SUM(" + COLUMN_SUMMARY_AMOUNT + ") " +
                    "FROM " + TABLE_TRANSACTIONAL_SUMMARY +
                    " WHERE " + COLUMN_IS_SPENT + " = " + isSpent +
                    " GROUP BY " + COLUMN_SUMMARY_MONTH;
            Cursor cursor = db.rawQuery(query, null);
            if (null != cursor) {
                try {
                    if (cursor.moveToFirst()) {
                        while (!cursor.isAfterLast()) {
                            String title = cursor.getString(0);
                            String value = cursor.getString(1);
                            barDataBeanses.add(new BarDataBeans(title, value));
                            cursor.moveToNext();
                        }
                    }
                } catch (Exception e) {
                    Log.d("W for file", e.getMessage().toString());
                } finally {
                    try {
                        if (!cursor.isClosed()) {
                          //  cursor.close();
                        }
                        cursor = null;
                    } catch (Exception e) {
                        Log.e("While closing cursor", e.getMessage().toString());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return barDataBeanses;
    }


    public static ArrayList<IncomeBeans> getSpentAndIncomeData(Context context, String isSpent, String month ,String year) {
        ArrayList<IncomeBeans> incomeBeanses = new ArrayList<>();
        try {
            //DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
            SQLiteDatabase db = MyApplication.dbHandler.getWritableDb();
            String query_old = "SELECT * " +
                    "FROM category_summary  cs INNER JOIN  category c on cs.category_id = c.category_id " +
                    "WHERE cs.summary_month = '" + month + "' AND  cs.is_spent = '" + isSpent + "' AND  cs.summary_year = '" + year + "'";

          String query = "select sum(no_of_transactions) as transaction_numbers, sum(category_summary.summary_amount), category.category_detail as amount , category.category_id from category_summary"+
            " inner join category where category_summary.summary_month = '" + month + "' and category_summary.summary_year = '" + year + "' and category_summary.category_id=category.category_id and category_summary.is_spent = '" + isSpent + "'";

            Cursor cursor = db.rawQuery(query, null);
            if (null != cursor) {
                try {
                    if (cursor.moveToFirst()) {
                        while (!cursor.isAfterLast()) {
                            String noOfTransc = cursor.getString(0);
                            String amount = cursor.getString(1);
                            String categoryDetail = cursor.getString(2);
                            int categoryId = cursor.getInt(3);
                            if(null != noOfTransc)
                            incomeBeanses.add(
                                    new IncomeBeans(ContextCompat.getDrawable(context,R.drawable.bank), categoryDetail, noOfTransc + " Transactions", amount, "" + categoryId, ""));
                            cursor.moveToNext();
                        }
                    }
                } catch (Exception e) {
                    Log.d("W for file", e.getMessage().toString());
                } finally {
                    try {
                        if (!cursor.isClosed()) {
                          //  cursor.close();
                        }
                        cursor = null;
                    } catch (Exception e) {
                        Log.e("While closing cursor", e.getMessage().toString());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return incomeBeanses;
    }
    public static  ArrayList<SpendIndividualMonthBeans> getIndividualMonthData(Context context, String isSpent, String month,String year) {
        ArrayList<SpendIndividualMonthBeans> incomeBeanses = new ArrayList<>();
        try {
            //DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
            SQLiteDatabase db = MyApplication.dbHandler.getWritableDb();
            String dateValue = month + "/" + year;
            String transaction_type = isSpent.equals("1") ? Commons.TRANSACTION_TYPE_DEBIT : Commons.TRANSACTION_TYPE_CREDIT;

            String query = "SELECT * " +
                    "FROM transactions  ts INNER JOIN  merchant m on ts.merchant_id = m.merchant_id " +
                    "WHERE  ts.date LIKE  '%" + dateValue + "'  AND  ts.transaction_type = '" + transaction_type + "'";
            Cursor cursor = db.rawQuery(query, null);
            if (null != cursor) {
                try {
                    if (cursor.moveToFirst()) {
                        while (!cursor.isAfterLast()) {
                            String date = cursor.getString(4);
                            String amount = cursor.getString(3);
                            String merchantName = cursor.getString(14);
                            int accountId = cursor.getInt(5);
                            String messageId = cursor.getString(9);
                            int categoryId = cursor.getInt(8);
                            int merchantId = cursor.getInt(6);
                            incomeBeanses.add(
                                    new SpendIndividualMonthBeans(ContextCompat.getDrawable(context,R.drawable.bank), merchantName, date, amount,
                                            "" + categoryId, messageId, "" + merchantId, date, "" + accountId, isSpent.equals("1") ? true : false));

                            cursor.moveToNext();
                        }
                    }
                } catch (Exception e) {
                    Log.d("W for file", e.getMessage().toString());
                } finally {
                    try {
                        if (!cursor.isClosed()) {
                            //  cursor.close();
                        }
                        cursor = null;
                    } catch (Exception e) {
                        Log.e("While closing cursor", e.getMessage().toString());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return incomeBeanses;
    }
    public static ArrayList<IncomeBeans> getSpentIndividualCategoryData(Context context, String isSpent, String month, String year, String category_id) {
        ArrayList<IncomeBeans> incomeBeanses = new ArrayList<>();
        try {
           // DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
            SQLiteDatabase db = MyApplication.dbHandler.getWritableDb();
            String dateValue = month + "/" + year;
            String transaction_type = isSpent.equals("1") ? Commons.TRANSACTION_TYPE_DEBIT : Commons.TRANSACTION_TYPE_CREDIT;

            String query = "SELECT * " +
                    "FROM transactions  ts INNER JOIN  merchant m on ts.merchant_id = m.merchant_id " +
                    "WHERE ts.category_id = '" + category_id + "' AND  ts.date LIKE  '%" + dateValue + "'  AND  ts.transaction_type = '" + transaction_type + "'";
            Cursor cursor = db.rawQuery(query, null);
            if (null != cursor) {
                try {
                    if (cursor.moveToFirst()) {
                        while (!cursor.isAfterLast()) {
                            String date = cursor.getString(4);
                            String amount = cursor.getString(3);
                            String merchantName = cursor.getString(14);
                            int accountId = cursor.getInt(5);
                            String messageId = cursor.getString(9);
                            int categoryId = cursor.getInt(8);
                            int merchantId = cursor.getInt(6);
                            incomeBeanses.add(
                                    new IncomeBeans(ContextCompat.getDrawable(context,R.drawable.bank), merchantName, date, amount,
                                            "" + categoryId, messageId, "" + merchantId, date, "" + accountId, isSpent.equals("1") ? true : false));

                            cursor.moveToNext();
                        }
                    }
                } catch (Exception e) {
                    Log.d("W for file", e.getMessage().toString());
                } finally {
                    try {
                        if (!cursor.isClosed()) {
                          //  cursor.close();
                        }
                        cursor = null;
                    } catch (Exception e) {
                        Log.e("While closing cursor", e.getMessage().toString());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return incomeBeanses;

    }
    public static ArrayList<SmsBeans> getUnprocessSmsList() {
        ArrayList<SmsBeans> incomeBeanses = new ArrayList<>();
        try {
            SQLiteDatabase db = MyApplication.dbHandler.getWritableDb();
            String query = "SELECT * FROM " + TABLE_ORIGINAL_MSG +
                    " WHERE " + COLUMN_IS_PROCESSED + " = '" + "0" + "'";
            Cursor cursor = db.rawQuery(query, null);
            if (null != cursor) {
                try {
                    if (cursor.moveToFirst()) {
                        while (!cursor.isAfterLast()) {
                            SmsBeans objSms = new SmsBeans();
                            int message_id = cursor.getInt(0);
                            String sender_id = cursor.getString(2);
                            String msg_date = cursor.getString(3);
                            String msg_body = cursor.getString(4);
                            objSms.setId(String.valueOf(message_id));
                            objSms.setAddress(sender_id);
                            objSms.setTime(msg_date);
                            objSms.setMsg(msg_body);
                            incomeBeanses.add(objSms);

                            cursor.moveToNext();
                        }
                    }
                } catch (Exception e) {
                    Log.d("W for file", e.getMessage().toString());
                } finally {
                    try {
                        if (!cursor.isClosed()) {
                            //  cursor.close();
                        }
                        cursor = null;
                    } catch (Exception e) {
                        Log.e("While closing cursor", e.getMessage().toString());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return incomeBeanses;

    }

    public static String getMessageBody(String messageId) {
        String smsBody = "";
       // DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase db = MyApplication.dbHandler.getWritableDb();
        try {
            String query = "SELECT " + COLUMN_MESSAGE_CONTENT + " FROM " + TABLE_ORIGINAL_MSG +
             //       " WHERE " + COLUMN_MESSAGE_ID + " = '" + messageId.substring(0, 5) + "'";
            " WHERE " + COLUMN_MESSAGE_ID + " = '" + messageId + "'";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        do {
                            try {
                                if (null != cursor.getString(0))
                                    smsBody = URLDecoder.decode(cursor.getString(0),"utf-8");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } while (cursor.moveToNext());
                    }
                } catch (Exception e) {
                    Log.d("W for file", e.getMessage().toString());
                } finally {
                    try {
                        if (!cursor.isClosed()) {
                          //  cursor.close();
                        }
                        cursor = null;
                    } catch (Exception e) {
                        Log.e("While closing cursor", e.getMessage().toString());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return smsBody;
    }

    public static String getMessageSender(String messageId) {
        String smsSender = "";
        //DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase db = MyApplication.dbHandler.getWritableDb();
        try {
            String query = "SELECT " + COLUMN_MESSAGE_SENDER + " FROM " + TABLE_ORIGINAL_MSG +
                  //  " WHERE " + COLUMN_MESSAGE_ID + " = '" + messageId.substring(0, 5) + "'";
            " WHERE " + COLUMN_MESSAGE_ID + " = '" + messageId + "'";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        do {
                            try {
                                if (null != cursor.getString(0))
                                    smsSender = cursor.getString(0);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } while (cursor.moveToNext());
                    }
                } catch (Exception e) {
                    Log.d("W for file", e.getMessage().toString());
                } finally {
                    try {
                        if (!cursor.isClosed()) {
                          //  cursor.close();
                        }
                        cursor = null;
                    } catch (Exception e) {
                        Log.e("While closing cursor", e.getMessage().toString());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return smsSender;
    }
    public static String getAccountProvider(String accountId) {
        String acProvider = "";
        //DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase db = MyApplication.dbHandler.getWritableDb();
        try {
            String query = "SELECT " + COLUMN_ACCOUNT_PROVIDER + " FROM " + TABLE_ACCOUNTS +
             " WHERE " + COLUMN_ACCOUNT_ID+ " = '" + accountId + "'";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        do {
                            try {
                                if (null != cursor.getString(0))
                                    acProvider = cursor.getString(0);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } while (cursor.moveToNext());
                    }
                } catch (Exception e) {
                    Log.d("W for file", e.getMessage().toString());
                } finally {
                    try {
                        if (!cursor.isClosed()) {
                            //  cursor.close();
                        }
                        cursor = null;
                    } catch (Exception e) {
                        Log.e("While closing cursor", e.getMessage().toString());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return acProvider;
    }
    public static String getMerchantName(String merchantId) {
        String merchantName = "";
       // DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase db = MyApplication.dbHandler.getWritableDb();
        try {
            String query = "SELECT " + COLUMN_MERCHANT_NAME + " FROM " + TABLE_MERCHANT +
                    " WHERE " + COLUMN_MERCHANT_ID + " = '" + merchantId + "'";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        do {
                            try {
                                if (null != cursor.getString(0))
                                    merchantName = cursor.getString(0);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } while (cursor.moveToNext());
                    }
                } catch (Exception e) {
                    Log.d("W for file", e.getMessage().toString());
                } finally {
                    try {
                        if (!cursor.isClosed()) {
                          //  cursor.close();
                        }
                        cursor = null;
                    } catch (Exception e) {
                        Log.e("While closing cursor", e.getMessage().toString());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return merchantName;
    }

    public static String getCategoryDetail(String categoryId) {
        String categoryDetail = "";
       // DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase db = MyApplication.dbHandler.getWritableDb();
        try {
            String query = "SELECT " + COLUMN_CATEGORY_DETAIL + " FROM " + TABLE_CATEGORY +
                    " WHERE " + COLUMN_CATEGORY_ID + " = '" + categoryId + "'";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        do {
                            try {
                                if (null != cursor.getString(0))
                                    categoryDetail = cursor.getString(0);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } while (cursor.moveToNext());
                    }
                } catch (Exception e) {
                    Log.d("W for file", e.getMessage().toString());
                } finally {
                    try {
                        if (!cursor.isClosed()) {
                         //   cursor.close();
                        }
                        cursor = null;
                    } catch (Exception e) {
                        Log.e("While closing cursor", e.getMessage().toString());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryDetail;
    }

    public static  String getAccountNumber(String accountId) {
        String accountNo = "";
        //DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
        SQLiteDatabase db = MyApplication.dbHandler.getWritableDb();
        try {
            String query = "SELECT " + COLUMN_ACCOUNT_NUMBER + " FROM " + TABLE_ACCOUNTS +
                    " WHERE " + COLUMN_ACCOUNT_ID + " = '" + accountId + "'";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        do {
                            try {
                                if (null != cursor.getString(0))
                                    accountNo = cursor.getString(0);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } while (cursor.moveToNext());

                    }
                } catch (Exception e) {
                    Log.d("W for file", e.getMessage().toString());
                } finally {
                    try {
                        if (!cursor.isClosed()) {
                          //  cursor.close();
                        }
                        cursor = null;
                    } catch (Exception e) {
                        Log.e("While closing cursor", e.getMessage().toString());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountNo;
    }


    public static String getHomeMonthSpend(String isSpent , String month) {
        String amountValue = "0 K";
        try {
         //   DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
            SQLiteDatabase db = MyApplication.dbHandler.getWritableDb();
            String query = "SELECT " + COLUMN_SUMMARY_MONTH + ", SUM(" + COLUMN_SUMMARY_AMOUNT + ") " +
                    "FROM " + TABLE_TRANSACTIONAL_SUMMARY +
                    " WHERE " + COLUMN_IS_SPENT + " = " + isSpent +
                    " AND " + COLUMN_SUMMARY_MONTH + " = '" + month + "'"+
                    " GROUP BY " + COLUMN_SUMMARY_MONTH;
            Cursor cursor = db.rawQuery(query, null);
            if (null != cursor) {
                try {
                    if (cursor.moveToFirst()) {
                        while (!cursor.isAfterLast()) {
                           // String title = cursor.getString(0);
                            amountValue = cursor.getString(1)+" K";
                            cursor.moveToNext();
                        }
                    }
                } catch (Exception e) {
                    Log.d("W for file", e.getMessage().toString());
                } finally {
                    try {
                        if (!cursor.isClosed()) {
                            //  cursor.close();
                        }
                        cursor = null;
                    } catch (Exception e) {
                        Log.e("While closing cursor", e.getMessage().toString());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return amountValue;
    }
}