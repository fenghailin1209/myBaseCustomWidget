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
		//AndroidĬ�ϵ�ģʽΪEXACTLY������������ҪΪ��������Сֵ����
		setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
	}

	/**
	 * �õ����������Ŀ��
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
			//Ĭ����СֵΪ200��������Ϊģ����룩
			width = 200;
			width = Math.min(defaultWidth, width);
		}
		return width;
	}

	/**
	 * �õ����������ĸ߶�
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
			//Ĭ����СֵΪ200��������Ϊģ����룩
			height = 200;
			height = Math.min(defaultHeight,height);
		}
		return height;
	}
	
}
