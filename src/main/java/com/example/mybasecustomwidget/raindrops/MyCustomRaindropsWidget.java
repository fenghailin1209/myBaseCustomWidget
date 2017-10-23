package com.example.mybasecustomwidget.raindrops;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.example.mybasecustomwidget.utils.AndroidUtil;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;

public class MyCustomRaindropsWidget extends ImageView{

	private static final String TAG = MyCustomRaindropsWidget.class.getSimpleName();
	private int width,height;
	private float radius;
	private Paint paint;
	private float dHeight;
	
	private float dy;
	private float startY;
	private int strokeWidth;
	
	public MyCustomRaindropsWidget(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init();
	}


	public MyCustomRaindropsWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MyCustomRaindropsWidget(Context context) {
		super(context);
		init();
	}

	
	private void init() {
		radius = AndroidUtil.dip2px(getContext(), 20);
		
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.GRAY);
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
		strokeWidth = 2;
		paint.setStrokeWidth(strokeWidth);
		paint.setStrokeCap(Cap.ROUND);
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawCircle(width/2, height/2 , radius, paint);
		
		dHeight += dy/3;
		
		Path path = new Path();
		path.moveTo(width/2 - radius + strokeWidth, height/2);
		path.quadTo(width/2 - radius * 1 / 20, height / 2 + (dHeight - height / 2) / 2, width/2, dHeight);
		path.quadTo(width/2 + radius * 1 / 20, height / 2 + (dHeight - height / 2) / 2, width/2 + radius - strokeWidth, height/2);
		path.close();
		canvas.drawPath(path, paint);
		
		Log.d(TAG, "--->>>dHeight:"+dHeight+",height:"+height);
		
		
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		width = w;
		height = h;
		
		dHeight = height/2;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startY = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			float mY = event.getY();
			dy = mY - startY;
			startY = mY;
			invalidate();
			Log.d(TAG, "--->>>dy:"+dy+",startY:"+startY+",my:"+mY);
			break;
		case MotionEvent.ACTION_UP:
			dy = startY = 0;
			upAnimator();
			break;
		}
		return true;
	}
	
	private void upAnimator(){
		ValueAnimator valueAnimator = ValueAnimator.ofFloat(dHeight,height/2);
		valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
		valueAnimator.setDuration(1000);
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator arg0) {
				float value = (Float) arg0.getAnimatedValue();
				Log.d(TAG, "--->>>value:"+value);
				dHeight = value;
				invalidate();
			}
		});
		valueAnimator.start();
	}
	
}
