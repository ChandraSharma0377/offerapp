package com.monetapp.in.utility;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Calendar;


public class Helper {




    public static void shareApp(Context context){
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "Coupon App");
            String sAux = "\nLet me recommend you this application\n\n";
            sAux = sAux + "https://play.google.com/store/apps/details?id=com.google.android.youtube \n\n";
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            context.startActivity(Intent.createChooser(i, "choose one"));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void rateApp(Context context){
        Uri uri = Uri.parse("market://details?id=" +"com.google.android.youtube");
                //context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    public static String formatRupee(String amount) {
        try {
            DecimalFormat formatter = new DecimalFormat("#,###,###");
            return  formatter.format(Integer.parseInt(amount));
           // return NumberFormat.getInstance().format(Integer.parseInt(amount));
        } catch (Exception e) {
            e.printStackTrace();
            return amount;
        }
    }

    public static String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static  String getWeek(String month,String date,String year){
        try {
            Calendar now = Calendar.getInstance();
            now.set(Calendar.YEAR, Integer.parseInt(year));
            now.set(Calendar.MONTH, Integer.parseInt(month));
            now.set(Calendar.DATE, Integer.parseInt(date));
            return String.valueOf(now.get(Calendar.WEEK_OF_MONTH));
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
