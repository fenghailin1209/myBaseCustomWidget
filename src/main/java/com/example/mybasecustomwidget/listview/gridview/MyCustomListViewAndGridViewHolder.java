package com.example.mybasecustomwidget.listview.gridview;

import java.util.ArrayList;
import java.util.List;

import com.example.mybasecustomwidget.baseAdapterHelper.multipleSupport.Bean;

public class MyCustomListViewAndGridViewHolder {

	public static List<Bean> getDatas()
	{
		List<Bean> mDatas = new ArrayList<Bean>();
		
		Bean bean = null;
		bean = new Bean("111", "222", "10086", "20130240122");
		mDatas.add(bean);
		bean = new Bean("11", "222", "10086", "20130240122");
		mDatas.add(bean);
		bean = new Bean("风格", "哥哥", "10086", "20130240122");
		mDatas.add(bean);
		bean = new Bean("个人个额", "哥哥", "10086",
				"20130240122");
		bean = new Bean("哥哥", "嘎嘎嘎嘎嘎嘎过", "10086", "20130240122");
		mDatas.add(bean);
		bean = new Bean("111", "222", "10086", "20130240122");
		mDatas.add(bean);
		bean = new Bean("11", "222", "10086", "20130240122");
		mDatas.add(bean);
		bean = new Bean("风格", "哥哥", "10086", "20130240122");
		mDatas.add(bean);
		bean = new Bean("个人个额", "哥哥", "10086",
				"20130240122");
		bean = new Bean("哥哥", "嘎嘎嘎嘎嘎嘎过", "10086", "20130240122");
		mDatas.add(bean);
		bean = new Bean("111", "222", "10086", "20130240122");
		mDatas.add(bean);
		bean = new Bean("11", "222", "10086", "20130240122");
		mDatas.add(bean);
		bean = new Bean("风格", "哥哥", "10086", "20130240122");
		mDatas.add(bean);
		bean = new Bean("个人个额", "哥哥", "10086",
				"20130240122");
		bean = new Bean("哥哥", "嘎嘎嘎嘎嘎嘎过", "10086", "20130240122");
		mDatas.add(bean);

		return mDatas;
	}
}
