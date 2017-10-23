package com.example.mybasecustomwidget.baseadpter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CommondFinalAdapter<T> extends BaseAdapter{
	protected ArrayList<T> myLw;
	private Context context;
	private int itemViewIds;

	public CommondFinalAdapter(Context context, ArrayList<T> myLw,int itemViewIds) {
		this.itemViewIds = itemViewIds;
		this.context = context;
		this.myLw = myLw;
	}

	@Override
	public int getCount() {
		return myLw == null ? 0 : myLw.size();
	}

	@Override
	public Object getItem(int position) {
		return myLw.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent){
		CommondViewHolder viewHolder = CommondViewHolder.getViewHolder(context, position, convertView, parent,itemViewIds);
		convert(position, viewHolder);
		return viewHolder.getConvertView();
	}
	public abstract void convert(int position,CommondViewHolder viewHolder);
}
