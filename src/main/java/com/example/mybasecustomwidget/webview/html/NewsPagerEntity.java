package com.example.mybasecustomwidget.webview.html;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/11.
 */
public class NewsPagerEntity implements Serializable{

    private String alt;
    private String src;
    private String title;

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
