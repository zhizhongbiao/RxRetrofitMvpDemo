package cn.com.tianyudg.rxretrofitmvpdemo.basic.widget.scrollable;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Author : WaterFlower.
 * Created on 2017/8/24.
 * Desc :  拦截事件，防止传给内部嵌套view - 解决recyclerView滑动惯性消失的问题
 */

public class MyScrollView extends ScrollView {


    public MyScrollView(Context context) {
        super(context);

    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (this.onScrollChangedListener == null) return;

        onScrollChangedListener.onScrollChanged(t);

    }

    public void setOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
        this.onScrollChangedListener = onScrollChangedListener;
    }

    private OnScrollChangedListener onScrollChangedListener;

    public interface OnScrollChangedListener {
        void onScrollChanged(int scrolledDistanceYFromTop);
    }

}