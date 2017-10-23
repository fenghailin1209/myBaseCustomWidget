package com.example.mybasecustomwidget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/9/29.
 */
public class MyCustomListView extends ListView {
    public MyCustomListView(Context context) {
        super(context);
    }

    public MyCustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCustomListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
