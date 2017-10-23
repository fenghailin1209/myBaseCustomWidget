package com.example.mybasecustomwidget.utils;

import android.os.Environment;

import java.io.File;

public class FileCreateUtil {

	public static String projectFilePath = Environment.getExternalStorageDirectory() + "/weixin";
	public static String projectImageFilePath = projectFilePath + "/image";

	public static void initCreateFile(){
		makeDirs(projectFilePath);
		makeDirs(projectImageFilePath);
	}
	
	public static void makeDirs(String path) {
		File f_pic = new File(path);
		if (!f_pic.exists()) {
			f_pic.mkdirs();
		}
	}

	public static void deleteFile(String filePath){
		File file = new File (filePath);
		if (file.exists()) {
			file.delete();
		}
	}
	
}
