package com.smallraw.library.smallpermissions.supprot.check;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

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
        return check;
    }
}
