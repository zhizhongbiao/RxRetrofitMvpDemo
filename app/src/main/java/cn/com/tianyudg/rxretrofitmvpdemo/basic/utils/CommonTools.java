package cn.com.tianyudg.rxretrofitmvpdemo.basic.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.Service;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class CommonTools {

    /**
     * 拼接字符串
     *
     * @param arg
     * @param args
     * @return 拼接完成的字符串
     */
    public static String join(Object arg, Object... args) {
        StringBuffer buffer = new StringBuffer(arg == null ? "" : arg.toString());
        for (Object s : args) {
            buffer.append(s == null ? "" : s.toString());
        }
        return buffer.toString();
    }

    /**
     * 截取目标数组指定长度的的数据
     *
     * @param data   目标数组
     * @param i      截取起始位置
     * @param length 截取长度
     * @return 截取到的数据
     */
    public static byte[] arrayCopy(byte[] data, int i, int length) {
        byte[] temp = new byte[length];
        System.arraycopy(data, i, temp, 0, length);
        return temp;
    }


    public String format2f(Double d) {
        return String.format("%1$.2f", d);
    }

    /**
     * 拼接多个数组
     *
     * @param first
     * @param rest
     * @return
     */
    public static byte[] concatAll(byte[] first, byte[]... rest) {
        int totalLength = first.length;
        for (byte[] array : rest) {
            totalLength += array.length;
        }
        byte[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (byte[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }

    /**
     * 设置多种颜色的字体
     *
     * @param msg
     * @param color1
     * @param color2
     * @param pos    两种颜色的分隔位置
     * @return
     */
    public static SpannableStringBuilder setColorful(String msg, int color1, int color2, int pos) {
        SpannableStringBuilder builder = new SpannableStringBuilder(msg);
        builder.setSpan(new ForegroundColorSpan(color1), 0, pos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(color2), pos + 1, msg.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return builder;
    }

    /**
     * 匹配手机号码
     *
     * @param mobile
     * @return
     */
    public static boolean matcherMobile(String mobile) {
        Pattern p = Pattern.compile("^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$");
        return p.matcher(mobile).find();
    }


    /**
     * 获取当前的页面
     *
     * @param count    总数
     * @param pagesize 一页含有多少
     * @return
     */
    public static int getTotalPage(int count, int pagesize) {
        return count % pagesize == 0 ? count / pagesize : count / pagesize + 1;
    }

    /**
     * 吐司提示
     */
    public static Toast mToast;

    public static void showTips(Context context, String text) {
        if (mToast == null) {
            mToast = Toast.makeText(context, "", Toast.LENGTH_LONG);
        }

        mToast.setText(text);
        mToast.show();
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获得格式化后的当前时间
     *
     * @param time
     * @return
     */
    public static SimpleDateFormat sdf;

    public static String getFormatTime(long time) {
        if (sdf == null) {
            sdf = new SimpleDateFormat();
        }
        sdf.applyPattern("yyyy-MM-dd");
        String format = sdf.format(time);
        return format;
    }

    /**
     * 获得格式化后的当前时间
     *
     * @return
     */
    public static String getFormatTime(String format) {
        if (sdf == null) {
            sdf = new SimpleDateFormat();
        }
        sdf.applyPattern(format);
        return sdf.format(System.currentTimeMillis());
    }


    public static String getFormatTime(String pattern, Object time) {
        if (sdf == null) {
            sdf = new SimpleDateFormat();
        }
        sdf.applyPattern(pattern);
        return sdf.format(Long.parseLong(String.valueOf(time)));
    }

    /**
     * 把已经格式化好的时间转换成其他格式的时间
     *
     * @param oldPattern
     * @param newPattern
     * @param time
     * @return
     */
    public static String getFormatTime(String oldPattern, String newPattern, String time) {
        if (sdf == null) {
            sdf = new SimpleDateFormat();
        }
        try {
            sdf.applyPattern(oldPattern);
            Date date = sdf.parse(time);
            sdf.applyPattern(newPattern);
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * String类型时间转长整型
     *
     * @param pattern
     * @param time
     * @return
     */
    public static long getLongTime(String pattern, String time) {
        if (sdf == null) {
            sdf = new SimpleDateFormat();
        }
        sdf.applyPattern(pattern);
        try {
            Date date = sdf.parse(time);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 旋转Bitmap
     *
     * @param b
     * @param rotateDegree
     * @return
     */
    public static Bitmap getRotateBitmap(Bitmap b, float rotateDegree) {
        Matrix matrix = new Matrix();
        matrix.postRotate((float) rotateDegree);
        Bitmap rotaBitmap = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), matrix, false);
        return rotaBitmap;
    }

    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * dip转px
     *
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * px转dip
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取屏幕宽度和高度，单位为px
     *
     * @param context
     * @return
     */
    public static Point getScreenMetrics(Context context) {
        DisplayMetrics dm       = context.getResources().getDisplayMetrics();
        int            w_screen = dm.widthPixels;
        int            h_screen = dm.heightPixels;
        return new Point(w_screen, h_screen);
    }

    /**
     * 获取屏幕长宽比
     *
     * @param context
     * @return
     */
    public static float getScreenRate(Context context) {
        Point P = getScreenMetrics(context);
        float H = P.y;
        float W = P.x;
        return (H / W);
    }

    /**
     * 判断当前是否是有效Integer类型数据
     *
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[1-9][\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 检查手机上是否安装了指定的软件
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }

    /**
     * 判断当前网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isNetConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            return true;
        }
        return false;
    }

    /**
     * 判断服务是否运行
     *
     * @param context
     * @param clazz   要判断的服务的class
     * @return
     */
    public static boolean isServiceRunning(Context context, Class<? extends Service> clazz) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        List<RunningServiceInfo> services = manager.getRunningServices(100);
        for (int i = 0; i < services.size(); i++) {
            String className = services.get(i).service.getClassName();
            if (className.equals(clazz.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取设备编号
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }


    // 显示或者隐藏输入法
    public static void toggleSoftInput(Context context) {
        InputMethodManager imm = null;
        if (imm == null) {
            imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 显示键盘
     *
     * @param context
     * @param view
     */
    public static void showInputMethod(Context context, View view) {
        InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        im.showSoftInput(view, 0);
    }

    //隐藏虚拟键盘
    public static void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }

    //显示密码
    public static void showPsw(EditText et) {
        if (et == null) return;
        et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
    }


    //隐藏密码
    public static void hidePsw(EditText et) {
        if (et == null) return;
        et.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }




    /**
     //透明状态栏
     getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
     //透明导航栏
     getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取导航栏高度
     * @param context
     * @return
     */
    public static int getDaoHangHeight(Context context) {
        int result = 0;
        int resourceId=0;
        int rid = context.getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        if (rid!=0){
            resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
//            CMLog.show("高度："+resourceId);
//            CMLog.show("高度："+context.getResources().getDimensionPixelSize(resourceId) +"");
            return context.getResources().getDimensionPixelSize(resourceId);
        }else
            return 0;
    }


}
