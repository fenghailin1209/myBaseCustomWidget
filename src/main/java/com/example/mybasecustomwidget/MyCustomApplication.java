package com.example.mybasecustomwidget;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.zhy.http.okhttp.OkHttpUtils;

import net.tsz.afinal.FinalBitmap;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


public class MyCustomApplication extends Application{
	
	public static DisplayImageOptions options;
	
	public static FinalBitmap fb;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		initImageLoader();
		
		getAppMaxMemorySize();
		
		initAfinal();

		initOkHttp();
	}

	private void initOkHttp() {
		OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
				.connectTimeout(10000L, TimeUnit.MILLISECONDS)
				.readTimeout(10000L, TimeUnit.MILLISECONDS)
				//其他配置
				.build();

		OkHttpUtils.initClient(okHttpClient);
	}

	private void initAfinal() {
		fb = FinalBitmap.create(getApplicationContext());
		fb.configLoadingImage(R.drawable.huati_head_default);
		fb.configLoadfailImage(R.drawable.huati_head_default);
	}
	
	private void getAppMaxMemorySize(){
		long maxMomery = (Runtime.getRuntime().maxMemory())/1024;
		Log.d("","--->>>maxMomery:"+maxMomery+"KB");//huawei 128M,sanxing 192M
	}
	
	private void initImageLoader() {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.discCacheFileCount(200)// Set max cache file count in SD card
				.build();
		options = new DisplayImageOptions.Builder()
		 .showStubImage(R.drawable.yd_default_img)
		 .showImageForEmptyUri(R.drawable.image_load_fail)
		 .showImageOnFail(R.drawable.image_load_fail)
		 .cacheInMemory(true).cacheOnDisc(true)
		 .imageScaleType(ImageScaleType.EXACTLY)
		 .bitmapConfig(Bitmap.Config.ARGB_8888)
//		 .displayer(new RoundedBitmapDisplayer(0))
		.build();
		ImageLoader.getInstance().init(config);
	}
}
