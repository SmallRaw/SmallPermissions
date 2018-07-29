package com.smallraw.library.smallpermissions.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.MessageQueue;
import android.util.Log;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class EnginePermission extends HandlerThread implements Handler.Callback {
    private static final int LOOP_MESSAGE = 1;
    private final Queue<Runnable> mRunnableQueue = new LinkedList<>();
    Handler mHandler;

    public EnginePermission() {
        super("request permission");
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        mHandler = new Handler(getLooper(), this);
        Runnable runnable = mRunnableQueue.poll();
        while (runnable != null) {
            Message obtain = Message.obtain();
            obtain.what = LOOP_MESSAGE;
            obtain.obj = runnable;
            mHandler.sendMessage(obtain);
            runnable = mRunnableQueue.poll();
        }
    }

    public void add(Runnable runnable) {
        if (mHandler == null) {
            mRunnableQueue.add(runnable);
        } else {
            mHandler.post(runnable);
        }
    }

    public void removeAllMessage() {
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case LOOP_MESSAGE:
                Object obj = message.obj;
                if (obj != null) {
                    Runnable runnable = (Runnable) obj;
                    runnable.run();
                }
                break;
        }
        return false;
    }
}
