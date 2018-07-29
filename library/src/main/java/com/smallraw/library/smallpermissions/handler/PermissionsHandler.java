package com.smallraw.library.smallpermissions.handler;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.AppOpsManagerCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.SparseArray;

import com.smallraw.library.smallpermissions.callback.PermissionsCallback;
import com.smallraw.library.smallpermissions.thread.MainThread;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class PermissionsHandler {
    private final SparseArray<PermissionsCallback> mCallbackList = new SparseArray<>();

    private static class PermissionsHandlerProvider {
        private static final PermissionsHandler PERMISSIONS_HANDLER = new PermissionsHandler();
    }

    public final static PermissionsHandler getInstance() {
        return PermissionsHandlerProvider.PERMISSIONS_HANDLER;
    }

    public void addPermissionCallback(int key, PermissionsCallback callback) {
        mCallbackList.put(key, callback);
    }

    public void removePermissionCallback(int key) {
        mCallbackList.remove(key);
    }

    public PermissionsCallback getPermissionCallback(int key) {
        return mCallbackList.get(key);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionsCallback permissionsCallback = getPermissionCallback(requestCode);
        if (permissionsCallback != null) {
            if (grantResults.length > 0) {
                List<String> deniedPermission = new ArrayList<>();
                //判读没有授予权限的权限，并放到一个集合里
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        deniedPermission.add(permissions[i]);
                    }
                }
                //判断是否全部通过授权
                if (deniedPermission.isEmpty()) {
                    permissionsCallback.onPermissionGranted();
                } else {
                    permissionsCallback.onPermissionDenied(deniedPermission);
                }
            }
            removePermissionCallback(requestCode);
        }
    }

    public String[] checkPermissions(Context context, String[] permissions) {
        ArrayList<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager
                    .PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        return permissionList.toArray(new String[permissionList.size()]);
    }

    public boolean checkPermissionsOps(Context context, String permission) {
        String permissionToOp = AppOpsManagerCompat.permissionToOp(permission);
        if (permissionToOp == null) {
            // 不支持的权限，或者是normal permission
            return true;
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        int noteOp = AppOpsManagerCompat.noteOp(context, permissionToOp, android.os.Process.myUid(), context.getPackageName());
        // AppOpsManagerCompat 与 checkSelfPermission都检测过则表明权限被开启
        return noteOp == AppOpsManagerCompat.MODE_ALLOWED && ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
//        } else {
//            checkPermissionsNoteOp(context,)
//        }
    }

    public static boolean checkPermissionsNoteOp(Context context, int op) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            if (appOpsManager != null) {
                try {
                    Method method = AppOpsManager.class.getDeclaredMethod("noteOp", Integer.TYPE, Integer.TYPE,
                            String.class);
                    int noteOp = (int) method.invoke(appOpsManager, op, android.os.Process.myUid(), context.getPackageName());
                    return noteOp == AppOpsManager.MODE_ALLOWED;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
