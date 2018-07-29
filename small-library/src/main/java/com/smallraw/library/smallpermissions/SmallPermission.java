package com.smallraw.library.smallpermissions;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.smallraw.library.smallpermissions.callback.PermissionsCallback;
import com.smallraw.library.smallpermissions.permisson.IPermission;
import com.smallraw.library.smallpermissions.retriever.PermissionsClientRetriever;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
        }
        if (iPermission == null) {
            Context applicationContext = getApplicationContext();
            if (applicationContext != null) {
                iPermission = permissionsClientRetriever.get(applicationContext);
            }
        }
        if (iPermission != null) {
            iPermission.requestPermissions(permissions, requestCode, callback);
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
