package com.example.mybasecustomwidget.coordinatorlayout;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/28.
 */
public class TempView_02 extends TextView{


    private float x,y,lastX,lastY;


    public TempView_02(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getRawX();
        y = event.getRawY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                CoordinatorLayout.MarginLayoutParams params = (CoordinatorLayout.MarginLayoutParams) getLayoutParams();

                float xSpace = x - lastX;
                float ySpace = y - lastY;

                float newLeftMargin = params.leftMargin + xSpace;
                float newTopMargin = params.topMargin + ySpace;

                params.leftMargin = (int) newLeftMargin;
                params.topMargin = (int) newTopMargin;

                setLayoutParams(params);

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        lastX = x;
        lastY = y;
        return true;
    }
}
