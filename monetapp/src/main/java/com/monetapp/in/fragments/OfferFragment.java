package com.monetapp.in.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dummy.R;
import com.monetapp.in.activities.MainActivity;
import com.squareup.picasso.Picasso;

public class OfferFragment extends Fragment {

    private ImageView iv_thumbnail, iv_offer_image;
    private TextView tv_offer_title, tv_offer_subheader, tv_offer_detail;
    private String url = "https://blog.woohoo.in/wp-content/uploads/2013/04/deals-and-offers-at-jabong.jpg";
    String url1 = "https://paytm.com/offer/wp-content/uploads/2017/01/offer-page-9.jpg";

    public OfferFragment() {
        super();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {

        View view = inflater.inflate(R.layout.lay_offer, container, false);
        iv_thumbnail = (ImageView) view.findViewById(R.id.iv_thumbnail);
        iv_offer_image = (ImageView) view.findViewById(R.id.iv_offer_image);

        tv_offer_title = (TextView) view.findViewById(R.id.tv_offer_title);
        tv_offer_subheader = (TextView) view.findViewById(R.id.tv_offer_subheader);
        tv_offer_detail = (TextView) view.findViewById(R.id.tv_offer_detail);
        Picasso.with(getActivity()).load(url1).placeholder(R.drawable.placeholder).resize(140, 140).into(iv_thumbnail);
        Picasso.with(getActivity()).load(url).placeholder(R.drawable.placeholder).into(iv_offer_image);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.getMainScreenActivity().setActionBarTitle(getString(R.string.screen_individual_offer));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
