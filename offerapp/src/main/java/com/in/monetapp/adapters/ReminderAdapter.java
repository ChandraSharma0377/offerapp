package com.in.monetapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dummy.R;
import com.in.monetapp.beans.ReminderBeans;
import com.in.monetapp.utility.Helper;

import java.util.ArrayList;



public class ReminderAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ReminderBeans> arraylist;

    public ReminderAdapter(Context context, ArrayList<ReminderBeans> items) {
        this.context = context;
        arraylist =items;

    }

    @Override
    public int getCount() {
        return arraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_item_reminder_home, null);
            holder = new ViewHolder();
            holder.tvheader = (TextView) convertView.findViewById(R.id.tvheader);
            holder.tvsubheader = (TextView) convertView.findViewById(R.id.tvsubheader);
            holder.tvamount = (TextView) convertView.findViewById(R.id.tvamount);
            holder.tvtransactiondate = (TextView) convertView.findViewById(R.id.tvtransactiondate);
            holder.ivthumb = (ImageView) convertView.findViewById(R.id.ivthumb);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvheader.setText(arraylist.get(position).getBankname());
        holder.tvsubheader.setText(arraylist.get(position).getTransactionNo());
        holder.tvamount.setText(context.getString(R.string.Rs)+" "+ Helper.formatRupee(arraylist.get(position).getAmount()));
        holder.tvtransactiondate.setText(arraylist.get(position).getTransactionDate());
        holder.ivthumb.setImageDrawable(arraylist.get(position).getThumb());
        return convertView;
    }

    public static class ViewHolder {
        TextView tvheader;
        TextView tvsubheader;
        TextView tvamount;
        TextView tvtransactiondate;
        ImageView ivthumb;
    }

}
