package com.example.mybasecustomwidget.invite_friend;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mybasecustomwidget.R;
import com.example.mybasecustomwidget.utils.L;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/10/13.
 */
public class InviteFriendActivity extends Activity{

    private EditText id_et;

    public static final String TAG = InviteFriendActivity.class.getSimpleName();

    private int currentSetSelection=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friend);

        EventBus.getDefault().register(this);

        id_et = (EditText) findViewById(R.id.id_et);
        id_et.requestFocus();
        id_et.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int start/* 开始位置 */,
                                          int before/* 改变前的内容数量 */, int count/* 新增数 */) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start/* 开始位置 */,
                                      int before/* 改变前的内容数量 */, int count/* 新增数 */) {

                //判断输入@跳转界面(这个判断是之前根据微信写的，如果@前面是数字)
                if(
                        count == 1
                                && s.charAt(start) == '@'
                                && (s.length() == 1 || start == 0 || !((s.charAt(start - 1) > 47 && s
                                .charAt(start - 1) < 58)
                                || (s.charAt(start - 1) > 64 && s
                                .charAt(start - 1) < 91) || (s
                                .charAt(start - 1) > 96 && s.charAt(start - 1) < 123)))
                        ){

                    deleteAiTe(id_et,s);

                    Intent intent = new Intent(InviteFriendActivity.this,FriendsActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

                deleteSpanableString(s);
            }
        });

        id_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectionPosition(id_et);
            }
        });
    }

    /**
     * 删除输入的@
     * @param s
     */
    private void deleteAiTe(EditText id_et,CharSequence s) {
        L.i(TAG,"--->>>s:"+s.toString());
        //删除@字符

        int selectionStart = id_et.getSelectionStart();
        if(selectionStart > 0){
            SpannableStringBuilder sb = new SpannableStringBuilder(s);
            SpannableStringBuilder newSb = sb.delete(selectionStart - 1,selectionStart);
            id_et.setText(newSb);
        }

//        SpannableStringBuilder sb = new SpannableStringBuilder(s);
//        SpannableStringBuilder newSb = sb.delete(s.length() - 1,s.length());
//        id_et.setText(newSb);
    }

    private void deleteSpanableString(Editable s) {
        InviteFriendColorSpan[] a = s.getSpans(0, s.length(), InviteFriendColorSpan.class);
        for (int i = 0; i < a.length; i++) {
            int start = s.getSpanStart(a[i]);
            int end = s.getSpanEnd(a[i]);
            String newStr = s.toString().substring(start, end);
            String finalStr = a[i].getFriendInfo().getName();
            if (start >= 0 && !newStr.equals(finalStr)) {
                s.delete(start,end);
            }
        }
    }

    private void setSelectionPosition(EditText id_et) {
        int index = id_et.getSelectionStart();
        currentSetSelection = index;

        Editable s = id_et.getEditableText();
        InviteFriendColorSpan[] a = s.getSpans(0, s.length(), InviteFriendColorSpan.class);
        for (int i = 0; i < a.length; i++) {
            int start = s.getSpanStart(a[i]);
            int end = s.getSpanEnd(a[i]);

            //判断是在哪一个SpannableString中
            if(index > start && index < end){
                //判断点击的是SpannableString的靠左位置还是靠右位置，然后设置光标
                if(index - start < end - index){
                    currentSetSelection = start;
                }else{
                    currentSetSelection = end;
                }
                id_et.setSelection(currentSetSelection);
                Log.i(TAG,"start:"+start+",end:"+end+",index:"+index+",currentSetSelection："+currentSetSelection);
            }
        }
    }

    private void setBackgroundColorSpan(EditText id_et,FriendsActivity.FriendInfo info) {
        String newName = info.getName();
        SpannableString spanString = new SpannableString(newName);
        BackgroundColorSpan span = new InviteFriendColorSpan(Color.parseColor("#b5daff"),info);
        spanString.setSpan(span, 0, newName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        int startPosition = currentSetSelection;
        int endPosition = currentSetSelection + spanString.length();
        L.i(TAG,"--->>>startPosition:"+startPosition+",endPosition:"+endPosition+",spanString.length():"+spanString.length());
        id_et.getText().insert(startPosition,spanString);
        id_et.getText().insert(startPosition+spanString.length()," ");
        int mSelection = endPosition + 1;
        id_et.setSelection(mSelection);
        currentSetSelection = mSelection;
        L.i(TAG,"--->>>currentSetSelection2:"+currentSetSelection);


//        String newName = info.getName();
//        SpannableString spanString = new SpannableString(newName);
//        BackgroundColorSpan span = new InviteFriendColorSpan(Color.parseColor("#b5daff"),info);
//        spanString.setSpan(span, 0, newName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        id_et.append(spanString);
//
//        id_et.append(" ");
    }

    public class InviteFriendColorSpan extends BackgroundColorSpan {
        FriendsActivity.FriendInfo info;
        /** * 构造方法 * * @param color 颜色值 * @param keyWords 显示的文字 */
        protected InviteFriendColorSpan(int color, FriendsActivity.FriendInfo info) {
            super(color);
            this.info = info;
        }

        /** * 原始文字 * * @return */
        protected FriendsActivity.FriendInfo getFriendInfo() {
            return info;
        }
    }


    public void onEventMainThread(FriendsActivity.FriendInfo info){
        String name = info.getName();
        String newName = "@" + name;
        info.setName(newName);

        if(!isFriendExist(id_et,info.getUid())){
            setBackgroundColorSpan(id_et,info);
        }
    }

    private boolean isFriendExist(EditText id_et,String uid){
        Editable s = id_et.getEditableText();
        InviteFriendColorSpan[] a = s.getSpans(0, s.length(), InviteFriendColorSpan.class);
        for (int i = 0; i < a.length; i++) {
            FriendsActivity.FriendInfo info = a[i].getFriendInfo();

            String finalUid = info.getUid();
            if (uid.equals(finalUid)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void send(View view){
        StringBuilder uids = new StringBuilder("uid集合为:");
        Editable s = id_et.getEditableText();
        InviteFriendColorSpan[] a = s.getSpans(0, s.length(), InviteFriendColorSpan.class);
        for (int i = 0; i < a.length; i++) {
            FriendsActivity.FriendInfo info = a[i].getFriendInfo();

            uids.append(info.getUid());
            uids.append("====");
        }
        Toast.makeText(this,uids.toString(),1).show();
    }
}
