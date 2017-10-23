package com.example.mybasecustomwidget.listview.gridview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class UIGridView extends GridView{
	 public UIGridView(Context context, AttributeSet attrs) {     
	        super(context, attrs);     
	    }     
	    
	    public UIGridView(Context context) {     
	        super(context);     
	    }     
	    
	    public UIGridView(Context context, AttributeSet attrs, int defStyle) {     
	        super(context, attrs, defStyle);     
	    }     
	    
	    @Override     
	    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {     
	    
	        int expandSpec = MeasureSpec.makeMeasureSpec(     
	                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
	        super.onMeasure(widthMeasureSpec, expandSpec);     
	    }     
}
