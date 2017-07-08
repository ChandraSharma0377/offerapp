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
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.Toast;

import com.example.dummy.R;
import com.example.dummy.activities.MainActivity;
import com.example.dummy.adapters.ExpandableListAdapter;
import com.example.dummy.asynctasks.AsyncProcess;
import com.example.dummy.beans.IncomeBeans;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FinancialFragment extends Fragment {

    private LoginTask lat;
    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private BarChart bar_chart;
    private HashMap<String, ArrayList<IncomeBeans>> listDataChild;
    public FinancialFragment() {
        super();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {

        View view = inflater.inflate(R.layout.lay_financial, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        expListView = (ExpandableListView) view.findViewById(R.id.lv_Exp);
        prepareListData();
        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);

       // to prevent the group collapse
        expListView.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // Doing nothing
                return true;
            }
        });
        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getActivity(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });


        bar_chart = (BarChart)view.findViewById(R.id.bar_chart);
        bar_chart.getDescription().setEnabled(false);
        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        bar_chart.setMaxVisibleValueCount(10);
        // scaling can now only be done on x- and y-axis separately
        bar_chart.setPinchZoom(false);

        bar_chart.setDrawBarShadow(false);
        bar_chart.setDrawGridBackground(false);

        XAxis xAxis = bar_chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        bar_chart.getAxisLeft().setDrawGridLines(false);
        // add a nice and smooth animation
        bar_chart.animateY(2500);
        bar_chart.getLegend().setEnabled(false);
        setBarData();
        bar_chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Toast.makeText(getActivity(),"Bar chart clicked",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.getMainScreenActivity().setActionBarTitle(getString(R.string.screen_financial_summary));
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
        listDataChild = new HashMap<String, ArrayList<IncomeBeans>>();

        // Adding header data
        listDataHeader.add("Bank Accounts");
        listDataHeader.add("Credit Cards");
        listDataHeader.add("Debit Cards");

        // Adding child data
        ArrayList<IncomeBeans> header_1 = new ArrayList<IncomeBeans>();
        header_1.add(new IncomeBeans(getActivity().getDrawable(R.drawable.bank),"Axis Bank", "XXXX1234", "1536"));
        header_1.add(new IncomeBeans(getActivity().getDrawable(R.drawable.bank),"Citi Bank", "XXXX5432", "1231"));
        header_1.add(new IncomeBeans(getActivity().getDrawable(R.drawable.bank),"SBI", "XXXX9087", "4532"));
        header_1.add(new IncomeBeans(getActivity().getDrawable(R.drawable.bank),"Axis Bank", "XXXX6785", "1536"));

        ArrayList<IncomeBeans> header_2 = new ArrayList<IncomeBeans>();
        header_2.add(new IncomeBeans(getActivity().getDrawable(R.drawable.bank),"Citi Bank", "XXXX8797", "1231"));
        header_2.add(new IncomeBeans(getActivity().getDrawable(R.drawable.bank),"SBI", "XXXX9435", "4532"));
        header_2.add(new IncomeBeans(getActivity().getDrawable(R.drawable.bank),"Axis Bank", "XXXX7980", "1536"));
        header_2.add(new IncomeBeans(getActivity().getDrawable(R.drawable.bank),"Citi Bank", "XXXX9098", "1231"));

        ArrayList<IncomeBeans> header_3 = new ArrayList<IncomeBeans>();
        header_3.add(new IncomeBeans(getActivity().getDrawable(R.drawable.bank),"SBI", "XXXX9978", "4532"));
        header_3.add(new IncomeBeans(getActivity().getDrawable(R.drawable.bank),"Axis Bank", "XXXX9564", "1536"));
        header_3.add(new IncomeBeans(getActivity().getDrawable(R.drawable.bank),"Citi Bank", "XXXX9932", "1231"));
        header_3.add(new IncomeBeans(getActivity().getDrawable(R.drawable.bank),"SBI", "XXXX9213", "4532"));

        listDataChild.put(listDataHeader.get(0), header_1); // Header, Child data
        listDataChild.put(listDataHeader.get(1), header_2);
        listDataChild.put(listDataHeader.get(2), header_3);
    }

    private void setBarData() {
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        float fff[] = new float[]{25.0f,49.0f,23.0f,12.0f};
        for (int i = 0; i < fff.length; i++) {

            yVals1.add(new BarEntry(i, fff[i]));
        }
        BarDataSet set1;
        if (bar_chart.getData() != null &&
                bar_chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) bar_chart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            bar_chart.getData().notifyDataChanged();
            bar_chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "Data Set");
            set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
            set1.setDrawValues(false);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            bar_chart.setData(data);
            bar_chart.setFitBars(true);
        }

        bar_chart.invalidate();
    }
}
