package com.example.mybasecustomwidget.guidemap;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.example.mybasecustomwidget.R;
import com.example.mybasecustomwidget.guidemap.GuideMapViewGroup.setOnMeanItemSelectListenner;

public class GuideMapViewActivity extends Activity {

	
	private int resIds[] = new int[]{
            R.drawable.selector_jgyw_tsxx,
            R.drawable.selector_jgyw_wzcx,
            R.drawable.selector_jgyw_rf,
            R.drawable.selector_jgyw_rj,
            R.drawable.selector_jgyw_zt
    };
    private String texts[] = new String[]{"111 ", "222", "333",
            "544", "666"
    };
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		init();
	}

	private void init() {
		GuideMapViewGroup vg = (GuideMapViewGroup) findViewById(R.id.vg);

//		vg.setPaintColor(getResources().getColor(R.color.orange));
//		vg.setPaintWidth(8);
//		vg.setArrowsWidth(20);
//		vg.setDashedSpace(15);
		vg.setMeanItemIconsAndTexts(resIds, texts);
		vg.setOnMeanItemSelectListenner(new setOnMeanItemSelectListenner() {
			
			@Override
			public void onMeanSelect(int position) {
				// TODO Auto-generated method stub
				if(position != -1)
				Toast.makeText(GuideMapViewActivity.this, texts[position] + "",0).show();
			}
		});
		
	}
}
