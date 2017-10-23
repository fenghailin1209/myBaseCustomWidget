package com.example.mybasecustomwidget.buttom_move;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.mybasecustomwidget.R;
import com.example.mybasecustomwidget.utils.AndroidUtil;

public class ButtonMoveActivity extends Activity {

	private int lastX, lastY;
	private float downX, downY;
	private float moveX, moveY;
	private int screenWidth, screenHeight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_button_move);

		initData();

		initView();
	}

	private void initData() {
		screenWidth = AndroidUtil.getScreenWidth(this);
		screenHeight = AndroidUtil.getScreenHeight(this);
	}

	private void initView() {
		Button b = (Button) findViewById(R.id.button1);

		b.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int ea = event.getAction();
				switch (ea) {
				case MotionEvent.ACTION_DOWN:
					lastX = (int) event.getRawX();
					lastY = (int) event.getRawY();
					
					//fhl add
					downX = event.getRawX();
					downY = event.getRawY();
					break;
				case MotionEvent.ACTION_MOVE:
					//fhl add
					moveX = event.getRawX();
					moveY = event.getRawY();
					
					
					
					int dx = (int) event.getRawX() - lastX;
					int dy = (int) event.getRawY() - lastY;

					int l = v.getLeft() + dx;
					int b = v.getBottom() + dy;
					int r = v.getRight() + dx;
					int t = v.getTop() + dy;
					if (l < 0) {
						l = 0;
						r = l + v.getWidth();
					}

					if (t < 0) {
						t = 0;
						b = t + v.getHeight();

					}

					if (r > screenWidth) {
						r = screenWidth;
						l = r - v.getWidth();
					}

					if (b > screenHeight) {
						b = screenHeight;
						l = b - v.getHeight();
					}
					v.layout(l, t, r, b);
					lastX = (int) event.getRawX();
					lastY = (int) event.getRawY();
					v.postInvalidate();
					break;

				case MotionEvent.ACTION_UP:
					//fhl add
					if(downX == moveX && downY == moveY){
						 Toast.makeText(ButtonMoveActivity.this, "sss", 0).show();
					}
					break;
				default:
					break;
				}
				return false;
			}
		});

	}

}
