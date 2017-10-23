package com.example.mybasecustomwidget.webview.html;

import android.app.Activity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybasecustomwidget.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Administrator on 2017/9/1.
 */
public class NewReplyBottomHelper {

    private static final String TAG = NewReplyBottomHelper.class.getSimpleName();
    private Activity activity;
    protected TextView id_reply_tv,id_bottom_reply_total_count_tv;
    protected View id_layout_new_reply_bottom1, id_layout_new_reply_bottom2, id_dismess_view;
    private EditText id_new_reply_et;
    private TextView id_new_reply_send_tv;
    private NewReplyBottom newReplyBottom;
    public static final String HITE_CONST = "回复：";
    protected TextView id_like_tv;
    private String is_like;//1已点过赞  0没有点过赞
    private RelativeLayout id_reply_rl;

    public NewReplyBottomHelper(Activity activity, NewReplyBottom newReplyBottom) {
        this.activity = activity;
        this.newReplyBottom = newReplyBottom;

        initBottomView();

        setListener();
    }


    public void  setBottomViewData(BottomEntity bottomViewData){
        setLikeViewData(bottomViewData.getIs_like());

        setReplyCountViewData(bottomViewData.getReplyCount());
    }

    /**
     * 评论view显示
     */
    private void setReplyCountViewData(String replyCount) {
        id_bottom_reply_total_count_tv.setText(replyCount);
    }

    private void setLikeViewData(String is_like){
        this.is_like = is_like;
        Log.i(TAG,"--->>>is_like:"+is_like);
        if(is_like.equals("0")){
            id_like_tv.setBackgroundResource(R.drawable.menu_bookmark);
        }else{
            id_like_tv.setBackgroundResource(R.drawable.menu_bookmark_ok);
        }
    }

    private void initBottomView() {
        id_reply_tv = (TextView) activity.findViewById(R.id.id_reply_tv);
        id_bottom_reply_total_count_tv = (TextView) activity.findViewById(R.id.id_bottom_reply_total_count_tv);
        id_layout_new_reply_bottom1 = activity.findViewById(R.id.id_layout_new_reply_bottom1);
        id_layout_new_reply_bottom2 = activity.findViewById(R.id.id_layout_new_reply_bottom2);
        id_dismess_view = activity.findViewById(R.id.id_dismess_view);
        id_new_reply_et = (EditText) activity.findViewById(R.id.id_new_reply_et);
        id_new_reply_send_tv = (TextView) activity.findViewById(R.id.id_new_reply_send_tv);
        id_reply_rl = (RelativeLayout) activity.findViewById(R.id.id_reply_rl);
        id_reply_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newReplyBottom != null){
                    newReplyBottom.onClickReply();
                }
            }
        });
        id_like_tv = (TextView) activity.findViewById(R.id.id_like_tv);
        id_like_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(is_like.equals("1")){
                    requestLikeServer("0");
                }else{
                    requestLikeServer("1");
                }
            }
        });
    }

    /***
     * 点赞
     */
    private void requestLikeServer(final String status) {
        String testToken = "c7df611133550e590b8b022fde97e68c";
        String url = "http://192.168.0.80:9022/api50/headline/like_topic.php?docid=1&token="+testToken+"&status="+status;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            org.json.JSONObject object = new org.json.JSONObject(response);
                            Toast.makeText(activity, object.getString("msg"), 1).show();
                            if(object.getString("state").equals("true")){
                                setLikeViewData(status);

                                if(newReplyBottom != null){
                                    newReplyBottom.onLikeSuccess(status);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    private void setListener() {
        id_reply_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputBottomView(true);
            }
        });
        id_dismess_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputBottomView(false);
            }
        });
        id_new_reply_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String content = id_new_reply_et.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    id_new_reply_send_tv.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.btn_no_broder_press));
                    id_new_reply_send_tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String content = id_new_reply_et.getText().toString();
                            if (!TextUtils.isEmpty(content)) {
                                replyServer(content);
                            }
                        }
                    });
                } else {
                    id_new_reply_send_tv.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.btn_no_broder_nopress));
                    id_new_reply_send_tv.setOnClickListener(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void replyServer(String content) {
        String to_commentId = getToCommentId();

        String testToken = "c7df611133550e590b8b022fde97e68c";
        String url = "http://192.168.0.80:9022/api50/headline/create_comment.php?content=" + content + "&docid=1&token=" + testToken + "&to_commentId=" + to_commentId;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback());
    }

    private String getToCommentId() {
        String to_commentId = "";
        Object mTag = id_reply_tv.getTag();
        if (mTag != null) {
            try {
                ConvertCommentsEntity commentsEntity = (ConvertCommentsEntity) mTag;
                to_commentId = commentsEntity.getCommentId() + "";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return to_commentId;
    }

    public void setBottomEdittExitHint(ConvertCommentsEntity itemConvertCommentsEntity) {
        String hide = HITE_CONST + itemConvertCommentsEntity.getUser().getNickname();
        Log.i(TAG, "--->>>hide:" + hide);
        id_reply_tv.setHint(hide);
        id_reply_tv.setTag(itemConvertCommentsEntity);
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
        public void onResponse(String content, int id) {
            onResponseHandler(content);
        }

        @Override
        public void inProgress(float progress, long total, int id) {
        }
    }

    private void onResponseHandler(String content) {
        id_reply_tv.setText("");
        id_reply_tv.setTag("");
        showInputBottomView(false);

        if (newReplyBottom != null) {
            newReplyBottom.onReplyDataSuccess(content);
        }
    }

    private void showInputBottomView(boolean isShow) {
        if (isShow) {
            setFocus();

            KeyBoardUtils.openKeybord(id_new_reply_et, activity);
            id_layout_new_reply_bottom2.setVisibility(View.VISIBLE);
            id_dismess_view.setVisibility(View.VISIBLE);
            id_layout_new_reply_bottom1.setVisibility(View.GONE);

            id_layout_new_reply_bottom2.setClickable(true);
        } else {
            id_layout_new_reply_bottom2.setClickable(false);
            KeyBoardUtils.closeKeybord(id_new_reply_et, activity);
            id_layout_new_reply_bottom2.setVisibility(View.GONE);
            id_dismess_view.setVisibility(View.INVISIBLE);
            id_layout_new_reply_bottom1.setVisibility(View.VISIBLE);
            id_new_reply_et.setText("");
        }
    }

    private void setFocus() {
        id_new_reply_et.setFocusable(true);
        id_new_reply_et.setFocusableInTouchMode(true);
        id_new_reply_et.requestFocus();
    }

}
