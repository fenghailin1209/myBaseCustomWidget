package com.example.mybasecustomwidget.guidemap;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mybasecustomwidget.R;
import com.example.mybasecustomwidget.utils.AndroidUtil;

public class GuideMapViewGroup extends ViewGroup implements OnClickListener{
	// �˵���Ŀ����
	private int mMeanItemCount;

	/**
	 * ����item�Ŀ��,��λΪpx
	 */
	private float meanItemSize = AndroidUtil.dip2px(getContext(), 120);

	//�뾶��Ϊ����item�Ŀ�ߵ�һ��
//	private float BJ = meanItemSize / 2;
	//ͨ���ı�뾶���ı�������ߵ���ʼ���յ�λ��
	private float BJ = meanItemSize / 2 - AndroidUtil.dip2px(getContext(), 12);

	private int meanItemCount;

	// ��Ļ�Ŀ��
	private int PW = 0;
	private int PH = 0;

	// ÿ��Բ�Ŀ��
	private float Circle1W, Circle1H;
	private float Circle2W, Circle2H;
	private float Circle3W, Circle3H;
	private float Circle4W, Circle4H;
	private float Circle5W, Circle5H;
	
	//ÿ���ߵ�����
	private float Line1StartX,Line1StartY,Line1PassX,Line1PassY,Line1EndX,Line1EndY;
	private float Line2StartX,Line2StartY,Line2PassX,Line2PassY,Line2EndX,Line2EndY;
	private float Line3StartX,Line3StartY,Line3PassX,Line3PassY,Line3EndX,Line3EndY;
	private float Line4StartX,Line4StartY,Line4PassX,Line4PassY,Line4EndX,Line4EndY;

	/**
	 * Y���ϵ�ƫ����
	 */
	private static final float DY = -20;

	/**
	 * ���߼��
	 */
	private float dashedSpace = 0;

	/**
	 * ��ͷ����
	 */
	private int DArraws;

	/**
	 * ��ͷ����
	 */
	private Paint arrowsPaint;
	
	/**
	 * ƫ�ƵĽǶ�
	 */
	private int jiaodu = 30;
	
	/**
	 * ���߻���Ĭ����ɫ
	 */
	private int paintColor;
	
	/**
	 * ���߻���Ĭ�Ͽ��
	 */
	private int paintWidth;
	
	private setOnMeanItemSelectListenner listenner;

	public GuideMapViewGroup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		/**
		 * ��ȡ�Զ�������ֵ
		 */
		TypedArray t = context.getTheme().obtainStyledAttributes(attrs, R.styleable.GuideMapSetting, defStyle, 0);
		int n = t.getIndexCount();
		for(int i=0;i<n;i++){
			int attr = t.getIndex(i);
			switch (attr) {
			case R.styleable.GuideMapSetting_ArrowsWidth:
				//Ĭ��10��dip
				DArraws = t.getDimensionPixelSize(attr, AndroidUtil.dip2px(getContext(), 0));
				break;
			case R.styleable.GuideMapSetting_PaintColor:
				paintColor = t.getColor(attr,Color.RED);
				break;
			case R.styleable.GuideMapSetting_PaintWidth:
				paintWidth = t.getInt(attr, 0);
				break;
			case R.styleable.GuideMapSetting_DashedSpace:
				dashedSpace = t.getFloat(attr, 10);
				break;
			default:
				break;
			}
		}
	}

	public GuideMapViewGroup(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	public GuideMapViewGroup(Context context) {
		this(context,null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		
		int resWidth = 0;
		int resHeight = 0;

		int width = MeasureSpec.getSize(widthMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);

		int height = MeasureSpec.getSize(heightMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);

		resWidth = width;
		resHeight = height;

		setMeasuredDimension(resWidth, resHeight);

		meanItemCount = getChildCount();

		// mean item ����ģʽ
		int meanItemMode = MeasureSpec.EXACTLY;
		for (int i = 0; i < meanItemCount; i++) {
			// mean item
			View childView = getChildAt(i);
			int makeMeasureSpec = -1;
			// ��mean item���в���
			makeMeasureSpec = MeasureSpec.makeMeasureSpec((int) meanItemSize,
					meanItemMode);
			childView.measure(makeMeasureSpec, makeMeasureSpec);
		}

		init();

	}

	private void init() {
		PW = getWidth();
		PH = getHeight();

		initCircleSize();
		
		arrowsPaint = getPaint();
		
		Calculate();
	}

	
	/**
	 * ����ÿһ��item�˵�����������
	 */
	private void initCircleSize() {
		Circle1W = PW * 1 / 4;
		Circle1H = PH * 1 / 5;

		Circle2W = PW * 3 / 4;
		Circle2H = PH * 2 / 7;

		Circle3W = PW * 6 / 11;
		Circle3H = PH * 7 / 13;

		Circle4W = PW * 1 / 4;
		Circle4H = PH * 3 / 4;

		Circle5W = PW * 3 / 4;
		Circle5H = PH * 9 / 10;
	}
	
	
	/**
	 * �õ�����
	 * @return
	 */
	public Paint getPaint() {
		Paint paint = new Paint();
		PathEffect effects = new DashPathEffect(new float[] { dashedSpace, dashedSpace }, 1);
		paint.setPathEffect(effects);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeCap(Paint.Cap.ROUND);
		//Ĭ��Ϊ8
		paint.setStrokeWidth(paintWidth);
		paint.setAntiAlias(true);
		paint.setColor(paintColor);
		return paint;
	}
	
	
	/**
	 * ����ÿ�������ߵ�����
	 */
	private void Calculate() {
		double hd = Math.toRadians(jiaodu);
		double lb= Math.cos(hd) * BJ;
		//��һ���ߵ�����
		Line1StartX = (float) (Circle1W + lb);
		Line1StartY = Circle1H - BJ * 1/2;
		
		Line1EndX = Circle2W - BJ * 1/2;
		Line1EndY = (float) (Circle2H - lb);
		
		Line1PassX = Line1StartX + (Line1EndX - Line1StartX)/2;
		Line1PassY = Line1StartY + DY;
		
		//�ڶ����ߵ�����
		Line2StartX = (float) (Circle2W + BJ * 1/2);
		Line2StartY = (float) (Circle2H + lb);
		
		Line2EndX = (float) (Circle3W + lb);
		Line2EndY = (float) (Circle3H - BJ * 1/2);
		
		Line2PassX = Line2StartX - DY;
		Line2PassY = Line2StartY - 4 * DY;//���Ȳ���
//		Line2PassY = Line2EndY - (Line2EndY - Line2StartY) /4 ;//���ȱȽ�����
		
		//�������ߵ�����
//		Line3StartX = (float) (Circle3W - BJ * 1/2);
		Line3StartX = (float) (Circle3W - BJ * 3/4);
		Line3StartY = (float) (Circle3H + lb * 3/4);
//		Line3StartX = (float) (Circle3W - BJ * 1/2);
//		Line3StartY = (float) (Circle3H + lb);
		
		Line3EndX = (float) (Circle4W + BJ * 1/2);
		Line3EndY = (float) (Circle4H - lb);
		
		Line3PassX = Line3EndX + (Line3StartX - Line3EndX) / 2 + 2 * DY;
		Line3PassY = Line3EndY - (Line3EndY - Line3StartY) / 2 ;
		
		//�������ߵ�����
		Line4StartX = (float) (Circle4W + BJ * 1/2);
		Line4StartY = (float) (Circle4H + lb);
		
		Line4EndX = (float) (Circle5W  - BJ);
		Line4EndY = (float) (Circle5H );
		
		Line4PassX = Line4StartX + (Line4EndX - Line4StartX) / 2 + 2 * DY;
		Line4PassY = Line4StartY + (Line4EndY - Line4StartY) / 2 - 2 * DY;
	}
	
	/**
	 * �����ߺͼ�ͷ
	 * @param canvas
	 */
	private void drawLineAndArrows(Canvas canvas) {
		// ����һ����
		final Path path = new Path();
		
		path.moveTo(Line1StartX, Line1StartY);
		path.quadTo(Line1PassX, Line1PassY, Line1EndX, Line1EndY);
		canvas.drawPath(path, arrowsPaint);
		
		// ����һ����ͷ
		canvas.drawLine(Line1EndX, Line1EndY, Line1EndX - DArraws, Line1EndY, arrowsPaint);
		canvas.drawLine(Line1EndX, Line1EndY, Line1EndX, Line1EndY - DArraws, arrowsPaint);

		// ���ڶ�����
		final Path path1 = new Path();
		path1.moveTo(Line2StartX, Line2StartY);
		path1.quadTo(Line2PassX, Line2PassY, Line2EndX, Line2EndY);
		canvas.drawPath(path1, arrowsPaint);

		// ���ڶ�����ͷ
		canvas.drawLine(Line2EndX, Line2EndY, Line2EndX + DArraws, Line2EndY, arrowsPaint);
		canvas.drawLine(Line2EndX, Line2EndY, Line2EndX, Line2EndY - DArraws, arrowsPaint);

		// ����������
		final Path path2 = new Path();
		path2.moveTo(Line3StartX,Line3StartY);
		path2.quadTo(Line3PassX, Line3PassY, Line3EndX, Line3EndY);
		canvas.drawPath(path2, arrowsPaint);
		// ����������ͷ
		int dx1 = (int) (Math.cos(Math.toRadians(30)) * DArraws);
		int dy1 = (int) (Math.sin(Math.toRadians(30)) * DArraws);
		canvas.drawLine(Line3EndX, Line3EndY, Line3EndX + dx1, Line3EndY - dy1, arrowsPaint);
		canvas.drawLine(Line3EndX, Line3EndY, Line3EndX - dy1, Line3EndY - dx1, arrowsPaint);
//		canvas.drawLine(Line3EndX, Line3EndY, Line3EndX + DArraws, Line3EndY, arrowsPaint);
//		canvas.drawLine(Line3EndX, Line3EndY, Line3EndX, Line3EndY - DArraws, arrowsPaint);

		// ����������
		final Path path3 = new Path();
		path3.moveTo(Line4StartX, Line4StartY);
		path3.quadTo(Line4PassX, Line4PassY, Line4EndX, Line4EndY);
		canvas.drawPath(path3, getPaint());
		// �����ĸ���ͷ
//		canvas.drawLine(Line4EndX, Line4EndY, Line4EndX - DArraws, Line4EndY, arrowsPaint);
//		canvas.drawLine(Line4EndX, Line4EndY, Line4EndX, Line4EndY - DArraws, arrowsPaint);
		int dx = (int) (Math.cos(Math.toRadians(45)) * DArraws);
		canvas.drawLine(Line4EndX, Line4EndY, Line4EndX - dx, Line4EndY - dx, arrowsPaint);
		canvas.drawLine(Line4EndX, Line4EndY, Line4EndX - dx, Line4EndY + dx, arrowsPaint);
	}

	/**
	 * ��ȡĬ�ϴ�С
	 * 
	 * @return
	 */
	private int getDefaultSize() {
		WindowManager windowManager = (WindowManager) getContext()
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(displayMetrics);
		return Math
				.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		drawLineAndArrows(canvas);
		
	}
	


	/**
	 * ��ÿ��item����
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		final View meanItemView1 = getChildAt(0);
		final View meanItemView2 = getChildAt(1);
		final View meanItemView3 = getChildAt(2);
		final View meanItemView4 = getChildAt(3);
		final View meanItemView5 = getChildAt(4);

		meanItemView1.layout((int) (Circle1W - BJ), (int) (Circle1H - BJ),
				(int) (Circle1W + BJ), (int) (Circle1H + BJ));
		meanItemView1.setId(0);
		
		meanItemView2.layout((int) (Circle2W - BJ), (int) (Circle2H - BJ),
				(int) (Circle2W + BJ), (int) (Circle2H + BJ));
		meanItemView2.setId(1);
		
		meanItemView3.layout((int) (Circle3W - BJ), (int) (Circle3H - BJ),
				(int) (Circle3W + BJ), (int) (Circle3H + BJ));
		meanItemView3.setId(2);
		
		meanItemView4.layout((int) (Circle4W - BJ), (int) (Circle4H - BJ),
				(int) (Circle4W + BJ), (int) (Circle4H + BJ));
		meanItemView4.setId(3);
		
		meanItemView5.layout((int) (Circle5W - BJ), (int) (Circle5H - BJ),
				(int) (Circle5W + BJ), (int) (Circle5H + BJ));
		meanItemView5.setId(4);
		
		meanItemView1.setOnClickListener(this);
		meanItemView2.setOnClickListener(this);
		meanItemView3.setOnClickListener(this);
		meanItemView4.setOnClickListener(this);
		meanItemView5.setOnClickListener(this);
	}

	/**
	 * ���ò˵���Ŀ��ͼ�������
	 */
	public void setMeanItemIconsAndTexts(int[] resIds, String[] texts) {
		if (resIds == null && texts == null) {
			new IllegalArgumentException("�˵���ͼƬ�����ı�����Ϊ��");
		}
		// ��ʼ��onMeanItemCount
		mMeanItemCount = Math.min(resIds.length, texts.length);

		// ѭ����ӵ�item�����ؼ���
		LayoutInflater inflater = LayoutInflater.from(getContext());
		for (int i = 0; i < mMeanItemCount; i++) {
			View itemView = inflater.inflate(R.layout.circle_mean_item, this,
					false);
			ImageView iv = (ImageView) itemView
					.findViewById(R.id.id_circle_menu_item_image);
			TextView tv = (TextView) itemView
					.findViewById(R.id.id_circle_menu_item_text);

			if (iv != null) {
				iv.setImageResource(resIds[i]);
			}
			if (tv != null) {
				tv.setText(texts[i].toString());
			}
			addView(itemView);
		}
	}
	
	public void setOnMeanItemSelectListenner(setOnMeanItemSelectListenner listenner){
		this.listenner = listenner;
	}
	
	
	public interface setOnMeanItemSelectListenner{
		public void onMeanSelect(int position);
	}


	@Override
	public void onClick(View v) {
		
		if(listenner != null){
			listenner.onMeanSelect(v.getId());
		}
	}

	/**
	 * �������߻�����ɫ
	 * @param color
	 */
	public void setPaintColor(int color){
		this.paintColor = color;
	}
	
	/**
	 * ���û��ʿ�ȣ�Ĭ��Ϊ8
	 * @param width
	 */
	public void setPaintWidth(int width){
		this.paintWidth = width;
	}
	
	/**
	 * ���ü�ͷ���
	 * @param aWidth
	 */
	public void setArrowsWidth(int arrawsWidth){
		this.DArraws = arrawsWidth;
	}
	
	/**
	 * �������߼��
	 * @param dashedSpace
	 */
	public void setDashedSpace(float dashedSpace){
		this.dashedSpace = dashedSpace;
	}
}
