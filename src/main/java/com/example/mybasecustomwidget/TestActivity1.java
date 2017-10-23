package com.example.mybasecustomwidget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TestActivity1 extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
	}
	
	public void BTN(View view){
		Intent intent = new Intent(this,TestActivity2.class);
		startActivity(intent);
		finish();
	}
}
