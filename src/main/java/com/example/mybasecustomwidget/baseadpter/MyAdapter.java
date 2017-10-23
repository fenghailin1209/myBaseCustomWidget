package com.example.mybasecustomwidget.baseadpter;

import java.util.ArrayList;

import android.content.Context;
import android.widget.TextView;

import com.example.mybasecustomwidget.R;

public class MyAdapter extends CommondFinalAdapter<Bean> {
	
	public MyAdapter(Context context, ArrayList<Bean> myLw, int itemViewIds) {
		super(context, myLw,itemViewIds);
	}

	@Override
	public void convert(int position,CommondViewHolder viewHolder) {
		Bean lb = myLw.get(position);


//		viewHolder.setText(R.id.lv_name_tv, lb.getName());
//		viewHolder.setText(R.id.lv_zsznc_tv, lb.getZsznc());
//		viewHolder.setText(R.id.lv_zszn_time_tv, lb.getZszn_time());
//		viewHolder.setImageResource(R.id.lv_ic_iv, lb.getHead_iv());
//
//		TextView tv = viewHolder.getViewById(R.id.lv_name_tv);
	}
}
