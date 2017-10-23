package com.example.mybasecustomwidget.webview.html;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.mybasecustomwidget.utils.AndroidUtil;

/**
 * Created by Administrator on 2017/7/28.
 */
public class MyCustomVerticalityGestureRelativeLayout extends RelativeLayout{


    private static final String TAG = MyCustomVerticalityGestureRelativeLayout.class.getSimpleName();

    private ViewGroup viewGroup;
    private int sw;
    public static final int MAX_SCROLL = 200;

    public MyCustomVerticalityGestureRelativeLayout(Context context) {
        super(context);
    }

    public MyCustomVerticalityGestureRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCustomVerticalityGestureRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyCustomVerticalityGestureRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        init();
    }

    private void init(){
        viewGroup = (ViewGroup) getChildAt(0);
        sw = AndroidUtil.getScreenWidth(getContext());
    }

    float startY, moveY, dY, totalY;
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                totalY = 0;
                dY = 0;

                startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                moveY = event.getY();
                dY = moveY - startY;

                startY = moveY;

                totalY += dY;

                if(totalY < MAX_SCROLL){
                    viewGroup.scrollBy(0,(int) -dY);
                }
                break;
        }
        return true;
    }
}
