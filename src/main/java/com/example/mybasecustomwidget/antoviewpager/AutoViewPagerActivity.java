package com.example.mybasecustomwidget.antoviewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.example.mybasecustomwidget.R;
import com.example.mybasecustomwidget.antoviewpager.MyBannerAdapter.OnPagerChangeeListener;

import java.util.ArrayList;

public class AutoViewPagerActivity extends Activity {
	
	private ViewPager viewPager;
	private ArrayList<String> viewList;
	private MyBannerAdapter mBannerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_auto_viewpager);

		viewList = initData();

		initView();

		setAdapter();
	}


	private void initView() {
		viewPager = (ViewPager) findViewById(R.id.view_pager);
	}

	private void setAdapter() {
		mBannerAdapter = new MyBannerAdapter(this,viewList);
		viewPager.setAdapter(mBannerAdapter);
		
		mBannerAdapter.startRoll(viewPager);
		mBannerAdapter.setOnPagerChangeeListener(new OnPagerChangeeListener() {
			
			@Override
			public void onPagerChange(int position) {
				Toast.makeText(AutoViewPagerActivity.this, "Click position is:"+position, 1).show();
			}
		});
	}

	private ArrayList<String> initData() {
		ArrayList<String> viewList = new ArrayList<String>();
		viewList.add("http://pic1.nipic.com/2008-12-15/20081215211851562_2.jpg");
		viewList.add("http://pic.nipic.com/2008-04-01/20084113367207_2.jpg");
		 viewList.add("http://pic31.nipic.com/20130708/12246968_161410243000_2.jpg");
		 viewList.add("http://pic27.nipic.com/20130227/4131239_232632474002_2.jpg");
		 viewList.add("http://pic5.nipic.com/20091222/3822085_091248554231_2.jpg");
		 viewList.add("http://pic4.nipic.com/20090803/2618170_095921092_2.jpg");
		return viewList;
	}

	

	@Override
	public void onPause() {
		super.onPause();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		mBannerAdapter.stopRoll();
		
	}
}