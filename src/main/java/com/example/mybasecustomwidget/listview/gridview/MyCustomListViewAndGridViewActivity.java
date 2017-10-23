package com.example.mybasecustomwidget.listview.gridview;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mybasecustomwidget.R;
import com.example.mybasecustomwidget.baseAdapterHelper.multipleSupport.BaseAdapterHelper;
import com.example.mybasecustomwidget.baseAdapterHelper.multipleSupport.Bean;
import com.example.mybasecustomwidget.baseAdapterHelper.multipleSupport.QuickAdapter;
import com.example.mybasecustomwidget.listview.gridview.HeaderAdvertisementVerticalAnimator.onLayoutClickListener;
import com.tandong.sa.view.AutoReFreshListView;
import com.tandong.sa.view.AutoReFreshListView.OnLoadMoreListener;
import com.tandong.sa.view.AutoReFreshListView.OnRefreshListener;

public class MyCustomListViewAndGridViewActivity extends Activity{

	private AutoReFreshListView mListView;

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

	private ArrayList<Integer> datas;

	private HeaderAdvertisementVerticalAnimator hAnimator;

	private HeaderAdvertisementHorizontalSlip hSlip;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_test);

		initView();

		setAdapter();
	}


	private void initView() {
		mListView = (AutoReFreshListView) findViewById(R.id.id_lv_main);
		
		mListView.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				handler.sendEmptyMessageDelayed(1, 1000);
			}
		});
		
		mListView.setOnLoadListener(new OnLoadMoreListener() {
			
			@Override
			public void onLoadMore() {
				handler.sendEmptyMessageDelayed(2, 1000);
			}
		});
		
		initTestData();
		
		setHeadView();
	}
	
	private void initTestData() {
		datas = new ArrayList<Integer>();
		datas.add(R.drawable.ic_ydy1);
		datas.add(R.drawable.ic_ydy2);
		datas.add(R.drawable.ic_ydy3);
		datas.add(R.drawable.ic_ydy4);
	}


	private void setHeadView() {
		ViewGroup headerView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.layout_listview_header, null);
		mListView.addHeaderView(headerView);

		setHeaderViewPager(headerView);

		setHeadViewViticalAnimator(headerView);

		setHeaderGridViews((headerView.findViewById(R.id.id_kstd_ll)));
	}

	private void setHeadViewViticalAnimator(ViewGroup headerView) {
		ArrayList<String> animatorDatas = new ArrayList<String>();
		animatorDatas.add("111");
		animatorDatas.add("222");
		animatorDatas.add("333");
		animatorDatas.add("4444");

		hAnimator = new HeaderAdvertisementVerticalAnimator(this, animatorDatas , headerView);
		hAnimator.startVerticalAnimator(0);
		hAnimator.setOnLayoutClickListener(new onLayoutClickListener() {

			@Override
			public void onLayoutClick(int position) {
				Toast.makeText(MyCustomListViewAndGridViewActivity.this, "click positoin:"+position,0).show();
			}
		});
	}

	private void setHeaderViewPager(ViewGroup vg) {
		hSlip = new HeaderAdvertisementHorizontalSlip(vg, this,datas);
		hSlip.setAdapter();
	}

	private void setHeaderGridViews(View view) {
//		HeaderAdvertisementViewGroup hGroup = new HeaderAdvertisementViewGroup(gv, getActivity(),null);
//		hGroup.setAdapter();

		HeaderAdvertisementHorizontalScrollView hScrollView = new HeaderAdvertisementHorizontalScrollView(MyCustomListViewAndGridViewActivity.this,view);
		hScrollView.operation();
	}

	private void setAdapter() {
		mAdapter = new QuickAdapter<Bean>(
				MyCustomListViewAndGridViewActivity.this, R.layout.item_list, MyCustomListViewAndGridViewHolder.getDatas())
		{

			@Override
			protected void convert(BaseAdapterHelper helper, Bean item)
			{
				Log.d(TAG, "--->>>convert");
				helper.setText(R.id.tv_title, item.getTitle());
				helper.setText(R.id.tv_describe, item.getDesc());
				helper.setText(R.id.tv_time, item.getTime());
			}
		};
		mListView.setAdapter(mAdapter);
	}

	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		destory();
	}

	private void destory() {
		if(hAnimator != null){
			hAnimator.clearAnimator();
		}
		
		if(hSlip != null){
			hSlip.stopAutoScroll();
		}
	}
	
}
