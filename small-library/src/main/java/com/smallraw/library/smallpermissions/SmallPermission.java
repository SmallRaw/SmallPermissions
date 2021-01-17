package com.smallraw.library.smallpermissions;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.smallraw.library.smallpermissions.callback.Action;
import com.smallraw.library.smallpermissions.callback.PermissionsCallback;
import com.smallraw.library.smallpermissions.permisson.IPermission;
import com.smallraw.library.smallpermissions.retriever.PermissionsClientRetriever;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

public class SmallPermission {
  public static SmallPermissionOptions with(Object o) {
    PermissionsClientRetriever permissionsClientRetriever = PermissionsClientRetriever.getInstance();
    IPermission iPermission = null;
    if (o instanceof Fragment) {
      iPermission = permissionsClientRetriever.get((Fragment) o);
    } else if (o instanceof android.app.Fragment) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        iPermission = permissionsClientRetriever.get((android.app.Fragment) o);
      }
    } else if (o instanceof FragmentActivity) {
      iPermission = permissionsClientRetriever.get((FragmentActivity) o);
    } else if (o instanceof Activity) {
      iPermission = permissionsClientRetriever.get((Activity) o);
    } else if (o instanceof Context) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
        iPermission = permissionsClientRetriever.get((Context) o);
      }
    }
    if (iPermission == null) {
      Context applicationContext = getApplicationContext();
      if (applicationContext != null) {
        iPermission = permissionsClientRetriever.get(applicationContext);
      }
    }
    if (iPermission != null) {
      return new SmallPermissionOptions(iPermission);
    }
    throw new RuntimeException("未知错误");
  }

  public static class SmallPermissionOptions {
    private IPermission mIPermission;
    private String[] mPermissions;
    private Action OnGrantedAction;
    private Action OnDeniedAction;
    private int mRequestCode = -1;

    SmallPermissionOptions(IPermission IPermission) {
      mIPermission = IPermission;
    }

    /**
     * 传入需要申请的权限
     *
     * @param permissions 需要申请的权限
     */
    public SmallPermissionOptions permission(String... permissions) {
      mPermissions = permissions;
      return this;
    }

    /**
     * 被授权权限的回调
     *
     * @param action 回调接口
     */
    public SmallPermissionOptions onGranted(Action action) {
      OnGrantedAction = action;
      return this;
    }

    /**
     * 被拒绝权限的回调
     *
     * @param action 回调接口
     */
    public SmallPermissionOptions onDenied(Action action) {
      OnDeniedAction = action;
      return this;
    }

    /**
     * 开始申请权限
     */
    public void start() {
      if (mPermissions == null || mPermissions.length == 0) {
        new RuntimeException("请调用 permission() 方法传入需要的申请的权限!");
      }

      if (mRequestCode == -1) {
        mRequestCode = new Random().nextInt(100);
      }

      mIPermission.requestPermissions(mPermissions, mRequestCode, new PermissionsCallback() {
        @Override
        public void onPermissionGranted(List<String> permissions) {
          if (OnGrantedAction != null) {
            OnGrantedAction.onAction(permissions);
          }
        }

        @Override
        public void onPermissionDenied(List<String> permissions) {
          if (OnDeniedAction != null) {
            OnDeniedAction.onAction(permissions);
          }
        }
      });
    }
  }

  public static Context getApplicationContext() {
    try {
      Class<?> clazz = Class.forName("android.app.ActivityThread");
      Field field = clazz.getDeclaredField("sCurrentActivityThread");
      field.setAccessible(true);
      //得到ActivityThread的对象，虽然是隐藏的，但已经指向了内存的堆地址
      Object object = field.get(null);
      Method method = clazz.getDeclaredMethod("getApplication");
      method.setAccessible(true);
      Application application = (Application) method.invoke(object);
      return application.getApplicationContext();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
