package com.example.mybasecustomwidget.AppBarLayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.mybasecustomwidget.R;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO:参考：http://blog.csdn.net/huachao1001/article/details/51558835
 * Created by Administrator on 2016/9/28.
 */
public class AppBarLayoutDemoActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{

    private static final String TAG = AppBarLayoutDemoActivity.class.getSimpleName();
    private AppBarLayout id_abl;
    private View tl_expand,tl_collapse;
    private int mMaskColor;

    private View v_expand_mask, v_collapse_mask, v_pay_mask;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private MyCommonAdapter adapter;
    private LRecyclerView autoReFreshListView;
    private Context context;
    private List<String> mDatas;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    autoReFreshListView.refreshComplete(0);
                    break;
            }
        }
    };

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_app_bar_layout);
        mMaskColor = getResources().getColor(R.color.blue_dark);

        //init refersh view
        autoReFreshListView = (LRecyclerView) findViewById(R.id.lv);
        autoReFreshListView.setNestedScrollingEnabled(true);
        autoReFreshListView.setHasFixedSize(true);
        autoReFreshListView.setLayoutManager(new LinearLayoutManager(this));

        id_abl = (AppBarLayout)findViewById(R.id.id_abl);
        id_abl.addOnOffsetChangedListener(this);
        tl_expand =findViewById(R.id.tl_expand);
        tl_collapse = findViewById(R.id.tl_collapse);
        v_expand_mask = (View) findViewById(R.id.v_expand_mask);
        v_collapse_mask = (View) findViewById(R.id.v_collapse_mask);
        v_pay_mask = (View) findViewById(R.id.v_pay_mask);

        setListener();

        initData();

        setAdapter();
    }

    protected void initData()
    {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
    }

    private void setAdapter() {
        if (adapter == null) {
            adapter = new MyCommonAdapter(this, R.layout.item_list, mDatas);
            mLRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
            autoReFreshListView.setAdapter(mLRecyclerViewAdapter);
            autoReFreshListView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            autoReFreshListView.setLScrollListener(new LRecyclerView.LScrollListener() {
                @Override
                public void onScrollUp() {
                    Log.i(TAG,"--->>>onScrollUp");
                }

                @Override
                public void onScrollDown() {
                    Log.i(TAG,"--->>>onScrollDown");
                }

                @Override
                public void onScrolled(int distanceX, int distanceY) {
                    Log.i(TAG,"--->>>onScrolled distanceY:"+distanceY);
                }

                @Override
                public void onScrollStateChanged(int state) {
                    Log.i(TAG,"--->>>onScrollStateChanged state:"+state);
                }
            });
        } else {
            mLRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    public class MyCommonAdapter extends CommonAdapter<String>{

        public MyCommonAdapter(Context context, int layoutId, List<String> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, String o, int position) {

        }
    }

    private void setListener() {
        autoReFreshListView.setRefreshProgressStyle(ProgressStyle.Pacman);
        autoReFreshListView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        autoReFreshListView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHandler.sendEmptyMessageDelayed(1,1500);
                    }
                },1000);
            }
        });
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        Log.d("abc", "verticalOffset="+verticalOffset);
        int offset = Math.abs(verticalOffset);
        Log.d("abc", "verticalOffset offset ="+offset);
        if(offset == 0){
            autoReFreshListView.setEnabled(true);
        }else{
            autoReFreshListView.setEnabled(false);
        }

        int total = appBarLayout.getTotalScrollRange();

//        float num=(float)(Math.round(totalPrice*100)/100);//如果要求精确4位就*10000然后/10000
        float alphaIn = Math.round(((255 * 100 /total) * offset) / 100);
        float alphaOut = 255-alphaIn;
        Log.d("abc", "verticalOffset total ="+total+",offset："+offset+",alphaIn:"+alphaIn+",alphaOut:"+alphaOut);

//        int alphaIn = offset;
//        int alphaOut = (200-offset)<0?0:200-offset;
        int maskColorIn = Color.argb((int) alphaIn, Color.red(mMaskColor),
                Color.green(mMaskColor), Color.blue(mMaskColor));
        int maskColorInDouble = Color.argb((int) (alphaIn), Color.red(mMaskColor),
                Color.green(mMaskColor), Color.blue(mMaskColor));
        int maskColorOut = Color.argb((int) alphaOut, Color.red(mMaskColor),
                Color.green(mMaskColor), Color.blue(mMaskColor));
        if (offset <= total / 2) {
            tl_expand.setVisibility(View.VISIBLE);
            tl_collapse.setVisibility(View.GONE);
            v_expand_mask.setBackgroundColor(maskColorInDouble);
        } else {
            tl_expand.setVisibility(View.GONE);
            tl_collapse.setVisibility(View.VISIBLE);
            v_collapse_mask.setBackgroundColor(maskColorOut);
        }
        v_pay_mask.setBackgroundColor(maskColorIn);
    }

}
