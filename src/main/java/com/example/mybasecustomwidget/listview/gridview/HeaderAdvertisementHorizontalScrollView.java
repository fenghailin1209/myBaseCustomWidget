package com.example.mybasecustomwidget.listview.gridview;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mybasecustomwidget.MyCustomApplication;
import com.example.mybasecustomwidget.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class HeaderAdvertisementHorizontalScrollView implements OnClickListener{
	
	private Context context;
	private View view;
	
	private View id_jgyw_new_ll,id_jb_sb_new_ll,id_fwsc_new_ll;
	
	private ImageView id_fwsc_iv;
	
	private String testImageUrl = "http://192.168.0.80:9022/upload/sys_push/20160615110926568.png";
	
	public HeaderAdvertisementHorizontalScrollView(Context context,View view){
		this.context = context;
		this.view = view;
	}
	
	public void operation(){
		initView();
		
		setListener();

		loadData();
	}

	private void loadData() {
		ImageLoader.getInstance().displayImage(testImageUrl, id_fwsc_iv, MyCustomApplication.options);
	}

	private void initView() {
		id_jgyw_new_ll = view.findViewById(R.id.id_jgyw_new_ll);
		id_jb_sb_new_ll = view.findViewById(R.id.id_jb_sb_new_ll);
		id_fwsc_new_ll = view.findViewById(R.id.id_fwsc_new_ll);
		id_fwsc_iv = (ImageView) view.findViewById(R.id.id_fwsc_iv);
	}
	
	private void setListener() {
		id_jgyw_new_ll.setOnClickListener(this);
		id_jb_sb_new_ll.setOnClickListener(this);
		id_fwsc_new_ll.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.id_jgyw_new_ll:
			Toast.makeText(context, "id_jgyw_new_ll",0).show();
			break;
		case R.id.id_jb_sb_new_ll:
			Toast.makeText(context, "id_jb_sb_new_ll",0).show();
			break;
		case R.id.id_fwsc_new_ll:
			Toast.makeText(context, "id_fwsc_new_ll",0).show();
			break;

		default:
			break;
		}
	}

}
