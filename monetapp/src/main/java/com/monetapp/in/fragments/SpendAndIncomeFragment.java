package com.monetapp.in.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dummy.R;
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
import com.monetapp.in.activities.MainActivity;
import com.monetapp.in.adapters.SpendAndIncomeAdapter;
import com.monetapp.in.beans.BarDataBeans;
import com.monetapp.in.beans.IncomeBeans;
import com.monetapp.in.database.DataHelperClass;
import com.monetapp.in.utility.Commons;
import com.monetapp.in.utility.DayAxisValueFormatter;
import com.monetapp.in.utility.MyAxisValueFormatter;

import java.util.ArrayList;

public class SpendAndIncomeFragment extends Fragment implements View.OnClickListener{

    private ListView list_view;
    private SpendAndIncomeAdapter spendAndIncomeAdapter;
    private ArrayList<IncomeBeans> arraylist;
    private TextView tv_list_title,tv_pre,tv_next;
    private boolean isSpendScreen =false;
    private BarChart bar_chart;
    private Spinner sp_filter;
    private ArrayList<BarDataBeans> barDataBeanses;
    private int currentMonth = 0;
    private String nextMonth = "";
    private String preMonth = "";
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
        currentMonth = Integer.parseInt(Commons.getCurrentMonth());
        preMonth = Commons.getCurrentMonthName(currentMonth -1);
        nextMonth = Commons.getCurrentMonthName(currentMonth+1);
        if(isSpendScreen) {
            updateLabels();
        }else{
            updateLabels();
        }
        initData();
        barDataBeanses =  DataHelperClass.getBarChartData(isSpendScreen?"1":"0");
        spendAndIncomeAdapter = new SpendAndIncomeAdapter(getActivity(), arraylist);
        list_view.setAdapter(spendAndIncomeAdapter);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SpendIndividualCategoryFragment sicf = new SpendIndividualCategoryFragment();
                Bundle b =new Bundle();
                b.putBoolean(Commons.KEY_IS_SPEND_SCREEN,isSpendScreen);
                b.putString("category_id",arraylist.get(i).getCategoryId());
                b.putString("month",Commons.getValueTwoDigit(""+currentMonth));
                sicf.setArguments(b);
                if (isSpendScreen) {
                    MainActivity.getMainScreenActivity().changeNavigationContentFragment(sicf, true);
                }else{
                    MainActivity.getMainScreenActivity().changeNavigationContentFragment(sicf, true);
                }
            }
        });
        sp_filter = (Spinner) view.findViewById(R.id.sp_filter);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item, Commons.filter_data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_filter.setAdapter(adapter);
        bar_chart = (BarChart)view.findViewById(R.id.bar_chart);
        initBarChart();
        setBarData();
        bar_chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Entry entry = e;
                float x = e.getX();
                Bundle b = new Bundle();
                b.putString("month",Commons.convertFloattoMonth(x));
                b.putBoolean(Commons.KEY_IS_SPEND_SCREEN,isSpendScreen);
              //  b.putString("category_id",arraylist.get((int)x).getCategoryId());
                Fragment f = new SpendIndividualMonthFragment();
                f.setArguments(b);
                MainActivity.getMainScreenActivity().changeNavigationContentFragment(f, true);
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



    private void initData(){
        arraylist = new ArrayList<>();
        arraylist = DataHelperClass.getSpentAndIncomeData(getActivity(),(isSpendScreen?"1":"0"),Commons.getCurrentMonth());
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.tv_pre:
                if(!tv_pre.getText().equals("Jan")){
                    currentMonth = currentMonth-1;
                    preMonth = Commons.getCurrentMonthName(currentMonth -1);
                    nextMonth = Commons.getCurrentMonthName(currentMonth+1);
                    arraylist = DataHelperClass.getSpentAndIncomeData(getActivity(),(isSpendScreen?"1":"0"),
                            Commons.getValueTwoDigit(""+currentMonth));
                    spendAndIncomeAdapter = new SpendAndIncomeAdapter(getActivity(), arraylist);
                    list_view.setAdapter(spendAndIncomeAdapter);
                    updateLabels();
                }

                break;
            case R.id.tv_next:
                currentMonth = currentMonth+1;
                preMonth = Commons.getCurrentMonthName(currentMonth -1);
                nextMonth = Commons.getCurrentMonthName(currentMonth+1);
                arraylist =  DataHelperClass.getSpentAndIncomeData(getActivity().getApplicationContext(),(isSpendScreen?"1":"0"),
                        Commons.getValueTwoDigit(""+currentMonth));
                spendAndIncomeAdapter = new SpendAndIncomeAdapter(getActivity(), arraylist);
                list_view.setAdapter(spendAndIncomeAdapter);
                updateLabels();
            break;
        }
    }


    private void setBarData( ) {
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        for (int i = 0; i <  barDataBeanses.size() ; i++) {
                yVals1.add(
                        new BarEntry(Integer.parseInt(barDataBeanses.get(i).getBarTitle()), Float.valueOf(barDataBeanses.get(i).getBarValue())));
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

    private void updateLabels(){
        tv_list_title.setText("All Categories ("+Commons.getCurrentMonthName(currentMonth)+")");
        tv_pre.setText(preMonth);
        tv_next.setText(nextMonth);
    }
}
