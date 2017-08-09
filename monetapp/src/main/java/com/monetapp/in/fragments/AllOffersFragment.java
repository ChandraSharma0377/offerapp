package com.monetapp.in.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.example.dummy.R;
import com.monetapp.in.activities.MainActivity;
import com.monetapp.in.adapters.ExpandableListOfferAdapter;
import com.monetapp.in.adapters.OfferAdapter;
import com.monetapp.in.beans.OfferBeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllOffersFragment extends Fragment {



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


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, ArrayList<OfferBeans>>();

        // Adding header data
        listDataHeader.add("Top Offers");
        listDataHeader.add("Offers Category");


        // Adding child data
        header_1 = new ArrayList<OfferBeans>();
        header_1.add(new OfferBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank), "Big Basket", "Get a free movie ticket on mobile bill payment,Promocode: 12344 Offer Vaild till 31st March 2017"));
        header_1.add(new OfferBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank), "Paytm", "Get a free movie ticket on mobile bill payment"));
        header_1.add(new OfferBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank), "Uber", "Get a free movie ticket on mobile bill payment"));

        header_2 = new ArrayList<OfferBeans>();
        header_2.add(new OfferBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank), "Travel", "3 Offers"));
        header_2.add(new OfferBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank), "Food", "2 Offers"));
        header_2.add(new OfferBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank), "E-Comerce ", "5 Offers"));


        listDataChild.put(listDataHeader.get(0), header_1); // Header, Child data
        listDataChild.put(listDataHeader.get(1), header_2);

    }
}
