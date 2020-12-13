package com.booking.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.booking.activity.ActivityCollector;
import com.booking.activity.MainActivity;

public class ForceOfflineReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(super.toString(),"下线");
        //测试：暂时跳转
        ActivityCollector.finishAll();
        Intent newIntent = new Intent(context, MainActivity.class);
        newIntent.addFlags(newIntent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(newIntent);
    }
}
