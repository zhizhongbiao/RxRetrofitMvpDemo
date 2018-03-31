package cn.com.tianyudg.rxretrofitmvpdemo.basic.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.com.tianyudg.rxretrofitmvpdemo.basic.MainApplication;
import me.yokeyword.fragmentation.SupportFragment;


/**
 * Author : WaterFlower.
 * Created on 2017/8/11.
 * Desc :
 */

public abstract class BaseFragment extends SupportFragment {
    private Unbinder mUnbinder;
    private View contentView;


    /**
     * @return 布局resourceId
     */
    public abstract int getViewLayout();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView = inflater.inflate(getViewLayout(), container, false);
//        mUnbinder = ButterKnife.bind(this, contentView);
        return contentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, contentView);
        initView(savedInstanceState, getArguments());

    }

    /**
     * 初始化View。或者其他view级第三方控件的初始化,及相关点击事件的绑定
     *
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState, Bundle args);


    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
//        检测内存
        RefWatcher memoryWatcher = MainApplication.getMemoryWatcher(getActivity());
        if (memoryWatcher == null) {
            return;
        }
        memoryWatcher.watch(this);
    }
}
