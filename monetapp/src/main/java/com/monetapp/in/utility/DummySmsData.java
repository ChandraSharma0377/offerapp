package com.monetapp.in.utility;

import com.monetapp.in.activities.MyApplication;
import com.monetapp.in.beans.SmsBeans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DummySmsData {


    public static List<SmsBeans> smsData() {
        List<SmsBeans> smsBeansList = new ArrayList<>();
        //String id, String address, String msg, String readState, String time, String folderName) {
//        smsBeansList.add(new SmsBeans(
//                "17101", "AD-FAASOS", "Saturday night blues?  a Place your order from Faasos - Buy 1 Get 1 free on Upvaas Specials & more - Code: BACK  - www.faasos.com - serving beyond midnight*", "0", "1501330611901", "inbox"));
//        smsBeansList.add(new SmsBeans(
//                "16196", "MD-JITIME", "Time is ticking ! a Choose ur exclusive timepiece @ upto 50% OFF from our wide range of brands. Visit JUST IN TIME stores now. Valid till 31st July. T&C.", "0", "1501305116503", "inbox"));
//        smsBeansList.add(new SmsBeans(
//                "16194", "650001", "63% discount! a Banayein Mere Rashke Qamar, Munna Michael film ke gaano ko Hello Tune, Airtel se call 578785(nishulk).IND v SL Cricket update, call 56789(nishulk)", "0", "1501235277893", "inbox"));
//        smsBeansList.add(new SmsBeans(
//                "16193", "685587", "Olive Trails a introduces Salad Subscription. Have your fill of healthy with salads starting at Rs.140/-. Order from Faasos App @ bit.ly/AppFaasos", "0", "1501228178715", "inbox"));
//        smsBeansList.add(new SmsBeans(
//                "16192", "650007", "Rs146=UNLIMITED a Local+STD Airtel call+2GB, 28 din.\n\nRs349=UNLIMITED Loc+STD call+28GB(1GB/din-4G H/S+4G SIM par) 28 din.\n\nShartein lagu\nClick Airtel.in/5/m", "0", "1501226783039", "inbox"));
//        smsBeansList.add(new SmsBeans(
//                "16191", "HP-LMSYST", "Retire RICH with a 1 CRORE by Investing Just Rs.4300pm. Also save tax U/S 80C. For FREE Pension Planning & TAX Advice leave a MISSED Call NOW on +912261816111", "0", "1501217143128", "inbox"));
//        smsBeansList.add(new SmsBeans(
//                "16190", "AM-PVRCIN", "The Laugh Riot a begins at PVR!  Book now for Mubarakan and use \"PVRNEW50\" code to get flat 50 off on PVR website/app. TCA. http://nmc.sg/JoPipn", "0", "1501207625775", "inbox"));
        smsBeansList.add(new SmsBeans(
                "16189", "AD-KOTAKB", "Cash withdrawal a of Rs.1000 made on Kotak Debit card XX8002 on 25-06-2017 21:42:44 at 900006.Combined balance in A/c XX7337 is Rs 24047.12.", "1", "1501171969245", "inbox"));
//        smsBeansList.add(new SmsBeans(
//                "16186", "650007", "Rs146=UNLIMITED a Local+STD Airtel call+2GB, 28 din.\n\nRs349=UNLIMITED Loc+STD call+28GB(1GB/din-4G H/S+4G SIM par) 28 din.\n\nShartein lagu\nClick Airtel.in/5/m", "1", "1501131683539", "inbox"));
//        smsBeansList.add(new SmsBeans(
//                "16161", "ERecharge", "Recharge done a on 22-07-2017 at 08:35PM,MRP: Rs 100.00, PF: Rs0.00,GST 18% payable by Company/Distributor/Retailer:Rs15.25,Talktime: RS100.00,Balance: Rs100.44 TransID 11325159", "1", "1500735972173", "inbox"));
//        smsBeansList.add(new SmsBeans(
//                "16152", "DM-IRCTCi", "PNR:2334372426,TRAIN:11060,DOJ:18-09-17,3A,BLTR-LTT,Dep:08:17,\nDEEPAK,B1 54,\nFare:1690,SC:0.0+PG+INS\n", "0", "12344564578", "inbox"));
        smsBeansList.add(new SmsBeans(
                "16151", "VK-ICICIB", "Dear Customer, a Purchase of INR 1,724.90 has been made on Debit Card linked to Acct XX6573 on 20-Jul-17. Info: VPS*mVISA*IRCTC. The Available Balance is INR 17,434.95.", "1", "1500532867239", "inbox"));
//        smsBeansList.add(new SmsBeans(
//                "16142", "AM-JIOMNY", "Dear Customer,a  Now is the time to redeem your Rs. 50 Jio voucher! Use it on your next recharge of Rs.309 or more using JioMoney. Click here bit.ly/2tiDb2a", "1", "1500369787668", "inbox"));
        smsBeansList.add(new SmsBeans(
                "16133", "AD-ICICIB", "Dear Customer, a  your Account XX6573 has been debited with INR 22,000.00 on 13-05-17. Info: ATM*CASH WDL*13-05-17*0. The Available Balance is INR 21,266.87.", "1", "1499921061125", "inbox"));
        smsBeansList.add(new SmsBeans(
                "16134", "AD-ICICIB", "Dear Customer, a  your Account XX6573 has been debited with INR 22,000.00 on 14-04-17. Info: ATM*CASH WDL*14-04-17*0. The Available Balance is INR 21,266.87.", "1", "1499921061125", "inbox"));
        smsBeansList.add(new SmsBeans(
                "16135", "AD-ICICIB", "Dear Customer, a  your Account XX6573 has been debited with INR 22,000.00 on 15-02-17. Info: ATM*CASH WDL*15-05-17*0. The Available Balance is INR 21,266.87.", "1", "1499921061125", "inbox"));

        smsBeansList.add(new SmsBeans(
                "1724", "VK-ICICIB", "Dear Customer, your Account XX6573 has been credited with deposit of INR 15,505.00 on 31-Jul-17. Info: NEFT-1707317. The Available Balance is INR 46,054.95.",
                "1","1501506852273", "inbox"));

        smsBeansList.add(new SmsBeans(
                "17241", "VK-ICICIB", "Dear Customer, your Account XX6573 has been credited with deposit of INR 56,505.00 on 31-Jul-17. Info: NEFT-1707317. The Available Balance is INR 46,054.95.",
                "1","15015022152273", "inbox"));

        smsBeansList.add(new SmsBeans(
                "17242", "VK-ICICIB", "Dear Customer, your Account XX6573 has been credited with deposit of INR 12,505.00 on 06-Aug-17. Info: NEFT-1707517. The Available Balance is INR 20,054.95.",
                "1","15015022132273", "inbox"));

        smsBeansList.add(new SmsBeans(
                "16296", "AD-ICICIB", "Dear Customer, a  your Account XX6573 has been debited with INR 11,000.00 on 06-08-17. Info: ATM*CASH WDL*06-08-17*0. The Available Balance is INR 11,266.87.", "1", "1499921561125", "inbox"));

        return smsBeansList;
    }

    public static String smsJsonString(List<SmsBeans> smsBeansList ){
      //  List<SmsBeans> smsBeansList = smsData();
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < smsBeansList.size() ; i++){
            JSONObject jsonObject =new JSONObject();
            SmsBeans smsBeans = smsBeansList.get(i);
            try {
                jsonObject.put("customer_id", MyApplication.getMd5Mobile());
                jsonObject.put("sender_id", smsBeans.getAddress());
                jsonObject.put("message_id", smsBeans.getId());//+"040"+""+i);
                jsonObject.put("message_body", smsBeans.getMsg());//+" cem "+""+i);
                jsonObject.put("message_timestamp", smsBeans.getTime());//+"107");
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return jsonArray.toString();
    }
    public static String smsJsonString(List<SmsBeans> smsBeansList ,int start ,int end){
        //  List<SmsBeans> smsBeansList = smsData();
        JSONArray jsonArray = new JSONArray();
        for (int i = start; i < end; i++){
            JSONObject jsonObject =new JSONObject();
            SmsBeans smsBeans = smsBeansList.get(i);
            try {
                jsonObject.put("customer_id", MyApplication.getMd5Mobile());
                jsonObject.put("sender_id", smsBeans.getAddress());
                jsonObject.put("message_id", smsBeans.getId());//+"040"+""+i);
                jsonObject.put("message_body", smsBeans.getMsg());//+" cem "+""+i);
                jsonObject.put("message_timestamp", smsBeans.getTime());//+"107");
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return jsonArray.toString();
    }
}
