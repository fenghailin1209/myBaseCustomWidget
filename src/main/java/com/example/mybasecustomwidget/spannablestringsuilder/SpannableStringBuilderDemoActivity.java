package com.example.mybasecustomwidget.spannablestringsuilder;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mybasecustomwidget.R;

public class SpannableStringBuilderDemoActivity extends Activity {

	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_spannable_string);

		initView();

		setData();
	}


	private void setData() {
		String s = getResources().getString(R.string.test_text);
		SpannableString ss = getSpannableString(s);
		tv.setMovementMethod(LinkMovementMethod.getInstance());
		
		SpannableStringBuilder ssb1 = new SpannableStringBuilder("asdfghjdfdfddddddddddddddddddddd");
		
		ForegroundColorSpan span = new ForegroundColorSpan(Color.BLUE);
		ssb1.setSpan(span, 1, 3, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);  
		
		ssb1.replace(5, 8, ss);
		
		tv.setText(ssb1);
	}

	
	private SpannableString getSpannableString(String s) {
		SpannableString ssb;
		String[] text = parseStr(s);
		String url = text[0].toString();
		int beginIndex = Integer.parseInt(text[3]);
		int endIndex = Integer.parseInt(text[4]);
		String content = text[2].toString();
		ssb = new SpannableString(content);
		ssb.setSpan(new URLSpan(url), beginIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // ����
		ssb.setSpan(new ForegroundColorSpan(Color.GREEN),beginIndex, endIndex,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return ssb;
	}
	
	
	private SpannableStringBuilder getSpannableStringBuilder(String s) {
		SpannableStringBuilder ssb;
		String[] text = parseStr(s);
		String url = text[0].toString();
		int beginIndex = Integer.parseInt(text[3]);
		int endIndex = Integer.parseInt(text[4]);
		String content = text[2].toString();
		ssb = new SpannableStringBuilder(content);
		ssb.setSpan(new URLSpan(url), beginIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); // ����
		ssb.setSpan(new ForegroundColorSpan(Color.GREEN),beginIndex, endIndex,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return ssb;
	}

	private String[] parseStr(String str) {
		int beginIndex = str.indexOf("[")+1;
		int endIndex = str.lastIndexOf("]");
		
		String s2 = str.substring(beginIndex,endIndex);
		String s3[] = s2.split(",");
		String title = s3[1];
		String url = s3[0];
		
		StringBuffer sb = new StringBuffer(str);
		sb.replace(beginIndex - 1, endIndex + 1, title);
		String text = sb.toString();
		int textIndex = text.indexOf(title);
		int textEnd = textIndex + title.length();
		
		String[] s4 = new String[5];
		s4[0] = url;
		s4[1] = title;
		s4[2] = text;
		s4[3] = textIndex+"";
		s4[4] = textEnd +"";
		return s4;
	}

	private void initView() {
		tv = (TextView)findViewById(R.id.tv);
	}

}