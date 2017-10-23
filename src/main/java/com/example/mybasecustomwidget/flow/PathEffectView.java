package com.example.mybasecustomwidget.flow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathDashPathEffect.Style;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

public class PathEffectView extends View{

	private Paint mPaint;
//	private Paint starPaint;
//	private Path mPath;
//	private Path star;

	private float phase = 0;
	
	public PathEffectView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public PathEffectView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public PathEffectView(Context context) {
		super(context);
		init();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		PathEffect pe = new DashPathEffect(new float[]{10f,3f}, phase);
		mPaint.setPathEffect(pe);
		canvas.drawLine(0, 40, 1000, 40, mPaint);
	}
	
	private void init(){
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeWidth(2);
		mPaint.setColor(Color.RED);
	}
	
	
}
