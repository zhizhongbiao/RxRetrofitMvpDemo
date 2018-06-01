package cn.com.tianyudg.rxretrofitmvpdemo;

import cn.com.tianyudg.rxretrofitmvpdemo.basic.network.ResponseInfo;
import cn.com.tianyudg.rxretrofitmvpdemo.contracts.TestContract;
import cn.com.tianyudg.rxretrofitmvpdemo.view.IView;
import cn.com.tianyudg.rxretrofitmvpdemo.view.MvpDialogFragment;

/**
 * Created by zhizhongbiao on 2018/5/31.
 */

public class TestPresenter extends TestContract.TestBasePresenter {
    @Override
    public void login(String account, String psw) {
        ((TestModel) getModel()).login(account,psw,this);
    }

    @Override
    protected void onRequestDataSuccess(ResponseInfo resoponseInfo) {
        ((MainActivity) getView()).showLoginMsg("登录成功！！！");
    }

    @Override
    protected void onRequestDataError(ResponseInfo resoponseInfo) {
        getView().showError("出错了。。。。");
    }
}
