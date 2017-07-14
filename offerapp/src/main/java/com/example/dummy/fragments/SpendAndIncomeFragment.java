package com.example.dummy.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dummy.R;
import com.example.dummy.activities.MainActivity;
import com.example.dummy.adapters.SpendAndIncomeAdapter;
import com.example.dummy.asynctasks.AsyncProcess;
import com.example.dummy.beans.IncomeBeans;
import com.example.dummy.utility.Commons;
import com.example.dummy.utility.DayAxisValueFormatter;
import com.example.dummy.utility.MyAxisValueFormatter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;

public class SpendAndIncomeFragment extends Fragment implements View.OnClickListener{

    private ListView list_view;
    private LoginTask lat;
    private SpendAndIncomeAdapter spendAndIncomeAdapter;
    private ArrayList<IncomeBeans> arraylist;
    private TextView tv_list_title,tv_pre,tv_next;
    private boolean isSpendScreen =false;
    private BarChart bar_chart;
    public SpendAndIncomeFragment() {
        super();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        View view = inflater.inflate(R.layout.lay_spend_income, container, false);
        list_view = (ListView) view.findViewById(R.id.list_view);
        tv_list_title = (TextView) view.findViewById(R.id.tv_list_title);
        tv_pre = (TextView) view.findViewById(R.id.tv_pre);
        tv_next = (TextView) view.findViewById(R.id.tv_next);
        tv_pre.setOnClickListener(this);
        tv_next.setOnClickListener(this);
        isSpendScreen = getArguments().getBoolean(Commons.KEY_IS_SPEND_SCREEN,false);
        if(isSpendScreen) {
            tv_list_title.setText("All Categories (Sep 2017)");
            tv_pre.setText("Aug");
            tv_next.setText("Oct");
        }else{
            tv_list_title.setText("Apr 2017");
            tv_pre.setText("Mar");
            tv_next.setText("May");
        }
        initData();
        spendAndIncomeAdapter = new SpendAndIncomeAdapter(getActivity(), arraylist);
        list_view.setAdapter(spendAndIncomeAdapter);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SpendIndividualCategoryFragment sicf = new SpendIndividualCategoryFragment();
                Bundle b =new Bundle();
                b.putBoolean(Commons.KEY_IS_SPEND_SCREEN,isSpendScreen);
                sicf.setArguments(b);
                if (isSpendScreen) {

                    MainActivity.getMainScreenActivity().changeNavigationContentFragment(sicf, true);
                }else{
                    MainActivity.getMainScreenActivity().changeNavigationContentFragment(sicf, true);
                }
            }
        });
        bar_chart = (BarChart)view.findViewById(R.id.bar_chart);
        initBarChart();
        setBarData();
        bar_chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                MainActivity.getMainScreenActivity().changeNavigationContentFragment(new SpendIndividualMonthFragment(), true);
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
        if (isSpendScreen)
            MainActivity.getMainScreenActivity().setActionBarTitle(getString(R.string.screen_spend));
        else
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
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.tv_pre:
                Toast.makeText(getActivity(),"Updating soon",Toast.LENGTH_LONG).show();
                break;
            case R.id.tv_next:
                Toast.makeText(getActivity(),"Updating soon",Toast.LENGTH_LONG).show();
            break;
        }
    }


    private void setBarData( ) {
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        float fff[] = new float[]{15.0f,44.0f,25.0f,49.0f};
        for (int i = 0; i <  fff.length ; i++) {
               // yVals1.add(new BarEntry(i, fff[i], getResources().getDrawable(R.drawable.star)));
                yVals1.add(new BarEntry(i+1, fff[i]));
        }
        BarDataSet barDataSet;
        if (bar_chart.getData() != null &&
                bar_chart.getData().getDataSetCount() > 0) {
            barDataSet = (BarDataSet) bar_chart.getData().getDataSetByIndex(0);
            barDataSet.setValues(yVals1);
            bar_chart.getData().notifyDataChanged();
            bar_chart.notifyDataSetChanged();
        } else {
            barDataSet = new BarDataSet(yVals1, "The Year 2017");
            barDataSet.setDrawIcons(false);
            barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(barDataSet);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            //data.setValueTypeface(mTfLight);
            data.setBarWidth(0.5f);
            bar_chart.setData(data);
        }
    }
    private void initBarChart() {

        bar_chart.setDrawBarShadow(false);
        bar_chart.setDrawValueAboveBar(true);
        bar_chart.getDescription().setEnabled(false);
        bar_chart.setMaxVisibleValueCount(60);
        bar_chart.setPinchZoom(false);
        bar_chart.setDrawGridBackground(false);

        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(bar_chart);

        XAxis xAxis = bar_chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //  xAxis.setTypeface(mTfLight);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
        xAxis.setValueFormatter(xAxisFormatter);

        IAxisValueFormatter custom = new MyAxisValueFormatter();

        YAxis leftAxis = bar_chart.getAxisLeft();
        // leftAxis.setTypeface(mTfLight);
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawLabels(false);
        leftAxis.setPosition(YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = bar_chart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        //rightAxis.setTypeface(mTfLight);
        rightAxis.setLabelCount(8, false);
        rightAxis.setValueFormatter(custom);
        rightAxis.setSpaceTop(15f);
        rightAxis.setDrawAxisLine(false);
        rightAxis.setDrawLabels(false);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        Legend l = bar_chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
    }
}
