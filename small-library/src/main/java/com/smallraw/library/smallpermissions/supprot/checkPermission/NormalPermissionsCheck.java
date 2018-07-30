package com.smallraw.library.smallpermissions.supprot.checkPermission;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.AppOpsManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;

import com.smallraw.library.smallpermissions.supprot.AppOp;

/**
 * @author QuincySx
 * @date 2018/7/30 下午2:22
 */
public class NormalPermissionsCheck implements IPermissionsCheck {
    @Override
    public boolean checkPermissions(Context context, String permission) {
        boolean check = ContextCompat.checkSelfPermission(context, permission) == PackageManager
                .PERMISSION_GRANTED;
        CheckLog.print("权限:" + check + " permission:" + permission);

        boolean appJavaOpsCheck = AppOp.isPermissionGranted(context, permission);
        CheckLog.print("反射 OPS 权限:" + appJavaOpsCheck + " permission:" + permission);

        boolean supportCheck = PermissionChecker.checkSelfPermission(context, permission) == PackageManager
                .PERMISSION_GRANTED;
        CheckLog.print("兼容包权限:" + supportCheck + " permission:" + permission);

        String permissionToOp = AppOpsManagerCompat.permissionToOp(permission);
        if (permissionToOp == null) {
            return check && supportCheck;
        }
        int noteOp = AppOpsManagerCompat.noteOp(context, permissionToOp, android.os.Process.myUid(), context.getPackageName());
        boolean appOpsCheck = noteOp == AppOpsManagerCompat.MODE_ALLOWED;
        CheckLog.print("OPS 权限:" + appOpsCheck + " permission:" + permission);

        return check && supportCheck && appOpsCheck;
    }
}
