package com.example.mybasecustomwidget.listview.gridview;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.text.TextUtils.TruncateAt;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mybasecustomwidget.R;
import com.example.mybasecustomwidget.utils.AndroidUtil;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.ObjectAnimator;

public class HeaderAdvertisementVerticalAnimator {

	private static final int LEFT_AND_RIGHT_TEXTVIEW_MAGRIN = 10;
	
	private static final int LAYOUT_LEFT_MAGRIN = 25;

	private static final int ANIMATOR_VIEW_LINNER_LAYOUT_HEIGHT = 45;
	
	private static final int ANIMATOR_VIEW_LEFT_HEIGHT = 50;
	
	private static final int WHAT_ANIMATOR = 1001;

	private static final int ANIMATOR_TIME = 1000;
	
	private static final int DELATE_TIME = 2000;
	
	private int currentPosition;
	
	private Context context;
	private ArrayList<String> datas;
	private LinearLayout id_vertical_advertisement_animator_ll;
	
	private ObjectAnimator objectAnimator;
	
	private onLayoutClickListener onLayoutClickListener;
	
	public void setOnLayoutClickListener(onLayoutClickListener onLayoutClickListener){
		this.onLayoutClickListener = onLayoutClickListener;
	}
	
	public interface onLayoutClickListener{
		public void onLayoutClick(int position);
	}
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case WHAT_ANIMATOR:
				currentPosition ++;
				startVerticalAnimator(currentPosition);
				break;
			default:
				break;
			}
		};
	};

	public HeaderAdvertisementVerticalAnimator(Context context,ArrayList<String> datas,ViewGroup vg){
		this.context = context;
		this.datas = datas;
		initView(vg);
	}

	private void initView(ViewGroup vg) {
		id_vertical_advertisement_animator_ll = (LinearLayout) vg.findViewById(R.id.id_vertical_advertisement_animator_ll);
		initVerticalAnimatorView();
	}

	private void initVerticalAnimatorView() {
		if(datas != null){
			LinearLayout.LayoutParams linParams = new LinearLayout.LayoutParams(AndroidUtil.getScreenWidth(context), AndroidUtil.dip2px(context, ANIMATOR_VIEW_LINNER_LAYOUT_HEIGHT) * (datas.size() + 1));
			//LinearLayout.LayoutParams linParams = new LinearLayout.LayoutParams(AndroidUtil.getScreenWidth(context), AndroidUtil.dip2px(context, ANIMATOR_VIEW_LINNER_LAYOUT_HEIGHT) * datas.size());
			linParams.setMargins(LAYOUT_LEFT_MAGRIN, 0, 0, 0);
			id_vertical_advertisement_animator_ll.setLayoutParams(linParams);
			
			for(int currentPosition=0;currentPosition<datas.size();currentPosition++){
				final int position = currentPosition;
				String text = datas.get(currentPosition);
				TextView child = initTextView(position, text);
				
				id_vertical_advertisement_animator_ll.addView(child);
			}
			
			//在最后面再添加一个view
			TextView lastView = initTextView(0, datas.get(0));
			id_vertical_advertisement_animator_ll.addView(lastView);
		}
	}

	private TextView initTextView(final int position, String text) {
		TextView child = new TextView(context);
		child.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(onLayoutClickListener != null){
					onLayoutClickListener.onLayoutClick(position);
				}
			}
		});
		child.setText(text);
		child.setGravity(Gravity.CENTER_VERTICAL);
		child.setTextColor(Color.BLACK);
		child.setMaxLines(1);
		child.setEllipsize(TruncateAt.END);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(AndroidUtil.getScreenWidth(context) - AndroidUtil.dip2px(context, ANIMATOR_VIEW_LEFT_HEIGHT + LEFT_AND_RIGHT_TEXTVIEW_MAGRIN * 2), AndroidUtil.dip2px(context, ANIMATOR_VIEW_LINNER_LAYOUT_HEIGHT));
		params.setMargins(LEFT_AND_RIGHT_TEXTVIEW_MAGRIN, 0, LEFT_AND_RIGHT_TEXTVIEW_MAGRIN, 0);
		child.setLayoutParams(params);
		return child;
	}

	public void startVerticalAnimator(int currentPosition){
		currentPosition = resetCurrentPosition(currentPosition);
		
		this.currentPosition  = currentPosition;
		
		float animatorY = (AndroidUtil.dip2px(context, ANIMATOR_VIEW_LINNER_LAYOUT_HEIGHT));
		float values1 = (currentPosition - 1) * (- animatorY);
		float values2 = (currentPosition - 1) * (- animatorY) + (- animatorY);
		
		objectAnimator = ObjectAnimator.ofFloat(id_vertical_advertisement_animator_ll, "TranslationY",values1,values2);
		objectAnimator.setDuration(ANIMATOR_TIME);
		objectAnimator.start();
		objectAnimator.setInterpolator(new AccelerateInterpolator());
		objectAnimator.addListener(new AnimatorListener() {
			@Override
			public void onAnimationStart(Animator arg0) {}
			@Override
			public void onAnimationRepeat(Animator arg0) {}
			@Override
			public void onAnimationEnd(Animator arg0) {
				handler.sendEmptyMessageDelayed(WHAT_ANIMATOR, DELATE_TIME);
			}
			@Override
			public void onAnimationCancel(Animator arg0) {}
		});
	}

	private int resetCurrentPosition(int currentPosition) {
		//if(currentPosition == datas.size()){
		//currentPosition = 0;
		if(currentPosition == datas.size() + 1){
			currentPosition = 1;
		}
		return currentPosition;
	}
	
	public void clearAnimator(){
		if(objectAnimator != null && objectAnimator.isRunning()){
			objectAnimator.clearAllAnimations();
		}
	}
}
