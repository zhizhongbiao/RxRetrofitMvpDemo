package cn.com.tianyudg.rxretrofitmvpdemo.view;

/**
 * Author : WaterFlower.
 * Created on 2018/3/31.
 * Desc :
 */

public interface IView {

    void initVM();

    void initLoading();

    void showLoading();

    void stopLoading();

    void showError(String errorMsg);
}
