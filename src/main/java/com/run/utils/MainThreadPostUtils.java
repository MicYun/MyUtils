package com.run.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.run.utils.config.GlobalConfig;

/**
 * Created by MicYun on 2018/7/6.
 */
public class MainThreadPostUtils {
    private static final int MESSAGE_TOAST = 1001;
    private static final int MESSAGE_TOAST_LONG = 1002;
    private static final int MESSAGE_SHORT_TOAST_IN_CENTER = 1003;
    private static Handler handler;

    private static byte[] handlerLock = new byte[0];

    public static Handler getHandler() {
        synchronized (handlerLock) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case MESSAGE_TOAST:
                                Toast.makeText(GlobalConfig.getAppContext(), msg.obj.toString(),
                                        Toast.LENGTH_SHORT).show();
                                break;
                            case MESSAGE_TOAST_LONG:
                                Toast.makeText(GlobalConfig.getAppContext(), msg.obj.toString(),
                                        Toast.LENGTH_LONG).show();
                                break;
                            case MESSAGE_SHORT_TOAST_IN_CENTER:
                                showToastWithCustomView((View) msg.obj);
                                break;
                            default:
                                break;
                        }
                    }
                };
            }
        }
        return handler;
    }

    public static void post(Runnable run) {
        getHandler().post(run);
    }

    public static void postAtFrontOfQueue(Runnable run) {
        getHandler().postAtFrontOfQueue(run);
    }

    public static void postDelayed(Runnable run, long delayMillis) {
        getHandler().postDelayed(run, delayMillis);
    }

    public static void toast(final String message) {
        if (!TextUtils.isEmpty(message)) {
            // Message msg = getHandler().obtainMessage(MESSAGE_TOAST, message);
            // msg.sendToTarget();
            if (!Thread.currentThread().getName().equals(Looper.getMainLooper().getThread().getName()))
                post(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showShortToast(message);
                    }
                });
            else
                ToastUtils.showShortToast(message);

        }
    }

    public static void toastLong(final String message) {
        if (!TextUtils.isEmpty(message)) {
            // Message msg = getHandler().obtainMessage(MESSAGE_TOAST_LONG, message);
            // msg.sendToTarget();
            if (!Thread.currentThread().getName().equals(Looper.getMainLooper().getThread().getName()))
                post(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showLongToast(message);
                    }
                });
            else
                ToastUtils.showLongToast(message);
        }
    }

    public static void toastCustomViewInCenter(View v) {
        if (v != null) {
            Message msg = getHandler().obtainMessage(MESSAGE_SHORT_TOAST_IN_CENTER, v);
            msg.sendToTarget();
        }
    }

    public static void toast(int res) {
        final String msg = GlobalConfig.getAppContext().getString(res);
        // toast(msg);
        if (!Thread.currentThread().getName().equals(Looper.getMainLooper().getThread().getName()))
            post(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.showShortToast(msg);
                }
            });
        else
            ToastUtils.showShortToast(msg);
    }

    public static void toastLong(int res) {
        final String msg = GlobalConfig.getAppContext().getString(res);
        toastLong(msg);
        if (!Thread.currentThread().getName().equals(Looper.getMainLooper().getThread().getName()))
            post(new Runnable() {
                @Override
                public void run() {

                    ToastUtils.showLongToast(msg);
                }
            });
        else
            ToastUtils.showLongToast(msg);
    }


    public static void showToastWithCustomView(View v) {
        final Toast toast = new Toast(GlobalConfig.getAppContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(v);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void cancel(Runnable r) {
        getHandler().removeCallbacks(r);
    }
}
