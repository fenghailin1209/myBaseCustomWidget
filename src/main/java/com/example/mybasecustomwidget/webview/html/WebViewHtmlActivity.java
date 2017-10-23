package com.example.mybasecustomwidget.webview.html;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybasecustomwidget.R;
import com.example.mybasecustomwidget.view.MyWebView;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;



/**
 * TODO：注意android:targetSdkVersion="16" 必须小于16，要不然的话不能设置其点击事件
 */
@SuppressLint("SetJavaScriptEnabled")
public class WebViewHtmlActivity extends NewCommonBaseActivity implements AdapterView.OnItemClickListener {

    private MyWebView idWebView;

    private ViewGroup id_header2_ll;

    private LRecyclerViewAdapter mLRecyclerViewAdapter;

    private ViewGroup headerView, footerView;
    private TextView id_big_title_tv, id_time_tv, id_from_tv;
    private NewReplyCommonAdapter adapter;

    private StringBuilder html;
    private ArrayList<ConvertCommentsEntity> convertCommentsEntitys;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setShowContentView();

        initView();

        getServerData();
    }
    @Override
    protected void onResultData(String response) {
        initData(response);

        setViewData();

        initWebviewHeader();

        setAdapter();

        setBottomViewData(new BottomEntity(is_like, replyCount));
    }


    private void getServerData() {
        String testToken = "c7df611133550e590b8b022fde97e68c";
        String url = "http://192.168.0.80:9022/api50/headline/get_topic_info.php?token=" + testToken + "&docid=1";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback());
    }

    private void setViewData() {
        setTitleText(title);
        id_big_title_tv.setText(title);
        id_time_tv.setText(ptime);
        id_from_tv.setText(source);
    }

    private void initView() {
        headerView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.header_webview_html, null);
        id_big_title_tv = (TextView) headerView.findViewById(R.id.id_big_title_tv);
        id_time_tv = (TextView) headerView.findViewById(R.id.id_time_tv);
        id_from_tv = (TextView) headerView.findViewById(R.id.id_from_tv);

        footerView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.footer_webview_html, null);
        View id_more_reply_tv = footerView.findViewById(R.id.id_more_reply_tv);
        id_more_reply_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReplyAgainReplyActivity.startReplyAgainReplyActivity((Activity) context, title, new BottomEntity(is_like, replyCount));
            }
        });
    }

    protected void initData(String response) {
        //①
//        String response = getFromAssets("201_Full.txt");
        Log.i(TAG, "--->>>str:" + response);

        //②
        String bodyStr = parsebodyStr(response);
        Log.i(TAG, "--->>>bodyStrNew:" + bodyStr);

        //③
        html = combination(bodyStr);

        //④获取评论并处理成一个数组
        try {
            JSONObject jsonObjectTie = jsonObjectInfo.getJSONObject("tie");
            if (jsonObjectTie != null) {
                convertCommentsEntitys = getComments(jsonObjectTie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAdapter() {
        Log.i(TAG, "--->>>setAdapter mRecyclerView:" + mRecyclerView + ",convertCommentsEntitys:" + (convertCommentsEntitys != null ? convertCommentsEntitys.size() : "NULL"));
        if (adapter == null) {
            adapter = new NewReplyCommonAdapter(this, R.layout.item_wap_html_reply, convertCommentsEntitys, this);
            mLRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
            mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                }
            });
            mRecyclerView.setAdapter(mLRecyclerViewAdapter);

            //必须是设置适配器以后才可以设置
            mRecyclerView.setLoadMoreEnabled(false);
            mRecyclerView.setPullRefreshEnabled(false);
            mLRecyclerViewAdapter.addHeaderView(headerView);
            mLRecyclerViewAdapter.addFooterView(footerView);
            mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    ReplyAgainReplyActivity.startReplyAgainReplyActivity((Activity) context, title, new BottomEntity(is_like, replyCount));
                }
            });
        } else {
            mLRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    //========================== webview start ===================================

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebviewHeader() {
        //init webview
        idWebView = (MyWebView) headerView.findViewById(R.id.id_webView);

        id_header2_ll = (ViewGroup) headerView.findViewById(R.id.id_header2_ll);
        WebSettings wSet = idWebView.getSettings();
        wSet.setCacheMode(WebSettings.LOAD_NO_CACHE); //默认不使用缓存！
        wSet.setJavaScriptEnabled(true);
        // 添加js交互接口类，并起别名 imagelistner
        idWebView.addJavascriptInterface(new JavascriptInterface(this), "imagelistner");
        // 添加js交互接口类，并起别名 imagelocationlistner
        idWebView.addJavascriptInterface(new ImageLocationJavascriptInterface(this), "imagelocationlistner");
        //设置webview的打开方式为在当前的Webview下打开
        idWebView.setWebViewClient(new MyWebViewClient3());
        idWebView.loadDataWithBaseURL("file:///android_asset/", html.toString(), "text/html", "charset=UTF-8", null);
        Log.i(TAG, "--->>>initWebviewHeader");
    }


    // 注入js函数监听
    private void addImageClickListner() {
        // 这段js函数的功能就是，遍历所有的img几点，并添加onclick函数，在还是执行的时候调用本地接口传递url过去
        idWebView.loadUrl(getFromAssets("get_img.txt"));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ReplyAgainReplyActivity.startReplyAgainReplyActivity((Activity) context, title, new BottomEntity(is_like, replyCount));
    }

    /**
     * 自定义WebView类，重载shouldOverrideUrlLoading，改变打开方式
     */
    private class MyWebViewClient3 extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(final WebView view, String url) {
            view.getSettings().setJavaScriptEnabled(true);
            super.onPageFinished(view, url);
            // html加载完成之后，添加监听图片的点击js函数
            addImageClickListner();

            setRefreshViewShow();

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    idWebView.scrollBy(0, 1000);
                }
            }, 5000);
        }
    }

    private void setRefreshViewShow() {
        mRecyclerView.setVisibility(View.VISIBLE);
        id_header2_ll.setVisibility(View.VISIBLE);
    }

    @Override
    public void onReplyDataSuccess(Object object) {
        super.onReplyDataSuccess(object);
        Toast.makeText(context, "发表成功", 0).show();
    }

    @Override
    public void onLikeSuccess(Object object) {
        setIs_like(object.toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ReplyAgainReplyActivity.NEWS_RESULT_CODE) {
            //TODO:刷新底部view
            BottomEntity bottomEntity = (BottomEntity) data.getSerializableExtra(ReplyAgainReplyActivity.COMMON_BOTTOM_ENTITY);
            setBottomViewData(bottomEntity);
        }
    }

    //========================== webview end ===================================
}
