package com.run.utils;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by MicYun on 2018/7/9.
 */
public class ObjectFileUtils {
    /**
     * 保存对象
     *
     * @param context
     * @param t
     * @param fileName
     * @return
     */

    public static <T extends Serializable> boolean saveObject(Context context, T t, String fileName) {
        ObjectOutputStream oos = null;
        try {
            // 使用Android上下问获取当前项目的路径
            File file = new File(context.getFilesDir(), fileName);
            // 创建输出流对象
            oos = new ObjectOutputStream(new FileOutputStream(file));
            // 向文件中写入信息
            oos.writeObject(t);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (oos != null) {
                try {
                    // 关闭输出流对象
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取对象
     *
     * @param context
     * @return
     */
    public static <T extends Serializable> T getObject(Context context, String fileName) {
        ObjectInputStream ois = null;
        try {
            File file = new File(context.getFilesDir(), fileName);
            if (file.exists()) {
                ois = new ObjectInputStream(new FileInputStream(file));
                T t = (T) ois.readObject();
                return t;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (ois != null) {
                try {
                    // 关闭流
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
