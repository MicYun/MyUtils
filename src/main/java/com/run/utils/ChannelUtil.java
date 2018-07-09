package com.run.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by MicYun on 2018/7/6.
 */
public class ChannelUtil {
    private static String getMetaData(Context context, String key) {
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            Object channel = ai.metaData.get(key);
            if (channel != null) {
                String[] values = channel.toString().split("_");
                return values[values.length - 1];
            }
        } catch (Exception e) {
            //
        }
        return null;
    }

    public static String getChannelInfo(Context context) {
        ApplicationInfo appinfo = context.getApplicationInfo();
        String sourceDir = appinfo.sourceDir;

        String ret = "channel_xxx_0";
        final String start_flag = "META-INF/channel_";

        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(sourceDir);
            Enumeration<?> entries = zipfile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String entryName = entry.getName();
                if (!TextUtils.isEmpty(entryName) && entryName.startsWith(start_flag)) {
                    ret = entryName.replace(start_flag, "channel_");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zipfile != null) {
                try {
                    zipfile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

    public static String getChannelCode(String channel) {
        String[] split = channel.split("_");
        if (split != null && split.length >= 2) {
            return split[split.length - 1];

        } else {
            return "0";
        }
    }
}
