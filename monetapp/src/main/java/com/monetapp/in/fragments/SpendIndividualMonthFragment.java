package com.monetapp.in.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dummy.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.monetapp.in.activities.MainActivity;
import com.monetapp.in.adapters.SpendIndividualMonthAdapter;
import com.monetapp.in.beans.IncomeBeans;
import com.monetapp.in.beans.SpendIndividualMonthBeans;
import com.monetapp.in.database.DataHelperClass;
import com.monetapp.in.utility.Commons;

import java.util.ArrayList;

public class SpendIndividualMonthFragment extends Fragment   {

    private ListView list_view;
    private SpendIndividualMonthAdapter spendIndividualMonthAdapter;
    private ArrayList<SpendIndividualMonthBeans> arraylist;
    private boolean isSpendScreen = false;
    private String selectedMonth = "";
    private PieChart mChart;
    public SpendIndividualMonthFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {

        View view = inflater.inflate(R.layout.lay_individual_month, container, false);
        list_view = (ListView) view.findViewById(R.id.list_view);
        isSpendScreen = getArguments().getBoolean(Commons.KEY_IS_SPEND_SCREEN, false);
        selectedMonth = getArguments().getString("month", "");
        initData();
        spendIndividualMonthAdapter = new SpendIndividualMonthAdapter(getActivity(), arraylist);
        list_view.setAdapter(spendIndividualMonthAdapter);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              //  if (isSpendScreen) {
                Bundle b = new Bundle();
                b.putString("account_id",arraylist.get(i).getAccountId());
                b.putString("message_id",arraylist.get(i).getMessageId());
                b.putString("amount",arraylist.get(i).getAmount());
                b.putString("merchant_id",arraylist.get(i).getMerchantId());
                b.putString("category_id",arraylist.get(i).getCategoryId());
                b.putString("date",arraylist.get(i).getDate());
                b.putBoolean("is_spent",arraylist.get(i).isSpent());
                Fragment f = new IndividualTransactionFragment();
                f.setArguments(b);
                MainActivity.getMainScreenActivity().changeNavigationContentFragment(f, true);
              //  }
            }
        });
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        mChart = (PieChart) view.findViewById(R.id.pie_chart);
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

//        mChart.setCenterTextTypeface(mTfLight);
//        mChart.setCenterText(generateCenterSpannableText());

        mChart.setDrawHoleEnabled(false);
        mChart.setDrawCenterText(false);


        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);



        setPieChartData(4, 100);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);



        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        mChart.setEntryLabelColor(Color.WHITE);
      //  mChart.setEntryLabelTypeface(mTfRegular);
        mChart.setEntryLabelTextSize(12f);

        mChart.setOnChartValueSelectedListener( new OnChartValueSelectedListener() {


            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Bundle b = new Bundle();
                int i = (int)e.getX();
                b.putBoolean(Commons.KEY_IS_SPEND_SCREEN,true);
                b.putString("category_id",arraylist.get(i).getCategoryId());
                b.putString("month",Commons.getValueTwoDigit(selectedMonth));
                SpendIndividualCategoryFragment sicf = new SpendIndividualCategoryFragment();
                sicf.setArguments(b);
                MainActivity.getMainScreenActivity().changeNavigationContentFragment(sicf, true);
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
       // if (isSpendScreen) {
            MainActivity.getMainScreenActivity().setActionBarTitle(getString(R.string.screen_spend_individual_month));
//        } else {
//            MainActivity.getMainScreenActivity().setActionBarTitle(getString(R.string.screen_income_details));
//        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }



    private void initData() {
        arraylist = new ArrayList<>();
//        arraylist.add(new IncomeBeans(getActivity().getDrawable(R.drawable.bank), "Snapdeal ", "03-June-2017", "1536","",""));
        arraylist = DataHelperClass.getIndividualMonthData(getActivity(),(isSpendScreen?"1":"0"),selectedMonth,Commons.getCurrentYear());

    }
    private void setPieChartData(int count, float range) {
        count = arraylist.size();
        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
         String[] mParties = new String[count] ;
        for (int i = 0; i < count ;i++)
        {
            mParties[i] = arraylist.get(i).getTitle();
        }
        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count ; i++) {
            entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5),
                    mParties[i % mParties.length],
                    getResources().getDrawable(R.drawable.star)));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Spend Results");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
       // data.setValueTypeface(mTfLight);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

}
