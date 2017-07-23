package com.in.monetapp.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.example.dummy.R;
import com.in.monetapp.activities.MainActivity;
import com.in.monetapp.adapters.ExpandableListOfferAdapter;
import com.in.monetapp.adapters.OfferAdapter;
import com.in.monetapp.asynctasks.AsyncProcess;
import com.in.monetapp.beans.OfferBeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllOffersFragment extends Fragment {


    private LoginTask lat;
    private ExpandableListOfferAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, ArrayList<OfferBeans>> listDataChild;
    private ListView list_view_top,list_view_category;
    private  ArrayList<OfferBeans> header_1,header_2;
    public AllOffersFragment() {
        super();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {

        View view = inflater.inflate(R.layout.lay_all_offers, container, false);
        list_view_top = (ListView) view.findViewById(R.id.list_view_top);
        list_view_category = (ListView) view.findViewById(R.id.list_view_category);
        prepareListData();

        OfferAdapter offferAdapter1 = new OfferAdapter(getActivity(), header_1);
        list_view_top.setAdapter(offferAdapter1);
        list_view_top.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity.getMainScreenActivity().changeNavigationContentFragment(new OfferFragment(),true);
            }
        });

        OfferAdapter offferAdapter2 = new OfferAdapter(getActivity(), header_2);
        list_view_category.setAdapter(offferAdapter2);
        list_view_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity.getMainScreenActivity().changeNavigationContentFragment(new IndividualCategoryOffersFragment(),true);
            }
        });
       // expListView = (ExpandableListView) view.findViewById(R.id.lv_Exp);
       // prepareListData();
      //  listAdapter = new ExpandableListOfferAdapter(getActivity(), listDataHeader, listDataChild);
      //  expListView.setAdapter(listAdapter);

        // to prevent the group collapse
//        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                // Doing nothing
//                return true;
//            }
//        });
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.getMainScreenActivity().setActionBarTitle(getString(R.string.screen_all_offers));
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
        listDataChild = new HashMap<String, ArrayList<OfferBeans>>();

        // Adding header data
        listDataHeader.add("Top Offers");
        listDataHeader.add("Offers Category");


        // Adding child data
        header_1 = new ArrayList<OfferBeans>();
        header_1.add(new OfferBeans(getActivity().getDrawable(R.drawable.bank), "Big Basket", "Get a free movie ticket on mobile bill payment,Promocode: 12344 Offer Vaild till 31st March 2017"));
        header_1.add(new OfferBeans(getActivity().getDrawable(R.drawable.bank), "Paytm", "Get a free movie ticket on mobile bill payment"));
        header_1.add(new OfferBeans(getActivity().getDrawable(R.drawable.bank), "Uber", "Get a free movie ticket on mobile bill payment"));

        header_2 = new ArrayList<OfferBeans>();
        header_2.add(new OfferBeans(getActivity().getDrawable(R.drawable.bank), "Travel", "3 Offers"));
        header_2.add(new OfferBeans(getActivity().getDrawable(R.drawable.bank), "Food", "2 Offers"));
        header_2.add(new OfferBeans(getActivity().getDrawable(R.drawable.bank), "E-Comerce ", "5 Offers"));


        listDataChild.put(listDataHeader.get(0), header_1); // Header, Child data
        listDataChild.put(listDataHeader.get(1), header_2);

    }
}
