package com.monetapp.in.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dummy.R;
import com.monetapp.in.beans.ReminderBeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListReminderAdapter extends BaseExpandableListAdapter {

	private Context context;
	private List<String> listDataHeader; // header titles
	// child data in format of header title, child title
	private HashMap<String, ArrayList<ReminderBeans>> listDataChild;

	public ExpandableListReminderAdapter(Context context, List<String> listDataHeader,
                                         HashMap<String, ArrayList<ReminderBeans>>listChildData) {
		this.context = context;
		this.listDataHeader = listDataHeader;
		this.listDataChild = listChildData;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this.listDataChild.get(this.listDataHeader.get(groupPosition))
				.get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		final ReminderBeans reminderBeans = (ReminderBeans) getChild(groupPosition, childPosition);
		final ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(R.layout.list_item_reminder, null);
			holder = new ViewHolder();
			holder.tvheader = (TextView) convertView.findViewById(R.id.tvheader);
			holder.tvsubheader = (TextView) convertView.findViewById(R.id.tvsubheader);
			holder.tvamount = (TextView) convertView.findViewById(R.id.tvamount);
			holder.tvtransactiondate = (TextView) convertView.findViewById(R.id.tvtransactiondate);
			holder.ivthumb = (ImageView) convertView.findViewById(R.id.ivthumb);
			holder.cbpaid = (CheckBox) convertView.findViewById(R.id.cbpaid);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvheader.setText(reminderBeans.getBankname());
		holder.tvsubheader.setText(reminderBeans.getTransactionNo());
		holder.tvamount.setText(context.getString(R.string.Rs)+" "+reminderBeans.getAmount());
		holder.tvtransactiondate.setText(reminderBeans.getTransactionDate());
		holder.ivthumb.setImageDrawable(reminderBeans.getThumb());
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this.listDataChild.get(this.listDataHeader.get(groupPosition))
				.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.listDataHeader.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this.listDataHeader.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_group, null);
		}

		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.lblListHeader);
		lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(headerTitle);

        // To expand all child
        ExpandableListView expandableListView = (ExpandableListView) parent;
        expandableListView.expandGroup(groupPosition);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	public static class ViewHolder {
		TextView tvheader;
		TextView tvsubheader;
		TextView tvamount;
		TextView tvtransactiondate;
		CheckBox cbpaid;
		ImageView ivthumb;
	}
}
