package com.example.mybasecustomwidget.poly;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mybasecustomwidget.R;

public class PolyLineViewActivity extends Activity {

	private PolylineView plv;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_poly_line_view);

		plv = (PolylineView) findViewById(R.id.plv);

		initData();
	}


	private void initData() {
		List<PointF> pointFs = new ArrayList<PointF>();
		pointFs.add(new PointF(0.3F, 0.5F));  
        pointFs.add(new PointF(1F, 2.7F));  
        pointFs.add(new PointF(2F, 3.5F));  
        pointFs.add(new PointF(3F, 3.2F));  
        pointFs.add(new PointF(4F, 1.8F));  
        pointFs.add(new PointF(5F, 1.5F));  
        pointFs.add(new PointF(6F, 2.2F));  
        pointFs.add(new PointF(7F, 5.5F));  
        pointFs.add(new PointF(8F, 7F));  
        pointFs.add(new PointF(8.6F, 5.7F));
		
		plv.setData(pointFs, "Money", "Time");
	}
}
