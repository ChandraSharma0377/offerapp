package com.example.dummy.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.example.dummy.R;
import com.example.dummy.activities.MainActivity;
import com.example.dummy.adapters.ExpandableListAdapter;
import com.example.dummy.adapters.ExpandableListReminderAdapter;
import com.example.dummy.adapters.ReminderAdapter;
import com.example.dummy.asynctasks.AsyncProcess;
import com.example.dummy.beans.IncomeBeans;
import com.example.dummy.beans.ReminderBeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReminderFragment extends Fragment {


    private LoginTask lat;
    private ExpandableListReminderAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, ArrayList<ReminderBeans>> listDataChild;
    public ReminderFragment() {
        super();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {

        View view = inflater.inflate(R.layout.lay_reminder, container, false);
        prepareListData();
        expListView = (ExpandableListView) view.findViewById(R.id.lv_Exp);
        prepareListData();
        listAdapter = new ExpandableListReminderAdapter(getActivity(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);

        // to prevent the group collapse
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // Doing nothing
                return true;
            }
        });
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.getMainScreenActivity().setActionBarTitle(getString(R.string.screen_reminder));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
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

        OnCancelListener cancelListener = new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface arg0) {
                if (null != lat) {
                    lat.cancel(true);
                    System.out.println("refe" + lat.isCancelled());
                    lat = null;
                    // activity.getSupportFragmentManager().popBackStack();
                }
            }
        };
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, ArrayList<ReminderBeans>>();

        // Adding header data
        listDataHeader.add("Sep 2017");
        listDataHeader.add("Aug 2017");
        listDataHeader.add("July 2017");

        // Adding child data
        ArrayList<ReminderBeans> header_1 = new ArrayList<ReminderBeans>();
        header_1.add(new ReminderBeans(getActivity().getDrawable(R.drawable.bank),"Axis Bank", "9874561230","Due on 20-june-17", "1536"));
        header_1.add(new ReminderBeans(getActivity().getDrawable(R.drawable.bank),"Axis Bank", "9874561230","Due on 20-june-17", "1536"));
        header_1.add(new ReminderBeans(getActivity().getDrawable(R.drawable.bank),"Citi Bank",  "4512789632","Due on 22-june-17", "12332"));

        ArrayList<ReminderBeans> header_2 = new ArrayList<ReminderBeans>();
        header_2.add(new ReminderBeans(getActivity().getDrawable(R.drawable.bank),"Citi Bank",  "XXXX147852","Due on 27-june-17", "2341"));
        header_2.add(new ReminderBeans(getActivity().getDrawable(R.drawable.bank),"SBI", "XXXXX78456","Due on 11-june-17", "6534"));


        ArrayList<ReminderBeans> header_3 = new ArrayList<ReminderBeans>();
        header_3.add(new ReminderBeans(getActivity().getDrawable(R.drawable.bank),"Axis Bank",  "9874561230","Due on 20-june-17", "8769"));
        header_3.add(new ReminderBeans(getActivity().getDrawable(R.drawable.bank),"Citi Bank",  "9874561230","Due on 20-june-17", "3858"));


        listDataChild.put(listDataHeader.get(0), header_1); // Header, Child data
        listDataChild.put(listDataHeader.get(1), header_2);
        listDataChild.put(listDataHeader.get(2), header_3);
    }
}
