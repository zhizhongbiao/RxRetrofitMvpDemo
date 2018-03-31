package cn.com.tianyudg.rxretrofitmvpdemo.basic.widget.popup;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Author : WaterFlower.
 * Created on 2017/12/4.
 * Desc :
 */

public class PopupWindowAdaptAndroidN extends PopupWindow {

    private static final int DEFAULT_ANCHORED_GRAVITY = Gravity.TOP | Gravity.START;

    public PopupWindowAdaptAndroidN(Context context) {
        super(context);
    }

    public PopupWindowAdaptAndroidN(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PopupWindowAdaptAndroidN(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PopupWindowAdaptAndroidN(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public PopupWindowAdaptAndroidN() {
    }

    public PopupWindowAdaptAndroidN(View contentView) {
        super(contentView);
    }

    public PopupWindowAdaptAndroidN(int width, int height) {
        super(width, height);
    }

    public PopupWindowAdaptAndroidN(View contentView, int width, int height) {
        super(contentView, width, height);
    }

    public PopupWindowAdaptAndroidN(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
    }


    @Override
    public void showAsDropDown(View anchor) {
        showAsDropDown(anchor, 0, 0);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        showAsDropDown(anchor, xoff, yoff, DEFAULT_ANCHORED_GRAVITY);
    }


    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        Rect rect = new Rect();
        anchor.getGlobalVisibleRect(rect);
        int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;

        if (Build.VERSION.SDK_INT >= 24) {
            setHeight(h);
        } else if (Build.VERSION.SDK_INT <= 18) {
            super.showAtLocation(anchor, Gravity.CENTER, 0, rect.bottom);
            return;
        }
        super.showAsDropDown(anchor, xoff, yoff, gravity);
    }


}
