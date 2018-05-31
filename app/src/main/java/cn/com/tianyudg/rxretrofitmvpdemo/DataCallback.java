package cn.com.tianyudg.rxretrofitmvpdemo;

import cn.com.tianyudg.rxretrofitmvpdemo.bean.IBean;

/**
 * Created by zhizhongbiao on 2018/5/31.
 */

public interface DataCallback {

    void onError(String url);
    void onSuccess(String url, IBean baseBean);
}
