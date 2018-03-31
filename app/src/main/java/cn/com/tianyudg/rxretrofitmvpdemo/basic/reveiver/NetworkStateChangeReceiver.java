package cn.com.tianyudg.rxretrofitmvpdemo.basic.reveiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.greenrobot.eventbus.EventBus;


public class NetworkStateChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectMgr  = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo         networkInfo = connectMgr.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            //网络未连接
            EventBus.getDefault().post(new NetworkStateEvent(false));
        } else {
            //网络已连接
            EventBus.getDefault().post(new NetworkStateEvent(true));
        }
    }
}
