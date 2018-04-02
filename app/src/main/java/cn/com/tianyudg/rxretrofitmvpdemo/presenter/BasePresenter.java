package cn.com.tianyudg.rxretrofitmvpdemo.presenter;

import java.lang.ref.WeakReference;

import cn.com.tianyudg.rxretrofitmvpdemo.model.IModel;
import cn.com.tianyudg.rxretrofitmvpdemo.util.MyUtil;
import cn.com.tianyudg.rxretrofitmvpdemo.view.IView;

/**
 * Author : WaterFlower.
 * Created on 2018/3/31.
 * Desc :
 */

public class BasePresenter<V extends IView, M extends IModel> implements IPresenter<V, M> {

    WeakReference<V> viewHolder;
    WeakReference<M> modelHolder;

    @Override
    public void attachVM(V v, M m) {
        MyUtil.checkNotNull(v);
        MyUtil.checkNotNull(m);
        viewHolder = new WeakReference<V>(v);
        modelHolder = new WeakReference<M>(m);
        onStart();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void detachVM() {
        if (viewHolder == null || modelHolder == null) return;
        viewHolder.clear();
        modelHolder.clear();
        onDesytroy();
    }

    @Override
    public void onDesytroy() {

    }

    @Override
    public V getView() {
        if (viewHolder == null) return null;
        return viewHolder.get();
    }

    @Override
    public M getModel() {
        if (viewHolder == null) return null;
        return modelHolder.get();
    }

    @Override
    public boolean isViewAttach() {
        return viewHolder != null && viewHolder.get() != null;
    }

    @Override
    public boolean isModelAttach() {
        return modelHolder != null && modelHolder.get() != null;
    }
}
