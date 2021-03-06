package com.monetapp.in.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dummy.R;
import com.monetapp.in.activities.MainActivity;
import com.monetapp.in.beans.OfferBeans;
import com.monetapp.in.fragments.IndividualCategoryOffersFragment;
import com.monetapp.in.fragments.OfferFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListOfferAdapter extends BaseExpandableListAdapter {

	private Context context;
	private List<String> listDataHeader; // header titles
	// child data in format of header title, child title
	private HashMap<String, ArrayList<OfferBeans>> listDataChild;

	public ExpandableListOfferAdapter(Context context, List<String> listDataHeader,
                                      HashMap<String, ArrayList<OfferBeans>>listChildData) {
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
	public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

		final OfferBeans offerBeans = (OfferBeans) getChild(groupPosition, childPosition);
		final ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(R.layout.list_item_offer, null);
			holder = new ViewHolder();
			holder.tvheader = (TextView) convertView.findViewById(R.id.tvheader);
			holder.tvsubheader = (TextView) convertView.findViewById(R.id.tvsubheader);
			holder.ivthumb = (ImageView) convertView.findViewById(R.id.ivthumb);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvheader.setText(offerBeans.getMerchantName());
		holder.tvsubheader.setText(offerBeans.getOfferDetails());
		holder.ivthumb.setImageDrawable(offerBeans.getThumb());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(groupPosition == 0){
                    MainActivity.getMainScreenActivity().changeNavigationContentFragment(new OfferFragment(),true);
                }else{
					MainActivity.getMainScreenActivity().changeNavigationContentFragment(new IndividualCategoryOffersFragment(),true);

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
		ImageView ivthumb;
	}
}
