package com.example.mybasecustomwidget.webview.html;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/9/1.
 */
// js通信接口
public class JavascriptInterface {
    private Context context;

    public JavascriptInterface(Context context) {
        this.context = context;
    }

    public void openImage(String img, String[] imgs) {
        Toast.makeText(context, img + ",imgs:" + imgs.length, 1).show();
        if (imgs != null && imgs.length > 0) {
            for (String str : imgs) {
                Log.i("", "--->>>imgStr:" + str);
            }
        }
    }
}
