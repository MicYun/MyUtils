package com.run.utils;

import com.google.gson.Gson;
import com.run.utils.builder.MyGsonBuilder;

/**
 * Created by MicYun on 2018/7/6.
 */
public class CloneUtils {

    private static Gson sGson = MyGsonBuilder.getGson();

    /**
     * use gson to serialization model. use deserialize to implements clone
     *
     */
    public static <T> T clone(T model, Class<T> clazz) {
        if (model == null) {
            return null;
        }
        String json = sGson.toJson(model);
        return sGson.fromJson(json, clazz);
    }
}
