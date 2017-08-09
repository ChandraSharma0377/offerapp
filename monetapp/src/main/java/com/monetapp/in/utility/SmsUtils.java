package com.monetapp.in.utility;


import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.monetapp.in.activities.MyApplication;
import com.monetapp.in.beans.SmsBeans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class SmsUtils {

    public static List<SmsBeans> readInboxSms(Activity context) {
        List<SmsBeans> lstSms = new ArrayList<SmsBeans>();
        Uri message = Uri.parse("content://sms/inbox");
        ContentResolver cr = context.getContentResolver();
        Cursor c = cr.query(message, null, null, null, null);
        context.startManagingCursor(c);
        int totalSMS = c.getCount();
        if (c.moveToFirst()) {
            for (int i = 0; i < totalSMS; i++) {
                SmsBeans objSms = new SmsBeans();
                objSms.setId(c.getString(c.getColumnIndexOrThrow("_id")));
                objSms.setAddress(c.getString(c
                        .getColumnIndexOrThrow("address")));
                try {
                    objSms.setMsg(URLEncoder.encode(c.getString(c.getColumnIndexOrThrow("body")),"utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                objSms.setReadState(c.getString(c.getColumnIndex("read")));
                objSms.setTime(c.getString(c.getColumnIndexOrThrow("date")));
                if (c.getString(c.getColumnIndexOrThrow("type")).contains("1")) {
                    objSms.setFolderName("inbox");
                } else {
                    objSms.setFolderName("sent");
                }
                lstSms.add(objSms);
                c.moveToNext();
            }
        }
        c.close();
        return lstSms;
    }

    public static String smsJsonString(List<SmsBeans> smsData){
        List<SmsBeans> smsBeansList = smsData;
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < smsBeansList.size(); i++){
            JSONObject jsonObject =new JSONObject();
            SmsBeans smsBeans = smsBeansList.get(i);
            try {
                jsonObject.put("customer_id", MyApplication.getMd5Mobile());
                jsonObject.put("sender_id", smsBeans.getAddress());
                jsonObject.put("message_id", smsBeans.getId());
                jsonObject.put("message_body", smsBeans.getMsg());
                jsonObject.put("message_timestamp", smsBeans.getTime());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return jsonArray.toString();
    }

    public static String smsJsonString(List<SmsBeans> smsData,int start ,int end){
        List<SmsBeans> smsBeansList = smsData;
        JSONArray jsonArray = new JSONArray();
        for (int i = start; i < end; i++){
            JSONObject jsonObject =new JSONObject();
            SmsBeans smsBeans = smsBeansList.get(i);
            try {
                jsonObject.put("customer_id", MyApplication.getMd5Mobile());
                jsonObject.put("sender_id", smsBeans.getAddress());
                jsonObject.put("message_id", smsBeans.getId());
                jsonObject.put("message_body", smsBeans.getMsg());
                jsonObject.put("message_timestamp", smsBeans.getTime());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return jsonArray.toString();
    }
}
