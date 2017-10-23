package com.example.mybasecustomwidget.canvas;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mybasecustomwidget.R;

public class MyCustomCanvasActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_my_canvas);

		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.whats_new_04_02).copy(Bitmap.Config.ARGB_8888, true);
		Canvas canvas = new Canvas(bitmap);

		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStrokeWidth(5);
		paint.setColor(Color.RED);

		canvas.drawLine(0, 0, 80, 80, paint);

		ImageView iv = (ImageView) findViewById(R.id.iv);
		iv.setImageBitmap(bitmap);

	}


}
