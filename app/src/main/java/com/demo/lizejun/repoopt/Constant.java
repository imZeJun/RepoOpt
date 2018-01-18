package com.demo.lizejun.repoopt;

import android.support.annotation.IntDef;

public class Constant {

    public static final int FLAG_START = 0;
    public static final int FLAG_STOP = 1;
    public static final int FLAG_PAUSE = 2;

    @IntDef({FLAG_START, FLAG_STOP, FLAG_PAUSE})
    public @interface VideoState {}
}
