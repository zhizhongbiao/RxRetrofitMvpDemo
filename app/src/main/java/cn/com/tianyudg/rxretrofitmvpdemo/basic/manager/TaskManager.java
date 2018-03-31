package cn.com.tianyudg.rxretrofitmvpdemo.basic.manager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author : WaterFlower.
 * Created on 2017/8/11.
 * Desc :
 */
public class TaskManager {
    private static TaskManager     instance;
    private        ExecutorService threadPoolExecutor;
    private        ExecutorService serialTaskExecutor;

    private TaskManager() {
        threadPoolExecutor = Executors.newFixedThreadPool(3);
        serialTaskExecutor = Executors.newSingleThreadExecutor();
    }

    public static TaskManager getDefault() {
        if (instance == null) {
            synchronized (TaskManager.class) {
                instance = new TaskManager();
            }

            return instance;
        }
        return instance;
    }

    public void post(Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }


    public void enqueue(Runnable task) {
        serialTaskExecutor.execute(task);
    }

    /**
     * 关闭所有任务线程
     */
    public void destory() {
        threadPoolExecutor.shutdown();
        threadPoolExecutor = null;
        serialTaskExecutor.shutdown();
        serialTaskExecutor = null;
        instance = null;
    }
}
