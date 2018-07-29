package com.smallraw.library.smallpermissions;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.smallraw.library.smallpermissions.callback.PermissionsCallback;
import com.smallraw.library.smallpermissions.permisson.IPermission;
import com.smallraw.library.smallpermissions.retriever.PermissionsClientRetriever;

public class SmallPermission {
    public static void requestPermission(Object o, String[] permissions, int requestCode, PermissionsCallback callback) {
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
        } else {
            throw new RuntimeException("不支持的类型");
        }
        if (iPermission != null) {
            iPermission.requestPermissions(permissions, requestCode, callback);
        } else {
            callback.onPermissionGranted();
        }
    }
}
