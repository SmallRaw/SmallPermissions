package com.smallraw.library.smallpermissions.check;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.AppOpsManagerCompat;
import android.support.v4.content.ContextCompat;

/**
 * @author QuincySx
 * @date 2018/7/30 下午2:22
 */
public class DoublePermissionsCheck implements IPermissionsCheck {
  private IPermissionsCheck mActualPermissionsCheck = new ActualPermissionsCheck();
  private IPermissionsCheck mNormalPermissionsCheck = new NormalPermissionsCheck();

  @Override
  public boolean checkPermissions(Context context, String permission) {
    boolean check = mActualPermissionsCheck.checkPermissions(context, permission);
    CheckLog.print("严格权限验证:" + check + " Actual:" + permission);

    boolean normalCheck = mNormalPermissionsCheck.checkPermissions(context, permission);
    CheckLog.print("普通权限验证:" + normalCheck + " permission:" + permission);

    return check && normalCheck;
  }
}
