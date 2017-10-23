package com.example.mybasecustomwidget.system_dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.mybasecustomwidget.R;
import com.example.mybasecustomwidget.utils.Base64;

/**
 * Created by Administrator on 2016/11/29.
 */
public class MyCustomSystemDialogActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_dialog);

        getBrowsableData(getIntent());
    }

    public void showSystemDialog(View view){
        new SystemDialog(getApplicationContext()).showSystemDialogOperation();
    }

    private String getData(String string){
        if(!TextUtils.isEmpty(string)){
            String[] s1 = string.split("\\?");
            if(s1 != null && s1.length >= 2){
                return s1[1].substring(5,s1[1].length());
            }
        }
        return null;
    }

    private void getBrowsableData(Intent intent){
        Uri uri = intent.getData();
        if (uri != null) {
            String urlEncodeStr = getData(uri.toString());
            try {
                byte[] b = Base64.decode(urlEncodeStr);
                String base64EncodeDataStr;
                base64EncodeDataStr = new String(b,"UTF-8");
                Toast.makeText(MyCustomSystemDialogActivity.this,"--->>>base64EncodeDataStr:"+base64EncodeDataStr,1).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(MyCustomSystemDialogActivity.this,"--->>>base64EncodeDataStr onNewIntent",1).show();
    }
}
