package com.example.dummy.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;

import com.example.dummy.R;
import com.example.dummy.activities.MainActivity;
import com.example.dummy.adapters.IncomeAdapter;
import com.example.dummy.asynctasks.AsyncProcess;
import com.example.dummy.beans.IncomeBeans;

import java.util.ArrayList;
import java.util.HashMap;

public class IncomeFragment extends Fragment {

    private ListView list_view;
    private LoginTask lat;
    private ProgressDialog progressDialog;
    private IncomeAdapter incomeAdapter;
    private ArrayList<IncomeBeans> arraylist;
    public IncomeFragment() {
        super();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {

        View view = inflater.inflate(R.layout.lay_income, container, false);
        list_view = (ListView) view.findViewById(R.id.list_view);
        initData();
        incomeAdapter = new IncomeAdapter(getActivity(), arraylist);
        list_view.setAdapter(incomeAdapter);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.getMainScreenActivity().setActionBarTitle(getString(R.string.screen_income));
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
            progressDialog = ProgressDialog.show(getActivity(), "", "login please wait...");
            progressDialog.setCancelable(true);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setOnCancelListener(cancelListener);
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


    private void initData(){
        arraylist = new ArrayList<>();
        arraylist.add(new IncomeBeans(getActivity().getDrawable(R.drawable.bank),"Axis Bank", "3 Transactions", "1536"));
        arraylist.add(new IncomeBeans(getActivity().getDrawable(R.drawable.bank),"Citi Bank", "5 Transactions", "1231"));
        arraylist.add(new IncomeBeans(getActivity().getDrawable(R.drawable.bank),"SBI", "9 Transactions", "4532"));
        arraylist.add(new IncomeBeans(getActivity().getDrawable(R.drawable.bank),"Axis Bank", "3 Transactions", "1536"));
        arraylist.add(new IncomeBeans(getActivity().getDrawable(R.drawable.bank),"Citi Bank", "5 Transactions", "1231"));
        arraylist.add(new IncomeBeans(getActivity().getDrawable(R.drawable.bank),"SBI", "9 Transactions", "4532"));
        arraylist.add(new IncomeBeans(getActivity().getDrawable(R.drawable.bank),"Axis Bank", "3 Transactions", "1536"));
        arraylist.add(new IncomeBeans(getActivity().getDrawable(R.drawable.bank),"Citi Bank", "5 Transactions", "1231"));
        arraylist.add(new IncomeBeans(getActivity().getDrawable(R.drawable.bank),"SBI", "9 Transactions", "4532"));
        arraylist.add(new IncomeBeans(getActivity().getDrawable(R.drawable.bank),"Axis Bank", "3 Transactions", "1536"));
        arraylist.add(new IncomeBeans(getActivity().getDrawable(R.drawable.bank),"Citi Bank", "5 Transactions", "1231"));
        arraylist.add(new IncomeBeans(getActivity().getDrawable(R.drawable.bank),"SBI", "9 Transactions", "4532"));
    }
}
