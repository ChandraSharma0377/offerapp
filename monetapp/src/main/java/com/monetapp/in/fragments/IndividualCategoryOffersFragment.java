package com.monetapp.in.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dummy.R;
import com.monetapp.in.activities.MainActivity;
import com.monetapp.in.adapters.OfferAdapter;
import com.monetapp.in.beans.OfferBeans;

import java.util.ArrayList;

public class IndividualCategoryOffersFragment extends Fragment {

    private ListView list_view;
    private ArrayList<OfferBeans> arraylist;

    public IndividualCategoryOffersFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {

        View view = inflater.inflate(R.layout.lay_individual_category_offer, container, false);
        list_view = (ListView) view.findViewById(R.id.list_view);
        initData();
        OfferAdapter offferAdapter = new OfferAdapter(getActivity(), arraylist);
        list_view.setAdapter(offferAdapter);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity.getMainScreenActivity().changeNavigationContentFragment(new OfferFragment(),true);
            }
        });
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
            MainActivity.getMainScreenActivity().setActionBarTitle(getString(R.string.screen_individual_category_offer));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }


    private void initData() {
        arraylist = new ArrayList<>();
        arraylist.add(new OfferBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank), "Axis Bank", "Get a free movie ticket on mobile bill payment,Promocode: 12344 Offer Vaild till 31st March 2017"));
        arraylist.add(new OfferBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank), "Paytm", "Get a free movie ticket on mobile bill payment"));
        arraylist.add(new OfferBeans(ContextCompat.getDrawable(getActivity(),R.drawable.bank), "ICICI Bank", "Get a free movie ticket on mobile bill payment"));

    }

}
