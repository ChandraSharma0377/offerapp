package com.monetapp.in.activities;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.monetapp.in.database.DBOpenHelperClass;
import com.monetapp.in.utility.FontsOverride;
import com.monetapp.in.utility.Helper;


public class MyApplication extends Application {

    private String fontName1 = "helvetica_neue.ttf";
    private String fontName = "helvetica_neue_light.ttf";
    public static final String MyPREFERENCES = "AppPref";
    private static SharedPreferences sharedpreferences;
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String KEY_MOBILE = "mobileNo";
    private static final String KEY_USER_NAME = "userName";
    private static final String KEY_EMAIL_ID = "emailID";
    private static final String KEY_MD5_MOBILE_ID = "mobileMd5";

    private static final String KEY_LAST_MSG_UPDATE_TIME = "lastSMSdbUpdateTime";
    public  static DBOpenHelperClass dbHandler;
    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "DEFAULT", fontName);
        FontsOverride.setDefaultFont(this, "MONOSPACE", fontName);
        FontsOverride.setDefaultFont(this, "SERIF", fontName);
        FontsOverride.setDefaultFont(this, "SANS_SERIF", fontName);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        dbHandler =  DBOpenHelperClass.getSharedObject(getApplicationContext());
    }

    public static SharedPreferences getSharePreferance() {
        return sharedpreferences;
    }

    public static String getUserName() {
        return sharedpreferences.getString(KEY_USER_NAME, "");
    }

    public static String getEmailID() {
        return sharedpreferences.getString(KEY_EMAIL_ID, "");
    }
    public static String getMobileNo() {
        return sharedpreferences.getString(KEY_MOBILE, "");
    }

    public static long getLastSMSProcessTime() {
        return sharedpreferences.getLong(KEY_LAST_MSG_UPDATE_TIME, -1);
    }
    public static boolean isLoggedIn() {
        return sharedpreferences.getBoolean(IS_LOGIN, false);
    }

    public static void setSharPreferancename(String userName, String emailID, String mobileNo, boolean isLogin) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(KEY_USER_NAME, userName);
        editor.putString(KEY_EMAIL_ID, emailID);
        editor.putString(KEY_MOBILE, mobileNo);
        editor.putBoolean(IS_LOGIN, isLogin);
        editor.putString(KEY_MD5_MOBILE_ID, Helper.md5(mobileNo));
        editor.commit();
    }
    public static void setSharPreferanceTime(long time) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putLong(KEY_LAST_MSG_UPDATE_TIME, time);
        editor.commit();
    }
    public static String getMd5Mobile() {
        return sharedpreferences.getString(KEY_MD5_MOBILE_ID, Helper.md5(getMobileNo()));
    }
}
