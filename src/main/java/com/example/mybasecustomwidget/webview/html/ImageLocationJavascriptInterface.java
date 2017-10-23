package com.example.mybasecustomwidget.webview.html;

import android.content.Context;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2017/9/1.
 */
// js通信接口
public class ImageLocationJavascriptInterface {
    private static final String TAG = ImageLocationJavascriptInterface.class.getSimpleName();
    private Context context;

    public ImageLocationJavascriptInterface(Context context) {
        this.context = context;
    }

    public void imageLocation(int[] ElePosition) {
        EventImageLocation eventImageLocation = new EventImageLocation();
        eventImageLocation.setElePosition(ElePosition);
        EventBus.getDefault().post(eventImageLocation);
    }
}
