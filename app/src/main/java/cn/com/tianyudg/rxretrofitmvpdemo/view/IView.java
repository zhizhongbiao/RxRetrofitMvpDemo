package cn.com.tianyudg.rxretrofitmvpdemo.view;

import cn.com.tianyudg.rxretrofitmvpdemo.model.IModel;
import cn.com.tianyudg.rxretrofitmvpdemo.presenter.IPresenter;

/**
 * Author : WaterFlower.
 * Created on 2018/3/31.
 * Desc :
 */

public interface IView <P extends IPresenter,M extends IModel>{

    void initVM();

    void showLoading();

    void stopLoading();

    void showError();
}
