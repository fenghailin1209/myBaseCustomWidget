package com.example.mybasecustomwidget.gesture;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.mybasecustomwidget.R;
import com.example.mybasecustomwidget.utils.AndroidUtil;

/**
 * Created by Administrator on 2017/7/28.
 */
public class MyCustomGestureRelativeLayout extends RelativeLayout {


    private static final String TAG = MyCustomGestureRelativeLayout.class.getSimpleName();

    private ViewGroup viewGroup;
    private View id_title_view;
    private int sw, titleViewHeight, myCustomGestureRelativeLayoutHeight;

    public MyCustomGestureRelativeLayout(Context context) {
        super(context);
    }

    public MyCustomGestureRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCustomGestureRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyCustomGestureRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        init();
    }

    private void init() {
        viewGroup = (ViewGroup) findViewById(R.id.id_vg);
        id_title_view = findViewById(R.id.id_title_view);
        titleViewHeight = id_title_view.getMeasuredHeight();
        sw = viewGroup.getMeasuredHeight();
        myCustomGestureRelativeLayoutHeight = AndroidUtil.dip2px(getContext(), 450);
        Log.i(TAG, "--->>>sw:" + sw + ",titleViewHeight：" + titleViewHeight + ",myCustomGestureRelativeLayoutHeight：" + myCustomGestureRelativeLayoutHeight);
    }

    boolean pageIsTop = true;//页面是否在上面，默认是
    float startY, moveY, dY, totalY, viewGropScrollY;
    boolean isTouchTitle;
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                totalY = 0;
                dY = 0;
                viewGropScrollY = 0;
                isTouchTitle = isTouchTitle(event.getRawX(),event.getRawY());
                startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int scrollY = getScrollY();
                moveY = event.getY();
                dY = moveY - startY;
                totalY += dY;
                Log.i(TAG, "--->>>dY:" + dY + ",scrollY:" + scrollY + ",sw:" + sw + ",xxx:" + (sw - titleViewHeight) + ",totalY:" + totalY+",isTouchTitle:"+isTouchTitle);
                //控制滑动范围
                boolean topRange = (dY > 0 || scrollY < 0);
//                boolean botttomRange = (dY < 0 && scrollY > -(sw - titleViewHeight) && scrollY < 0);
                if (topRange && isTouchTitle) {
                    startY = moveY;
                    viewGroup.scrollBy(0, (int) -dY);
                }

                break;
            case MotionEvent.ACTION_UP:
                if(isTouchTitle){
                    if (totalY >= 0) {//向下滑动
                        if (Math.abs(totalY) > sw / 2) {
                            viewGroup.scrollTo(0, (int) -sw + titleViewHeight);//将title留出来
                            pageIsTop = false;
                        } else {
                            viewGroup.scrollTo(0, 0);
                            pageIsTop = true;
                        }
                    } else {//向上滑动
                        if (Math.abs(totalY) > sw / 2) {
                            viewGroup.scrollTo(0, 0);
                            pageIsTop = true;
                        } else {
                            viewGroup.scrollTo(0, (int) -sw);
                            pageIsTop = false;
                        }
                    }
                }
                break;
        }
        return true;
    }

    private boolean isTouchTitle(float x, float y){
        int[] location = new  int[2] ;
        id_title_view.getLocationInWindow(location); //获取在当前窗口内的绝对坐标

        int left = location[0];
        int top = location[1];
        int right = left + id_title_view.getMeasuredWidth();
        int bottom = top + id_title_view.getMeasuredHeight();

        Log.i(TAG,"--->>>left:"+left+",top："+top+",right:"+right+",bottom:"+bottom+",x:"+x+",y:"+y);
        if(x >= left && x <= right && y >= top && y <= bottom){
            return true;
        }
        return false;
    }
}
