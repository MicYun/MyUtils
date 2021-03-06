package com.run.utils;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

/**
 * Created by MicYun on 2018/7/9.
 */
public class TextViewUtils {
    private enum Action {
        GONE, INVISIBLE
    }

    public static void setTextGoneWhenNull(TextView textView, String text) {
        setTextGoneWhenNull(textView, text, text);
    }

    public static void setTextGoneWhenNull(TextView textView, String text, String... keyValue) {
        setText(Action.GONE, textView, text, keyValue);
    }

    public static void setTextInvisibleWhenNull(TextView textView, String text) {
        setTextInvisibleWhenNull(textView, text, text);
    }

    public static void setTextInvisibleWhenNull(TextView textView, String text, String... keyValue) {
        setText(Action.INVISIBLE, textView, text, keyValue);
    }


    private static void setText(Action action, TextView textView, String text, String... keyValue) {
        if (textView == null) {
            return;
        }
        if (keyValue == null || TextUtils.isEmpty(text)) {
            doAction(textView, action);
            return;
        }
        for (int i = 0; i < keyValue.length; i++) {
            if (TextUtils.isEmpty(keyValue[i])) {
                doAction(textView, action);
                return;
            }
        }
        textView.setVisibility(View.VISIBLE);
        textView.setText(text);
    }

    private static void doAction(TextView textView, Action action) {
        if (action == Action.GONE) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.INVISIBLE);
        }
    }
}
