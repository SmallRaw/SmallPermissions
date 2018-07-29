package com.smallraw.library.smallpermissions.permisson;


import android.os.Build;
import android.os.Bundle;
import android.os.MessageQueue;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smallraw.library.smallpermissions.callback.PermissionsCallback;
import com.smallraw.library.smallpermissions.handler.EnginePermission;
import com.smallraw.library.smallpermissions.handler.PermissionsHandler;
import com.smallraw.library.smallpermissions.thread.MainThread;

import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;

public class RequestPermissionsSupportFragment extends Fragment implements IPermission {
    private final PermissionsHandler mPermissionsHandler = PermissionsHandler.getInstance();
    private final EnginePermission mEnginePermission = new EnginePermission();
    private final Executor mMainExecutor = new MainThread();

    @Override
    public void requestPermissions(final String[] permissions, final int requestCode, final PermissionsCallback callback) {
        mEnginePermission.add(new Runnable() {
            @Override
            public void run() {
                String[] permission = mPermissionsHandler.checkPermissions(getContext(), permissions);
                if (permission.length == 0) {
                    mMainExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            callback.onPermissionGranted();
                        }
                    });
                } else {
                    try {
                        mPermissionsHandler.addPermissionCallback(requestCode, callback);
                        requestPermissions(permission, requestCode);
                    } catch (Exception e) {
                        mMainExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                callback.onPermissionGranted();
                            }
                        });
                        mPermissionsHandler.removePermissionCallback(requestCode);
                    }
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mEnginePermission.start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mPermissionsHandler.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onDestroy() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            mEnginePermission.quitSafely();
        } else {
            mEnginePermission.removeAllMessage();
            mEnginePermission.quit();
        }
        super.onDestroy();
    }
}
