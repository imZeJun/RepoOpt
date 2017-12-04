package com.demo.lizejun.repoopt;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class OptApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (isMainProcess()) {
            //对主进程的资源进行初始化。
            Log.d("OptApplication", "isMainProcess=" + true);
        } else {
            //对其它进程资源进行初始化。
            Log.d("OptApplication", "isMainProcess=" + false);
        }
    }

    private boolean isMainProcess() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> process = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : process) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }
}
