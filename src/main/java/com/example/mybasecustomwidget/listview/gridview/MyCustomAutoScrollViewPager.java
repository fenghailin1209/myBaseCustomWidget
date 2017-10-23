package com.example.mybasecustomwidget.listview.gridview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class MyCustomAutoScrollViewPager extends AutoScrollViewPager {

	public MyCustomAutoScrollViewPager(Context paramContext,
			AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
	}

	public MyCustomAutoScrollViewPager(Context paramContext) {
		super(paramContext);
	}
	
	float downX1,downY,moveX,moveY,dX,dY;
	@Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
    	switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downX1 = ev.getX();
			downY = ev.getY();
			getParent().requestDisallowInterceptTouchEvent(true);
			break;
		case MotionEvent.ACTION_MOVE:
			moveX = ev.getX();
			moveY = ev.getY();
			
			dX = Math.abs(downX1 - moveX);
			dY = Math.abs(downY - moveY);
			
			if(dY > dX){
				//别的子View处理
				getParent().requestDisallowInterceptTouchEvent(false);
			}else{
				//自己处理
				getParent().requestDisallowInterceptTouchEvent(true);
			}
			
			break;
		default:
			break;
		}
    	 return super.dispatchTouchEvent(ev);
	}
}
