package com.run.utils;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by MicYun on 2018/7/9.
 */
public class SharePrefSubmitter {
    private static final ExecutorService pool = Executors
            .newSingleThreadExecutor();

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static void submit(final SharedPreferences.Editor editor) {
        if (SystemUtil.aboveApiLevel(Build.VERSION_CODES.GINGERBREAD)) {
            editor.apply();
        } else {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    editor.commit();
                }
            });
        }
    }
}
