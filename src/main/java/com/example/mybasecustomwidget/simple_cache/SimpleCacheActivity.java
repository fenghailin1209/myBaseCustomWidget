package com.example.mybasecustomwidget.simple_cache;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mybasecustomwidget.R;

import org.afinal.simplecache.ACache;

/**
 * Created by Administrator on 2016/10/14.
 */
public class SimpleCacheActivity extends Activity{

    private ACache mCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_cache);

        mCache = ACache.get(this);
    }

    public void Get(View view){
        Toast.makeText(this,mCache.getAsString("sss"),0).show();
    }

    public void Set(View view){
        mCache.put("sss","sb");
    }
}
