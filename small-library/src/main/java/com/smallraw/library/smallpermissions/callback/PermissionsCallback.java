package com.smallraw.library.smallpermissions.callback;

import java.util.List;

public interface PermissionsCallback {
    /**
     * 正常获取了权限
     * @param permissions 被许可的权限
     */
    void onPermissionGranted(List<String> permissions);

    /**
     * 有一部分权限被拒绝
     * @param permissions 被拒绝的权限
     */
    void onPermissionDenied(List<String> permissions);
}
