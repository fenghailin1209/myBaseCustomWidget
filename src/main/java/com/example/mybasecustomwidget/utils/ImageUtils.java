package com.example.mybasecustomwidget.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

/**
 * 图片工具类
 * @author Administrator
 *
 */
public class ImageUtils {
	
	/**
	 * 得到指定大小的图片
	 * @param mBitmap 图片
	 * @param size 指定大小
	 * @return
	 */
	public static  Bitmap getSmallImage(Bitmap mBitmap, int size) {
		int width = mBitmap.getWidth();
		int height = mBitmap.getHeight();
		float scal;
		Bitmap b = null;
		if (width > size || height > size) {
			if (width > height) {
				scal = size / (float) width;
				b = Bitmap.createScaledBitmap(mBitmap, (int) (width * scal),
						(int) (height * scal), false);
			} else {
				scal = size / (float) height;
				b = Bitmap.createScaledBitmap(mBitmap, (int) (width * scal),
						(int) (height * scal), false);
			}
		} else {
			b = mBitmap;
		}
		return b;
	}
	
	public static  Bitmap getSmallImageAsPath(String path, int size) {
		BitmapFactory.Options options=new Options();
		options.inJustDecodeBounds=true;
		BitmapFactory.decodeFile(path, options);
		int width =options.outWidth;
		int height = options.outHeight;
		int widthS=width/size;
		int heightS=height/size;
		int sampleSize=widthS>heightS?widthS:heightS;
		options.inJustDecodeBounds=false;
		options.inSampleSize=sampleSize;
		Bitmap bm=BitmapFactory.decodeFile(path, options);
		return bm;
	}
	
	
	public static String getSmallImagePath(Context context,String path,int size){
		BitmapFactory.Options options=new Options();
		options.inJustDecodeBounds=true;
		BitmapFactory.decodeFile(path, options);
		int width =options.outWidth;
		int height = options.outHeight;
		int widthS=width/size;
		int heightS=height/size;
		int sampleSize=widthS>heightS?widthS:heightS;
		if(sampleSize<=1)return path;
		options.inJustDecodeBounds=false;
		options.inSampleSize=sampleSize;
		Bitmap bm=BitmapFactory.decodeFile(path, options);
		File file=context.getExternalCacheDir();
		if(!file.exists()){
			file=context.getFilesDir();
		}
		String smallPath=file.getPath()+File.separator+"panda";
		File smallFile=new File(smallPath);
		if(!smallFile.exists())smallFile.mkdir();
		String smallFilePath=smallPath+File.separator+System.currentTimeMillis()+".jpg";
		try {
			FileOutputStream fos = new FileOutputStream(smallFilePath);
			bm.compress(CompressFormat.JPEG, 80, fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return smallFilePath;
	}
	public static File getSmallImageFile(Context context,String path,int size){
		String small=getSmallImagePath(context, path, size);
		return new File(small);
	}
	
	public static String getImageFileName(String url){
		String[] ss=url.split("/");
		if(ss.length>0){
			return ss[ss.length-1];
		}else{
			return null;
		}
	}
	
	/**
	 * 得到指定大小的图片
	 * @param context
	 * @param data 图片数据
	 * @param size 指定大小
	 * @return
	 */
	public static Bitmap getBitmapByByte(Context context,byte[] data,int size){
		BitmapFactory.Options options=new Options();
		//图片不加载到内存中
		options.inJustDecodeBounds=true;
		
		BitmapFactory.decodeByteArray(data, 0, data.length,options);
		int width =options.outWidth;
		int height = options.outHeight;
		int widthS=width/size;
		int heightS=height/size;
		int sampleSize=widthS>heightS?widthS:heightS;
		if(sampleSize<=1)return BitmapFactory.decodeByteArray(data, 0, data.length);
		//图片加载到内存中
		options.inJustDecodeBounds=false;
		options.inSampleSize=sampleSize;
		Bitmap bm=BitmapFactory.decodeByteArray(data, 0, data.length,options);
		return bm;
	}
	/**
	 * 清除缓存
	 * @param context
	 * 2015-4-21 下午5:42:24
	 */
	public static void clearCacheImageResource(Context context){
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.discCacheFileCount(60)// Set max cache file count in SD card
				.build();
		ImageLoader.getInstance().init(config);
		ImageLoader.getInstance().clearMemoryCache();
		ImageLoader.getInstance().clearDiscCache();
	}
}
