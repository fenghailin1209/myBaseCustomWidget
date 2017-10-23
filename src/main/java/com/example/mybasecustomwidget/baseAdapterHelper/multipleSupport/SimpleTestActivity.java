package com.example.mybasecustomwidget.baseAdapterHelper.multipleSupport;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.mybasecustomwidget.R;
import com.tandong.sa.view.AutoReFreshListView;
import com.tandong.sa.view.AutoReFreshListView.OnLoadMoreListener;
import com.tandong.sa.view.AutoReFreshListView.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class SimpleTestActivity extends Activity
{

	private AutoReFreshListView mListView;
	private List<Bean> mDatas = new ArrayList<Bean>();

	private QuickAdapter<Bean> mAdapter;

	private Handler handler = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				mListView.onRefreshComplete();
				break;
			case 2:
				mListView.onLoadMoreComplete();
				break;

			default:
				break;
			}
		};
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_test);

		initDatas();

		mListView = (AutoReFreshListView) findViewById(R.id.id_lv_main);
		
		mListView.setOnRefreshListener(new OnRefreshListener() {
			
			/**
			 * 上拉刷新
			 */
			@Override
			public void onRefresh() {
				handler.sendEmptyMessageDelayed(1, 3000);
			}
		});
		
		/**
		 * 下拉加载更多
		 */
		mListView.setOnLoadListener(new OnLoadMoreListener() {
			
			@Override
			public void onLoadMore() {
				handler.sendEmptyMessageDelayed(2, 3000);
			}
		});
		
		
		mAdapter = new QuickAdapter<Bean>(
				SimpleTestActivity.this, R.layout.item_list, mDatas)
		{

			@Override
			protected void convert(BaseAdapterHelper helper, Bean item)
			{
				Log.d(TAG, "--->>>convert");
				helper.setText(R.id.tv_title, item.getTitle());
				helper.setText(R.id.tv_describe, item.getDesc());
				helper.setText(R.id.tv_time, item.getTime());
				// // helper.getView(R.id.tv_title).setOnClickListener(l)
			}
		};
//		mAdapter.showIndeterminateProgress(true);
		// 设置适配器
		mListView.setAdapter(mAdapter);

	}

	private void initDatas()
	{
		Bean bean = null;
		bean = new Bean("美女一只", "周三早上捡到妹子一只，在食堂二楼", "10086", "20130240122");
		mDatas.add(bean);
		bean = new Bean("美女一捆", "周三早上捡到妹子一捆，在食堂三楼", "10086", "20130240122");
		mDatas.add(bean);
		bean = new Bean("比卡丘一个", "周三早上捡到比卡丘一个，在食堂一楼", "10086", "20130240122");
		mDatas.add(bean);
		bean = new Bean("汉子一火车", "周三早上捡到xxxxxxxxxx，在xxx", "10086",
				"20130240122");
		bean = new Bean("美女一只", "周三早上捡到妹子一只，在食堂二楼", "10086", "20130240122");
		mDatas.add(bean);
		bean = new Bean("美女一捆", "周三早上捡到妹子一捆，在食堂三楼", "10086", "20130240122");
		mDatas.add(bean);
		bean = new Bean("比卡丘一个", "周三早上捡到比卡丘一个，在食堂一楼", "10086", "20130240122");
		mDatas.add(bean);
		bean = new Bean("汉子一火车", "周三早上捡到xxxxxxxxxx，在xxx", "10086",
				"20130240122");
		bean = new Bean("美女一只", "周三早上捡到妹子一只，在食堂二楼", "10086", "20130240122");
		mDatas.add(bean);
		bean = new Bean("美女一捆", "周三早上捡到妹子一捆，在食堂三楼", "10086", "20130240122");
		mDatas.add(bean);
		bean = new Bean("比卡丘一个", "周三早上捡到比卡丘一个，在食堂一楼", "10086", "20130240122");
		mDatas.add(bean);
		bean = new Bean("汉子一火车", "周三早上捡到xxxxxxxxxx，在xxx", "10086",
				"20130240122");
		bean = new Bean("美女一只", "周三早上捡到妹子一只，在食堂二楼", "10086", "20130240122");
		mDatas.add(bean);
		bean = new Bean("美女一捆", "周三早上捡到妹子一捆，在食堂三楼", "10086", "20130240122");
		mDatas.add(bean);
		bean = new Bean("比卡丘一个", "周三早上捡到比卡丘一个，在食堂一楼", "10086", "20130240122");
		mDatas.add(bean);
		bean = new Bean("汉子一火车", "周三早上捡到xxxxxxxxxx，在xxx", "10086",
				"20130240122");
		mDatas.add(bean);
	}

}
