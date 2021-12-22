package com.heng.myLibrary.util;

import android.util.Log;

/**
 * @author : HengZhang
 * @date : 2021/12/22 16:35
 * <p>
 * 我的log类
 */

public class MyLogging {

    public MyLogging() {
    }

    public static void myLog(String logClassName, String logText) {
        Log.e(logClassName, logText);
    }
}
