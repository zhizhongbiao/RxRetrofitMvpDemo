package cn.com.tianyudg.rxretrofitmvpdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.com.tianyudg.rxretrofitmvpdemo.basic.view.BaseActivity;
import cn.com.tianyudg.rxretrofitmvpdemo.view.MvpActivity;

public class MainActivity extends MvpActivity<TestPresenter,TestModel> {

    @Override
    public int getViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState, ToolbarHolder tbHolder, Intent args) {

    }
}
