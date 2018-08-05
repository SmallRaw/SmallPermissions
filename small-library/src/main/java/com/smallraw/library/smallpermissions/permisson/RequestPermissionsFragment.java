package com.smallraw.library.smallpermissions.permisson;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.smallraw.library.smallpermissions.callback.PermissionsCallback;
import com.smallraw.library.smallpermissions.executor.EngineThread;
import com.smallraw.library.smallpermissions.permisson.handler.PermissionsHandler;
import com.smallraw.library.smallpermissions.executor.MainThread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.Executor;

public class RequestPermissionsFragment extends Fragment implements IPermission {
  private final PermissionsHandler mPermissionsHandler = PermissionsHandler.getInstance();
  private final EngineThread mEngineThread = new EngineThread();
  private final Executor mMainExecutor = new MainThread();

  @Override
  public void requestPermissions(final String[] permissions, final int requestCode, final PermissionsCallback callback) {
    mEngineThread.add(new Runnable() {
      @Override
      public void run() {
        String[] permission = mPermissionsHandler.checkPermissions(getActivity(), permissions);
        if (permission.length == 0) {
          mMainExecutor.execute(new Runnable() {
            @Override
            public void run() {
              callback.onPermissionGranted(Arrays.asList(permissions));
            }
          });
        } else {
          try {
            mPermissionsHandler.addPermissionCallback(requestCode, callback);
            requestPermissions(permission, requestCode);
          } catch (NoSuchMethodError e) {
            e.printStackTrace();
            mMainExecutor.execute(new Runnable() {
              @Override
              public void run() {
                callback.onPermissionGranted(Arrays.asList(permissions));
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
    mEngineThread.start();
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    mPermissionsHandler.onRequestPermissionsResult(requestCode, permissions, grantResults);
  }

  @Override
  public void onDestroy() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
      mEngineThread.quitSafely();
    } else {
      mEngineThread.removeAllMessage();
      mEngineThread.quit();
    }
    super.onDestroy();
  }
}
