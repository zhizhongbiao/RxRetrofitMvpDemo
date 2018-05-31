package cn.com.tianyudg.rxretrofitmvpdemo.contracts;

import cn.com.tianyudg.rxretrofitmvpdemo.presenter.BasePresenter;

/**
 * Created by zhizhongbiao on 2018/5/31.
 */

public interface TestContract {

    interface ITestView
    {
        void showLoginMsg(String msg);

    }


    abstract class TestBasePresenter extends BasePresenter
    {
        abstract void login(String account,String psw);

    }

    interface ITestModel
    {
        void login(String account,String psw);

    }

}
