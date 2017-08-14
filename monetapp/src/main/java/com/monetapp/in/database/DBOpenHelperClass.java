package com.monetapp.in.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBOpenHelperClass extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "monetapp.db";
    private static final int DATABASE_VERSION = 1;

    private static DBOpenHelperClass instance;
    public Context context;
    private SQLiteDatabase db = null;

    public static synchronized  DBOpenHelperClass getSharedObject(Context context) {
        if (instance == null) {
            instance = new DBOpenHelperClass(context);
        }
        instance.context = context;
        return instance;
    }

    private DBOpenHelperClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public SQLiteDatabase getWritableDb() {
        if (null == db) {
            Log.d("DBOpenHandler", "db is NULL, re-initialized");
            db = instance.getWritableDatabase();
        }
        return db;
    }
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
             DataHelperClass.createAllTable(db);
        } catch (Exception ex) {
            Log.d("DBException", ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
