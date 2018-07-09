package com.run.utils;

import android.util.TypedValue;

import com.run.utils.config.GlobalConfig;

/**
 * Created by MicYun on 2018/7/6.
 */
public class DensityUtils {

    public DensityUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");

    }

    /**
     * dp转px
     *
     * @param dp
     * @return
     */
    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                GlobalConfig.getAppContext().getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param sp
     * @return
     */
    public static float sp2px(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                GlobalConfig.getAppContext().getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param px
     * @return
     */
    public static float px2dp(float px) {
        final float scale = GlobalConfig.getAppContext().getResources().getDisplayMetrics().density;
        return (px / scale);
    }

    /**
     * px转sp
     *
     * @param px
     * @return
     */
    public static float px2sp(float px) {
        return (px / GlobalConfig.getAppContext().getResources().getDisplayMetrics().scaledDensity);
    }
}
