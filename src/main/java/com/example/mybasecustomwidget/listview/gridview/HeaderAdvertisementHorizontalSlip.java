/**
 * 
 */
package com.example.mybasecustomwidget.listview.gridview;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybasecustomwidget.R;
import com.example.mybasecustomwidget.listview.gridview.ImagePagerAdapter.onImageClickListener;
import com.example.mybasecustomwidget.utils.AndroidUtil;

/**
 * @author Administrator
 *
 */
public class HeaderAdvertisementHorizontalSlip {
	
	private static final int POINT_WIDTH = 7;

	private static final int MARGIN_SPACE = 8;

	private Context context;
	
	private MyCustomAutoScrollViewPager viewPager;
	
	private ArrayList<Integer> datas;
	
	private LinearLayout id_bannaer_viewpager_point_ll;
	
	private ArrayList<TextView> points;

	private ImagePagerAdapter iAdapter;
	
	public HeaderAdvertisementHorizontalSlip(ViewGroup viewGroup,Context context,ArrayList<Integer> datas){
		this.context = context;
		this.datas = datas;
		
		initView(viewGroup);
	}

	private void initView(ViewGroup viewGroup) {
		viewPager = (MyCustomAutoScrollViewPager)viewGroup.findViewById(R.id.view_pager);
		id_bannaer_viewpager_point_ll = (LinearLayout) viewGroup.findViewById(R.id.id_bannaer_viewpager_point_ll);
		
		initBannaerViewpagerPoints();
	}

	private void initBannaerViewpagerPoints() {
		points = new ArrayList<TextView>();
		
		for(int point : datas){
			TextView child = new TextView(context);
			child.setBackgroundResource(R.drawable.shape_banner_point_default);
			int width = AndroidUtil.dip2px(context, POINT_WIDTH);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, width);
			params.setMargins(MARGIN_SPACE, MARGIN_SPACE, MARGIN_SPACE, MARGIN_SPACE);
			child.setLayoutParams(params);
			
			id_bannaer_viewpager_point_ll.addView(child);
			
			points.add(child);
		}
	}

	public void addHeaderAdvertisementHorizontalSlipData(ArrayList<Integer> datas){
		this.datas = datas;
		setAdapter();
	}
	
	
	public void setAdapter(){
		iAdapter = new ImagePagerAdapter(context, datas);
		iAdapter.setOnImageClickListener(new onImageClickListener() {
			@Override
			public void onImageClick(int position) {
				Toast.makeText(context, "position:"+position, 0).show();
			}
		});
		if(datas != null && datas.size() > 1){
			iAdapter.setInfiniteLoop(true);
		}
		viewPager.setAdapter(iAdapter);
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

        viewPager.setInterval(2000);
        viewPager.startAutoScroll();
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % ListUtils.getSize(datas));
	}
	
	public class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
        	int currentPosition =iAdapter.getPosition(position);
        	
        	setBananerBackgroundResource(currentPosition);
        }
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageScrollStateChanged(int arg0) {}
    }
	
	private void setBananerBackgroundResource(int currentPosition) {
		//        	Toast.makeText(context, "��ѡ����ǣ�"+currentPosition, 0).show();
    	for(int selectPosition =0;selectPosition < points.size();selectPosition++){
    		if(currentPosition == selectPosition){
    			points.get(selectPosition).setBackgroundResource(R.drawable.shape_banner_point_select);
    		}else{
    			points.get(selectPosition).setBackgroundResource(R.drawable.shape_banner_point_default);
    		}
    	}
	}
	
	public void stopAutoScroll(){
		if(viewPager != null){
			viewPager.stopAutoScroll();
		}
	}
}
