package com.in.monetapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.in.monetapp.beans.SmsBeans;

import java.util.List;

public class DataHelperClass {
	private Context mContext;

	private final String TEXT_TYPE = " TEXT";
	private final String COMMA_SEP = ", ";
	private final String INT_TYPE = " INT";
	private final String DOUBLE_TYPE = " REAL";

	// Table
	public final String TABLE_MERCHANT_SUMMARY = "merchant_summary";
	public final String TABLE_TRANSACTIONAL_SUMMARY = "transactional_summary";
	public final String TABLE_ACCOUNTS = "accounts";
	public final String TABLE_TRANSACTIONS = "transactions";
	public final String TABLE_MERCHANT = "merchant";
	public final String TABLE_OFFERS = "offers";
	public final String TABLE_GOALS = "goals";
	public final String TABLE_REMINDER = "reminders";
	public final String TABLE_CATEGORY_SUMMARY = "category_summary";
	public final String TABLE_ORIGINAL_MSG = "original_messages";
	public final String TABLE_CATEGORY = "category";

	// Column
	public final String COLUMN_CATEGORY_DETAIL = "category_detail";
	public final String COLUMN_CATEGORY_ICON = "category_icon";

	public final String COLUMN_MESSAGE_ID = "message_id";
	public final String COLUMN_MESSAGE_SENDER = "message_sender";
	public final String COLUMN_MESSAGE_DATE = "message_date";
	public final String COLUMN_MESSAGE_CONTENT = "message_content";
	public final String COLUMN_IS_PROCESSED = "is_processed";
	public final String COLUMN_IS_DELETED = "is_deleted";


	public final String COLUMN_CATEGORY__SUMMARY_ID = "category_summary_id";

	public final String COLUMN_REMINDER_ID = "reminder_id";
	public final String COLUMN_REMINDER_DUE_DATE = "reminder_due_date";
	public final String COLUMN_REMINDER_COMPLETED_DATE = "reminder_completed_date";
	public final String COLUMN_REMINDER_TYPE = "reminder_type";
	public final String COLUMN_REMINDER_TYPE_ID = "reminder_type_id";
	public final String COLUMN_REMINDER_DETAILS = "reminder_details";


	public final String COLUMN_GOAL_ID = "goal_id";
	public final String COLUMN_GOAL_TYPE = "goal_type";
	public final String COLUMN_START_DATE = "start_date";
	public final String COLUMN_END_DATE = "end_date";
	public final String COLUMN_TARGET_AMOUNT = "target_amount";
	public final String COLUMN_TARGET_ACHIEVED = "target_achieved";
	public final String COLUMN_GOAL_COMPLETED = "goal_completed";


	public final String COLUMN_OFFER_ID = "offer_id";
	public final String COLUMN_OFFER_DETAILS = "offer_details";
	public final String COLUMN_OFFER_START_DATE = "offer_start_date";
	public final String COLUMN_OFFER_END_DATE = "offer_end_date";
	public final String COLUMN_IS_ACTIVE = "is_active";


	public final String COLUMN_MERCHANT_SUMMARY_ID = "merchant_summary_id";
	public final String COLUMN_CUSTOMER_ID = "customer_id";
	public final String COLUMN_SUMMARY_MONTH = "summary_month";
	public final String COLUMN_SUMMARY_YEAR = "summary_year";
	public final String COLUMN_SUMMARY_AMOUNT = "summary_amount";
	public final String COLUMN_TRANSACTIONAL_SUMMARY_ID = "transactional_summary_id";
	public final String COLUMN_MERCHANT_ID = "merchant_id";

	public final String COLUMN_ACCOUNT_ID = "account_id";
	public final String COLUMN_ACCOUNT_NUMBER = "account_number";
	public final String COLUMN_ACCOUNT_PROVIDER = "account_provider";
	public final String COLUMN_ACCOUNT_BALANCE = "account_balance";
	public final String COLUMN_ACCOUNT_SPENT = "account_spent";
	public final String COLUMN_ACCOUNT_TYPE_ID = "account_type_id";
	public final String COLUMN_LAST_UPDATED = "last_updated";


	public final String COLUMN_TRANSACTION_ID = "transaction_id";
	public final String COLUMN_TRANSACTION_TYPE = "transaction_type";
	public final String COLUMN_AMOUNT = "amount";
	public final String COLUMN_DATE = "date";
	public final String COLUMN_INDUSTRY_ID = "industry_id";
	public final String COLUMN_CATEGORY_ID = "category_id";
	public final String COLUMN_ORIGINAL_MESSAGE_ID = "original_message_id";
	public final String COLUMN_IS_EXPENSE = "is_expense";
	public final String COLUMN_TRANSACTION_DETAILS = "transactions_details";


	public final String COLUMN_MERCHANT_NAME = "merchant_name";
	public final String COLUMN_MERCHANT_DEATIL = "merchant_detail";

	private final String CREATE_TABLE_TRANSACTIONAL_SUMMARY = "CREATE TABLE " + TABLE_TRANSACTIONAL_SUMMARY + " ( "
			+ COLUMN_TRANSACTIONAL_SUMMARY_ID + " INTEGER PRIMARY KEY NOT NULL, "
			+ COLUMN_CUSTOMER_ID + INT_TYPE + COMMA_SEP
			+ COLUMN_SUMMARY_MONTH + TEXT_TYPE + COMMA_SEP
			+ COLUMN_SUMMARY_YEAR + TEXT_TYPE + COMMA_SEP
			+ COLUMN_SUMMARY_AMOUNT + DOUBLE_TYPE + " );";

	private final String CREATE_TABLE_MERCHANT_SUMMARY = "CREATE TABLE " + TABLE_MERCHANT_SUMMARY + " ( "
			+ COLUMN_MERCHANT_SUMMARY_ID + " INTEGER PRIMARY KEY NOT NULL, "
			+ COLUMN_CUSTOMER_ID + INT_TYPE + COMMA_SEP
			+ COLUMN_SUMMARY_MONTH + TEXT_TYPE + COMMA_SEP
			+ COLUMN_SUMMARY_YEAR + TEXT_TYPE + COMMA_SEP
			+ COLUMN_SUMMARY_AMOUNT + DOUBLE_TYPE + COMMA_SEP
			+ COLUMN_TRANSACTIONAL_SUMMARY_ID + INT_TYPE + COMMA_SEP
			+ COLUMN_MERCHANT_ID + INT_TYPE + " );";


	private final String CREATE_TABLE_ACCOUNTS = "CREATE TABLE " + TABLE_ACCOUNTS + " ( "
			+ COLUMN_ACCOUNT_ID + " INTEGER PRIMARY KEY NOT NULL, "
			+ COLUMN_CUSTOMER_ID + INT_TYPE + COMMA_SEP
			+ COLUMN_ACCOUNT_TYPE_ID + INT_TYPE + COMMA_SEP
			+ COLUMN_ACCOUNT_NUMBER + TEXT_TYPE + COMMA_SEP
			+ COLUMN_ACCOUNT_PROVIDER + TEXT_TYPE + COMMA_SEP
			+ COLUMN_ACCOUNT_BALANCE + DOUBLE_TYPE + COMMA_SEP
			+ COLUMN_ACCOUNT_SPENT + DOUBLE_TYPE + COMMA_SEP
			+ COLUMN_LAST_UPDATED + TEXT_TYPE + " );";

	private final String CREATE_TABLE_TRANSACTIONS = "CREATE TABLE " + TABLE_TRANSACTIONS + " ( "
			+ COLUMN_TRANSACTION_ID + " INTEGER PRIMARY KEY NOT NULL, "
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


	private final String CREATE_TABLE_MERCHANT = "CREATE TABLE " + TABLE_MERCHANT + " ( "
			+ COLUMN_MERCHANT_ID + " INTEGER PRIMARY KEY NOT NULL, "
			+ COLUMN_INDUSTRY_ID + INT_TYPE + COMMA_SEP
			+ COLUMN_MERCHANT_NAME + TEXT_TYPE + COMMA_SEP
			+ COLUMN_MERCHANT_DEATIL + TEXT_TYPE + " );";

	private final String CREATE_TABLE_OFFERS = "CREATE TABLE " + TABLE_OFFERS + " ( "
			+ COLUMN_OFFER_ID + " INTEGER PRIMARY KEY NOT NULL, "
			+ COLUMN_CUSTOMER_ID + INT_TYPE + COMMA_SEP
			+ COLUMN_OFFER_DETAILS + TEXT_TYPE + COMMA_SEP
			+ COLUMN_OFFER_START_DATE + DOUBLE_TYPE + COMMA_SEP
			+ COLUMN_OFFER_END_DATE + TEXT_TYPE + COMMA_SEP
			+ COLUMN_IS_ACTIVE + INT_TYPE + COMMA_SEP
			+ COLUMN_MERCHANT_ID + INT_TYPE + " );";

	private final String CREATE_TABLE_GOALS = "CREATE TABLE " + TABLE_GOALS + " ( "
			+ COLUMN_GOAL_ID + " INTEGER PRIMARY KEY NOT NULL, "
			+ COLUMN_CUSTOMER_ID + INT_TYPE + COMMA_SEP
			+ COLUMN_GOAL_TYPE + INT_TYPE + COMMA_SEP
			+ COLUMN_START_DATE + DOUBLE_TYPE + COMMA_SEP
			+ COLUMN_END_DATE + TEXT_TYPE + COMMA_SEP
			+ COLUMN_TARGET_AMOUNT + DOUBLE_TYPE + COMMA_SEP
			+ COLUMN_TARGET_ACHIEVED + DOUBLE_TYPE + COMMA_SEP
			+ COLUMN_GOAL_COMPLETED + INT_TYPE + COMMA_SEP
			+ COLUMN_LAST_UPDATED + TEXT_TYPE + " );";

	private final String CREATE_TABLE_REMINDER = "CREATE TABLE " + TABLE_REMINDER + " ( "
			+ COLUMN_REMINDER_ID + " INTEGER PRIMARY KEY NOT NULL, "
			+ COLUMN_CUSTOMER_ID + INT_TYPE + COMMA_SEP
			+ COLUMN_REMINDER_DUE_DATE + TEXT_TYPE + COMMA_SEP
			+ COLUMN_IS_ACTIVE + INT_TYPE + COMMA_SEP
			+ COLUMN_REMINDER_COMPLETED_DATE + TEXT_TYPE + COMMA_SEP
			+ COLUMN_REMINDER_TYPE + INT_TYPE + COMMA_SEP
			+ COLUMN_REMINDER_TYPE_ID + TEXT_TYPE + COMMA_SEP
			+ COLUMN_REMINDER_DETAILS + TEXT_TYPE + " );";

	private final String CREATE_TABLE_CATEGORY_SUMMARY = "CREATE TABLE " + TABLE_CATEGORY_SUMMARY + " ( "
			+ COLUMN_CATEGORY__SUMMARY_ID + " INTEGER PRIMARY KEY NOT NULL, "
			+ COLUMN_CUSTOMER_ID + INT_TYPE + COMMA_SEP
			+ COLUMN_SUMMARY_MONTH + TEXT_TYPE + COMMA_SEP
			+ COLUMN_SUMMARY_YEAR + TEXT_TYPE + COMMA_SEP
			+ COLUMN_SUMMARY_AMOUNT + DOUBLE_TYPE + COMMA_SEP
			+ COLUMN_CATEGORY_ID + INT_TYPE + COMMA_SEP
			+ COLUMN_TRANSACTIONAL_SUMMARY_ID + INT_TYPE + " );";

	private final String CREATE_TABLE_ORIGINAL_MSG = "CREATE TABLE " + TABLE_ORIGINAL_MSG + " ( "
			+ COLUMN_MESSAGE_ID + " INTEGER PRIMARY KEY NOT NULL, "
			+ COLUMN_CUSTOMER_ID + INT_TYPE + COMMA_SEP
			+ COLUMN_MESSAGE_SENDER + TEXT_TYPE + COMMA_SEP
			+ COLUMN_MESSAGE_DATE + TEXT_TYPE + COMMA_SEP
			+ COLUMN_MESSAGE_CONTENT + TEXT_TYPE + COMMA_SEP
			+ COLUMN_IS_PROCESSED + INT_TYPE + COMMA_SEP
			+ COLUMN_IS_DELETED + INT_TYPE + " );";

	private final String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY + " ( "
			+ COLUMN_CATEGORY_ID + " INTEGER PRIMARY KEY NOT NULL, "
			+ COLUMN_CATEGORY_DETAIL + TEXT_TYPE + COMMA_SEP
			+ COLUMN_CATEGORY_ICON + INT_TYPE + " );";
	public DataHelperClass(Context con) {
		mContext = con;
	}

	public void createAllTable(SQLiteDatabase db) {
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
		} catch (Exception ex) {
			Log.d("DBException", ex.getMessage());
		}

	}

	public boolean insertAllSms(List<SmsBeans> smsBeansList) {
		DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
		SQLiteDatabase SQDB = DBOHC.getWritableDatabase();

		for (int i =0; i< smsBeansList.size();i++) {
			ContentValues values = new ContentValues();
			SmsBeans smsBeans = smsBeansList.get(i);
			values.put(COLUMN_MESSAGE_ID, Integer.parseInt(smsBeans.getId()));
			values.put(COLUMN_CUSTOMER_ID, 123);
			values.put(COLUMN_MESSAGE_SENDER, smsBeans.getAddress());
			values.put(COLUMN_MESSAGE_DATE, smsBeans.getTime());
			values.put(COLUMN_MESSAGE_CONTENT, smsBeans.getMsg());
			values.put(COLUMN_IS_PROCESSED, 0);
			values.put(COLUMN_IS_DELETED, 0);


			SQDB.beginTransaction();
			try {
				SQDB.insert(TABLE_ORIGINAL_MSG, null, values);
			} catch (Exception e) {
				e.printStackTrace();
			}

			SQDB.setTransactionSuccessful();
			SQDB.endTransaction();
		}
		return  true;
	}
//
//
//	public ArrayList<ComplaintDetailDto> getUserData() {
//
//		{
//			DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
//			SQLiteDatabase SQDB = DBOHC.getWritableDatabase();
//			ArrayList<ComplaintDetailDto> userList = new ArrayList<ComplaintDetailDto>();
//			String myQuery = "SELECT  * FROM "+DBOHC.TABLE_USER_DETAIL;
//					//+ " ORDER BY NOTIFICATION_ID DESC";
//			try {
//				Cursor cursor = SQDB.rawQuery(myQuery, null);
//				if (cursor != null) {
//					if (cursor.moveToFirst()) {
//						do {
//							try {
//								ComplaintDetailDto cpdetails = new ComplaintDetailDto();
//								cpdetails.setCallID(cursor.getString(0));
//								cpdetails.setCustomerID(cursor.getString(1));
//								cpdetails.setCallType(cursor.getString(2));
//								cpdetails.setComplaintDetails(cursor.getString(3));
//								cpdetails.setCallLoginTime(cursor.getString(4));
//								cpdetails.setCallLoginDate(cursor.getString(5));
//								cpdetails.setStatus(cursor.getString(6));
//								cpdetails.setFeedBack(cursor.getString(7));
//								cpdetails.setContactPerson(cursor.getString(8));
//								cpdetails.setDescription(cursor.getString(9));
//								cpdetails.setCustomerName(cursor.getString(10));
//								cpdetails.setCustomerAddress(cursor.getString(11));
//								cpdetails.setCustomerCellNo(cursor.getString(12));
//								cpdetails.setCustomerLocation(cursor.getString(13));
//								cpdetails.setEmpName(cursor.getString(14));
//								userList.add(cpdetails);
//							} catch (Exception e) {
//								Log.d("DB_EXCEPTION" + "OBJ_NOT : ", e.getMessage());
//							}
//						} while (cursor.moveToNext());
//					}
//				}
//				cursor.close();
//				// SQDB.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			return userList;
//		}
//
//	}
	public boolean isRecordExist(String CALLID) {
		boolean isexit = false;
		String selectQuery = "SELECT * FROM OBJ_ART WHERE CALL_ID = " + CALLID;
		DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
		SQLiteDatabase db = DBOHC.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		isexit = cursor.getCount() > 0 ? true : false;
		if (null != cursor)
			cursor.close();

		return isexit;

	}
	
	public void clearRecord(){
//		DBOpenHelperClass DBOHC = DBOpenHelperClass.getSharedObject(mContext);
//		SQLiteDatabase db = DBOHC.getWritableDatabase();
//		db.delete(DBOHC.TABLE_USER_DETAIL,null,null);
//	    db.close();
	}
}
