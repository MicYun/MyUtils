package com.run.utils;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by MicYun on 2018/7/6.
 */
public class BitmapUtil {

    /**
     * @param rootView      ROOT VIEW
     * @param y      The start y coordinate
     * @param height The result bitmap height
     * @return
     */
    public static Bitmap getCropScreenShotBitmap(View rootView, int y, int height) {
        Bitmap bitmap = getScreenShotBitmap(rootView);
        if (bitmap != null) {
            Bitmap newBitmap = getCropScreenShotBitmap(bitmap, 0, y, bitmap.getWidth(), height);
            return newBitmap;
        }
        return null;
    }

    /**
     * @param bitmap
     * @param x      The start x coordinate
     * @param y      The start y coordinate
     * @param height The result bitmap height
     * @return
     */
    public static Bitmap getCropScreenShotBitmap(Bitmap bitmap, int x, int y, int width,
                                                 int height) {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, x, y, width, height);
        return newBitmap;
    }

    @TargetApi(Build.VERSION_CODES.DONUT)
    public static Bitmap getScreenShotBitmap(View rootView) {
        rootView.setDrawingCacheEnabled(true);
        rootView.buildDrawingCache(true);
        Bitmap bitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);
        return bitmap;
    }

    public static Bitmap getBitmap(ImageView imageView) {
        if (imageView == null) {
            return null;
        }
        Drawable drawable = imageView.getDrawable();
        if (drawable != null && drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        return null;
    }
}
