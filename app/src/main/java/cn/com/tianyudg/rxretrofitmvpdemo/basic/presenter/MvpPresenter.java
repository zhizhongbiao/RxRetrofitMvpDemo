package cn.com.tianyudg.rxretrofitmvpdemo.basic.presenter;

import android.content.SharedPreferences;
import android.support.v4.util.ArrayMap;

import cn.com.tianyudg.rxretrofitmvpdemo.basic.MainApplication;
import cn.com.tianyudg.rxretrofitmvpdemo.basic.manager.DataManager;
import cn.com.tianyudg.rxretrofitmvpdemo.basic.manager.FileManager;
import cn.com.tianyudg.rxretrofitmvpdemo.basic.model.BaseBean;
import cn.com.tianyudg.rxretrofitmvpdemo.basic.network.RequestInfo;
import cn.com.tianyudg.rxretrofitmvpdemo.basic.network.ResponseInfo;
import cn.com.tianyudg.rxretrofitmvpdemo.basic.utils.LogUtils;
import cn.com.tianyudg.rxretrofitmvpdemo.basic.utils.ToastUtil;
import cn.com.tianyudg.rxretrofitmvpdemo.basic.view.MvpView;

import java.io.Serializable;


/**
 * Author : WaterFlower.
 * Created on 2017/6/18.
 * Desc :
 */

public class MvpPresenter extends BasePresenter<MvpView> implements Callback {
    protected boolean needDialog = true;
    private int requestCount;


    public void getData(String url, ArrayMap<String, Serializable> params) {
        getData(url, params, null);
    }

    public void getData(String url, Class<? extends BaseBean> dataClass) {
        getData(url, null, dataClass);
    }


    public void getData(String url, ArrayMap<String, Serializable> params, Class<? extends BaseBean> dataClass) {
        if (isViewAttached() && needDialog && requestCount == 0) {
            getView().onLoading();
        }

        RequestInfo requestInfo = new RequestInfo(url, dataClass);
        requestInfo.setRequestType(RequestInfo.REQUEST_GET);
        requestInfo.setRequestParams(params);
        //requestInfo.setNeedMockData(true);
        DataManager.getDefault().loadData(requestInfo, this);
        requestCount++;
    }


    public void postData(String url, ArrayMap<String, Serializable> params) {
        postData(url, params, null);
    }

    public void postData(String url, Class<? extends BaseBean> dataClass) {
        postData(url, null, dataClass);
    }


    public void postData(String url, ArrayMap<String, Serializable> params, Class<? extends BaseBean> dataClass) {
        if (isViewAttached() && needDialog && requestCount == 0) {
            getView().onLoading();
        }

        RequestInfo requestInfo = new RequestInfo(url, dataClass);
        requestInfo.setRequestType(RequestInfo.REQUEST_POST);
        requestInfo.setRequestParams(params);
        DataManager.getDefault().loadData(requestInfo, this);
        requestCount++;
    }

    public FileManager getFileManager() {
        return FileManager.getInstance();
    }


    public SharedPreferences getDefaultPrefs() {
        //返回整个默认的SharedPreference文件;
        return MainApplication.getMainPreferences();
    }


    public void syncDataInDB(String url, ArrayMap<String, Serializable> params, Class<? extends BaseBean> dataVoClass) {
//        DataManager.getDefault().loadDataFromLocal(url, params, dataVoClass, this);
    }

    /**
     * 请求成功，回调View层方法处理成功的结果
     *
     * @param responseInfo 包含的返回数据的BaseVo子类对象
     */
    @Override
    public void onSuccess(ResponseInfo responseInfo) {
        requestCount--;
        if (isViewAttached()) {
            getView().beforeSuccess();
            getView().showContentView(responseInfo.getUrl(), responseInfo.getDataVo());
            if (requestCount == 0) {
                getView().onStopLoading();
            }
        } else {
            LogUtils.e("MvpView已被销毁，onSuccess方法无法回调showContentView方法");
        }
    }


    public boolean isNeedDialog() {
        return needDialog;
    }

    public void setNeedDialog(boolean needDialog) {
        this.needDialog = needDialog;
    }

    /**
     * 请求失败，回调View层的方法处理错误信息
     *
     * @param responseInfo 包含错误码和错误信息的BaseVo子类对象
     */
    @Override
    public void onError(ResponseInfo responseInfo) {
        requestCount--;
        if (isViewAttached()) {
            getView().onStopLoading();

            switch (responseInfo.getState()) {
                case ResponseInfo.FAILURE:
                case ResponseInfo.CACHE_PARSE_ERROR:
                case ResponseInfo.JSON_PARSE_ERROR:

                    LogUtils.e("responseInfo.getMsg()=" + responseInfo.getMsg());
//                    禁止提示错误信息
                    if (!"暂无符合条件的数据".equals(responseInfo.getMsg())) {
                        ToastUtil.showShort(responseInfo.getMsg());
                    }

                    break;
                case ResponseInfo.TIME_OUT:
                    ToastUtil.showShort("网络连接不稳定，请检查网络设置");
                    break;
                case ResponseInfo.NO_INTERNET_ERROR:
                    ToastUtil.showShort("没有可用的网络，请检查您的网络设置");
                    break;
                case ResponseInfo.SERVER_UNAVAILABLE:
                    ToastUtil.showShort("接口不可用!");
                    break;
                case ResponseInfo.UN_LOGIN:
//                    Context context;
//                    if (getView() instanceof Context) {
//                        context = (Context) getView();
//                    } else {
//                        context = ((Fragment) getView()).getContext();
//                    }
//                    TODO WaterFlower did this
//                    Intent intent = new Intent(context, LoginActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    context.startActivity(intent);
                    break;
                case ResponseInfo.UN_SET_APP:
                    //TODO WaterFlower did this
//                    mParams.put("type", "MALL_MOBILE_ANDROID");
//                    getData(ApiConfig.API_SET_CLIENT_TYPE, mParams, BaseBean.class);
                    break;
                case 8000:
                    break;
            }

            getView().onError(responseInfo);
        } else {
            LogUtils.e("MvpView已销毁，onError方法无法回调MvpView层的方法: " + viewClassName);
        }
    }

    @Override
    public void detachView() {
        super.detachView();
        //取消默认的还未完成的请求
        DataManager.getDefault().onViewDetach(getView());
    }
}
