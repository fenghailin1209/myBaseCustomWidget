package com.example.mybasecustomwidget.view;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/7/20.
 */
public class MyCustomCollapsingToolbarLayout extends CollapsingToolbarLayout{
    public MyCustomCollapsingToolbarLayout(Context context) {
        super(context);
    }

    public MyCustomCollapsingToolbarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCustomCollapsingToolbarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }
}
