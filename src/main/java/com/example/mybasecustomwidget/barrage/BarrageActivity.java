package com.example.mybasecustomwidget.barrage;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mybasecustomwidget.R;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BarrageActivity extends Activity implements View.OnClickListener {

    private DanmakuView mDanmakuView;
    private Button switcherBtn;
    private Button sendBtn;
    private EditText textEditText;
    private View ic_right_iv;
    private View id_right_btn;

    private Handler handler = new Handler(){
    	public void handleMessage(android.os.Message msg) {
    		switch (msg.what) {
			case 0:
				startArrowAnimator(ic_right_iv);
				break;

			default:
				break;
			}
    	};
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_danmaku);

        initView();
    }

    /**
     * һ����ִ���������Һ�������ڣ�ƫ����Ϊ20�����أ�
     * @param view
     */
    private void startArrowAnimator(View view){
    	ObjectAnimator oa = ObjectAnimator.ofFloat(view, "TranslationX", 0,20);
//    	oa.setRepeatCount(ObjectAnimator.INFINITE);
    	oa.setRepeatCount(2);
    	oa.setInterpolator(new CycleInterpolator(1));
    	oa.setDuration(500);
    	oa.start();

    	oa.addListener(new AnimatorListener() {
			@Override
			public void onAnimationStart(Animator arg0) {}
			@Override
			public void onAnimationRepeat(Animator arg0) {}
			@Override
			public void onAnimationEnd(Animator arg0) {
				handler.sendEmptyMessageDelayed(0, 1000);
			}
			@Override
			public void onAnimationCancel(Animator arg0) {}
		});
    }

    private void initView() {
    	 mDanmakuView = (DanmakuView) findViewById(R.id.danmakuView);
         switcherBtn = (Button) findViewById(R.id.switcher);
         sendBtn = (Button) findViewById(R.id.send);
         textEditText = (EditText) findViewById(R.id.text);
         ic_right_iv =  findViewById(R.id.ic_right_iv);
         id_right_btn =  findViewById(R.id.id_right_btn);

         List<IDanmakuItem> list = initItems();
         Collections.shuffle(list);
         mDanmakuView.addItem(list, true);

         switcherBtn.setOnClickListener(this);
         sendBtn.setOnClickListener(this);
         id_right_btn.setOnClickListener(this);

	}



    private List<IDanmakuItem> initItems() {
        List<IDanmakuItem> list = new ArrayList<IDanmakuItem>();
        for (int i = 0; i < 10; i++) {
        	ChannelMessageInfo channelMessageInfo = new ChannelMessageInfo();
        	channelMessageInfo.setType(0);
        	channelMessageInfo.setMessage(i + " : plain text danmuku");

            IDanmakuItem item = new DanmakuItem( this,channelMessageInfo, mDanmakuView.getWidth());
            list.add(item);
        }

        String msg = " : text with image   ";
        for (int i = 0; i < 10; i++) {
        	ChannelMessageInfo channelMessageInfo = new ChannelMessageInfo();
        	channelMessageInfo.setType(1);
        	channelMessageInfo.setMessage(i + " : plain text danmuku32333");

        	IDanmakuItem item = new DanmakuItem( this, channelMessageInfo, mDanmakuView.getWidth(), 0, R.color.gray, 0, 1.5f);
            list.add(item);
        }
        return list;
    }

   @Override
   public void onResume() {
	super.onResume();
	 mDanmakuView.show();

	 startArrowAnimator(ic_right_iv);
   }

    @Override
    public void onPause() {
        super.onPause();
        mDanmakuView.hide();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDanmakuView.clear();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switcher:
                if (mDanmakuView.isPaused()) {
                    switcherBtn.setText(R.string.hide);
                    mDanmakuView.show();
                } else {
                    switcherBtn.setText(R.string.show);
                    mDanmakuView.hide();
                }
                break;
            case R.id.send:
                String input = textEditText.getText().toString();
                if (TextUtils.isEmpty(input)) {
                    Toast.makeText( this, R.string.empty_prompt, Toast.LENGTH_SHORT).show();
                } else {
                	ChannelMessageInfo channelMessageInfo = new ChannelMessageInfo();
                	channelMessageInfo.setType(2);
                	channelMessageInfo.setMessage(input);

                    IDanmakuItem item = new DanmakuItem( this, channelMessageInfo, mDanmakuView.getWidth(),0,R.color.beijingf7,0,1);

//                    IDanmakuItem item = new DanmakuItem(this, input, mDanmakuView.getWidth());
//                    item.setTextColor(getResources().getColor(R.color.my_item_color));
//                    item.setTextSize(14);
//                    item.setTextColor(textColor);
                    mDanmakuView.addItemToHead(item);
//                      mDanmakuView.addItemToEnd(item);
                }
                textEditText.setText("");
                break;
            case R.id.id_right_btn:
            	startArrowAnimator(ic_right_iv);
            	break;
        }
    }

}