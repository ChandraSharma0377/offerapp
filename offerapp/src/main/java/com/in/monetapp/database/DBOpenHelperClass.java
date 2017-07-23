package com.in.monetapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBOpenHelperClass extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "monetapp";
    private static final int DATABASE_VERSION = 1;

    private static DBOpenHelperClass instance;
    public Context context;


    public static DBOpenHelperClass getSharedObject(Context context) {
        if (instance == null) {
            instance = new DBOpenHelperClass(context);
        }
        instance.context = context;
        return instance;
    }

    public DBOpenHelperClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase sqdb = super.getWritableDatabase();
        return sqdb;
    }

    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            new DataHelperClass(context).createAllTable(db);
        } catch (Exception ex) {
            Log.d("DBException", ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
