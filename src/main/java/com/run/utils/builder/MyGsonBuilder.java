package com.run.utils.builder;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by MicYun on 2018/7/6.
 */
public class MyGsonBuilder {

    private static final String TAG = "MyGsonBuilder";
    private static MyGsonBuilder instance = null;
    private static GsonBuilder mGsonBuilder = null;


    private static MyGsonBuilder getInstance() {
        if (instance == null) {
            instance = new MyGsonBuilder();
        }

        return instance;
    }

    public synchronized static Gson getGson() {
        if (mGsonBuilder == null) {
            getInstance();
        }

        return mGsonBuilder.create();
    }

    private MyGsonBuilder() {
        init();
    }

    private void init() {
        mGsonBuilder = new GsonBuilder();
        mGsonBuilder.registerTypeAdapter(String.class, STRING);
        // mGsonBuilder.registerTypeAdapter(Integer.class, INTEGER);
        mGsonBuilder.registerTypeAdapter(int.class, INTEGER);
        // mGsonBuilder.registerTypeAdapter(Long.class, LONG);
        mGsonBuilder.registerTypeAdapter(long.class, LONG);
        // mGsonBuilder.registerTypeAdapter(Double.class, DOUBLE);
        mGsonBuilder.registerTypeAdapter(double.class, DOUBLE);

        // boolean 不能自定义，不知道 Gson nextBoolean 为什么有的时候不起作用，即使用了，也无法控制异常处理，暂时不要用了
        // mGsonBuilder.registerTypeAdapter(Boolean.class, BOOLEAN);
        // mGsonBuilder.registerTypeAdapter(boolean.class, BOOLEAN);
    }

    /*
     ** 备注：JsonReader 的原生解析函数会抛出 JsonSyntaxException 异常，如 nextDouble()，nextInt()等
     ** 如果想正常解析的同时又抛出 JsonSyntaxException 这个异常供外层使用，请使用原始的nextXXX()函数
     **/

    private static final TypeAdapter<String> STRING = new TypeAdapter<String>() {
        public String read(JsonReader reader) throws IOException {
            try {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return "";// 原先是返回Null，这里改为返回空字符串
                }

                return reader.nextString();
            } catch (IllegalStateException e) {
                // e.printStackTrace();
                Log.e(TAG, e.toString() + "\r\n" + reader.toString());
                reader.skipValue();
                return "";
            } catch (Exception e) {
                // e.printStackTrace();
                Log.e(TAG, e.toString() + "\r\n" + reader.toString());
                return "";
            }
        }

        public void write(JsonWriter writer, String value) {
            try {
                if (value == null) {
                    writer.nullValue();
                    return;
                }
                writer.value(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private static final TypeAdapter<Integer> INTEGER = new TypeAdapter<Integer>() {
        public Integer read(JsonReader reader) throws IOException {
            try {
                // return reader.nextInt();
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return 0;
                }

                return Integer.valueOf(reader.nextString());
            } catch (IllegalStateException e) {
                // e.printStackTrace();
                Log.e(TAG, e.toString() + "\r\n" + reader.toString());
                reader.skipValue();
                return 0;
            } catch (Exception e) {
                // e.printStackTrace();
                Log.e(TAG, e.toString() + "\r\n" + reader.toString());
                return 0;
            }
        }

        public void write(JsonWriter writer, Integer value) {
            try {
                writer.value(String.valueOf(value));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private static final TypeAdapter<Long> LONG = new TypeAdapter<Long>() {
        public Long read(JsonReader reader) throws IOException {
            try {
                // return reader.nextLong();
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return 0L;
                }

                return Long.valueOf(reader.nextString());
            } catch (IllegalStateException e) {
                // e.printStackTrace();
                Log.e(TAG, e.toString() + "\r\n" + reader.toString());
                reader.skipValue();
                return 0L;
            } catch (Exception e) {
                // e.printStackTrace();
                Log.e(TAG, e.toString() + "\r\n" + reader.toString());
                return 0L;
            }
        }

        public void write(JsonWriter writer, Long value) {
            try {
                writer.value(String.valueOf(value));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private static final TypeAdapter<Double> DOUBLE = new TypeAdapter<Double>() {
        public Double read(JsonReader reader) throws IOException {
            try {
                // value = reader.nextDouble();
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return 0.0D;
                }

                return Double.valueOf(reader.nextString());
            } catch (IllegalStateException e) {
                // e.printStackTrace();
                Log.e(TAG, e.toString() + "\r\n" + reader.toString());
                reader.skipValue();
                return 0.0D;
            } catch (Exception e) {
                // e.printStackTrace();
                Log.e(TAG, e.toString() + "\r\n" + reader.toString());
                return 0.0D;
            }
        }

        public void write(JsonWriter writer, Double value) {
            try {
                writer.value(String.valueOf(value));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
