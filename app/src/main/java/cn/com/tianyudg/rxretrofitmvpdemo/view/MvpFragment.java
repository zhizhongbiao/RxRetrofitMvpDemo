package cn.com.tianyudg.rxretrofitmvpdemo.view;


import android.app.Dialog;
import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.ParameterizedType;

import cn.com.tianyudg.rxretrofitmvpdemo.R;
import cn.com.tianyudg.rxretrofitmvpdemo.basic.presenter.BasePresenter;
import cn.com.tianyudg.rxretrofitmvpdemo.basic.utils.ToastUtil;
import cn.com.tianyudg.rxretrofitmvpdemo.basic.utils.UIUtils;
import cn.com.tianyudg.rxretrofitmvpdemo.basic.view.BaseFragment;
import cn.com.tianyudg.rxretrofitmvpdemo.model.IModel;
import cn.com.tianyudg.rxretrofitmvpdemo.presenter.IPresenter;


/**
 * Author : WaterFlower.
 * Created on 2017/8/11.
 * Desc :
 */

public abstract class MvpFragment<P extends IPresenter, M extends IModel> extends BaseFragment implements IView {

    protected P presenter;
    protected Dialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVM();
        initLoading();
        EventBus.getDefault().register(this);
    }


    @Override
    public void initVM() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class<? extends BasePresenter> presenterClass = (Class<? extends BasePresenter>) type.getActualTypeArguments()[0];
        Class<? extends IModel> modelClass = (Class<? extends IModel>) type.getActualTypeArguments()[1];
        M model = null;
        try {
            this.presenter = (P) presenterClass.newInstance();
            model = (M) modelClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }

        presenter.attachVM(this, model);
    }

    @Override
    public void initLoading() {
        progressDialog = new MaterialDialog.Builder(getActivity())
                .customView(R.layout.layout_load_data_progress, false)
                .cancelable(true).build();
        progressDialog.getWindow().getDecorView().setBackgroundColor(UIUtils.getColor(android.R.color.transparent));
    }

    @Override
    public void showLoading() {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void stopLoading() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showError(String errorMsg) {
        ToastUtil.showShort(errorMsg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        progressDialog = null;
        presenter.detachVM();
        presenter = null;
        EventBus.getDefault().unregister(this);
    }


}
