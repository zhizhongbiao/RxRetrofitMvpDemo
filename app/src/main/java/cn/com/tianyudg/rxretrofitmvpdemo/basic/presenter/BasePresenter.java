package cn.com.tianyudg.rxretrofitmvpdemo.basic.presenter;

import java.lang.ref.WeakReference;

import cn.com.tianyudg.rxretrofitmvpdemo.basic.view.MvpView;


/**
 * Author : WaterFlower.
 * Created on 2016/9/18.
 * Desc :
 */

public class BasePresenter<V extends MvpView> implements Presenter<V> {

    //用弱引用管理MvpView
    private   WeakReference<V> viewReference;
    protected String           viewClassName;


    /**
     * presenter 绑定MvpView
     *
     * @param view MvpView
     */
    @Override
    public void attachView(V view) {
        viewClassName = view.getClass().getName();
        viewReference = new WeakReference<V>(view);
    }

    /**
     * 判断该Presenter是否已经绑定MvpView
     *
     * @return
     */
    @Override
    public boolean isViewAttached() {
        return viewReference != null && viewReference.get() != null;
    }

    /**
     * 获取该Presenter与之绑定的MvpView
     *
     * @return
     */
    @Override
    public V getView() {
        return viewReference == null ? null : viewReference.get();
    }


    /**
     * 该Presenter与之对应MvpView解绑
     */
    @Override
    public void detachView() {
        if (viewReference != null) {
            viewReference.clear();
            viewReference = null;
        }
    }
}
