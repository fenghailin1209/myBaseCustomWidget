package com.example.mybasecustomwidget.baseAdapterHelper.multipleSupport;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mybasecustomwidget.R;

public class BaseAdapterHelperMultipleSupportFragment extends Fragment{

	private Context context;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		context = getActivity();
		
		
		View view = container.inflate(context, R.layout.fragment_base_adapter_helper_multiple_support, null);
		Button sat = (Button) view.findViewById(R.id.sat);
		Button mst = (Button) view.findViewById(R.id.mst);
		
		sat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, SimpleTestActivity.class);
				startActivity(intent);
			}
		});
		mst.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, ChatActivity.class);
				startActivity(intent);
			}
		});
		
		return view;
	}

}
