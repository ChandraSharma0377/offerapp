package com.example.dummy.adapters;

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
import com.example.dummy.beans.ReminderBeans;
import com.example.dummy.utility.Helper;

import java.util.ArrayList;


public class ReminderViewPagerAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<ReminderBeans> arraylist;

    public ReminderViewPagerAdapter(Context context, ArrayList<ReminderBeans> items) {
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
        View itemView = inflater.inflate(R.layout.list_item_reminder_home, container,
                false);
        TextView tvheader = (TextView) itemView.findViewById(R.id.tvheader);
        TextView tvsubheader = (TextView) itemView.findViewById(R.id.tvsubheader);
        TextView tvamount = (TextView) itemView.findViewById(R.id.tvamount);
        TextView tvtransactiondate = (TextView) itemView.findViewById(R.id.tvtransactiondate);
        ImageView ivthumb = (ImageView) itemView.findViewById(R.id.ivthumb);


        tvheader.setText(arraylist.get(position).getBankname());
        tvsubheader.setText(arraylist.get(position).getTransactionNo());
        tvamount.setText(context.getString(R.string.Rs) + " " + Helper.formatRupee(arraylist.get(position).getAmount()));
        tvtransactiondate.setText(arraylist.get(position).getTransactionDate());
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