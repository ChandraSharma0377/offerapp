package com.example.dummy.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dummy.R;
import com.example.dummy.activities.MainActivity;
import com.example.dummy.adapters.OfferAdapter;
import com.example.dummy.adapters.OfferViewPagerAdapter;
import com.example.dummy.adapters.ReminderAdapter;
import com.example.dummy.adapters.ReminderViewPagerAdapter;
import com.example.dummy.asynctasks.AsyncProcess;
import com.example.dummy.beans.OfferBeans;
import com.example.dummy.beans.ReminderBeans;
import com.example.dummy.pageindicator.CirclePageIndicator;
import com.example.dummy.utility.Commons;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private LoginTask lat;

    private RelativeLayout rl_financial,rl_current_income,rl_current_spend;
    private ListView lv_reminder,lv_offer;
    private ArrayList<OfferBeans> offerBeanses = new ArrayList<>();
    private ArrayList<ReminderBeans> reminderBeanses = new ArrayList<>();
    private TextView tv_show_all_reminder,tv_show_all_offer;
    private ViewPager viewpager_reminder,viewpager_offer;
    private CirclePageIndicator indicator_reminder,indicator_offer;
    public HomeFragment() {
        super();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {

        View view = inflater.inflate(R.layout.lay_home, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        rl_current_income = (RelativeLayout)view.findViewById(R.id.rl_current_income);
        rl_current_spend = (RelativeLayout)view.findViewById(R.id.rl_current_spend);
        rl_financial = (RelativeLayout)view.findViewById(R.id.rl_financial);
        lv_offer = (ListView) view.findViewById(R.id.lv_offer);
        lv_reminder = (ListView) view.findViewById(R.id.lv_reminder);
        tv_show_all_reminder = (TextView) view.findViewById(R.id.tv_show_all_reminder);
        tv_show_all_offer = (TextView) view.findViewById(R.id.tv_show_all_offer);
        viewpager_reminder = (ViewPager) view.findViewById(R.id.viewpager_reminder);
        viewpager_offer = (ViewPager) view.findViewById(R.id.viewpager_offer);
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
                MainActivity.getMainScreenActivity().changeNavigationContentFragment(new ReminderFragment(),true);
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
        lv_offer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity.getMainScreenActivity().changeNavigationContentFragment(new OfferFragment(),true);
            }
        });
        rl_financial.setOnClickListener(this);
        rl_current_income.setOnClickListener(this);
        rl_current_spend.setOnClickListener(this);
        if(MainActivity.getMainScreenActivity().isReadWriteSMSPermissionAllowed()){
            Toast.makeText(getActivity(),"permision allowed",Toast.LENGTH_LONG).show();
        }else{
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
        switch (id){
            case R.id.rl_financial:
                MainActivity.getMainScreenActivity().changeNavigationContentFragment(new FinancialFragment(),true);
                break;
            case R.id.rl_current_spend:
                {
                    SpendAndIncomeFragment sif =new SpendAndIncomeFragment();
                    Bundle b =new Bundle();
                    b.putBoolean(Commons.KEY_IS_SPEND_SCREEN,true);
                    sif.setArguments(b);
                    MainActivity.getMainScreenActivity().changeNavigationContentFragment( sif,true);
                }
                break;
            case R.id.rl_current_income:
            {
                SpendAndIncomeFragment sif =new SpendAndIncomeFragment();
                Bundle b =new Bundle();
                b.putBoolean(Commons.KEY_IS_SPEND_SCREEN,false);
                sif.setArguments(b);
                MainActivity.getMainScreenActivity().changeNavigationContentFragment( sif,true);
            }
            break;
            case R.id.tv_show_all_reminder:
                MainActivity.getMainScreenActivity().changeNavigationContentFragment(new ReminderFragment(),true);

                break;
            case R.id.tv_show_all_offer:
                MainActivity.getMainScreenActivity().changeNavigationContentFragment(new AllOffersFragment(),true);

                break;
        }
    }

    private class LoginTask extends AsyncProcess {

        public LoginTask(HashMap<String, String> postDataParams) {
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

//			if (200 == responseCode) {
//
//				String value = result.replace("\\", "");
//				if (value.length() > 2)
//					value = value.substring(1, value.length() - 1);
//				try {
//
//					// JSONArray jarray = new JSONArray(value);
//					JSONObject jo = new JSONObject(value);
//					// [{"status": "Success","User_ID": "2","UserName": "ashishs
//					// ","FirstName": "ashish","LastName": ""}]
//					//
//					String status = jo.getString("status");
//
//					if (status.equals("Success")) {
//						String User_ID = jo.getString("User_ID");
//						String FirstName = jo.getString("FirstName");
//						String LastName = jo.getString("LastName");
//						System.out.println(status + "\n" + User_ID + "\n" + FirstName + "\n" + LastName);
//						//delete old records
//						new DataHelperClass(getActivity()).clearRecord();
//						MainActivity.getMainScreenActivity().tv_name
//								.setText((FirstName + " " + LastName).toUpperCase());
//						MainActivity.getMainScreenActivity().setSharPreferancename(FirstName + " " + LastName, User_ID,
//								edtMobile.getText().toString().trim(), true);
//						MainActivity.getMainScreenActivity().changeNavigationContentFragment(new LandingFragment(),
//								false);
//
//					} else {
//						progressDialog.dismiss();
//						ShowAlertInformation.showDialog(getActivity(), "Error", "Invalid Mobile  Number");
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//					ShowAlertInformation.showDialog(getActivity(), "Error", "No data found");
//					progressDialog.dismiss();
//				}
//				System.out.println("LoginTask result is : " + (result == null ? "" : result));
//				progressDialog.dismiss();
//			} else {
//				Log.i("LoginTask response", result == null ? "" : result);
//				ShowAlertInformation.showDialog(getActivity(), "Error", "Error");
//				progressDialog.dismiss();
//			}

        }


    }

    private void initOfferData() {


        offerBeanses.add(new OfferBeans(getActivity().getDrawable(R.drawable.bank), "Axis Bank", "Get a free movie ticket on mobile bill payment,Promocode: 12344 Offer Vaild till 31st March 2017"));
        offerBeanses.add(new OfferBeans(getActivity().getDrawable(R.drawable.bank), "Paytm", "Get a free movie ticket on mobile bill payment"));
        offerBeanses.add(new OfferBeans(getActivity().getDrawable(R.drawable.bank), "ICICI Bank", "Get a free movie ticket on mobile bill payment"));

    }
    private void initReminderData(){

        reminderBeanses.add(new ReminderBeans(getActivity().getDrawable(R.drawable.bank),"Axis Bank", "9874561230","Due on 20-june-17", "1536"));
        reminderBeanses.add(new ReminderBeans(getActivity().getDrawable(R.drawable.bank),"Citi Bank",  "4512789632","Due on 22-june-17", "12332"));
        reminderBeanses.add(new ReminderBeans(getActivity().getDrawable(R.drawable.bank),"SBI",  "012457896","Due on 21-june-17", "4321"));
        reminderBeanses.add(new ReminderBeans(getActivity().getDrawable(R.drawable.bank),"Axis Bank",  "XXXX789456","Due on 24-june-17", "6523"));
        reminderBeanses.add(new ReminderBeans(getActivity().getDrawable(R.drawable.bank),"Citi Bank",  "XXXX147852","Due on 27-june-17", "2341"));
        reminderBeanses.add(new ReminderBeans(getActivity().getDrawable(R.drawable.bank),"SBI", "XXXXX78456","Due on 11-june-17", "6534"));
          }
}
