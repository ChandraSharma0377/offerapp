package com.in.monetapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dummy.R;
import com.in.monetapp.activities.MainActivity;
import com.in.monetapp.utility.Helper;

public class IndividualTransactionFragment extends Fragment {

    private Switch switch_amount;
    private TextView tv_amount;
    private TextView tv_date_of_spend_value,tv_merchant_value,tv_category_value;
    private ImageView iv_delete;
    public IndividualTransactionFragment() {
        super();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {

        View view = inflater.inflate(R.layout.lay_individual_transaction, container, false);
        tv_amount = (TextView) view.findViewById(R.id.tv_amount);
        tv_amount.setText(getString(R.string.Rs)+" "+ Helper.formatRupee("8799"));
        switch_amount = (Switch) view.findViewById(R.id.switch_amount);
        iv_delete = (ImageView) view.findViewById(R.id.iv_delete);
        switch_amount.setChecked(true);
        tv_date_of_spend_value = (TextView) view.findViewById(R.id.tv_date_of_spend_value);
        tv_merchant_value = (TextView) view.findViewById(R.id.tv_merchant_value);
        tv_category_value = (TextView) view.findViewById(R.id.tv_category_value);
        tv_date_of_spend_value.setText("05-June-2017");
        tv_merchant_value.setText("Amazon");
        tv_category_value.setText("E-Commerce");
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"delete",Toast.LENGTH_LONG).show();
            }
        });
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.getMainScreenActivity().setActionBarTitle(getString(R.string.screen_individual_transaction));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }


}
