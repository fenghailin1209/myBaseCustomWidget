package com.example.mybasecustomwidget.gesture;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mybasecustomwidget.R;
import com.example.mybasecustomwidget.baseadpter.Bean;
import com.example.mybasecustomwidget.baseadpter.CommondFinalAdapter;
import com.example.mybasecustomwidget.baseadpter.CommondViewHolder;
import com.example.mybasecustomwidget.baseadpter.MyAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/28.
 */
public class GestureActivity extends Activity{

    private ListView lv,lv2;
    private ArrayList<Bean> myLw;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_gesture);

        lv = (ListView) findViewById(R.id.lv);
        lv2 = (ListView) findViewById(R.id.lv2);

        setEmptyView();

        initData();


        lv.setAdapter(new CommondFinalAdapter<Bean>(context, myLw,R.layout.item_header_advertisement_viewgroup) {

            @Override
            public void convert(int position, CommondViewHolder viewHolder) {
                Bean lb = myLw.get(position);

                viewHolder.setText(R.id.lv_name_tv, lb.getName());
                viewHolder.setText(R.id.lv_zsznc_tv, lb.getZsznc());
                viewHolder.setText(R.id.lv_zszn_time_tv, lb.getZszn_time());
                viewHolder.setImageResource(R.id.lv_ic_iv, lb.getHead_iv());
            }

        });

        lv.setAdapter(new MyAdapter(context, myLw,R.layout.item_header_advertisement_viewgroup));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GestureActivity.this,"position："+position,0).show();
            }
        });

        lv2.setAdapter(new CommondFinalAdapter<Bean>(context, myLw,R.layout.item_header_advertisement_viewgroup) {

            @Override
            public void convert(int position, CommondViewHolder viewHolder) {
                Bean lb = myLw.get(position);

                viewHolder.setText(R.id.lv_name_tv, lb.getName());
                viewHolder.setText(R.id.lv_zsznc_tv, lb.getZsznc());
                viewHolder.setText(R.id.lv_zszn_time_tv, lb.getZszn_time());
                viewHolder.setImageResource(R.id.lv_ic_iv, lb.getHead_iv());
            }

        });

        lv2.setAdapter(new MyAdapter(context, myLw,R.layout.item_header_advertisement_viewgroup));
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GestureActivity.this,"position："+position,0).show();
            }
        });



    }

    private void setEmptyView() {
        View emptyView = ((Activity)context).getLayoutInflater().inflate(R.layout.layout_no_data, null);
        ((ViewGroup)lv.getParent()).addView(emptyView);

        lv.setEmptyView(emptyView);

    }

    private void initData() {
        myLw = new ArrayList<Bean>();

        Bean bean1 = new Bean("111", "222", "2015-05-12", "http://img1.imgtn.bdimg.com/it/u=3672397738,3936660589&fm=11&gp=0.jpg");
        Bean bean2 = new Bean("111", "222", "2015-05-12", "http://img1.imgtn.bdimg.com/it/u=3672397738,3936660589&fm=11&gp=0.jpg");
        Bean bean3 = new Bean("111", "222", "2015-05-12", "http://img1.imgtn.bdimg.com/it/u=3672397738,3936660589&fm=11&gp=0.jpg");
        Bean bean4 = new Bean("111", "222", "2015-05-12", "http://img1.imgtn.bdimg.com/it/u=3672397738,3936660589&fm=11&gp=0.jpg");
        Bean bean5 = new Bean("111", "222", "2015-05-12", "http://img1.imgtn.bdimg.com/it/u=3672397738,3936660589&fm=11&gp=0.jpg");
        Bean bean6 = new Bean("111", "222", "2015-05-12", "http://img1.imgtn.bdimg.com/it/u=3672397738,3936660589&fm=11&gp=0.jpg");
        Bean bean7 = new Bean("111", "222", "2015-05-12", "http://img1.imgtn.bdimg.com/it/u=3672397738,3936660589&fm=11&gp=0.jpg");
        Bean bean8 = new Bean("111", "222", "2015-05-12", "http://img1.imgtn.bdimg.com/it/u=3672397738,3936660589&fm=11&gp=0.jpg");
        Bean bean9 = new Bean("111", "222", "2015-05-12", "http://img1.imgtn.bdimg.com/it/u=3672397738,3936660589&fm=11&gp=0.jpg");
        Bean bean10 = new Bean("111", "222", "2015-05-12", "http://img1.imgtn.bdimg.com/it/u=3672397738,3936660589&fm=11&gp=0.jpg");
        Bean bean11 = new Bean("111", "222", "2015-05-12", "http://img1.imgtn.bdimg.com/it/u=3672397738,3936660589&fm=11&gp=0.jpg");
        Bean bean12 = new Bean("111", "222", "2015-05-12", "http://img1.imgtn.bdimg.com/it/u=3672397738,3936660589&fm=11&gp=0.jpg");
        Bean bean13 = new Bean("111", "222", "2015-05-12", "http://img1.imgtn.bdimg.com/it/u=3672397738,3936660589&fm=11&gp=0.jpg");
        Bean bean14 = new Bean("111", "222", "2015-05-12", "http://img1.imgtn.bdimg.com/it/u=3672397738,3936660589&fm=11&gp=0.jpg");
        Bean bean15 = new Bean("111", "222", "2015-05-12", "http://img1.imgtn.bdimg.com/it/u=3672397738,3936660589&fm=11&gp=0.jpg");
        Bean bean16 = new Bean("111", "222", "2015-05-12", "http://img1.imgtn.bdimg.com/it/u=3672397738,3936660589&fm=11&gp=0.jpg");

        myLw.add(bean1);
        myLw.add(bean2);
        myLw.add(bean3);
        myLw.add(bean4);
        myLw.add(bean5);
        myLw.add(bean6);
        myLw.add(bean7);
        myLw.add(bean8);
        myLw.add(bean9);
        myLw.add(bean10);
        myLw.add(bean11);
        myLw.add(bean12);
        myLw.add(bean13);
        myLw.add(bean14);
        myLw.add(bean15);
        myLw.add(bean16);
    }


}
