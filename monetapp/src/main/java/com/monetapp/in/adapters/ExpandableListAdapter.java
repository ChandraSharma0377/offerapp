package com.monetapp.in.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dummy.R;
import com.monetapp.in.beans.IncomeBeans;
import com.monetapp.in.utility.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private Context context;
	private List<String> listDataHeader; // header titles
	// child data in format of header title, child title
	private HashMap<String, ArrayList<IncomeBeans>> listDataChild;

	public ExpandableListAdapter(Context context, List<String> listDataHeader,
								 HashMap<String, ArrayList<IncomeBeans>>listChildData) {
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

		final IncomeBeans incomeBeans = (IncomeBeans) getChild(groupPosition, childPosition);
		final ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(R.layout.list_item_financial, null);
			holder = new ViewHolder();
			holder.tvheader = (TextView) convertView.findViewById(R.id.tvheader);
			holder.tvsubheader = (TextView) convertView.findViewById(R.id.tvsubheader);
			holder.tvamount = (TextView) convertView.findViewById(R.id.tvamount);
			holder.ivthumb = (ImageView) convertView.findViewById(R.id.ivthumb);
			holder.iv_send_sms = (ImageView) convertView.findViewById(R.id.iv_send_sms);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvheader.setText(incomeBeans.getTitle());
		holder.tvsubheader.setText(incomeBeans.getSubtitle());
		holder.tvamount.setText(context.getString(R.string.Rs)+" "+ Helper.formatRupee(incomeBeans.getAmount()));
		holder.ivthumb.setImageDrawable(incomeBeans.getThumb());
		holder.iv_send_sms.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				try {
					Intent sendIntent = new Intent(Intent.ACTION_VIEW);
					sendIntent.setData(Uri.parse("sms:"+ "9900990000"));
					sendIntent.putExtra("sms_body", "");
					context.startActivity(sendIntent);
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
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
		ImageView ivthumb;
		ImageView iv_send_sms;
	}
}
