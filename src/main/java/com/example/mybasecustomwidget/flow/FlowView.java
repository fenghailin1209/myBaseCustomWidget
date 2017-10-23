package com.example.mybasecustomwidget.flow;

import com.example.mybasecustomwidget.R;
import com.example.mybasecustomwidget.utils.AndroidUtil;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PathEffect;
import android.graphics.Paint.Cap;
import android.util.AttributeSet;
import android.widget.ImageView;

public class FlowView extends ImageView{

	private static final float DEFAULT_FLOW_HEIGHT = 2;
	private static final float DEFAULT_ARROWS_LEHGTH = 2;
	private float flowHeight;
	private int flowColor;
	private float arrowsLength;
	private Paint paint;
	
	public FlowView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	public FlowView(Context context) {
		this(context,null);
	}
	
	public FlowView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.flowView, defStyle, 0);
		flowHeight = ta.getDimension(R.styleable.flowView_flowHeight, AndroidUtil.dip2px(context, DEFAULT_FLOW_HEIGHT));
		arrowsLength = ta.getDimension(R.styleable.flowView_arrowsLength, AndroidUtil.dip2px(context, DEFAULT_ARROWS_LEHGTH));
		flowColor = ta.getColor(R.styleable.flowView_flowColor, getResources().getColor(R.color.orange));
		
		init();
	}
	
	private void init() {
		PathEffect effect = new DashPathEffect(new float[]{8,8}, 1);
		
		paint = new Paint();
		paint.setStrokeCap(Cap.ROUND);
		paint.setAntiAlias(true);
		paint.setColor(flowColor);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(flowHeight);
		paint.setPathEffect(effect);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		drawLine(canvas);
		
	}

	private void drawLine(Canvas canvas) {
		canvas.drawLine(0, getMeasuredHeight()/2, getMeasuredWidth(), getMeasuredHeight()/2, paint);
		
	}
	
}
