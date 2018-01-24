package com.demo.lizejun.repoopt;


import android.util.SparseArray;

public class SparseArraySample {

    public static void startRun() {
        SparseArray<Object> valueHolder = new SparseArray<Object>();
        valueHolder.put(0, 0);
        valueHolder.put(1, 1);
        valueHolder.put(2, 2);
        valueHolder.put(-1, -1);
        valueHolder.put(3, 3);
    }
}
