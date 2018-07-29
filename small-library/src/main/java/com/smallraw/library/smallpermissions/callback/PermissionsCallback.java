package com.smallraw.library.smallpermissions.callback;

import java.util.List;

public interface PermissionsCallback {
    /**
     * 正常获取了权限
     */
    void onPermissionGranted();

    /**
     * 有一部分权限被拒绝
     * @param Permissions 被拒绝的权限
     */
    void onPermissionDenied(List<String> Permissions);
}
