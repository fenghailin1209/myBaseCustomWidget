package com.example.mybasecustomwidget.webview.html;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.example.mybasecustomwidget.R;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/1.
 */
public class ReplyAgainReplyActivity extends NewCommonBaseActivity {

    public static final String COMMON_TITLE = "COMMON_TITLE";
    public static final String COMMON_BOTTOM_ENTITY = "COMMON_BOTTOM_ENTITY";
    public static final int NEWS_RESULT_CODE = 1002;

    private NewReplyCommonAdapter adapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;

    private boolean isLoadMore;
    private String title;
    private BottomEntity bottomEntity;


    public static void startReplyAgainReplyActivity(Activity activity, String title,BottomEntity bottomEntity) {
        Intent intent = new Intent(activity, ReplyAgainReplyActivity.class);
        intent.putExtra(COMMON_TITLE, title);
        intent.putExtra(COMMON_BOTTOM_ENTITY, bottomEntity);
        activity.startActivityForResult(intent,1001);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setShowContentView();

        getIntentData();

        setViewData();

        refresh();
    }

    private void setViewData() {
        setTitleText(title);

        setBottomViewData(bottomEntity);
    }

    private void getServerData(String minId) {
        String url = "http://192.168.0.80:9022/api50/headline/get_comment.php?docid=1&minid=" + minId;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback());
    }


    @Override
    protected void onResultData(String response) {
        getDataOperation(response);
    }

    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            title = bundle.getString(COMMON_TITLE);
            bottomEntity = (BottomEntity) bundle.getSerializable(COMMON_BOTTOM_ENTITY);
        }
    }

    private int getDataOperation(String reply) {
//        String reply = getFromAssets("reply.txt");

        try {
            //parse
            ArrayList<ConvertCommentsEntity> convertCommentsEntitys = getComments(new JSONObject(reply));

            //set adapter
            setAdapter(convertCommentsEntitys);

            return convertCommentsEntitys.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    protected void initRefershView() {
        super.initRefershView();
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.Pacman);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                isLoadMore = true;

                if (more.equals("1")) {
                    // loading data
                    getServerData(minId);
                } else {
                    mRecyclerView.setNoMore(true);
                }
            }
        });
    }

    private void refresh() {
        isLoadMore = false;
        getServerData("0");
    }

    private void refreshComplete(int pageSize) {
        mRecyclerView.refreshComplete(pageSize);
        mLRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void setAdapter(ArrayList<ConvertCommentsEntity> convertCommentsEntitys) {
        if (adapter == null) {
            adapter = new NewReplyCommonAdapter(this, R.layout.item_wap_html_reply, convertCommentsEntitys, null);
            mLRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
            mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    ConvertCommentsEntity itemConvertCommentsEntity = adapter.getDatas().get(position);

                    setBottomEditTextViewData(itemConvertCommentsEntity);
                }
            });
            mRecyclerView.setAdapter(mLRecyclerViewAdapter);
        } else {
            if (isLoadMore) {
                adapter.addAllData(convertCommentsEntitys);
            } else {
                adapter.replyAllData(convertCommentsEntitys);
            }
            mLRecyclerViewAdapter.notifyDataSetChanged();
        }

        refreshComplete((convertCommentsEntitys != null ? convertCommentsEntitys.size() : 0));
    }


    @Override
    public void onReplyDataSuccess(Object object) {
        super.onReplyDataSuccess(object);
        //TODO:应该本地创建一个对象动态add到第一条数据里面
        //TODO:目前测试版直接请求数据
        refresh();
    }

    @Override
    public void onLikeSuccess(Object object) {
        setIs_like(object.toString());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(COMMON_BOTTOM_ENTITY,new BottomEntity(is_like,replyCount));

            Intent intent = new Intent();
            intent.putExtras(bundle);

            setResult(NEWS_RESULT_CODE,intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClickReply() {
    }
}
