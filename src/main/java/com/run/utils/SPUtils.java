package com.run.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.run.utils.config.GlobalConfig;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;

/**
 * Created by MicYun on 2018/7/9.
 */
public class SPUtils {
    /*
     * 保存在手机里面的文件名
     */
    public static final String FILE_NAME = "share_data";

    private static SharedPreferences getSharedPreferences() {
        return GlobalConfig.getAppContext().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor() {
        SharedPreferences sp = getSharedPreferences();
        SharedPreferences.Editor editor = sp.edit();
        return editor;
    }

    public static void putString(String key, String value) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(key, value);
        SharedPreferencesCompat.apply(editor);
    }

    public static String getString(String key, String defaultValue) {
        SharedPreferences sp = getSharedPreferences();
        return sp.getString(key, defaultValue);
    }

    public static void putInt(String key, int value) {
        SharedPreferences.Editor editor = getEditor();
        editor.putInt(key, value);
        SharedPreferencesCompat.apply(editor);
    }

    public static int getInt(String key, int defaultValue) {
        SharedPreferences sp = getSharedPreferences();
        return sp.getInt(key, defaultValue);
    }

    public static void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getEditor();
        editor.putBoolean(key, value);
        SharedPreferencesCompat.apply(editor);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        SharedPreferences sp = getSharedPreferences();
        return sp.getBoolean(key, defaultValue);
    }

    public static void putFloat(String key, float value) {
        SharedPreferences.Editor editor = getEditor();
        editor.putFloat(key, value);
        SharedPreferencesCompat.apply(editor);
    }

    public static float getFloat(String key, float defaultValue) {
        SharedPreferences sp = getSharedPreferences();
        return sp.getFloat(key, defaultValue);
    }

    public static void putLong(String key, long value) {
        SharedPreferences.Editor editor = getEditor();
        editor.putLong(key, value);
        SharedPreferencesCompat.apply(editor);
    }

    public static long getLong(String key, long defaultValue) {
        SharedPreferences sp = getSharedPreferences();
        return sp.getLong(key, defaultValue);
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param
     * @param key
     */
    public static void remove(String key) {
        SharedPreferences.Editor editor = getEditor();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     *
     * @param
     */
    public static void clear() {
        SharedPreferences.Editor editor = getEditor();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param
     * @param key
     * @return
     */
    public static boolean contains(String key) {
        SharedPreferences sp = getSharedPreferences();
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @param
     * @return
     */
    public static Map<String, ?> getAll() {
        SharedPreferences sp = getSharedPreferences();
        return sp.getAll();
    }

    public static Set<String> getStringSet(String key) {
        SharedPreferences sp = getSharedPreferences();
        return sp.getStringSet(key, new HashSet<String>());
    }

    public static void putStringSet(String key, Set<String> value) {
        SharedPreferences.Editor editor = getEditor();
        editor.putStringSet(key, value);
        SharedPreferencesCompat.apply(editor);
    }



    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author zhy
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {}

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(final SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (Exception e) {
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        editor.commit();
                    }
                });
            }
        }
    }
}
