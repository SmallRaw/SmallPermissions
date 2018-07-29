package com.smallraw.library.smallpermissions.permisson;

import com.smallraw.library.smallpermissions.callback.PermissionsCallback;

public interface IPermission {
    void requestPermissions(String[] permissions, int requestCode, PermissionsCallback callback);
}