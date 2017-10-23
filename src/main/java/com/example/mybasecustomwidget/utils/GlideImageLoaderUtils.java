package com.example.mybasecustomwidget.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mybasecustomwidget.R;

/**
 * Created by Administrator on 2017/8/31.
 */
public class GlideImageLoaderUtils {

    /**
     * 方形熊猫头像图片加载器
     * @param url
     * @param imageView
     */
    public static void squarePandaImageLoader(Context context, String url, ImageView imageView){
        if(context != null){
            Glide.with(context.getApplicationContext()).load(url)
                    .asBitmap()
                    .error(R.drawable.huati_head_default)
                    .placeholder(R.drawable.huati_head_default)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }
}
