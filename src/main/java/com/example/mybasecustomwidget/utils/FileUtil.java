package com.example.mybasecustomwidget.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

public class FileUtil {

	
	 /**
	  * 获取指定文件大小
	  * 
	  * @param f
	  * @return
	  * @throws Exception
	  */
	public static long getFileSize(String  path) {
		long size = 0;
		File file = new File(path);
		FileInputStream fis = null;
		try {
			if (file.exists()) {
				fis = new FileInputStream(file);
				size = fis.available();
				fis.close();
			} else {
				Log.e("获取文件大小", "文件不存在!");
			}
		} catch (Exception e) {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}
		return size;
	}

	public static void createFile(String outPath) throws IOException {
		File file = new File(outPath);
		if (!file.exists()) {
			// 创建apk文件
			file.createNewFile();
		}
	}

	public static void deleteFile(String path){
		try {
			File file = new File (path);
			if (file.exists()) {
				file.delete();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 获取拍照相片存储文件
	 * @param context
	 * @return
	 */
	public static File createFile(Context context){
		File file;
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			String timeStamp = String.valueOf(new Date().getTime());
			file = new File(Environment.getExternalStorageDirectory() +
					File.separator + timeStamp+".jpg");
		}else{
			File cacheDir = context.getCacheDir();
			String timeStamp = String.valueOf(new Date().getTime());
			file = new File(cacheDir, timeStamp+".jpg");
		}
		return file;
	}

}
