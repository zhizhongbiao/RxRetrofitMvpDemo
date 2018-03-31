package cn.com.tianyudg.rxretrofitmvpdemo.basic.presenter;

/**
 * Author : WaterFlower.
 * Created on 2016/9/18.
 * Desc :
 */

public interface Presenter<V> {

    /**
     * 绑定MvpView
     * @param view
     */
    void attachView(V view);

    /**
     * 判断Presenter是否绑定MvpView
     * @return
     */
    boolean isViewAttached();

    /**
     * 获取该Presenter绑定的MvpView
     * @return
     */
    V getView();

    /**
     * 该Presenter与之对应的MvpView解绑
     */
    void detachView();
}
