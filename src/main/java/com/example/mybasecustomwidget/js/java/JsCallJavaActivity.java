package com.example.mybasecustomwidget.js.java;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mybasecustomwidget.R;
/**
 * Created by Administrator on 2016/10/19.
 */
public class JsCallJavaActivity extends Activity{

    private WebView webView;

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface", "JavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_java);

        webView = (WebView) findViewById(R.id.id_webView);

        webView.setVerticalScrollbarOverlay(true);
        //开启WebView内直接使用JavaScript
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl("file:///android_asset/1.html");

        //在js中调用本地java方法
        webView.addJavascriptInterface(new JsInterface(this), "AndroidWebView");

        //添加客户端支持
        webView.setWebChromeClient(new MyWebChromeClient());
    }

    private class MyWebChromeClient extends WebChromeClient{
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Toast.makeText(JsCallJavaActivity.this,"onJsAlert message："+message,0).show();
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            Toast.makeText(JsCallJavaActivity.this,"onJsConfirm message："+message,0).show();
            return super.onJsConfirm(view, url, message, result);
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            Toast.makeText(JsCallJavaActivity.this,"onJsPrompt message："+message,0).show();
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }
    }

    private class JsInterface {
        private Context mContext;

        public JsInterface(Context context) {
            this.mContext = context;
        }

        //在js中调用window.AndroidWebView.showInfoFromJs(name)，便会触发此方法。
        @JavascriptInterface
        public void showInfoFromJs(String name) {
            Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 1:
                    String msgStr = ((EditText) findViewById(R.id.input_et)).getText().toString();
                    //调用js中的函数：showInfoFromJava(msg)
                    webView.loadUrl("javascript:showInfoFromJava('" + msgStr + "')");
                    break;

            }
        }
    };

    //在java中调用js代码
    public void sendInfoToJs(View view) {

        //handler.sendEmptyMessageDelayed(1,10000);

        String msg = ((EditText) findViewById(R.id.input_et)).getText().toString();
        //调用js中的函数：showInfoFromJava(msg)
        webView.loadUrl("javascript:showInfoFromJava('" + msg + "')");
    }



}
