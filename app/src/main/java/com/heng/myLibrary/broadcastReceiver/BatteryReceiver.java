package com.heng.myLibrary.broadcastReceiver;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.heng.myLibrary.util.MyLogging;

/**
 * @author : HengZhang
 * @date : 2021/12/20 18:49
 * <p>
 * 电池电量广播接收器
 */

public class BatteryReceiver extends BroadcastReceiver {
    private static final String TAG = "BatteryBroadcastReceiver";

    @SuppressLint("LongLogTag")
    @Override
    public void onReceive(Context context, Intent intent) {
        MyLogging.myLog(TAG, "onReceive()");

        if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
            int level = intent.getIntExtra("level", 0);
            Toast.makeText(context.getApplicationContext(), "当前电量：" + level + "%", Toast.LENGTH_SHORT)
                    .show();
            MyLogging.myLog(TAG, "当前电量：" + level + "%");

            if (level < 15) {
                Toast.makeText(context.getApplicationContext(), "电池电量不足15%，请及时充电", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}
