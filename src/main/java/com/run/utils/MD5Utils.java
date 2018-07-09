package com.run.utils;

import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by MicYun on 2018/7/9.
 */
public class MD5Utils {
    private static final int BYTE_SIZE = 1024;
    private static final String MD5 = "MD5";
    private static final int END_OF_FILE = -1;
    private static final int FILE_OFFSET = 0;

    public static final String get(final String src) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(src.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2) {
                    h = "0" + h;
                }
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean check(String content, String md5) {
        return get(content).equals(md5);
    }

    // get md5 signed with key
    public static final String sign(String content, String key) {
        return get(content + key);
    }

    // check md5 signed with key
    public static boolean check(String content, String key, String md5) {
        return get(content + key).equals(md5);
    }

    public static String getFileMD5(String path) throws NoSuchAlgorithmException, IOException {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        File file = new File(path);
        return getFileMD5(file, false);
    }

    /**
     * 获取文件MD5值(注意补位0).
     *
     * @param file 文件.
     * @param upperCase 是否返回大写MD5值，默认返回小写.
     * @return 默认返回小写MD5摘要信息.
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String getFileMD5(File file, boolean upperCase)
            throws NoSuchAlgorithmException, IOException {
        if (file == null) {
            return null;
        }
        MessageDigest messageDigest = MessageDigest.getInstance(MD5);
        byte[] data = new byte[BYTE_SIZE];
        int size;
        FileInputStream fis = new FileInputStream(file);
        while ((size = fis.read(data)) != END_OF_FILE) {
            messageDigest.update(data, FILE_OFFSET, size);
        }
        fis.close();
        byte[] digest = messageDigest.digest();
        int digestLength = digest.length;
        StringBuilder md5 = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < digestLength; i++) {
            temp.setLength(0);
            String hex = Integer.toHexString(digest[i] & 0xFF);
            if (hex.length() == 1) {
                temp.append("0").append(hex);
            } else {
                temp.append(hex);
            }
            md5.append(temp.toString());
        }
        if (upperCase) {
            return md5.toString().toUpperCase();
        }
        return md5.toString().toLowerCase();
    }
}
