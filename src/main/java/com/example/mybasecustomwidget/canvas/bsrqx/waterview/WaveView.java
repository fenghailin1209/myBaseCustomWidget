package com.example.mybasecustomwidget.canvas.bsrqx.waterview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * mind���ױ�������������
 * ˮ����ˮ������(ԭ�������ƶ��������ߵĿ��Ƶ�ͬʱ���ϸ��Ķ��������Y���꣬Ȼ�󲻶��ػ�)
 * @author Administrator
 *
 */
public class WaveView extends View {
	
	private Path mPath;
	private Paint mPaint;
	
	//�ؼ����
	private int vWidth,vHeight;
	private float ctrX,ctrY;//���Ƶ��x��y����
	private float waveY;//����Ware�������˵��Y���꣬����������Ƶ��Y����������һ��
	
	private boolean isInc;//�жϿ��Ƶ����ƻ�������
	

	public WaveView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public WaveView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public WaveView(Context context) {
		super(context);
		init();
	}

	private void init() {
		// ʵ�������ʲ����ò���  
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);  
        mPaint.setColor(0xFFA2D6AE);  
  
        // ʵ����·������  
        mPath = new Path(); 
	}

	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		 // ��ȡ�ؼ����  
		vWidth = w;
		vHeight = h;
		
		//������Ƶ�Y����
		waveY = 1 / 8f * vHeight;
		
		//����˵��Y����
		ctrY = -1 / 16f * vHeight;
		
		Log.d("", "--->>>waveY:"+waveY+",ctrY:"+ctrY);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		/* 
         * ����Path��� 
         * ע���ҽ�Path������������˿ؼ����ⲿ������������ 
         * ������ǽ���������ڿؼ����x=0��λ�û�ʹ�ñ��������߱����Ӳ 
         * ����Ϊʲô�ղ����Ѿ�˵�� 
         * ����������΢����������⡱�ߵ� 
         */ 
		mPath.moveTo(-1 / 4f * vWidth, waveY);
		
		/* 
         * �Զ������ߵķ�ʽͨ�����Ƶ�����λ�ڿؼ��ұߵ��յ� 
         * �յ��λ��Ҳ���ڿؼ��ⲿ 
         * ����ֻ�費����ctrX�Ĵ�С�仯����ʵ�֡��ˡ���Ч�� 
         */  
		mPath.quadTo(ctrX, ctrY, vWidth + 1 / 4f * vWidth, waveY);
		Log.d("", "--->>>startX:" + -1 / 4f * vWidth + ",startY:"+waveY+",ctrX:"+ctrX+",ctrY:"+ctrY+",endX:"+(vWidth + 1 / 4f * vWidth)+",endY:"+waveY+",vWidth:"+vWidth+",vHeight:"+vHeight);
		
		// Χ�ƿؼ��պ�����  
		mPath.lineTo(vWidth + 1/4f * vWidth, vHeight);
		mPath.lineTo(-1 / 4f * vWidth, vHeight);
		mPath.close();
		
		canvas.drawPath(mPath, mPaint);
		
		/**
		 * �����Ƶ��x������ڻ�����յ�x����ʱ���ı�ʶֵ 
		 */
		if(ctrX > vWidth + 1/4f * vWidth){
			isInc = false;
			/**
	         * �����Ƶ��x����С�ڻ�������x����ʱ���ı�ʶֵ 
	         */
		}else if(ctrX <= -1 / 4f * vWidth){
			isInc = true;
		}
		// ���ݱ�ʶֵ�жϵ�ǰ�Ŀ��Ƶ�x�����Ǹüӻ��Ǽ�
		ctrX = isInc ? ctrX + 20 : ctrX - 20;
		
		/* 
         * �á�ˮ�����ϼ��� 
         */ 
		if(ctrX <= vHeight){
			ctrY += 2;
			waveY += 2;
		}
		
		mPath.reset();
		if(waveY < vHeight - 50){
			invalidate();
		}
	}
}
