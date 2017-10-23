package com.example.mybasecustomwidget.listview.gridview;

import java.util.List;

public class ListUtils {

	public static int getSize(List<Integer> imageIdList) {
		if(imageIdList != null && imageIdList.size() > 0){
			return imageIdList.size();
		}
		return 0;
	}

}
