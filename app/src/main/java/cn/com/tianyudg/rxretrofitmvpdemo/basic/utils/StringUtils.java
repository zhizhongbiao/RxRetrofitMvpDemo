package cn.com.tianyudg.rxretrofitmvpdemo.basic.utils;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author : WaterFlower.
 * Created on 2017/8/25.
 * Desc :
 */

public class StringUtils {

    public static String formatPhoneNumber(String phoneNo, int start, int replaceLength) {
        if (TextUtils.isEmpty(phoneNo) || phoneNo.length() < (start + replaceLength)) {
            return phoneNo;
        }

        StringBuilder stringBuilder = new StringBuilder(phoneNo);
        return stringBuilder.replace(start, start + replaceLength, "****").toString();
    }

    public static Spanned getValueText(String color, String key, String value) {
        StringBuilder string = new StringBuilder();
        string.append(key);
        string.append("<font color=\"" + color + "\">");
        string.append(value);
        string.append("</font>");
        return Html.fromHtml(string.toString());
    }

    /**
     * 除字符串中的空格
     * Author：HeFeng on 2017/4/5 21:40.
     * Email：13713113943@163.com
     *
     * @param editText 输入的字符串
     * @return 去除空格后的字符串
     */
    public static String replaceBlank(EditText editText) {
        String dest = editText.getText().toString().trim();
        if (dest != null) {
            Pattern p = Pattern.compile("\\s*");
            Matcher m = p.matcher(dest);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 格式化银行卡号
     * @param blankNum
     * @return
     */
    public static String formBlankNum(String blankNum){
        if(blankNum == null){
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder(blankNum);
        if(stringBuilder.length() < 15){
            return blankNum.toString();
        }
        StringBuilder newBlankNum = new StringBuilder();
        return newBlankNum
                .append(stringBuilder.substring(0,4))
                .append("**********")
                .append(stringBuilder.substring(stringBuilder.length() - 4))
                .toString();
    }

}
