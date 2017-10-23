package com.example.mybasecustomwidget.AppBarLayout.NestedScrollView.ListView;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mybasecustomwidget.R;
import com.example.mybasecustomwidget.baseAdapterHelper.multipleSupport.BaseAdapterHelper;
import com.example.mybasecustomwidget.baseAdapterHelper.multipleSupport.Bean;
import com.example.mybasecustomwidget.baseAdapterHelper.multipleSupport.QuickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO:参考：http://blog.csdn.net/huachao1001/article/details/51558835
 * Created by Administrator on 2016/9/28.
 */
public class NestedScrollViewListViewActivity_back extends AppCompatActivity{

    private List<Bean> mDatas = new ArrayList<Bean>();

    private CollapsingToolbarLayout ctl;

    private ListView id_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_srcollview_listview_layout);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        setListView();
    }

    private void setListView() {
        initDatas();

        id_lv = (ListView) findViewById(R.id.id_lv);
        QuickAdapter mAdapter = new QuickAdapter<Bean>(
                NestedScrollViewListViewActivity_back.this, R.layout.item_list, mDatas) {
            @Override
            protected void convert(BaseAdapterHelper helper, Bean item)
            {
                Log.d(TAG, "--->>>convert");
                helper.setText(R.id.tv_title, item.getTitle());
                helper.setText(R.id.tv_describe, item.getDesc());
                helper.setText(R.id.tv_time, item.getTime());
                // // helper.getView(R.id.tv_title).setOnClickListener(l)
            }
        };
        // 设置适配器
        id_lv.setAdapter(mAdapter);
        id_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Snackbar.make(view,"click position:"+i,Snackbar.LENGTH_SHORT).setAction("", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
            }
        });
    }

    private void initDatas()
    {
        Bean bean = null;
        bean = new Bean("美女一只", "周三早上捡到妹子一只，在食堂二楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("美女一捆", "周三早上捡到妹子一捆，在食堂三楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("比卡丘一个", "周三早上捡到比卡丘一个，在食堂一楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("汉子一火车", "周三早上捡到xxxxxxxxxx，在xxx", "10086",
                "20130240122");
        bean = new Bean("美女一只", "周三早上捡到妹子一只，在食堂二楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("美女一捆", "周三早上捡到妹子一捆，在食堂三楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("比卡丘一个", "周三早上捡到比卡丘一个，在食堂一楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("汉子一火车", "周三早上捡到xxxxxxxxxx，在xxx", "10086",
                "20130240122");
        bean = new Bean("美女一只", "周三早上捡到妹子一只，在食堂二楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("美女一捆", "周三早上捡到妹子一捆，在食堂三楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("比卡丘一个", "周三早上捡到比卡丘一个，在食堂一楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("汉子一火车", "周三早上捡到xxxxxxxxxx，在xxx", "10086",
                "20130240122");
        bean = new Bean("美女一只", "周三早上捡到妹子一只，在食堂二楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("美女一捆", "周三早上捡到妹子一捆，在食堂三楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("比卡丘一个", "周三早上捡到比卡丘一个，在食堂一楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("汉子一火车", "周三早上捡到xxxxxxxxxx，在xxx", "10086",
                "20130240122");bean = new Bean("美女一只", "周三早上捡到妹子一只，在食堂二楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("美女一捆", "周三早上捡到妹子一捆，在食堂三楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("比卡丘一个", "周三早上捡到比卡丘一个，在食堂一楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("汉子一火车", "周三早上捡到xxxxxxxxxx，在xxx", "10086",
                "20130240122");
        bean = new Bean("美女一只", "周三早上捡到妹子一只，在食堂二楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("美女一捆", "周三早上捡到妹子一捆，在食堂三楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("比卡丘一个", "周三早上捡到比卡丘一个，在食堂一楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("汉子一火车", "周三早上捡到xxxxxxxxxx，在xxx", "10086",
                "20130240122");
        bean = new Bean("美女一只", "周三早上捡到妹子一只，在食堂二楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("美女一捆", "周三早上捡到妹子一捆，在食堂三楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("比卡丘一个", "周三早上捡到比卡丘一个，在食堂一楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("汉子一火车", "周三早上捡到xxxxxxxxxx，在xxx", "10086",
                "20130240122");
        bean = new Bean("美女一只", "周三早上捡到妹子一只，在食堂二楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("美女一捆", "周三早上捡到妹子一捆，在食堂三楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("比卡丘一个", "周三早上捡到比卡丘一个，在食堂一楼", "10086", "20130240122");
        mDatas.add(bean);
        bean = new Bean("汉子一火车", "周三早上捡到xxxxxxxxxx，在xxx", "10086",
                "20130240122");


        mDatas.add(bean);
    }
}
