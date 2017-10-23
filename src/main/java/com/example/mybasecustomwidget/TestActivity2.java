package com.example.mybasecustomwidget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class TestActivity2 extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test2);
	}
	
	public void BTN(View view){
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
		finish();
	}
}
