package com.demo.lizejun.repoopt;

import android.util.Log;

class ThreadLocalSamples {

    private static ThreadLocal<Integer> sThreadLocal = new ThreadLocal<Integer>() {

        @Override
        protected Integer initialValue() {
            return 5;
        }

    };

    static void startSample() {
        for (int i = 0; i < 3; i++) {
            new SampleThread("thread_" + i).start();
        }
    }

    private static class SampleThread extends Thread {

        private String mThreadName;

        SampleThread(String threadName) {
            mThreadName = threadName;
        }

        @Override
        public void run() {
            for (int j = 0; j < 5; j++) {
                try {
                    long sleep = (long) (Math.random() * 50);
                    Thread.sleep(sleep);
                    int result = sThreadLocal.get();
                    sThreadLocal.set(++result);
                    Log.d("ThreadLocalSamples", "ThreadName=" + mThreadName + ",result=" + result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
