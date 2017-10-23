package com.example.mybasecustomwidget;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mybasecustomwidget.AppBarLayout.AppBarLayoutDemoActivity;
import com.example.mybasecustomwidget.AppBarLayout.NestedScrollView.ListView.NestedScrollViewListViewActivity;
import com.example.mybasecustomwidget.afinal.FinalImageLoadingActivity;
import com.example.mybasecustomwidget.antoviewpager.AutoViewPagerActivity;
import com.example.mybasecustomwidget.barrage.BarrageActivity;
import com.example.mybasecustomwidget.baseAdapterHelper.multipleSupport.SimpleTestActivity;
import com.example.mybasecustomwidget.baseadpter.MyCustomBaseAdapterActivity;
import com.example.mybasecustomwidget.buttom_move.ButtonMoveActivity;
import com.example.mybasecustomwidget.canvas.MyCustomCanvasActivity;
import com.example.mybasecustomwidget.canvas.bsrqx.waterview.WaveViewActivity;
import com.example.mybasecustomwidget.coordinatorlayout.CoordinatorLayoutActivity;
import com.example.mybasecustomwidget.coordinatorlayout.CoordinatorLayoutListActivity;
import com.example.mybasecustomwidget.flow.FlowActivity;
import com.example.mybasecustomwidget.gesture.GestureActivity;
import com.example.mybasecustomwidget.gifview.GifViewActivity;
import com.example.mybasecustomwidget.guidemap.GuideMapViewActivity;
import com.example.mybasecustomwidget.invite_friend.InviteFriendActivity;
import com.example.mybasecustomwidget.js.java.JsCallJavaActivity;
import com.example.mybasecustomwidget.js.web_call_local_photo.WebviewCallLocalPhotoActivity;
import com.example.mybasecustomwidget.listview.gridview.MyCustomListViewAndGridViewActivity;
import com.example.mybasecustomwidget.measure.ViewMeasureDemoActivity;
import com.example.mybasecustomwidget.poly.PolyLineViewActivity;
import com.example.mybasecustomwidget.raindrops.RaindropsActivity;
import com.example.mybasecustomwidget.simple_cache.SimpleCacheActivity;
import com.example.mybasecustomwidget.spannablestringsuilder.SpannableStringBuilderDemoActivity;
import com.example.mybasecustomwidget.system_dialog.MyCustomSystemDialogActivity;
import com.example.mybasecustomwidget.utils.Base64;
import com.example.mybasecustomwidget.webview.html.NewsPagerActivity;
import com.example.mybasecustomwidget.webview.html.WebViewHtmlActivity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {

	private static final String TAG = MainActivity.class.getSimpleName();
	private DrawerLayout drawer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0){
			finish();
			return;
		}

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("MyBaseCustomWidget");

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
						.setAction("取消", new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								Toast.makeText(MainActivity.this,"click",0).show();
							}
						}).show();
			}
		});

		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, 0, 0);
		drawer.setDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.id_nav_view);
		navigationView.setNavigationItemSelectedListener(this);

		getBrowsableData(getIntent());

		//main();
	}

	@Override
	public void onBackPressed() {
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		if (id == R.id.Afinal) {
			startActivity(FinalImageLoadingActivity.class);
		} else if (id == R.id.AutoViewPager) {
			startActivity(AutoViewPagerActivity.class);
		} else if (id == R.id.Barrage) {
			startActivity(BarrageActivity.class);
		} else if (id == R.id.multiPleSupport) {
			startActivity(SimpleTestActivity.class);
		}else if (id == R.id.BaseAdapter) {
			startActivity(MyCustomBaseAdapterActivity.class);
		} else if (id == R.id.ButtonMove) {
			startActivity(ButtonMoveActivity.class);
		}else if (id == R.id.Canvas) {
			startActivity(MyCustomCanvasActivity.class);
		} else if (id == R.id.WaterView) {
			startActivity(WaveViewActivity.class);
		} else if (id == R.id.FlowView) {
			startActivity(FlowActivity.class);
		} else if (id == R.id.GiveView) {
			startActivity(GifViewActivity.class);
		}else if (id == R.id.GuideMapView) {
			startActivity(GuideMapViewActivity.class);
		}else if (id == R.id.ListViwe_GridView) {
			startActivity(MyCustomListViewAndGridViewActivity.class);
		}else if (id == R.id.Measure) {
			startActivity(ViewMeasureDemoActivity.class);
		} else if (id == R.id.Poly) {
			startActivity(PolyLineViewActivity.class);
		} else if (id == R.id.RainDrops) {
			startActivity(RaindropsActivity.class);
		} else if (id == R.id.SpanableStringBuilder) {
			startActivity(SpannableStringBuilderDemoActivity.class);
		}else if(id == R.id.CoordinatorLayoutActivity){
			startActivity(CoordinatorLayoutActivity.class);
		}else if(id == R.id.CoordinatorLayoutActivity2){
			startActivity(CoordinatorLayoutListActivity.class);
		}else if(id == R.id.AppBarLayoutDemoActivity){
			startActivity(AppBarLayoutDemoActivity.class);
		}else if(id == R.id.NestedScrollViewListView){
			startActivity(NestedScrollViewListViewActivity.class);
		}else if(id == R.id.InviteFriendActivity){
			startActivity(InviteFriendActivity.class);
		}else if(id == R.id.SimpleCacheActivity){
			startActivity(SimpleCacheActivity.class);
		}else if(id == R.id.JsCallJavaActivity){
			startActivity(JsCallJavaActivity.class);
		}else if(id == R.id.JsBugActivity){
			startActivity(WebviewCallLocalPhotoActivity.class);
		}else if(id == R.id.MyCustomSystemDialogActivity){
			startActivity(MyCustomSystemDialogActivity.class);
		}else if(id == R.id.WebViewHtmlFragment){
			startActivity(WebViewHtmlActivity.class);
		}else if(id == R.id.WebViewHtmlFragment2){
			startActivity(NewsPagerActivity.class);
		}else if(id == R.id.GestureActivity){
			startActivity(GestureActivity.class);
		}

		//drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	private void startActivity(Class clazz){
		Intent intent = new Intent(this,clazz);
		startActivity(intent);
	}

	public void main() {

		  String va="eyJ3YXBfbGluayI6ICJodHRwOi8veG14aW5nLm5ldC93YXAvamllbXUvd2FwMy5waHAiLA0KICAgICAgICAgICAgICAgICAgICAiaHRfdHlwZSI6ICIwIiwNCiAgICAgICAgICAgICAgICAgICAgImh0X2lkIjogIjAiLA0KICAgICAgICAgICAgICAgICAgICAidGl0bGUiOiAi5rWL6K+VIiwNCiAgICAgICAgICAgICAgICAgICAgIm1vZHVsZV90eXBlIjogIjEiLA0KICAgICAgICAgICAgICAgICAgICAibmFtZSI6ICLmtYvor5UiDQogICAgICAgICAgICAgICAgfQ==";

		// TODO Auto-generated method stub
		//String va="eyJ3YXBfbGluayI6ICJodHRwOi8veG14aW5nLm5ldC93YXAvamllbXUvd2FwMy5waHAiLA0KICAgICAgICAgICAgICAgICAgICAiaHRfdHlwZSI6ICIwIiwNCiAgICAgICAgICAgICAgICAgICAgImh0X2lkIjogIjAiLA0KICAgICAgICAgICAgICAgICAgICAidGl0bGUiOiAi5rWL6K VIiwNCiAgICAgICAgICAgICAgICAgICAgIm1vZHVsZV90eXBlIjogIjEiLA0KICAgICAgICAgICAgICAgICAgICAibmFtZSI6ICLmtYvor5UiDQogICAgICAgICAgICAgICAgfQ==";

//		String va="eyJ3YXBfbGluayI6ICJodHRwOi8veG14aW5nLm5ldC93YXAvamllbXUvd2FwMy5waHAiLA0KICAgICAgICAgICAgICAgICAgICAiaHRfdHlwZSI6ICIwIiwNCiAgICAgICAgICAgICAgICAgICAgImh0X2lkIjogIjAiLA0KICAgICAgICAgICAgICAgICAgICAidGl0bGUiOiAi5rWL6K+VIiwNCiAgICAgICAgICAgICAgICAgICAgIm1vZHVsZV90eXBlIjogIjEiLA0KICAgICAgICAgICAgICAgICAgICAibmFtZSI6ICLmtYvor5UiDQogICAgICAgICAgICAgICAgfQ==";

		byte[] b = new byte[0];
		try {
			b = Base64.decode(va);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String bv;
		try {
			bv = new String(b,"UTF-8");
			Log.i(TAG,"--->>>bv:"+bv);
			Toast.makeText(MainActivity.this,"--->>>bv:"+bv,1).show();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getData(String string){
		if(!TextUtils.isEmpty(string)){
			String[] s1 = string.split("\\?");
			if(s1 != null && s1.length >= 2){
				return s1[1].substring(5,s1[1].length());
			}
		}
		return null;
	}

	private void getBrowsableData(Intent intent){
		Uri uri = intent.getData();
		if (uri != null) {
			String urlEncodeStr = getData(uri.toString());
			try {
				byte[] b =Base64.decode(urlEncodeStr);
				String base64EncodeDataStr;
				base64EncodeDataStr = new String(b,"UTF-8");
				Toast.makeText(MainActivity.this,"--->>>base64EncodeDataStr:"+base64EncodeDataStr,1).show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		getBrowsableData(intent);
	}
}
