package cn.com.tianyudg.rxretrofitmvpdemo.presenter;

import cn.com.tianyudg.rxretrofitmvpdemo.model.IModel;
import cn.com.tianyudg.rxretrofitmvpdemo.view.IView;

/**
 * Author : WaterFlower.
 * Created on 2018/3/31.
 * Desc :
 */

public interface IPresenter<V extends IView, M extends IModel> {


    void attachVM(V v, M m);

    void onStart();

    void detachVM();

    void onDesytroy();


    V getView();

    M getModel();

    boolean isViewAttach();

    boolean isModelAttach();
}
