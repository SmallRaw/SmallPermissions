package com.smallraw.library.smallpermissions.supprot.check;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.AppOpsManagerCompat;
import android.support.v4.content.ContextCompat;

import com.smallraw.library.smallpermissions.supprot.AppOp;

/**
 * @author QuincySx
 * @date 2018/7/30 下午2:22
 */
public class AppOpPermissionsCheck implements IPermissionsCheck {
    @Override
    public boolean checkPermissions(Context context, String permission) {
        boolean normalCheck = ContextCompat.checkSelfPermission(context, permission) == PackageManager
                .PERMISSION_GRANTED;
        CheckLog.print("Normal 权限:" + normalCheck + " permission:" + permission);
        String permissionToOp = AppOpsManagerCompat.permissionToOp(permission);
        if (permissionToOp == null) {
            return normalCheck;
        }
        boolean appOpsCheck = AppOp.isPermissionGranted(context, permission);
        CheckLog.print("OPS 权限:" + appOpsCheck + " permission:" + permission);

        boolean check = normalCheck && appOpsCheck;

        CheckLog.print("权限:" + check + " permission:" + permission);

        return check;
    }
}
