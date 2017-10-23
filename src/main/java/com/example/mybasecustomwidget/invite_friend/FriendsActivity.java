package com.example.mybasecustomwidget.invite_friend;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mybasecustomwidget.R;
import com.example.mybasecustomwidget.baseAdapterHelper.multipleSupport.BaseAdapterHelper;
import com.example.mybasecustomwidget.baseAdapterHelper.multipleSupport.QuickAdapter;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/10/13.
 */
public class FriendsActivity extends Activity{

    private ListView lv;
    private List< FriendInfo> mDatas = new ArrayList< FriendInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        lv = (ListView) findViewById(R.id.lv);

        initDatas();

        QuickAdapter mAdapter = new QuickAdapter<FriendInfo>(
                FriendsActivity.this, R.layout.item_friend, mDatas) {
            @Override
            protected void convert(BaseAdapterHelper helper, FriendInfo item)
            {
                Log.d(TAG, "--->>>convert");
                helper.setText(R.id.id_name_tv, item.getName());
                helper.setText(R.id.id_friend_uid_tv, item.getUid());
            }
        };
        // 设置适配器
        lv.setAdapter(mAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FriendInfo info = (FriendInfo) adapterView.getItemAtPosition(i);
                EventBus.getDefault().post(info);

                finish();
            }
        });
    }

    private void initDatas()
    {
        FriendInfo FriendInfo = null;

        FriendInfo = new  FriendInfo("张三", "1001");
        mDatas.add(FriendInfo);
        FriendInfo = new  FriendInfo("李四", "1002");
        mDatas.add(FriendInfo);
        FriendInfo = new  FriendInfo("wanger", "1003");
        mDatas.add(FriendInfo);
        FriendInfo = new  FriendInfo("赵薇", "1004");
        mDatas.add(FriendInfo);
        FriendInfo = new  FriendInfo("李连杰", "1005");
        mDatas.add(FriendInfo);
        FriendInfo = new  FriendInfo("李小龙", "1006");
        mDatas.add(FriendInfo);
        FriendInfo = new  FriendInfo("王宝强", "1007");
        mDatas.add(FriendInfo);
        FriendInfo = new  FriendInfo("马蓉", "1008");
        mDatas.add(FriendInfo);
        FriendInfo = new  FriendInfo("李四", "1009");
        mDatas.add(FriendInfo);

    }
    
    public class FriendInfo{
        private String name;
        private String uid;

        public FriendInfo(String name,String uid){
            this.name = name;
            this.uid = uid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
