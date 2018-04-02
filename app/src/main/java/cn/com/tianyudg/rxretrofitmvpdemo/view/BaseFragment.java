package cn.com.tianyudg.rxretrofitmvpdemo.view;


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



    public abstract int getViewLayout();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView = inflater.inflate(getViewLayout(), container, false);
        return contentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, contentView);
        initView(savedInstanceState, getArguments());

    }

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
