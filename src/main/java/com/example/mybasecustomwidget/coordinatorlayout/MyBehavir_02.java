package com.example.mybasecustomwidget.coordinatorlayout;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import com.example.mybasecustomwidget.utils.AndroidUtil;
import com.example.mybasecustomwidget.utils.L;

/**
 * Created by Administrator on 2016/9/28.
 */
public class MyBehavir_02 extends CoordinatorLayout.Behavior{

    private int screentWidth,screenHeight;

    /**
     * 注意这个构造方法必须写的，要不然报错
     * @param context
     * @param attrs
     */
    public MyBehavir_02(Context context, AttributeSet attrs) {
        super(context, attrs);

        screentWidth = AndroidUtil.getScreenWidth(context);
        screenHeight = AndroidUtil.getScreenHeight(context);
    }

    private static final String TAG = MyBehavir_02.class.getSimpleName();

    /**
     * 判断child的布局是否依赖dependency
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof TempView_02;
    }

    /**
     * 当dependency发生变换（宽高，位置）的时候，执行这个函数
     * 返回true表示child的位置或者宽高要发生变化
     * TODO:①只有一个child和dependency吗?
     * TODO:②只有一个child和dependency吗?
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {

        int left = dependency.getLeft();
        int top = dependency.getTop();

        int newLeft = screentWidth - left - dependency.getMeasuredWidth();
        int newTop = top;

        CoordinatorLayout.MarginLayoutParams params = (CoordinatorLayout.MarginLayoutParams) child.getLayoutParams();
        params.leftMargin = newLeft;
        params.topMargin = newTop;

        child.setLayoutParams(params);

        L.i(TAG,"--->>>dependency getLeft:"+left+",getTop:"+top);
        return true;
    }

}
