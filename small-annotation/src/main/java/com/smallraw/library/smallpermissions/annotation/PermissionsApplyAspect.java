package com.smallraw.library.smallpermissions.annotation;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.smallraw.library.smallpermissions.SmallPermission;
import com.smallraw.library.smallpermissions.callback.PermissionsCallback;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.List;

@Aspect
public class PermissionsApplyAspect {
    private static final String TAG = "PermissionsApplyAspect";

    @Pointcut("execution(@com.smallraw.library.smallpermissions.annotation.PermissionsApply  * *(..))")
    public void executionAspectJ() {
    }

    @Around("executionAspectJ()")
    public void aroundAspectJ(final ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object o = joinPoint.getThis();

        final Context context;

        if (o instanceof Activity) {
            context = (Activity) o;
        } else if (o instanceof Fragment) {
            context = ((Fragment) o).getContext();
        } else if (o instanceof android.app.Fragment) {
            context = ((android.app.Fragment) o).getActivity();
        } else if (o instanceof Service) {
            context = ((Service) o).getApplicationContext();
        } else {
            context = SmallPermission.getApplicationContext();
        }

        if (context == null) {
            return;
        }

        final PermissionsApply aspectJAnnotation = methodSignature.getMethod().getAnnotation(PermissionsApply.class);
        final String[] permissions = aspectJAnnotation.permissions();
        final int hint = aspectJAnnotation.hint();

        Log.e("====", "===权限申请===");
        SmallPermission.requestPermission(o, permissions, 1, new PermissionsCallback() {
            @Override
            public void onPermissionGranted() {
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }

            @Override
            public void onPermissionDenied(List<String> Permissions) {
                Toast.makeText(context, context.getString(hint), Toast.LENGTH_SHORT).show();
                if (aspectJAnnotation.openSetting()) {
                    if (Permissions.get(0).equals(Manifest.permission.REQUEST_INSTALL_PACKAGES)) {
                        Intent localIntent = new Intent();
                        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        if (Build.VERSION.SDK_INT >= 9) {//2.3
                            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
                        } else if (Build.VERSION.SDK_INT <= 8) {//2.2
                            localIntent.setAction(Intent.ACTION_VIEW);
                            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
                        }
                        context.startActivity(localIntent);
                    } else {
                        startActivity(context, new Intent(Settings.ACTION_SETTINGS));
                    }
                }
            }
        });
    }

    private void startActivity(Context context, Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ContextCompat.startActivity(context, intent, null);
    }

}
