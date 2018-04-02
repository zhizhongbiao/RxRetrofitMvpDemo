package cn.com.tianyudg.rxretrofitmvpdemo.basic.view;


import cn.com.tianyudg.rxretrofitmvpdemo.basic.model.BaseBean;
import cn.com.tianyudg.rxretrofitmvpdemo.basic.network.ResponseInfo;

/**
 * Author : WaterFlower.
 * Created on 2017/8/11.
 * Desc :
 */
public interface MvpView {


    /**
     * 开始加载数据时回调此方法，用以显示加载ProgressDialog或者其他的的操作
     */
    void onLoading();


    /**
     * 数据加载成功后的回调
     */
    void beforeSuccess();

    /**
     * 默认请求数据解析成功后，将数据填充到View，并显示View
     *
     * @param url    请求的url
     * @param baseBean 解析成功后返回VO对象
     */
    void showContentView(String url, BaseBean baseBean);


    /**
     * 加载数据完成回调方法
     */
    void onStopLoading();


    /**
     * 加载出错
     * @param responseInfo 响应信息
     */
    void onError(ResponseInfo responseInfo);

}
