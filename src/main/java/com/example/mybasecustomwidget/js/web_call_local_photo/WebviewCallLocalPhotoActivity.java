package com.example.mybasecustomwidget.js.web_call_local_photo;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.mybasecustomwidget.R;

/**
 * Created by Administrator on 2016/10/19.
 */
public class WebviewCallLocalPhotoActivity extends Activity{

    private WebView webView;
    private String url = "http://192.168.0.202/22.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_bug);

        webView = (WebView) findViewById(R.id.id_webView);

        webView.setVerticalScrollbarOverlay(true);
        //设置WebView支持JavaScript
        webView.getSettings().setJavaScriptEnabled(true);

        //在js中调用本地java方法
//        webView.addJavascriptInterface(new JsInterface(), "jsInterface");`
        //webView.loadUrl("file:///android_asset/2.html");
        webView.loadUrl(url);
    }

//    private class JsInterface {
//
//        @JavascriptInterface
//        public String onButtonClick(String text) {
//            final String str = text;
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(getApplicationContext(), "onButtonClick: text = " + str, Toast.LENGTH_LONG).show();
//                }
//            });
//
//            return "This text is returned from Java layer.  js text = " + text;
//        }
//
//        @JavascriptInterface
//        public void onImageClick(String url, int width, int height) {
//            final String str = "onImageClick: text = " + url + "  width = " + width + "  height = " + height;
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
//                }
//            });
//        }
//    }
}
