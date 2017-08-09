package com.monetapp.in.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dummy.R;
import com.monetapp.in.beans.OfferBeans;

import java.util.ArrayList;


public class OfferViewPagerAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<OfferBeans> arraylist;

    public OfferViewPagerAdapter(Context context, ArrayList<OfferBeans> items) {
        this.context = context;
        arraylist = items;

    }

    @Override
    public int getCount() {
        return arraylist.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View itemView = inflater.inflate(R.layout.list_item_offer, container,
                false);
        TextView tvheader = (TextView) itemView.findViewById(R.id.tvheader);
        TextView tvsubheader = (TextView) itemView.findViewById(R.id.tvsubheader);
        ImageView ivthumb = (ImageView) itemView.findViewById(R.id.ivthumb);


        tvheader.setText(arraylist.get(position).getMerchantName());
        tvsubheader.setText(arraylist.get(position).getOfferDetails());
        ivthumb.setImageDrawable(arraylist.get(position).getThumb());

        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((LinearLayout) object);

    }

}
