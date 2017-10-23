package com.example.mybasecustomwidget.webview.html;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.mybasecustomwidget.R;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Administrator on 2017/9/1.
 */
public abstract class NewCommonBaseActivity extends Activity implements NewReplyBottom {

    protected static final String TAG = NewCommonBaseActivity.class.getSimpleName();

    private View id_new_back_view;
    private TextView id_title_tv;
    protected String minId = "";
    protected Handler mHandler = new Handler();
    protected Context context;
    protected LRecyclerView mRecyclerView;
    protected String title,is_like,replyCount;//"is_like":"0" //1已点过赞  0没有点过赞
    protected String ptime;
    protected JSONArray jsonArrayImg;

    public void setIs_like(String is_like) {
        this.is_like = is_like;
    }

    protected String source;
    protected JSONObject jsonObjectInfo = null;
    protected String more;//是否还有更多页0表示没有，1表示有
    private NewReplyBottomHelper newReplyBottomHelper;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        newReplyBottomHelper = new NewReplyBottomHelper((Activity) context, this);
    }

    /**
     * 设置view，专门写了一个方法出来
     */
    protected void setShowContentView(){
        setContentView(R.layout.activity_webview_html);

        initRefershView();

        initOtherView();

        newReplyBottomHelper = new NewReplyBottomHelper((Activity) context, this);
    }


    protected void setBottomViewData(BottomEntity bottomViewData){
        is_like = bottomViewData.getIs_like();
        replyCount = bottomViewData.getReplyCount();
        Log.i(TAG,"--->>>is_like:"+is_like+",replyCount:"+replyCount);

        newReplyBottomHelper.setBottomViewData(bottomViewData);
    }


    private void initOtherView() {
        id_new_back_view = findViewById(R.id.id_new_back_view);
        id_new_back_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        id_title_tv = (TextView) findViewById(R.id.id_title_tv);
    }

    protected void initRefershView() {
        //init refersh view
        mRecyclerView = (LRecyclerView) findViewById(R.id.id_lrecyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    protected void setTitleText(String title) {
        if (!TextUtils.isEmpty(title)) {
            id_title_tv.setText(title);
        }
    }

    protected void setBottomEditTextViewData(ConvertCommentsEntity itemConvertCommentsEntity) {
        newReplyBottomHelper.setBottomEdittExitHint(itemConvertCommentsEntity);
    }

    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {
        }

        @Override
        public void onAfter(int id) {
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(String response, int id) {
            onResultData(response);
        }

        @Override
        public void inProgress(float progress, long total, int id) {
        }
    }

    protected abstract void onResultData(String response);


    protected String getFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 获取minId，用于区分是否是最后一页，minId=0或不传则是最新的一页，反之分页最后一条id,若有楼中楼取逗号隔开的最后一条id
     *
     * @param commentIds
     * @param position
     */
    private void getMinId(ArrayList<String> commentIds, int position) {
        if (position == commentIds.size() - 1) {
            String lastIds = commentIds.get(position);
            if (!TextUtils.isEmpty(lastIds) && lastIds.contains(",")) {
                String minIds[] = lastIds.split(",");
                minId = minIds[minIds.length - 1];
            } else {
                minId = lastIds;
            }
            Log.i(TAG,"--->>>minId:"+minId);
        }
    }

    protected ArrayList<ConvertCommentsEntity> getComments(JSONObject jsonObjectTie) {
        ArrayList<ConvertCommentsEntity> convertCommentsEntitys = new ArrayList<>();
        try {
            if (jsonObjectTie != null) {
                if(!jsonObjectTie.isNull("more")){
                    more = jsonObjectTie.getString("more");
                }

                ArrayList<String> commentIds = (ArrayList<String>) JSON.parseArray(jsonObjectTie.getString("commentIds"), String.class);
                JSONObject comments = jsonObjectTie.getJSONObject("comments");
                for (int position = 0; position < commentIds.size(); position++) {
                    String commentId = commentIds.get(position);

                    getMinId(commentIds, position);

                    String[] totalCommentId = commentId.split(",");
                    int length = totalCommentId.length;
                    if (length == 1) {
                        //只有一条回复
                        String commentIdJsonObject = comments.getString(totalCommentId[0]);
                        ConvertCommentsEntity convertCommentsEntity = JSON.parseObject(commentIdJsonObject.toString(), ConvertCommentsEntity.class);
                        convertCommentsEntitys.add(convertCommentsEntity);
                    } else if (length > 1) {
                        //回复+楼中楼
                        //取最后一条作为评论
                        String commentIdJsonObject = comments.getString(totalCommentId[totalCommentId.length - 1]);
                        ConvertCommentsEntity convertCommentsEntity = JSON.parseObject(commentIdJsonObject.toString(), ConvertCommentsEntity.class);

                        ArrayList<ConvertCommentsEntity> replys = new ArrayList<>();
                        for (int positon = 0; positon < totalCommentId.length - 1; positon++) {
                            replys.add(JSON.parseObject(comments.getString(totalCommentId[positon]), ConvertCommentsEntity.class));
                        }
                        convertCommentsEntity.setReplys(replys);
                        convertCommentsEntitys.add(convertCommentsEntity);
                    }
                }

            }
            return convertCommentsEntitys;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String parsebodyStr(String str) {
        try {
            JSONObject jsonObject = new JSONObject(str);
            jsonObjectInfo = jsonObject.getJSONObject("info");
            JSONObject jsonObjectArticle = jsonObjectInfo.getJSONObject("article");
            String bodyStr = jsonObjectArticle.getString("body");
            title = jsonObjectArticle.getString("title");

            is_like = jsonObjectArticle.getString("is_like");
            replyCount = jsonObjectArticle.getString("replyCount");

            source = jsonObjectArticle.getString("source");
            ptime = jsonObjectArticle.getString("ptime");
            if (jsonObjectArticle != null) {
                jsonArrayImg = jsonObjectArticle.getJSONArray("img");
                int length = jsonArrayImg.length();
                for (int i = 0; i < length; i++) {//遍历JSONArray
                    JSONObject oj = jsonArrayImg.getJSONObject(i);
                    String src = oj.getString("src");
                    String ref = oj.getString("ref");

                    if (bodyStr.contains(ref)) {
                        String newSrc = "<img src=\"" + src + "\" alt=\"\" />";
                        bodyStr = bodyStr.replace(ref, newSrc);
                    }
                }
                return bodyStr;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    @NonNull
    protected StringBuilder combination(String bodyStr) {
        String cssName = "News.css";
        StringBuilder html = new StringBuilder("<html>");
        html.append("<head><meta charset=\"UTF-8\">");
        html.append("<link rel =\"stylesheet\" href =");
        html.append(cssName);
        html.append(" type=\"text/css\"/>");
        html.append("</head><body>");
        html.append(bodyStr);
        html.append("</body></html>");
        Log.i(TAG, "--->>>html:" + html.toString());
        return html;
    }

    @Override
    public void onReplyDataSuccess(Object object) {
        //回复成功加1
        int replyCountInt = Integer.parseInt(replyCount);
        int newreplyCountInt = (++replyCountInt);
        Log.i(TAG,"--->>>newreplyCountInt:"+newreplyCountInt+",replyCountInt:"+replyCountInt);
        BottomEntity bottomEntity = new BottomEntity(is_like,newreplyCountInt+"");
        setBottomViewData(bottomEntity);
    }

    @Override
    public void onClickReply() {
        ReplyAgainReplyActivity.startReplyAgainReplyActivity((Activity) context, title, new BottomEntity(is_like, replyCount));
    }
}
