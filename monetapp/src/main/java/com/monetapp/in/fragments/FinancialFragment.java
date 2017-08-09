package com.monetapp.in.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dummy.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
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
import com.monetapp.in.adapters.ExpandableListAdapter;
import com.monetapp.in.beans.IncomeBeans;
import com.monetapp.in.utility.Commons;
import com.monetapp.in.utility.DayAxisValueFormatter;
import com.monetapp.in.utility.MyAxisValueFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FinancialFragment extends Fragment {

    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private BarChart bar_chart;
    private HashMap<String, ArrayList<IncomeBeans>> listDataChild;

    private Spinner sp_filter;
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
                                childPosition), Toast.LENGTH_SHORT);
                       // .show();
                return false;
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
               // Toast.makeText(getActivity(),"Bar chart clicked",Toast.LENGTH_LONG).show();
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



    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, ArrayList<IncomeBeans>>();

        // Adding header data
        listDataHeader.add("Bank Accounts");
        listDataHeader.add("Credit Cards");
        listDataHeader.add("Debit Cards");

        // Adding child data
        ArrayList<IncomeBeans> header_1 = new ArrayList<IncomeBeans>();
        header_1.add(new IncomeBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank),"Axis Bank", "XXXX1234", "1536","",""));
        header_1.add(new IncomeBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank),"Citi Bank", "XXXX5432", "1231","",""));
        header_1.add(new IncomeBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank),"SBI", "XXXX9087", "4532","",""));
        header_1.add(new IncomeBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank),"Axis Bank", "XXXX6785", "1536","",""));

        ArrayList<IncomeBeans> header_2 = new ArrayList<IncomeBeans>();
        header_2.add(new IncomeBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank),"Citi Bank", "XXXX8797", "1231","",""));
        header_2.add(new IncomeBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank),"SBI", "XXXX9435", "4532","",""));
        header_2.add(new IncomeBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank),"Axis Bank", "XXXX7980", "1536","",""));
        header_2.add(new IncomeBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank),"Citi Bank", "XXXX9098", "1231","",""));

        ArrayList<IncomeBeans> header_3 = new ArrayList<IncomeBeans>();
        header_3.add(new IncomeBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank),"SBI", "XXXX9978", "4532","",""));
        header_3.add(new IncomeBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank),"Axis Bank", "XXXX9564", "1536","",""));
        header_3.add(new IncomeBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank),"Citi Bank", "XXXX9932", "1231","",""));
        header_3.add(new IncomeBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank),"SBI", "XXXX9213", "4532","",""));

        listDataChild.put(listDataHeader.get(0), header_1); // Header, Child data
        listDataChild.put(listDataHeader.get(1), header_2);
        listDataChild.put(listDataHeader.get(2), header_3);
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
            barDataSet = new BarDataSet(yVals1, "The year 2017");
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
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
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
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
    }
}
