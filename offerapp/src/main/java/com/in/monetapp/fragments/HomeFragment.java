package com.in.monetapp.fragments;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dummy.R;
import com.in.monetapp.activities.MainActivity;
import com.in.monetapp.adapters.OfferAdapter;
import com.in.monetapp.adapters.OfferViewPagerAdapter;
import com.in.monetapp.adapters.ReminderAdapter;
import com.in.monetapp.adapters.ReminderViewPagerAdapter;
import com.in.monetapp.asynctasks.AsyncProcess;
import com.in.monetapp.beans.OfferBeans;
import com.in.monetapp.beans.ReminderBeans;
import com.in.monetapp.beans.SmsBeans;
import com.in.monetapp.database.DataHelperClass;
import com.in.monetapp.pageindicator.CirclePageIndicator;
import com.in.monetapp.utility.Commons;
import com.in.monetapp.views.ClickableViewPager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener {


    private RelativeLayout rl_financial, rl_current_income, rl_current_spend;
    private ListView lv_reminder, lv_offer;
    private ArrayList<OfferBeans> offerBeanses = new ArrayList<>();
    private ArrayList<ReminderBeans> reminderBeanses = new ArrayList<>();
    private TextView tv_show_all_reminder, tv_show_all_offer;
    private ClickableViewPager viewpager_reminder, viewpager_offer;
    private CirclePageIndicator indicator_reminder, indicator_offer;

    public HomeFragment() {
        super();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {

        View view = inflater.inflate(R.layout.lay_home, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        rl_current_income = (RelativeLayout) view.findViewById(R.id.rl_current_income);
        rl_current_spend = (RelativeLayout) view.findViewById(R.id.rl_current_spend);
        rl_financial = (RelativeLayout) view.findViewById(R.id.rl_financial);
        lv_offer = (ListView) view.findViewById(R.id.lv_offer);
        lv_reminder = (ListView) view.findViewById(R.id.lv_reminder);
        tv_show_all_reminder = (TextView) view.findViewById(R.id.tv_show_all_reminder);
        tv_show_all_offer = (TextView) view.findViewById(R.id.tv_show_all_offer);
        viewpager_reminder = (ClickableViewPager) view.findViewById(R.id.viewpager_reminder);
        viewpager_offer = (ClickableViewPager) view.findViewById(R.id.viewpager_offer);
        indicator_offer = (CirclePageIndicator) view.findViewById(R.id.indicator_offer);
        indicator_reminder = (CirclePageIndicator) view.findViewById(R.id.indicator_reminder);
        tv_show_all_reminder.setOnClickListener(this);
        tv_show_all_offer.setOnClickListener(this);
        initOfferData();
        initReminderData();
        ReminderAdapter reminderAdapter = new ReminderAdapter(getActivity(), reminderBeanses);
        lv_reminder.setAdapter(reminderAdapter);
        lv_reminder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity.getMainScreenActivity().changeNavigationContentFragment(new ReminderFragment(), true);
            }
        });

        viewpager_reminder.setOnItemClickListener(new ClickableViewPager.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MainActivity.getMainScreenActivity().changeNavigationContentFragment(new ReminderFragment(), true);
            }
        });
        ReminderViewPagerAdapter adapter = new ReminderViewPagerAdapter(getActivity(), reminderBeanses);
        viewpager_reminder.setAdapter(adapter);
        indicator_reminder.setViewPager(viewpager_reminder);

        lv_reminder.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });
        lv_offer.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });
        OfferAdapter offferAdapter = new OfferAdapter(getActivity(), offerBeanses);
        lv_offer.setAdapter(offferAdapter);

        OfferViewPagerAdapter offerViewPagerAdapter = new OfferViewPagerAdapter(getActivity(), offerBeanses);
        viewpager_offer.setAdapter(offerViewPagerAdapter);
        indicator_offer.setViewPager(viewpager_offer);
        viewpager_offer.setOnItemClickListener(new ClickableViewPager.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MainActivity.getMainScreenActivity().changeNavigationContentFragment(new AllOffersFragment(), true);
            }
        });
        lv_offer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity.getMainScreenActivity().changeNavigationContentFragment(new OfferFragment(), true);
            }
        });
        rl_financial.setOnClickListener(this);
        rl_current_income.setOnClickListener(this);
        rl_current_spend.setOnClickListener(this);
        if (MainActivity.getMainScreenActivity().isReadWriteSMSPermissionAllowed()) {
            //Toast.makeText(getActivity(), "permision allowed", Toast.LENGTH_LONG).show();
            List<SmsBeans> smsBeansList = readInboxSms();
            if (smsBeansList.size() > 0) {
                boolean isInserted = new DataHelperClass(getActivity()).insertAllSms(smsBeansList);
                if (isInserted) {
                   // for (int i = 0; i < smsBeansList.size(); i++) {
                        new UploadSmsTask(null).execute(Commons.UPLOAD_SMS_URL +
                               // smsBeansList.get(i).getMsg());
                                "Dear Customer, your Account XX6573 has been debited with INR 10,000.00 on 20-Jul-17. Info: BIL*001255248894*Chandra*NSP. The Available Balance is INR 7,434.95.");

                   // }
                }

            }

        } else {
            MainActivity.getMainScreenActivity().requestReadWriteSMSPermission();
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.getMainScreenActivity().setActionBarTitle(getString(R.string.screen_home));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.rl_financial:
                MainActivity.getMainScreenActivity().changeNavigationContentFragment(new FinancialFragment(), true);
                break;
            case R.id.rl_current_spend: {
                SpendAndIncomeFragment sif = new SpendAndIncomeFragment();
                Bundle b = new Bundle();
                b.putBoolean(Commons.KEY_IS_SPEND_SCREEN, true);
                sif.setArguments(b);
                MainActivity.getMainScreenActivity().changeNavigationContentFragment(sif, true);
            }
            break;
            case R.id.rl_current_income: {
                SpendAndIncomeFragment sif = new SpendAndIncomeFragment();
                Bundle b = new Bundle();
                b.putBoolean(Commons.KEY_IS_SPEND_SCREEN, false);
                sif.setArguments(b);
                MainActivity.getMainScreenActivity().changeNavigationContentFragment(sif, true);
            }
            break;
            case R.id.tv_show_all_reminder:
                MainActivity.getMainScreenActivity().changeNavigationContentFragment(new ReminderFragment(), true);

                break;
            case R.id.tv_show_all_offer:
                MainActivity.getMainScreenActivity().changeNavigationContentFragment(new AllOffersFragment(), true);

                break;
        }
    }

    private class UploadSmsTask extends AsyncProcess {

        public UploadSmsTask(HashMap<String, String> postDataParams) {
            super(postDataParams);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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
                    JSONObject jo = new JSONObject(result);
//                    {"transaction_type":"Debit","amount":"10000.00","account_number":"xx1173","date":"20\/07\/17",
//                            "account_type":"Bank Account","merchant":"Personal"}

                    String transaction_type = jo.getString("transaction_type");
                    String amount = jo.getString("amount");
                    String account_number = jo.getString("account_number");
                    String merchant = jo.getString("merchant");
                    String date = jo.getString("date");
                    String account_type = jo.getString("account_type");
                    System.out.println(merchant + "\n" + account_type + "\n" + date + "\n" + transaction_type + "\n" + amount + "\n" + account_number);

                } catch (Exception e) {
                    e.printStackTrace();
                }
//				System.out.println("LoginTask result is : " + (result == null ? "" : result));
//				progressDialog.dismiss();
//			} else {
//				Log.i("LoginTask response", result == null ? "" : result);
//				ShowAlertInformation.showDialog(getActivity(), "Error", "Error");
//				progressDialog.dismiss();
//			}
            }
        }


    }

    private void initOfferData() {


        offerBeanses.add(new OfferBeans(getActivity().getDrawable(R.drawable.bank), "Axis Bank", "Get a free movie ticket on mobile bill payment,Promocode: 12344 Offer Vaild till 31st March 2017"));
        offerBeanses.add(new OfferBeans(getActivity().getDrawable(R.drawable.bank), "Paytm", "Get a free movie ticket on mobile bill payment"));
        offerBeanses.add(new OfferBeans(getActivity().getDrawable(R.drawable.bank), "ICICI Bank", "Get a free movie ticket on mobile bill payment"));

    }

    private void initReminderData() {

        reminderBeanses.add(new ReminderBeans(getActivity().getDrawable(R.drawable.bank), "Axis Bank", "9874561230", "Due on 20-june-17", "1536"));
        reminderBeanses.add(new ReminderBeans(getActivity().getDrawable(R.drawable.bank), "Citi Bank", "4512789632", "Due on 22-june-17", "12332"));
        reminderBeanses.add(new ReminderBeans(getActivity().getDrawable(R.drawable.bank), "SBI", "012457896", "Due on 21-june-17", "4321"));
        reminderBeanses.add(new ReminderBeans(getActivity().getDrawable(R.drawable.bank), "Axis Bank", "XXXX789456", "Due on 24-june-17", "6523"));
        reminderBeanses.add(new ReminderBeans(getActivity().getDrawable(R.drawable.bank), "Citi Bank", "XXXX147852", "Due on 27-june-17", "2341"));
        reminderBeanses.add(new ReminderBeans(getActivity().getDrawable(R.drawable.bank), "SBI", "XXXXX78456", "Due on 11-june-17", "6534"));
    }


    public List<SmsBeans> readInboxSms() {
        List<SmsBeans> lstSms = new ArrayList<SmsBeans>();
        Uri message = Uri.parse("content://sms/inbox");
        ContentResolver cr = getActivity().getContentResolver();
        Cursor c = cr.query(message, null, null, null, null);
        getActivity().startManagingCursor(c);
        int totalSMS = c.getCount();
        if (c.moveToFirst()) {
            for (int i = 0; i < totalSMS; i++) {
                SmsBeans objSms = new SmsBeans();
                objSms.setId(c.getString(c.getColumnIndexOrThrow("_id")));
                objSms.setAddress(c.getString(c
                        .getColumnIndexOrThrow("address")));
                objSms.setMsg(c.getString(c.getColumnIndexOrThrow("body")));
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
}
