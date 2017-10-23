package com.example.mybasecustomwidget.listview.gridview;

/**
 * 
 */
import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.mybasecustomwidget.R;
import com.example.mybasecustomwidget.baseAdapterHelper.multipleSupport.BaseAdapterHelper;
import com.example.mybasecustomwidget.baseAdapterHelper.multipleSupport.QuickAdapter;

/**
 * @author Administrator
 *
 */
public class HeaderAdvertisementViewGroup {
	
	private Context context;
	
	private UIGridView uIGridView;
	
	private ArrayList<GridViewTestBean> datas;
	
	public HeaderAdvertisementViewGroup(UIGridView UIGridView,Context context,ArrayList<GridViewTestBean> datas){
		this.uIGridView = UIGridView;
		this.context = context;
		this.datas = datas;
		initTestData();
	}

	private void initTestData() {
		datas = new ArrayList<GridViewTestBean>();
		
		for(int i=0;i<10;i++){
			GridViewTestBean gBean = new GridViewTestBean();
			datas.add(gBean);
		}
	}


	public void addHeaderAdvertisementHorizontalSlipData(ArrayList<GridViewTestBean> datas){
		this.datas = datas;
		setAdapter();
	}
	
	
	public void setAdapter(){
		uIGridView.setAdapter(new QuickAdapter<GridViewTestBean>(context,R.layout.item_header_advertisement_viewgroup,datas) {

			@Override
			protected void convert(BaseAdapterHelper helper,
					GridViewTestBean item) {
				
			}
		});
		uIGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(context, "Äúµã»÷µÄÊÇ:"+position,0).show();
				
			}
		});
	}
	
}
