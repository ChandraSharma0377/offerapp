package com.monetapp.in.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dummy.R;
import com.monetapp.in.asynctasks.AsyncProcessTemp;
import com.monetapp.in.beans.GenericBeans;
import com.monetapp.in.beans.SmsBeans;
import com.monetapp.in.database.DBOpenHelperClass;
import com.monetapp.in.database.DataHelperClass;
import com.monetapp.in.utility.Commons;
import com.monetapp.in.utility.DummySmsData;
import com.monetapp.in.utility.NetworkHelper;
import com.monetapp.in.utility.ShowAlertInformation;
import com.monetapp.in.utility.SmsUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class SplashScreen extends AppCompatActivity {

    protected boolean isAactive = true;
    protected int splashTime = 2000;
    protected static boolean display = true;
    static SplashScreen splashScreen;
    private final int SMS_PERMISSION_CODE = 100;
    private static final int REQUEST_WRITE_STORAGE = 112;
    private ProgressBar progressBar;
    private TextView myTextProgress;
    public SplashHandler mHandler;
    private int start = 0;
    private int end = 0;
    private int slab = 20;
    private List<SmsBeans> uploadList;
    private boolean isFirstTimeErrorShown = true;
    private boolean hasPermission = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //	getSupportActionBar().hide();
        mHandler = new SplashHandler();
        splashScreen = this;
        if (!MyApplication.isLoggedIn()) {
            Intent i = new Intent(splashScreen, MainActivity.class);
            splashScreen.startActivity(i);
            splashScreen.finish();
        } else {
            setContentView(R.layout.splash);
            progressBar = (ProgressBar) findViewById(R.id.progressBar);
            myTextProgress = (TextView) findViewById(R.id.myTextProgress);
            hasPermission = (ContextCompat.checkSelfPermission(splashScreen,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
            Message msg = new Message();
            msg.what = 0;
            //mHandler.sendMessageDelayed(msg, splashTime); // 2 sec delay
            if (!hasPermission) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_STORAGE);

            }
            if (isReadWriteSMSPermissionAllowed()) {
                proceedAfterPermission();
            } else {
                requestReadWriteSMSPermission();
            }
        }
    }

    public void requestReadWriteSMSPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS) ||
                ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
            //Provide an additional info to the user if the permission was not granted
        }
        //External storage has not been granted yet. Request it directly.
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);

    }

    public boolean isReadWriteSMSPermissionAllowed() {
        //Getting the permission status
        int result_read = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
        int result_write = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        //If permission is granted returning true
        if (result_read == PackageManager.PERMISSION_GRANTED && result_write == PackageManager.PERMISSION_GRANTED)
            return true;
        //If permission is not granted returning false
        return false;
    }

    private class SplashHandler extends Handler {

        // This method is used to handle received messages
        public void handleMessage(Message msg) {
            // switch to identify the message by its code
            switch (msg.what) {
                default:
                case 0:
                    super.handleMessage(msg);
                    if (display) {
                        Intent i = new Intent(splashScreen, MainActivity.class);
                        splashScreen.startActivity(i);
                        splashScreen.finish();
                    } else {
                        splashScreen.finish();
                    }
                    break;
                case 1:
                   // loadData();
                    Commons.exportDatabse(splashScreen, DBOpenHelperClass.DATABASE_NAME);
                    progressBar.setVisibility(View.INVISIBLE);
                    myTextProgress.setText( "sms Inserted in database");
                    Toast.makeText(splashScreen,"Check the database now",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            isAactive = false;
            display = false;
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    private class UploadSmsTask extends AsyncProcessTemp {

        List<SmsBeans> smsBeansList;

        public UploadSmsTask(HashMap<String, String> postDataParams, List<SmsBeans> smsBeansList) {
            super(postDataParams);
            this.smsBeansList = smsBeansList;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            return super.doInBackground(params);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (200 == responseCode) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jo = jsonArray.getJSONObject(i);
                        try {

//                        "message_id": "1618970",
//                                "customer_id": "13e51f663d67b866c05babbc7455e862",
//                                "transaction_type": "Debit",
//                                "amount": "1000",
//                                "account_provider": "",
//                                "account_number": "xx8002",
//                                "date": "27\/07\/2017",
//                                "account_type": "Credit Card",
//                                "account_balance": "",
//                                "merchant_id": "",
//                                "merchant": "900006.combined balance in a\/c xx7337 is rs 24047.12.welcome0",
//                                "industry_id": "",
//                                "industry_details": "",
//                                "category_id": "",
//                                "category_details": "",
//                                "processed": 1
                            String transaction_type = jo.getString("transaction_type");
                            if (transaction_type.equals("Debit") || transaction_type.equals("Credit")) {
                                String message_id = jo.getString("message_id");
                                String customer_id = jo.getString("customer_id");
                                String account_provider = jo.getString("account_provider");
                                String account_balance = jo.getString("account_balance");
                                String merchant_id = jo.getString("merchant_id");
                                String industry_id = jo.getString("industry_id");

                                String industry_details = jo.getString("industry_details");
                                String category_id = jo.getString("category_id");
                                String category_details = jo.getString("category_details");
                                String processed = jo.getString("processed");
                                // String transaction_type = jo.getString("transaction_type");
                                String amount = jo.getString("amount");
                                String account_number = jo.getString("account_number");
                                String merchant = jo.getString("merchant");
                                String date = jo.getString("date");
                                String account_type = jo.getString("account_type");

                                GenericBeans genericBeans = new GenericBeans();
                                genericBeans.setMessage_id(message_id);
                                genericBeans.setCustomer_id(customer_id);
                                genericBeans.setAccount_provider(account_provider);
                                genericBeans.setAccount_balance(account_balance);
                                genericBeans.setMerchant_id(merchant_id);
                                genericBeans.setIndustry_id(industry_id);
                                genericBeans.setIndustry_details(industry_details);
                                genericBeans.setCategory_id(category_id);
                                genericBeans.setCategory_details(category_details);
                                genericBeans.setProcessed(processed);
                                genericBeans.setTransaction_type(transaction_type);
                                genericBeans.setAmount(amount);
                                genericBeans.setAccount_number(account_number);
                                genericBeans.setMerchant(merchant);
                                genericBeans.setDate(date);
                                genericBeans.setAccount_type(account_type);
                                //if (processed.equals("1")) {
                               ////// DataHelperClass.insertAllParseDate(genericBeans);
                                DataHelperClass.insertMessageResponse(jo.getString("message_id"),jo.toString());
                                // }
                            } else {
                               ////// DataHelperClass.updateUploadedSmsStatus(jo.getString("message_id"));
                                DataHelperClass.insertMessageResponse(jo.getString("message_id"),jo.toString());
                            }
                        } catch (Exception e) {
                            // String error_message = jo.getString("error_message");
                            // if(error_message.equalsIgnoreCase("Duplicate message")){
                           /////// DataHelperClass.updateUploadedSmsStatus(jo.getString("message_id"));
                           DataHelperClass.insertMessageResponse(jo.getString("message_id"),jo.toString());
                            //}
//                            if(isFirstTimeErrorShown) {
//                                ShowAlertInformation.showDialog(SplashScreen.this, "Error", error_message);
//                                isFirstTimeErrorShown = false;
//                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (isFirstTimeErrorShown) {
                        ShowAlertInformation.showDialog(SplashScreen.this, "Error", result);
                        isFirstTimeErrorShown = false;
                    }
                }
                //progressBar.setVisibility(View.INVISIBLE);
                // Commons.exportDatabse(SplashScreen.this, "monetapp.db");
                if (end < uploadList.size()) {
                    start = end;
                    try {
                        myTextProgress.setText("Total " + end + " sms are processed out of " + uploadList.size());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    end = uploadList.size() > end + slab ? end + slab : uploadList.size();
                    HashMap<String, String> postDataParams = new HashMap<String, String>();
                    postDataParams.put("message", SmsUtils.smsJsonString(smsBeansList, start, end));
                    new UploadSmsTask(postDataParams, smsBeansList).execute(Commons.UPLOAD_SMS_URL);
                } else {
                     progressBar.setVisibility(View.INVISIBLE);
                     myTextProgress.setText( "sms uploaded to server \n check db now");
               /*     Commons.exportDatabse(splashScreen, DBOpenHelperClass.DATABASE_NAME);
                    Intent i = new Intent(splashScreen, MainActivity.class);
                    splashScreen.startActivity(i);
                    splashScreen.finish();*/
                }
            } else {
                progressBar.setVisibility(View.INVISIBLE);
                if (result.equals("")) {
                    if (isFirstTimeErrorShown) {
                        ShowAlertInformation.showDialog(SplashScreen.this, "Error", "No data from server");
                        isFirstTimeErrorShown = false;
                    }
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                proceedAfterPermission();
            }
        } else if (requestCode == REQUEST_WRITE_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //reload my activity with permission granted or use the features what required the permission
            } else {
                // Toast.makeText(parentActivity, "The app was not allowed to write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void proceedAfterPermission() {
        progressBar.setVisibility(View.VISIBLE);
        myTextProgress.setText( "sms processing...");
        Thread thread = new Thread() {
            @Override
            public void run() {
                setUpDB();
                mHandler.sendEmptyMessage(1);
            }
        };
        thread.start();

    }

    private void setUpDB() {

        List<SmsBeans> smsBeansList = SmsUtils.readInboxSms(SplashScreen.this);
        if (smsBeansList.size() > 0) {
            boolean isInserted = DataHelperClass.insertAllSms(smsBeansList);
            if (isInserted) {
                uploadList = DataHelperClass.getUnprocessSmsList();
            } else {
                Commons.exportDatabse(splashScreen, DBOpenHelperClass.DATABASE_NAME);
                Intent i = new Intent(splashScreen, MainActivity.class);
                splashScreen.startActivity(i);
                splashScreen.finish();
            }
        } else {
            Commons.exportDatabse(splashScreen, DBOpenHelperClass.DATABASE_NAME);
            Intent i = new Intent(splashScreen, MainActivity.class);
            splashScreen.startActivity(i);
            splashScreen.finish();
        }
    }


    private void loadData() {

        if (new NetworkHelper(this).isOnline()) {
            // List<SmsBeans> smsBeansList = SmsUtils.readInboxSms(this);
            // if (smsBeansList.size() > 0) {
            // boolean isInserted = DataHelperClass.insertAllSms(smsBeansList);
            // if (isInserted) {
            //   uploadList = DataHelperClass.getUnprocessSmsList();
            if (uploadList.size() > 0) {
                end = uploadList.size() > slab ? slab : uploadList.size();
                myTextProgress.setText("Total " + end + " sms are processed out of " + uploadList.size());
                HashMap<String, String> postDataParams = new HashMap<String, String>();
                postDataParams.put("message", SmsUtils.smsJsonString(uploadList, start, end));
                new UploadSmsTask(postDataParams, uploadList).execute(Commons.UPLOAD_SMS_URL);
            } else {
                Commons.exportDatabse(splashScreen, DBOpenHelperClass.DATABASE_NAME);
                Intent i = new Intent(splashScreen, MainActivity.class);
                splashScreen.startActivity(i);
                splashScreen.finish();
            }
            // }
//            }else{
//                Commons.exportDatabse(splashScreen, DBOpenHelperClass.DATABASE_NAME);
//                Intent i = new Intent(splashScreen, MainActivity.class);
//                splashScreen.startActivity(i);
//                splashScreen.finish();
//            }
            //new DataHelperClass().getTablews();
            /*
            * For testing hardcoded
            * */
        /*    List<SmsBeans> smsBeansList = DummySmsData.smsData();
            if (smsBeansList.size() > 0) {
                boolean isInserted = DataHelperClass.insertAllSms(smsBeansList);
                if (isInserted) {
                    DataHelperClass.getTablews();
                    uploadList = DataHelperClass.getUnprocessSmsList();
                    if (uploadList.size() > 0) {
                        end = uploadList.size() > slab ? slab : uploadList.size();
                         myTextProgress.setText("Total " + end + " sms are processed out of " + uploadList.size());
                        HashMap<String, String> postDataParams = new HashMap<String, String>();
                        postDataParams.put("message", DummySmsData.smsJsonString(uploadList, start, end));
                        new UploadSmsTask(postDataParams, smsBeansList).execute(Commons.UPLOAD_SMS_URL);
                    } else {
                        Intent i = new Intent(splashScreen, MainActivity.class);
                        splashScreen.startActivity(i);
                        splashScreen.finish();
                    }

                }
            }*/
        } else {
            ShowAlertInformation.showDialog(SplashScreen.this, "Error", "Device is not connect to network. Please connect and re-launch the app.");
        }
    }


}