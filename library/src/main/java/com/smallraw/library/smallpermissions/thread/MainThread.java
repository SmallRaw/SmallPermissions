package com.smallraw.library.smallpermissions.thread;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Executor;

public class MainThread implements Executor {
    private Handler mHandler;

    public MainThread() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void execute(@NonNull Runnable runnable) {
        mHandler.post(runnable);
    }
}
