package com.example.mybasecustomwidget.system_dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mybasecustomwidget.R;

/**
 * Created by Administrator on 2016/11/29.
 */
public class PublicDialog {
    /**
     * 得到自定义dialog
     *
     * @param context
     * @param msg
     * @return
     */
    public static Dialog createDialog(Context context, String msg,
                                      boolean isDoubleButton, final OnBtnClick onBtnClick) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.exit_dialog_talk, null);// 得到加载view
//		LinearLayout layout = (LinearLayout) v.findViewById(R.id.exit_layout);// 得到加载view
        TextView txtinfo = (TextView) v.findViewById(R.id.txtinfo);
        Button btn_ok = (Button) v.findViewById(R.id.btn_ok);

        Button btn_cancel = (Button) v.findViewById(R.id.btn_cancel);
        if (!isDoubleButton) {
            btn_cancel.setVisibility(View.GONE);
        }
        txtinfo.setText(msg);
        final Dialog myDialog = new Dialog(context, R.style.my_dialog);// 创建自定义样式dialog
//		final Dialog myDialog =  new AlertDialog.Builder(context).create();;// 创建自定义样式dialog

        btn_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (onBtnClick != null) {
                    onBtnClick.onClickOK();
                }
                myDialog.dismiss();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (onBtnClick != null) {
                    onBtnClick.onClickCancel();
                }
                myDialog.dismiss();
            }
        });

        myDialog.setCancelable(false);// 不可以用“返回键”取消
        myDialog.setContentView(v, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        return myDialog;
    }

    public interface OnBtnClick {
        public void onClickOK();

        public void onClickCancel();
    }
}
