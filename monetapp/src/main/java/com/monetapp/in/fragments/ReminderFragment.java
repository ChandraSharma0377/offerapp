package com.monetapp.in.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ExpandableListView;

import com.example.dummy.R;
import com.monetapp.in.activities.MainActivity;
import com.monetapp.in.adapters.ExpandableListReminderAdapter;
import com.monetapp.in.beans.ReminderBeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReminderFragment extends Fragment {


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


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, ArrayList<ReminderBeans>>();

        // Adding header data
        listDataHeader.add("Sep 2017");
        listDataHeader.add("Aug 2017");
        listDataHeader.add("July 2017");

        // Adding child data
        ArrayList<ReminderBeans> header_1 = new ArrayList<ReminderBeans>();
        header_1.add(new ReminderBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank),"Axis Bank", "9874561230","Due on 20-june-17", "1536"));
        header_1.add(new ReminderBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank),"Axis Bank", "9874561230","Due on 20-june-17", "1536"));
        header_1.add(new ReminderBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank),"Citi Bank",  "4512789632","Due on 22-june-17", "12332"));

        ArrayList<ReminderBeans> header_2 = new ArrayList<ReminderBeans>();
        header_2.add(new ReminderBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank),"Citi Bank",  "XXXX147852","Due on 27-june-17", "2341"));
        header_2.add(new ReminderBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank),"SBI", "XXXXX78456","Due on 11-june-17", "6534"));


        ArrayList<ReminderBeans> header_3 = new ArrayList<ReminderBeans>();
        header_3.add(new ReminderBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank),"Axis Bank",  "9874561230","Due on 20-june-17", "8769"));
        header_3.add(new ReminderBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank),"Citi Bank",  "9874561230","Due on 20-june-17", "3858"));


        listDataChild.put(listDataHeader.get(0), header_1); // Header, Child data
        listDataChild.put(listDataHeader.get(1), header_2);
        listDataChild.put(listDataHeader.get(2), header_3);
    }
}
