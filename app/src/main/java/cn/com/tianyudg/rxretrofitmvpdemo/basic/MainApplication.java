package cn.com.tianyudg.rxretrofitmvpdemo.basic;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Handler;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.store.PersistentCookieStore;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import cn.com.tianyudg.rxretrofitmvpdemo.basic.manager.CacheManager;
import cn.com.tianyudg.rxretrofitmvpdemo.basic.utils.LogUtils;


public class MainApplication
        extends Application {
    public static final String MAIN_PREFERENCE = "INFO_PREFERENCE";
    public SharedPreferences mainPreferences;
    //主线程handler
    private static Handler mMainThreadHandler = new Handler();
    //主线程
    private static Thread mMainThread = Thread.currentThread();
    //主线程Id
    private static int mMainThreadId = android.os.Process.myTid();
    //context
    private static MainApplication instance;

    //内存检测watcher
    private RefWatcher memoryWatcher;

//    public static AMap aMap = null;

    public static RefWatcher getMemoryWatcher(Context context) {
        return ((MainApplication) context.getApplicationContext()).memoryWatcher;
    }

    public static boolean isApkDebugable(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {

        }
        return false;
    }


//    public LocationService locationService;
//    PatchManager patchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initOkGo();
        CacheManager.openCache(getInstance(), "data");
        mainPreferences = getSharedPreferences(MAIN_PREFERENCE, MODE_PRIVATE);

        initLeakCanary();
//        GreenDaoHelper.initGD(this,"history.db");

    }



    /**
     * 初始化LeakCanary,内存检测
     */
    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this) || !isApkDebugable(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            LogUtils.e("LeakCanary.isInAnalyzerProcess(this)||!isApkDebugable(this)初始化LeakCanary,内存检测");
            return;
        }
        memoryWatcher = LeakCanary.install(this);
    }


    /**
     * 初始化OkGO
     */
    private void initOkGo() {
        OkGo.init(this);
        try {
            OkGo.getInstance()
                    //打开该调试开关,控制台会使用 红色error 级别打印log,并不是错误,是为了显眼,不需要就不要加入该行
                    .debug("OkHttpUtils")
                    .setCookieStore(new PersistentCookieStore());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static int getMainThreadId() {
        return mMainThreadId;
    }

    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    public static MainApplication getInstance() {
        return instance;
    }

    public static SharedPreferences getMainPreferences() {
        return getInstance().mainPreferences;
    }


}
