package com.example.mybasecustomwidget.webview.html;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/11.
 */
public class BottomEntity implements Serializable{

    private String replyCount;
    private String is_like;

    public BottomEntity(String is_like, String replyCount) {
        this.is_like = is_like;
        this.replyCount = replyCount;
    }

    public String getIs_like() {
        return is_like;
    }

    public void setIs_like(String is_like) {
        this.is_like = is_like;
    }

    public String getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(String replyCount) {
        this.replyCount = replyCount;
    }
}
