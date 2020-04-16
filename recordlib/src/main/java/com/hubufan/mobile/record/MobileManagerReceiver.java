package com.hubufan.mobile.record;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.zlw.main.recorderlib.RecordManager;

/**
 * @author With You
 * @version 5.0.0
 * @date 2020/04/16 14:56
 * @email 1713397546@qq.com
 * @description
 */
public class MobileManagerReceiver extends BroadcastReceiver {

    private static final String TAG = MobileManagerReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            TelephonyManager tManager = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
            switch (tManager.getCallState()) {
                case TelephonyManager.CALL_STATE_RINGING://电话进来时
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK://呼叫状态(开始录音)
                    Log.d(TAG, "**********************呼叫状态(开始录音)**********************");
                    RecordManager.getInstance().start();
                    break;
                case TelephonyManager.CALL_STATE_IDLE://电话处于挂断状态(结束录音)
                    Log.d(TAG, "**********************电话处于挂断状态(结束录音)**********************");
                        RecordManager.getInstance().stop();
                    break;
            }
        }
    }

}
