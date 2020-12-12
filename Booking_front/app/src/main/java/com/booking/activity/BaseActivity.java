package com.booking.activity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.booking.utils.NetBroadcastReceiver;

/*
 * 页面需要监听那个页面就继承BaseActivity，然后打开监听方法
 * */
public class BaseActivity extends AppCompatActivity {
    private NetBroadcastReceiver netBroadcastReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("onRestart", "actiivty正在重新启动，当当前activity由不可见变成可见时，onRestart就会被调用");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("onStart", "activity可见，未出现在前台无法和用户交互，已显示，但看不见");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("onStop", "activity即将停止");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy", "activity即将被销毁，做回收工作和最终的资源释放");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("onPause", "activity正在停止，紧接着就是onstop,如果快速再回到当前，onResume就会被调用,可以在此方法停止动画但不要做耗时操作，因为onPause必须先执行完，新activity的onResume才会执行");
        Log.w("onPause2---->", "onPause2---->");
//        if (netBroadcastReceiver != null) {
        Log.d("onPause---->", "unregisterReceiver");
        unregisterReceiver(netBroadcastReceiver);
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume", "activity可见，出现在前台和用户交互");
        Log.d("onResume1---->", "onResume1---->");
//        if (netBroadcastReceiver == null) {
        Log.d("onResume---->", "注册广播");
        registerBroadcastReceiver();
//        }
    }

    /**
     * 注册网络状态广播
     */
    private void registerBroadcastReceiver() {
        Log.d("注册方法", "-->");
        //注册广播
        Log.d("正式注册", "-->");
        netBroadcastReceiver = new NetBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netBroadcastReceiver, filter);
        //设置监听
        //netBroadcastReceiver.setStatusMonitor(this);
    }
}
