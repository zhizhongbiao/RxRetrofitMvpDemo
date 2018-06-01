package cn.com.tianyudg.rxretrofitmvpdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.com.tianyudg.rxretrofitmvpdemo.basic.view.BaseActivity;
import cn.com.tianyudg.rxretrofitmvpdemo.contracts.TestContract;
import cn.com.tianyudg.rxretrofitmvpdemo.view.MvpActivity;

public class MainActivity extends MvpActivity<TestPresenter,TestModel>
        implements View.OnClickListener ,TestContract.ITestView {

    private Button btnLogin;
    private TextView tvMsg;

    @Override
    public int getViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState, ToolbarHolder tbHolder, Intent args) {
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvMsg = (TextView) findViewById(R.id.tvMsg);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnLogin:
                presenter.login("密码","账号");
                break;
        }
    }

    @Override
    public void showLoginMsg(String msg) {
        tvMsg.setText(msg);
    }
}
