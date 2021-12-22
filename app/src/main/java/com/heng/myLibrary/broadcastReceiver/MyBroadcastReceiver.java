package com.heng.myLibrary.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.heng.myLibrary.util.MyLogging;

/**
 * @author : HengZhang
 * @date : 2021/12/19 17:26
 *
 * 广播接收者
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        MyLogging.myLog("MyBroadcastReceiver", "广播接收者,接收到了广播 :" + intent.getAction());

        Toast.makeText(context.getApplicationContext(), "BGM开始播放", Toast.LENGTH_SHORT)
                .show();
    }
}
