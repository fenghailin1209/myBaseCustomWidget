package com.example.mybasecustomwidget.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import android.os.Environment;
import android.util.Log;

@SuppressWarnings("unused")
public class FileWriteRead {
	private String fliePath;
	private String fileName;
	private String TAG = "FileWriteRead";
	public FileWriteRead(String filePath,String fileName){
		this.fliePath = filePath;
		this.fileName = fileName;
		createFile(this.fliePath, this.fileName);
	}
	public FileWriteRead(String fileName) {
		this.fliePath = Environment.getExternalStorageDirectory() + "/html";
		this.fileName = fileName;
		createFile(this.fliePath, this.fileName);
	}
	public void createFile(String path, String name) {
		File f = new File(path);
		if (!f.exists()) {
			if(f.mkdirs())
			{
				makefileDir(path,name);
				Log.i("file", "name-->>" + name);
			}
		}else{
			makefileDir(path,name);
			Log.i("file", "name-->>" + name);
		}
	}
	public static void makefileDir(String path,String name) {
		File f_pic = new File(path+ "/" + name);
		if (!f_pic.exists()) {
			try {
				f_pic.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean writeFileSdcard(String message){ 
		try{ 
		   FileOutputStream fout = new FileOutputStream(fliePath +"/"+fileName);
	       Log.i("path", TAG+"->writeFileSdcard.path:" + fliePath + "/"+fileName);
	       byte [] bytes = message.getBytes(); 
	       fout.write(bytes);         
	       fout.close(); 
	   } 
	   catch(Exception e){ 
	       e.printStackTrace(); 
	       return false;
	   } 
		return true;
		
	}
	
	/**
	 * 通过追加的方式不断写入到文件中并换行
	 * @param message
	 * @return
	 */
	public boolean writeFileSdcardByAppend(String message){ 
		try{ 
			FileOutputStream fout = new FileOutputStream(fliePath +"/"+fileName,true);
			byte [] bytes = message.getBytes(); 
			fout.write(bytes);     
			fout.write("\r\n".getBytes());
			fout.close(); 
		} 
		catch(Exception e){ 
			e.printStackTrace(); 
			return false;
		} 
		return true;
	}
	
	public boolean delFile(String filePath){
		File file = new File(filePath);
		boolean r = false;
		if (file.exists()) {
			r = file.delete();
		}
		return r;
	}
	
	public String readFileSdcard(){ 
		StringBuffer sb = new StringBuffer();
		int b = 0;
	    try{
	    	FileInputStream fout = new FileInputStream(fliePath+"/"+fileName);
	    	while((b=fout.read()) != -1){
	    	    sb.append((char)b);
	    	}
	    	fout.close(); 
	    }  
	    catch(Exception e){ 
	        e.printStackTrace(); 
	    }
	    String JsonString="";
		try {
			JsonString = new String(sb.toString().getBytes("ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	    return JsonString; 
	}
	
	public boolean checkFileSdcard(){ 
		File f=new File(fliePath+"/"+fileName);
		if(!f.exists()){
                return false;
        }
        return true;
		
	}
	
}
