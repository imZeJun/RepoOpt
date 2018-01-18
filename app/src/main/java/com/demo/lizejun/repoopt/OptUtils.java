package com.demo.lizejun.repoopt;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;

import java.util.List;

public class OptUtils {

    public static void badAssemble() {
        int sum = 0;
        for (int i = 0; i < (1 << 30); i++) {
            sum = sum + i;
        }
    }

    public static void badString() {
        String result = "result";
        String append = "append";
        for (int i = 0; i < (1 << 20); i++) {
            result += append;
        }
    }

    public static void goodString() {
        StringBuilder result = new StringBuilder("result");
        String append = "append";
        for (int i = 0; i < (1 << 20); i++) {
            result.append(append);
        }
    }

    public static void startApp(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("www.qq.com"));
        intent.setComponent(new ComponentName("com.android.browser", "com.android.browser.BrowserActivity"));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            return;
        }
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, RemoteActivity.class);
        context.startActivity(intent);
    }


    private static final boolean DEBUG = true;

    public static void LogD(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void accept(@Constant.VideoState int videoState) {
        Log.d("OptUtils", "state=" + videoState);
    }

    public static void testAccept() {
        accept(Constant.FLAG_START);
    }
}
