package com.monetapp.in.fragments;

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
import com.monetapp.in.activities.MainActivity;
import com.monetapp.in.database.DataHelperClass;
import com.monetapp.in.utility.Helper;

public class IndividualTransactionFragment extends Fragment {

    private Switch switch_amount;
    private TextView tv_amount;
    private TextView tv_date_of_spend_value,tv_merchant_value,tv_category_value,tv_original_msg;
    private TextView tv_source_ac_no,tv_source_date,tv_source_label;
    private ImageView iv_delete;
    public IndividualTransactionFragment() {
        super();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {

        View view = inflater.inflate(R.layout.lay_individual_transaction, container, false);
        tv_amount = (TextView) view.findViewById(R.id.tv_amount);

        switch_amount = (Switch) view.findViewById(R.id.switch_amount);
        iv_delete = (ImageView) view.findViewById(R.id.iv_delete);

        tv_date_of_spend_value = (TextView) view.findViewById(R.id.tv_date_of_spend_value);
        tv_merchant_value = (TextView) view.findViewById(R.id.tv_merchant_value);
        tv_category_value = (TextView) view.findViewById(R.id.tv_category_value);
        tv_original_msg = (TextView) view.findViewById(R.id.tv_original_msg);

        tv_source_label = (TextView) view.findViewById(R.id.tv_source_label);
        tv_source_date = (TextView) view.findViewById(R.id.tv_source_date);
        tv_source_ac_no = (TextView) view.findViewById(R.id.tv_source_ac_no);

        String account_id = getArguments().getString("account_id");
        String message_id = getArguments().getString("message_id");
        String amount = getArguments().getString("amount");
        String merchant_id = getArguments().getString("merchant_id");
        String category_id = getArguments().getString("category_id");
        String date = getArguments().getString("date");
        boolean is_spent = getArguments().getBoolean("is_spent",false);
        switch_amount.setChecked(is_spent);
        String message_body = DataHelperClass.getMessageBody(message_id);
        tv_date_of_spend_value.setText(date);
        tv_source_date.setText(date);
        tv_merchant_value.setText(DataHelperClass.getMerchantName(merchant_id));
        tv_category_value.setText(DataHelperClass.getCategoryDetail(category_id));
        tv_original_msg.setText(message_body);
        tv_amount.setText(getString(R.string.Rs)+" "+ Helper.formatRupee(amount));
        tv_source_ac_no.setText(DataHelperClass.getAccountNumber(account_id));
        tv_source_label.setText(DataHelperClass.getAccountProvider(account_id));
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
