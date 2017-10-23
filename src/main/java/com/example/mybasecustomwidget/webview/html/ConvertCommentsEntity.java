/**
  * Copyright 2017 bejson.com 
  */
package com.example.mybasecustomwidget.webview.html;

import java.util.ArrayList;

/**
 * Auto-generated: 2017-08-31 15:51:42
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ConvertCommentsEntity {

    private int buildLevel;
    private String content;
    private int commentId;
    private String createTime;
    private int vote;
    private User user;


    //custom local new add (楼中楼)
    private ArrayList<ConvertCommentsEntity> replys;

    public int getBuildLevel() {
        return buildLevel;
    }

    public void setBuildLevel(int buildLevel) {
        this.buildLevel = buildLevel;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public ArrayList<ConvertCommentsEntity> getReplys() {
        return replys;
    }

    public void setReplys(ArrayList<ConvertCommentsEntity> replys) {
        this.replys = replys;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}