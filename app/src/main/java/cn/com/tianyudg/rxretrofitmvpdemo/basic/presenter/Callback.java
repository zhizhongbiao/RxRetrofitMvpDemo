package cn.com.tianyudg.rxretrofitmvpdemo.basic.presenter;


import cn.com.tianyudg.rxretrofitmvpdemo.basic.network.ResponseInfo;

public interface Callback {

    /**
     * 请求成功回调方法
     */
    void onSuccess(ResponseInfo resoponseInfo);

    /**
     * 请求失败回调方法
     */
    void onError(ResponseInfo resoponseInfo);
}
