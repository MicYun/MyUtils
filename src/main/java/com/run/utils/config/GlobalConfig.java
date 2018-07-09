package com.run.utils.config;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by MicYun on 2018/7/6.
 */
public class GlobalConfig {
    private static final String CLIENT_VERSION = "client_version";
    private static Context appContext;
    private static boolean mainProcessInited = false;
    private static String rootDir = "wanda";
    private static boolean debug = false;
    private static boolean sIsUpgrade;
    private static String DETECT_WEBGL = "webglDetect";
    private static String SUPPORT_WEBGL = "webglSupport";
    public static int SUPPORT_WEBGL_VALUE = 1;
    public static int NO_SUPPORT_WEBGL_VALUE = 2;
    public static int INIT_SUPPORT_WEBGL_VALUE = 0;



    public static void setAppContext(Context context) {
        appContext = context;
    }

    public static Context getAppContext() {
        return appContext;
    }

    public static boolean isMainProcessInited() {
        return mainProcessInited;
    }

    public static void mainProcessInited(boolean mainProcessInited) {
        GlobalConfig.mainProcessInited = mainProcessInited;
    }

    public static void setAppRootDir(String dir) {
        rootDir = dir;
    }

    public static String getAppRootDir() {
        return rootDir;
    }

    /**
     * This should only be called in main android project, like in Application,
     * because in Gradle build system the BuildConfig.DEBUG in library project is always false.
     *
     * @param debug true if in debug mode, false otherwise.
     */
    public static void setDebug(boolean debug) {
        GlobalConfig.debug = debug;
    }

    /**
     * Because in Gradle build system the BuildConfig.DEBUG in library project is always false,
     * and this will not be fixed in short-term, so we need to use this instead of
     * BuildConfig.DEBUG in library project.
     *
     * @return true if in debug mode, false otherwise.
     */
    public static boolean isDebug() {
        return debug;
    }

    public static int getVersionCode() {
        try {
            PackageInfo packageInfo = appContext.getPackageManager().getPackageInfo(appContext
                    .getPackageName(), PackageManager.GET_ACTIVITIES);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getVersionName() {
        try {
            PackageInfo packageInfo = appContext.getPackageManager().getPackageInfo(appContext
                    .getPackageName(), PackageManager.GET_ACTIVITIES);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean isUpgrade() {
        return sIsUpgrade;
    }
}
