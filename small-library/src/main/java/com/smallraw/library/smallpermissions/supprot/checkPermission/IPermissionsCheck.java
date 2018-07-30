package com.smallraw.library.smallpermissions.supprot.checkPermission;

import android.content.Context;

/**
 * @author QuincySx
 * @date 2018/7/30 下午2:19
 */
public interface IPermissionsCheck {
    boolean checkPermissions(Context context, String permission);
}
