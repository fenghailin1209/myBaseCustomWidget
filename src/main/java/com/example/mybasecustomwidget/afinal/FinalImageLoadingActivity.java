package com.example.mybasecustomwidget.afinal;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.mybasecustomwidget.R;
import com.example.mybasecustomwidget.baseAdapterHelper.multipleSupport.BaseAdapterHelper;
import com.example.mybasecustomwidget.baseAdapterHelper.multipleSupport.QuickAdapter;
import com.example.mybasecustomwidget.constant.Config;

public class FinalImageLoadingActivity extends Activity{
	
	private ArrayList<ImageBean> als;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_test);

		initData();
		
		
		initView();
	}

	private void initView() {
		ListView id_lv_main = (ListView) findViewById(R.id.id_lv_main);
		
		id_lv_main.setAdapter(new QuickAdapter<ImageBean>(FinalImageLoadingActivity.this,R.layout.item_image_loading,als) {

			@Override
			protected void convert(BaseAdapterHelper helper, ImageBean item) {
				helper.setText(R.id.time_tv,item.getUserName());
				helper.setImageUrl(R.id.image_iv, item.getUrl());
			}
		});
		
	}

	private void initData() {
		als = new ArrayList<ImageBean>();
		for(int i=0;i<15;i++){
			ImageBean b = new ImageBean("ssssss"+i, Config.images[i]);
			als.add(b);
		}
	}

}
