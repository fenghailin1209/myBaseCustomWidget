package com.example.mybasecustomwidget.measure;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class ViewMeasureView extends View{

	public ViewMeasureView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ViewMeasureView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ViewMeasureView(Context context) {
		super(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//Android默认的模式为EXACTLY所以我们这里要为其设置最小值才行
		setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
	}

	/**
	 * 得到经过测量的宽度
	 * @param widthMeasureSpec
	 * @return
	 */
	private int measureWidth(int widthMeasureSpec) {
		int width;
		int defaultMode = MeasureSpec.getMode(widthMeasureSpec);
		int defaultWidth = MeasureSpec.getSize(widthMeasureSpec);
		if(defaultMode == MeasureSpec.EXACTLY){
			width = defaultWidth;
		}else{
			//默认最小值为200（可以作为模板代码）
			width = 200;
			width = Math.min(defaultWidth, width);
		}
		return width;
	}

	/**
	 * 得到经过测量的高度
	 * @param widthMeasureSpec
	 * @return
	 */
	private int measureHeight(int heightMeasureSpec) {
		int height;
		int defaultMode = MeasureSpec.getMode(heightMeasureSpec);
		int defaultHeight = MeasureSpec.getSize(heightMeasureSpec);
		if(defaultMode == MeasureSpec.EXACTLY){
			height = defaultHeight;
		}else{
			//默认最小值为200（可以作为模板代码）
			height = 200;
			height = Math.min(defaultHeight,height);
		}
		return height;
	}
	
}
