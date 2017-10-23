package com.example.mybasecustomwidget.barrage;

import android.graphics.Canvas;
import android.widget.ImageView;
import android.widget.TextView;

public interface IDanmakuItem {

    void doDraw(Canvas canvas);

    void setTextSize(int sizeInDip);

    void setTextColor(int colorResId);

    void setText(TextView tv);
    
    void setHeadImgeView(ImageView iv);
    
    int getPosition();
    
    void setStartPosition(int x, int y);

    void setSpeedFactor(float factor);

    float getSpeedFactor();

    boolean isOut();

    boolean willHit(IDanmakuItem runningItem);

    void release();

    int getWidth();

    int getHeight();

    int getCurrX();

    int getCurrY();
}
