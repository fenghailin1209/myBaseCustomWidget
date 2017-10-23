package com.example.mybasecustomwidget.system_dialog;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/29.
 */
public class SystemDialog {
    private Dialog dialog;
    private Context context;
    private CloseSystemDialogsReceiver mCloseSystemDialogsReceiver;

    public SystemDialog(Context context){
        this.context = context;

        registerClickHomeReceiver();
    }

    private void registerClickHomeReceiver(){
        IntentFilter filter = new IntentFilter(
                Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        mCloseSystemDialogsReceiver = new CloseSystemDialogsReceiver();
        context.registerReceiver(mCloseSystemDialogsReceiver, filter);
    }

    public void showSystemDialogOperation(){
        dialog = PublicDialog.createDialog(context, "主持人邀请你上麦", true,
                new PublicDialog.OnBtnClick() {
                    @Override
                    public void onClickOK() {
                        Toast.makeText(context,"OK",0).show();
                    }

                    @Override
                    public void onClickCancel() {
                        Toast.makeText(context,"CANCLE",0).show();
                    }
                });
        dialog.getWindow().setType(android.view.WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
    }

    private class CloseSystemDialogsReceiver extends BroadcastReceiver {
        final String SYSTEM_DIALOG_REASON_KEY = "reason";
        final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_CLOSE_SYSTEM_DIALOGS.equals(intent.getAction())) {
                String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
                if (SYSTEM_DIALOG_REASON_HOME_KEY.equals(reason)) {
                    if(dialog != null && dialog.isShowing()){
                        dialog.dismiss();
                    }

                    context.unregisterReceiver(mCloseSystemDialogsReceiver);
                }
            }

        }
    }

}
