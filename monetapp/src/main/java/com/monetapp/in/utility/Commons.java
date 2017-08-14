package com.monetapp.in.utility;


import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.monetapp.in.activities.MyApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Commons {

    public static String KEY_IS_SPEND_SCREEN = "Screen name";
    public static final String PUSH_NOTIFICATION = "pushNotification";
    public static final int requestCodeNotification = 2;

    public static final String UPLOAD_SMS_URL = "http://monetapp.in/ws/";
    //"http://monetapp.in/ws/?message=";

    public static final int ACCOUNT_TYPE_BANK_ACCOUNT = 1;
    public static final int ACCOUNT_TYPE_DEBIT_CARD = 2;
    public static final int ACCOUNT_TYPE_CREDIT_CARD = 3;
    public static final int ACCOUNT_TYPE_WALLET = 4;
    public static final int ACCOUNT_TYPE_CASH= 5;

    public static final String TRANSACTION_TYPE_DEBIT = "Debit";
    public static final String TRANSACTION_TYPE_CREDIT= "Credit";

    public static final String INCOME = "0";
    public static final String SPEND= "1";

    public static String[] filter_data = { "Week", "Month",
           // "Quarterly",
            "Year"  };


    public static void exportDatabse(Context context,String databaseName) {
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "//data//"+context.getPackageName()+"//databases//"+databaseName+"";
                String backupDBPath = "backupname.db";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String convertFloattoMonth(float value){
        String output="";
        try{
            String val = String.valueOf(value);
            if(val.endsWith(".0"))
                val = val.replace(".0","");
            if(val.length()==1)
                output = "0"+val;
            else
                output = val;
        }catch(Exception e){
            e.printStackTrace();
        }
        return output;
    }

    public static String getCurrentMonth(){

        Calendar now = Calendar.getInstance();
        //
       // System.out.println("Current Year is : " + now.get(Calendar.YEAR));
        // month start from 0 to 11
        //System.out.println("Current Month is : " + (now.get(Calendar.MONTH) + 1));
        //System.out.println("Current Date is : " + now.get(Calendar.DATE));
        String month = String.valueOf(now.get(Calendar.MONTH) + 1);

        return getValueTwoDigit(month);
    }
    public static String getCurrentYear(){

        Calendar now = Calendar.getInstance();
        //
        // System.out.println("Current Year is : " + now.get(Calendar.YEAR));
        // month start from 0 to 11
        //System.out.println("Current Month is : " + (now.get(Calendar.MONTH) + 1));
        //System.out.println("Current Date is : " + now.get(Calendar.DATE));
        String month = String.valueOf(now.get(Calendar.YEAR));

        return getValueTwoDigit(month);
    }
    public static String getCurrentMonthName(int month) {

        String monthString = "";
        switch (month) {
            case 1:
                monthString = "Jan";
                break;
            case 2:
                monthString = "Feb";
                break;
            case 3:
                monthString = "Mar";
                break;
            case 4:
                monthString = "Apr";
                break;
            case 5:
                monthString = "May";
                break;
            case 6:
                monthString = "Jun";
                break;
            case 7:
                monthString = "Jul";
                break;
            case 8:
                monthString = "Aug";
                break;
            case 9:
                monthString = "Sep";
                break;
            case 10:
                monthString = "Oct";
                break;
            case 11:
                monthString = "Nov";
                break;
            case 12:
                monthString = "Dec";
                break;
            default:
                monthString = "Jan";
                break;
        }
        return monthString;
    }

    public static String getValueTwoDigit(String month){
        if(month.length()==1)
            month =  "0"+month;
        return month;
    }


    public  static String getFormattedDate(String inputDate){
        try {
            Long timestamp = Long.parseLong(inputDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timestamp);
            Date outputDate = calendar.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
            String formattedDate=formatter.format(outputDate);
            return formattedDate;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    public static void updateLastTime(String inputtime) {
        long msgTime = Long.valueOf(inputtime);
        long lastTime = MyApplication.getLastSMSProcessTime();
        if(lastTime == -1){
            MyApplication.setSharPreferanceTime(msgTime);
        }else{
            if( Long.valueOf(msgTime).compareTo(Long.valueOf(lastTime)) > 0){
                MyApplication.setSharPreferanceTime(msgTime);
            }
        }


    }
}
