package com.example.mybasecustomwidget.webview.html;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.mybasecustomwidget.R;
import com.example.mybasecustomwidget.baseAdapterHelper.multipleSupport.BaseAdapterHelper;
import com.example.mybasecustomwidget.baseAdapterHelper.multipleSupport.QuickAdapter;
import com.example.mybasecustomwidget.utils.GlideImageLoaderUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */
public class NewReplyCommonAdapter extends CommonAdapter<ConvertCommentsEntity>{

    private static final String TAG = NewReplyCommonAdapter.class.getSimpleName();
    private Context context;
    private List<ConvertCommentsEntity> cuurentDatas;
    private AdapterView.OnItemClickListener onItemClickListener;

    public NewReplyCommonAdapter(Context context, int layoutId, List<ConvertCommentsEntity> cuurentDatas,AdapterView.OnItemClickListener onItemClickListener) {
        super(context, layoutId, cuurentDatas);
        this.context = context;
        this.cuurentDatas = cuurentDatas;
        this.onItemClickListener = onItemClickListener;
    }


    /**
     * 下拉刷新
     * @param datas
     */
    protected void replyAllData(List<ConvertCommentsEntity> datas){
        if(cuurentDatas != null){
            cuurentDatas.clear();
            cuurentDatas.addAll(datas);
        }
    }

    /**
     * 加载更多
     * @param datas
     */
    protected void addAllData(List<ConvertCommentsEntity> datas){
        if(cuurentDatas != null){
            cuurentDatas.addAll(datas);
        }
    }

    @Override
    protected void convert(ViewHolder holder, ConvertCommentsEntity commentsEntity, int position) {
        User user = commentsEntity.getUser();
        ImageView id_item_wap_html_reply_head_iv = holder.getView(R.id.id_item_wap_html_reply_head_iv);
        GlideImageLoaderUtils.squarePandaImageLoader(context,user.getAvatar(),id_item_wap_html_reply_head_iv);

        holder.setText(R.id.id_item_wap_html_reply_nick_iv,user.getNickname());

//        holder.setText(R.id.id_item_wap_html_reply_location_iv,user.getLocation());

        holder.setText(R.id.id_item_wap_html_reply_content_iv,commentsEntity.getContent());

        holder.setText(R.id.id_item_wap_html_reply_praise_iv,commentsEntity.getVote()+"顶");

        //没有回复不显示listview
        ListView listView = holder.getView(R.id.id_reply_again_reply_lv);
        ArrayList<ConvertCommentsEntity> replys = commentsEntity.getReplys();
        if(replys != null && replys.size() > 0){
            listView.setVisibility(View.VISIBLE);
        }else{
            listView.setVisibility(View.GONE);
        }
        Log.i(TAG,"--->>>replys size:"+(replys != null ? replys.size() : "NULL"));
        listView.setAdapter(new QuickAdapter<ConvertCommentsEntity>(context,R.layout.item_reply_agin_reply,replys) {
            @Override
            protected void convert(BaseAdapterHelper helper, ConvertCommentsEntity item) {
                User user = item.getUser();

                helper.setText(R.id.id_reply_again_reply_nick_tv,user.getNickname());

                helper.setText(R.id.id_reply_again_reply_content_tv,item.getContent());

                helper.setText(R.id.id_reply_again_reply_praise_tv,(helper.getPosition()+ 1) + "");

                //最后一根先不显示
                View id_reply_again_reply_line_view = helper.getView(R.id.id_reply_again_reply_line_view);
                if(helper.getPosition() == getCount() - 1){
                    id_reply_again_reply_line_view.setVisibility(View.GONE);
                }else{
                    id_reply_again_reply_line_view.setVisibility(View.VISIBLE);
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(parent,view,position,id);
                }
            }
        });
    }
}
