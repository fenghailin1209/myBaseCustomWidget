package com.example.mybasecustomwidget.baseadpter;

import java.util.ArrayList;

import com.example.mybasecustomwidget.MyCustomApplication;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CommondViewHolder {
	
	private View convertView;
	
	private SparseArray sparseArray;

	public CommondViewHolder(Context context,int position, View convertView, ViewGroup parent,int resource){
		sparseArray = new SparseArray();
		this.convertView = LayoutInflater.from(context).inflate(resource, parent,false);
		this.convertView.setTag(this);
	}
	
	public static CommondViewHolder getViewHolder(Context context,int position, View convertView, ViewGroup parent,int resource){
		if(convertView == null){
			return new CommondViewHolder(context, position, convertView, parent, resource);
		}
		return (CommondViewHolder) convertView.getTag();
	}
	
	public <T extends View>T getViewById(int resId){
		View view = (View) sparseArray.get(resId);
		if(view == null){
			view = convertView.findViewById(resId);
			sparseArray.put(resId, view);
		}
		return (T) view;
	}
	
	public View getConvertView(){
		return convertView;
	}
	
	/*** ��������
	 * @param resId
	 * @param text
	 */
	public void setText(int resId,String text){
		TextView tv = getViewById(resId);
		tv.setText(text);
	}
	
	
	/**
	 * ��������ͼͼƬ
	 * @param resId
	 * @param url
	 */
	public void setImageResource(int resId,String url){
		ImageView iv = getViewById(resId);
		ImageLoader.getInstance().displayImage(url, iv, MyCustomApplication.options);
	}
	
}
