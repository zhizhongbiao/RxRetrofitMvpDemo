package cn.com.tianyudg.rxretrofitmvpdemo.basic.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import cn.com.tianyudg.rxretrofitmvpdemo.basic.widget.popup.PopupWindowAdaptAndroidN;


/**
 * Author : WaterFlower.
 * Created on 2017/8/30.
 * Desc :
 */

public class WindowUtil {


    /**
     * @param view
     * @param width
     * @param height
     * @param anchorView
     * @return
     */
    public static PopupWindowAdaptAndroidN showPw(View view, int width, int height, View anchorView) {
        final PopupWindowAdaptAndroidN popupWindow = new PopupWindowAdaptAndroidN();
        popupWindow.setContentView(view);
        popupWindow.setWidth(width);
        popupWindow.setHeight(height);
        // 需要设置一下此参数，点击外边可消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 设置点击窗口外边窗口消失
        popupWindow.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        popupWindow.setFocusable(true);

//        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
////                点击外面消失
//                LogUtils.e("event.getAction()="+event.getAction());
//                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
//                    LogUtils.e("MotionEvent.ACTION_OUTSIDE="+MotionEvent.ACTION_OUTSIDE);
//                    closePopWin(popupWindow);
//                    return true;
//                }
//                return false;
//            }
//        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            popupWindow.showAsDropDown(anchorView, 0, 0, Gravity.CENTER);
        } else {
            popupWindow.showAsDropDown(anchorView, 0, 0);
        }

        return popupWindow;
    }


    public static PopupWindow showPw(View view, View anchorView, int width, int height) {
        final PopupWindow popupWindow = new PopupWindow();
        popupWindow.setContentView(view);
        popupWindow.setWidth(width);
        popupWindow.setHeight(height);
        // 需要设置一下此参数，点击外边可消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 设置点击窗口外边窗口消失
        popupWindow.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        popupWindow.setFocusable(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            popupWindow.showAsDropDown(anchorView, 0, 0, Gravity.CENTER);
        } else {
            popupWindow.showAsDropDown(anchorView, 0, 0);
        }

        return popupWindow;
    }





    public static PopupWindowAdaptAndroidN showPw(View view, View anchorView) {

        return showPw(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, anchorView);
    }

    public static boolean closePopWin(PopupWindow pw) {
        if (pw != null && pw.isShowing()) {
            pw.dismiss();
            return true;
        }
        return false;

    }


    public static AlertDialog showDialog(Context context, @LayoutRes int layoutResId) {
        return showDialog(context, layoutResId, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    public static AlertDialog showDialog(Context context, @LayoutRes int layoutResId, int width, int height) {
        View view = LayoutInflater.from(context).inflate(layoutResId, null, false);
        return showDialog(context, view, width, height);
    }

    public static AlertDialog showDialog(Context context, @LayoutRes int layoutResId, double factor) {
        View view = LayoutInflater.from(context).inflate(layoutResId, null, false);
        return showDialog(context, view, factor);
    }

    public static AlertDialog showDialog(Context context, View view, int width, int height) {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setView(view)
                .setCancelable(false)
                .create();
        alertDialog.show();
        setDialogParams(alertDialog, width, height);
        return alertDialog;
    }

    public static AlertDialog showDialog(Context context, View view, double factor) {
        return showDialog(context, view, factor, factor);
    }

    public static AlertDialog showDialog(Context context, View view, double widthFactor, double heightFactor) {

        return showDialog(context, view, (int) (getScreenWidth(context) * widthFactor), (int) (getScreenHeight(context) * heightFactor));
    }

    /**
     * 关闭对话框
     *
     * @param ag
     * @return
     */
    public static boolean closeDialog(Dialog ag) {
        if (ag != null && ag.isShowing()) {
            ag.dismiss();
            return true;
        }

        return false;
    }

    /**
     * 展示对话框
     *
     * @param ag
     * @return
     */
    public static boolean showDialog(Dialog ag) {
        if (ag != null && !ag.isShowing()) {
            ag.show();
            return true;
        }

        return false;
    }


    /**
     * 设置一个对话框大小
     *
     * @param context
     * @param dlg
     * @param factor
     */
    public static void setDialogParams(Context context, Dialog dlg, double factor) {
        int width = ((int) (getScreenWidth(context) * factor));
        int height = ((int) (getScreenHeight(context) * factor));
        setDialogParams(dlg, width, height);
    }


    public static void setDialogParams(Context context, Dialog dlg, double wFactor, double hFactor) {
        int width = ((int) (getScreenWidth(context) * wFactor));
        int height = ((int) (getScreenHeight(context) * hFactor));
        setDialogParams(dlg, width, height);
    }


    /**
     * 设置DialogFragment 大小
     *
     * @param context 上下文
     * @param dlg     对话框
     * @param factor  比例因子
     */
    public static void setDgFragmentLayoutParams(Context context, Dialog dlg, double factor) {
        int width = ((int) (getScreenWidth(context) * factor));
        int height = ((int) (getScreenHeight(context) * factor));
        dlg.getWindow().setLayout(width, height);
    }


    /**
     * 设置Dialogd的大小，在dialog.show()之后调用
     */
    public static void setDialogParams(Dialog dlg, int width, int height) {
        Window window = dlg.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = width;//宽高可设置具体大小,单位pixel
        lp.height = height;
        dlg.getWindow().setAttributes(lp);
    }


    /**
     * 获取屏幕宽度像素
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度像素
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }


    /**
     * 设置状态栏字体颜色
     *
     * @param activity
     * @param darkmode
     * @return
     */
    public static boolean setMiuiStatusBarDarkMode(Activity activity, boolean darkmode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}