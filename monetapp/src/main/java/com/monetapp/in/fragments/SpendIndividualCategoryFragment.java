package com.monetapp.in.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dummy.R;
import com.monetapp.in.activities.MainActivity;
import com.monetapp.in.adapters.IncomeAdapter;
import com.monetapp.in.beans.IncomeBeans;
import com.monetapp.in.database.DataHelperClass;
import com.monetapp.in.utility.Commons;

import java.util.ArrayList;

public class SpendIndividualCategoryFragment extends Fragment {

    private ListView list_view;
    private IncomeAdapter incomeAdapter;
    private ArrayList<IncomeBeans> arraylist;
    private boolean isSpendScreen = false;
    private String category_id,month;

    public SpendIndividualCategoryFragment() {
        super();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {

        View view = inflater.inflate(R.layout.lay_individual_category, container, false);
        list_view = (ListView) view.findViewById(R.id.list_view);
        isSpendScreen = getArguments().getBoolean(Commons.KEY_IS_SPEND_SCREEN, false);
        month = getArguments().getString("month","");
        category_id = getArguments().getString("category_id","");
        initData();
        incomeAdapter = new IncomeAdapter(getActivity(), arraylist);
        list_view.setAdapter(incomeAdapter);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (isSpendScreen) {
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
                }
            }
        });
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isSpendScreen) {
            MainActivity.getMainScreenActivity().setActionBarTitle(getString(R.string.screen_spend_individual));
        } else {
            MainActivity.getMainScreenActivity().setActionBarTitle(getString(R.string.screen_income_details));
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }



    private void initData() {
        arraylist = new ArrayList<>();
        arraylist = DataHelperClass.getSpentIndividualCategoryData(getActivity(),(isSpendScreen?"1":"0"),month,Commons.getCurrentYear(),category_id);

    }

}
