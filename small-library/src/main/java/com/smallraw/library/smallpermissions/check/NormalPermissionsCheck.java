package com.smallraw.library.smallpermissions.check;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.AppOpsManagerCompat;
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

    String permissionToOp = AppOpsManagerCompat.permissionToOp(permission);
    if (permissionToOp == null) {
      return check;
    }
    int noteOp = AppOpsManagerCompat.noteOp(context, permissionToOp, android.os.Process.myUid(), context.getPackageName());
    boolean appOpsCheck = noteOp == AppOpsManagerCompat.MODE_ALLOWED;
    CheckLog.print("OPS 权限:" + appOpsCheck + " permission:" + permission);

    return check && appOpsCheck;
  }
}
