package com.demo.lizejun.repoopt;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;


public class LeakActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LeakHandler leakHandler = new LeakHandler();
        leakHandler.sendEmptyMessageDelayed(0, 50000);
        leakHandler.removeCallbacksAndMessages(null);
    }

    private class LeakHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }

    private static class SafeHandler extends Handler {

        private WeakReference<Activity> mActHolder;

        public SafeHandler(Activity activity) {
            mActHolder = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mActHolder != null) {
                Activity activity = mActHolder.get();
                if (activity != null && !activity.isDestroyed()) {

                }
            }
        }
    }
}
