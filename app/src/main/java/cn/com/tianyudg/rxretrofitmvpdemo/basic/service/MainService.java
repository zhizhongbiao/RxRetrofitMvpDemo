package cn.com.tianyudg.rxretrofitmvpdemo.basic.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MainService extends IntentService {
    private static final String ACTION_CHECK_PATCH_UPDATE = "cn.com.tianyudg.android_agent.base.mvp.service.action.ACTION_CHECK_PATCH_UPDATE";
    private static final String ACTION_CHECK_APK_UPDATE   = "cn.com.tianyudg.android_agent.base.mvp.service.action.ACTION_CHECK_APK_UPDATE";


    public MainService() {
        super("MainService");
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (ACTION_CHECK_PATCH_UPDATE.equals(action)) {
                handleActionPatchUpdate();
            } else if (ACTION_CHECK_APK_UPDATE.equals(action)) {
                handleActionApkUpdate();
            }
        }
    }


    public static void startActionCheckPatchUpdate(Context context) {
        Intent intent = new Intent(context, MainService.class);
        intent.setAction(ACTION_CHECK_PATCH_UPDATE);
        context.startService(intent);
    }

    public static void startActionApkUpdate(Context context) {
        Intent intent = new Intent(context, MainService.class);
        intent.setAction(ACTION_CHECK_APK_UPDATE);
        context.startService(intent);
    }


    private void handleActionPatchUpdate() {

    }

    private void handleActionApkUpdate() {
        // TODO: 2016/4/21 检查更新，处理更新
    }
}
