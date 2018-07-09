package com.run.utils;

import android.app.Activity;
import android.util.TypedValue;

import com.run.utils.config.GlobalConfig;

/**
 * Created by MicYun on 2018/7/9.
 */
public class TitleUtils {
    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = GlobalConfig.getAppContext().getResources().getIdentifier("status_bar_height",
                "dimen", "android");
        if (resourceId > 0) {
            result = GlobalConfig.getAppContext().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getActionbarHeight(Activity activity) {
        if (activity == null) {
            return -1;
        }
        TypedValue tv = new TypedValue();
        if (activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            int actionBarHeight =
                    TypedValue.complexToDimensionPixelSize(tv.data, GlobalConfig.getAppContext()
                            .getResources().getDisplayMetrics());
            return actionBarHeight;
        }
        return 0;
    }
}
