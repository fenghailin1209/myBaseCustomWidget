package com.example.mybasecustomwidget.antoviewpager;

import java.util.ArrayList;

import com.example.mybasecustomwidget.MainActivity;
import com.example.mybasecustomwidget.MyCustomApplication;
import com.example.mybasecustomwidget.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class MyBannerAdapter extends PagerAdapter implements
		ViewPager.OnPageChangeListener {
	private LayoutInflater mInflater;
	private long DELAYED = 2000;
	private static final int ARG0 = 0;
	private int mBannerPosition = 0;
	private final int FAKE_BANNER_SIZE = 10;
	private int DEFAULT_BANNER_SIZE;
	private boolean mIsUserTouched = false;

	private ViewPager viewPager;

	private Context mContext;

	private ArrayList<String> viewList;

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				if (!mIsUserTouched) {
					mBannerPosition = (mBannerPosition + 1) % FAKE_BANNER_SIZE;

					if (mBannerPosition == FAKE_BANNER_SIZE - 1) {
						viewPager
								.setCurrentItem(DEFAULT_BANNER_SIZE - 1, false);
					} else {
						viewPager.setCurrentItem(mBannerPosition);
					}

					mHandler.sendEmptyMessageDelayed(ARG0, DELAYED);
				}
				break;

			default:
				break;
			}
		};
	};

	public MyBannerAdapter(Context mContext,
			ArrayList<String> viewList) {
		this.mContext = mContext;
		this.viewList = viewList;
		this.DEFAULT_BANNER_SIZE = viewList.size();
		

		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return FAKE_BANNER_SIZE;
	}

	@Override
	public boolean isViewFromObject(View view, Object o) {
		return view == o;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		position %= DEFAULT_BANNER_SIZE;
		View view = mInflater.inflate(R.layout.item_pager1, container, false);
		ImageView imageView = (ImageView) view.findViewById(R.id.iv);
		ImageLoader.getInstance().displayImage(viewList.get(position),
				imageView, MyCustomApplication.options);
		final int pos = position;
		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(onPagerChangeeListener != null){
					onPagerChangeeListener.onPagerChange(pos);
				}
			}
		});
		container.addView(view);
		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public void finishUpdate(ViewGroup container) {
		int position = viewPager.getCurrentItem();
		if (position == 0) {
			position = DEFAULT_BANNER_SIZE;
			viewPager.setCurrentItem(position, false);
		} else if (position == FAKE_BANNER_SIZE - 1) {
			position = DEFAULT_BANNER_SIZE - 1;
			viewPager.setCurrentItem(position, false);
		}
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
	}

	@Override
	public void onPageSelected(int position) {
		mBannerPosition = position;
	}

	@Override
	public void onPageScrollStateChanged(int state) {
	}

	public void startRoll(ViewPager viewPager,long delayed) {
		this.viewPager = viewPager;
		this.DELAYED = delayed;
		
		setPagerListenner(viewPager);
		
		mHandler.sendEmptyMessageDelayed(ARG0, DELAYED);
	}

	public void startRoll(ViewPager viewPager) {
		this.viewPager = viewPager;
		
		setPagerListenner(viewPager);

		mHandler.sendEmptyMessageDelayed(ARG0, DELAYED);
	}

	private void setPagerListenner(ViewPager viewPager) {
		viewPager.setOnPageChangeListener(this);
		viewPager.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				if (action == MotionEvent.ACTION_DOWN
						|| action == MotionEvent.ACTION_MOVE) {
					mIsUserTouched = true;
				} else if (action == MotionEvent.ACTION_UP) {
					mIsUserTouched = false;
				}
				return false;
			}
		});
	}

	/**
	 * ֹͣ����
	 */
	public void stopRoll() {
		if (mHandler != null) {
			mHandler.removeMessages(ARG0);
		}
	}
	
	public OnPagerChangeeListener onPagerChangeeListener;
	
	public void setOnPagerChangeeListener(OnPagerChangeeListener onPagerChangeeListener){
		this.onPagerChangeeListener = onPagerChangeeListener;
	}
	
	public interface OnPagerChangeeListener{
		public void onPagerChange(int position);
	}
}
