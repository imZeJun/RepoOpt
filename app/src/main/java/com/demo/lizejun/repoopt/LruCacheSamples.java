package com.demo.lizejun.repoopt;


import android.util.Log;
import android.util.LruCache;

public class LruCacheSamples {

    private static final int MAX_SIZE = 50;


    public static void startRun() {
        LruCacheSample sample = new LruCacheSample();
        Log.d("LruCacheSample", "Start Put Object1, size=" + sample.size());
        sample.put("Object1", new Holder("Object1", 10));

        Log.d("LruCacheSample", "Start Put Object2, size=" + sample.size());
        sample.put("Object2", new Holder("Object2", 20));

        Log.d("LruCacheSample", "Start Put Object3, size=" + sample.size());
        sample.put("Object3", new Holder("Object3", 30));

        Log.d("LruCacheSample", "Start Put Object4, size=" + sample.size());
        sample.put("Object4", new Holder("Object4", 10));
    }


    static class LruCacheSample extends LruCache<String, Holder> {

        LruCacheSample() {
            super(MAX_SIZE);
        }

        @Override
        protected int sizeOf(String key, Holder value) {
            return value.getSize();
        }

        @Override
        protected void entryRemoved(boolean evicted, String key, Holder oldValue, Holder newValue) {
            if (oldValue != null) {
                Log.d("LruCacheSample", "remove=" + oldValue.getName());
            }
            if (newValue != null) {
                Log.d("LruCacheSample", "add=" + newValue.getName());
            }
        }
    }

    static class Holder {

        private String mName;
        private int mSize;

        Holder(String name, int size) {
            mName = name;
            mSize = size;
        }

        public String getName() {
            return mName;
        }

        public int getSize() {
            return mSize;
        }
    }
}
