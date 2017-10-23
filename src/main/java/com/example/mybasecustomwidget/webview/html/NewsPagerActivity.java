package com.example.mybasecustomwidget.webview.html;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.mybasecustomwidget.R;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */
public class NewsPagerActivity extends NewCommonBaseActivity {

    private TextView id_news_pager_title_tv, id_news_pager_select_position_tv, id_news_pager_total_count_tv, id_news_pager_content_tv;
    ViewPager id_news_vp;
    private List<View> mListViews;//把需要滑动的页卡添加到这个list中

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_pager);
        initView();

        getServerData();
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

    private void initView() {
        id_news_vp = (ViewPager) findViewById(R.id.id_news_vp);
    }

    @Override
    protected void onResultData(String response) {

        parsebodyStr(response);

        initPager();

        id_news_vp.setAdapter(new MyViewPagerAdapter());
    }

    private void initPager() {
        mListViews = new ArrayList<View>();// 将要分页显示的View装入数组中
        ArrayList<NewsPagerEntity> pagerEntities = (ArrayList<NewsPagerEntity>) JSON.parseArray(jsonArrayImg.toString(), NewsPagerEntity.class);
        for (NewsPagerEntity entity : pagerEntities) {
            entity.setTitle(title);
            LayoutInflater lf = getLayoutInflater().from(this);
            View view = lf.inflate(R.layout.item_news_pager, null);
            view.setTag(entity);
            mListViews.add(view);
        }
    }

    @Override
    public void onLikeSuccess(Object object) {
        setIs_like(object.toString());
    }

    private void setPagerViewData(int position) {
        id_news_pager_title_tv = (TextView) findViewById(R.id.id_news_pager_title_tv);
        id_news_pager_select_position_tv = (TextView) findViewById(R.id.id_news_pager_select_position_tv);
        id_news_pager_total_count_tv = (TextView) findViewById(R.id.id_news_pager_total_count_tv);
        id_news_pager_content_tv = (TextView) findViewById(R.id.id_news_pager_content_tv);

        NewsPagerEntity entity = (NewsPagerEntity) mListViews.get(position).getTag();
        id_news_pager_title_tv.setText(entity.getTitle());
        id_news_pager_content_tv.setText(entity.getAlt());
        id_news_pager_select_position_tv.setText((position + 1) + "");
        id_news_pager_total_count_tv.setText("/" + mListViews.size() + "");
    }

    public class MyViewPagerAdapter extends PagerAdapter {

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mListViews.get(position));//删除页卡
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {    //这个方法用来实例化页卡
            View view = mListViews.get(position);
            setPagerViewData(position);
            container.addView(view, 0);//添加页卡
            return mListViews.get(position);
        }

        @Override
        public int getCount() {
            return mListViews.size();//返回页卡的数量
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;//官方提示这样写
        }
    }

}
