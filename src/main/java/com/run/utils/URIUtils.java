package com.run.utils;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

import java.util.Map;

/**
 * Created by MicYun on 2018/7/9.
 */
public class URIUtils {
    public static String putParamsToURL(String url, Map<String, Object> params) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        if (params == null || params.isEmpty()) {
            return url;
        }

        Uri.Builder uriBuilder = Uri.parse(url).buildUpon();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry == null || entry.getValue() == null) {
                continue;
            }
            uriBuilder.appendQueryParameter(entry.getKey(), String.valueOf(entry.getValue()));
        }
        return uriBuilder.toString();
    }

    /**
     * 不会覆盖原有的 key value
     */
    public static String putParamsToURLWithNoReplace(String url, Map<String, Object> params) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        if (params == null || params.isEmpty()) {
            return url;
        }

        if(url.contains("h5.ffan.com/hybrid/#")
                || url.contains("h5.sit.ffan.com/hybrid/#")
                || url.contains("h5.uat.ffan.com/hybrid/#")){
            return putParamsToURLWithNoReplaceExceptionForHashTag(url, params);
        }

        if(url.contains("?")){
            return putParamsToURLWithQuestion(url, params);
        }

        Uri uri = Uri.parse(url);
        Uri.Builder uriBuilder = uri.buildUpon();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry == null || entry.getValue() == null) {
                continue;
            }
            // 只在uri 没有的时候添加
            if (TextUtils.isEmpty(uri.getQueryParameter(entry.getKey()))) {
                uriBuilder.appendQueryParameter(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        return uriBuilder.toString();
    }

    /**
     * 不会覆盖原有的 key value
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static String putParamsToURLWithNoReplaceExceptionForHashTag(String url, Map<String, Object> params) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        if (params == null || params.isEmpty()) {
            return url;
        }

        Uri uri = Uri.parse(url);
        StringBuilder stringBuilder = null;

        int position = url.indexOf("?");
        String part1 = "";
        String part2 = "";

        if(position != -1){
            part1 = url.substring(0, position);
            part2 = url.substring(position + 1, url.length());

            stringBuilder = new StringBuilder(part1);
        }else{
            stringBuilder = new StringBuilder(url);
        }

        if(!params.isEmpty()){
            stringBuilder.append("?");
        }

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry == null || entry.getValue() == null) {
                continue;
            }

            // 只在uri 没有的时候添加
            if (TextUtils.isEmpty(uri.getQueryParameter(entry.getKey()))) {
                stringBuilder.append(entry.getKey()).append("=").append(Uri.encode(String.valueOf(entry.getValue()))).append("&");
            }
        }

        if(TextUtils.isEmpty(part2)){
            return stringBuilder.toString();
        }else {
            return stringBuilder.append(part2).toString();
        }
    }

    public static String putParamsToURLWithQuestion(String url, Map<String, Object> params) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        if (params == null || params.isEmpty()) {
            return url;
        }

        Uri uri = Uri.parse(url);
        StringBuilder stringBuilder = null;

        int position = url.indexOf("?");
        String part1 = "";
        String part2 = "";

        if(position != -1){
            part1 = url.substring(0, position + 1);
            part2 = url.substring(position + 1, url.length());

            stringBuilder = new StringBuilder(part1);
        }else{
            return url;
        }

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry == null || entry.getValue() == null) {
                continue;
            }

            // 只在uri 没有的时候添加
            if (TextUtils.isEmpty(uri.getQueryParameter(entry.getKey()))) {
                stringBuilder.append(entry.getKey()).append("=").append(Uri.encode(String.valueOf(entry.getValue()))).append("&");
            }
        }

        if(TextUtils.isEmpty(part2)){
            return stringBuilder.toString();
        }else {
            return stringBuilder.append(part2).toString();
        }
    }
}
