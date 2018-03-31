package cn.com.tianyudg.rxretrofitmvpdemo.basic.utils;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

/**
 * Created by Walker on 2016/10/9.
 */

public class ViewUtils {
    public static boolean isEmpty(TextView contentView, String errorInfo) {
        return isEmpty(contentView, errorInfo, false);
    }

    public static boolean isEmpty(TextView contentView, String errorInfo, boolean showToast) {
        boolean isEmpty = TextUtils.isEmpty(contentView.getText());
        if (isEmpty && showToast) {
            ToastUtil.showLong(errorInfo);
        } else if (isEmpty && !showToast) {
            contentView.setError(errorInfo);
            contentView.requestFocus();
            contentView.performClick();
        }
        return isEmpty;
    }

    public static boolean isValidLength(TextView contentView, int validLength, String errorInfo) {
        String content = contentView.getText().toString();
        if (TextUtils.isEmpty(content) || content.trim().length() < validLength) {
            contentView.setError(errorInfo);
            contentView.requestFocus();
            contentView.performClick();
            return false;
        }

        return true;
    }

    public static boolean passwordValidate(EditText passwordEt, EditText confirmPasswordEt, String errorInfo) {
        String password        = passwordEt.getText().toString();
        String confirmPassword = confirmPasswordEt.getText().toString();

        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword) || !password.equals(confirmPassword)) {
            confirmPasswordEt.setError(errorInfo);
            confirmPasswordEt.requestFocus();
            confirmPasswordEt.performClick();
            return false;
        }

        return true;
    }

    public static boolean isPhoneNumber(TextView contentView, String errorInfo) {
        String content = contentView.getText().toString();
        if (TextUtils.isEmpty(content) || !Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$").matcher(content).matches()) {
            contentView.setError(errorInfo);
            contentView.requestFocus();
            contentView.performClick();
            return false;
        }

        return true;
    }
}
