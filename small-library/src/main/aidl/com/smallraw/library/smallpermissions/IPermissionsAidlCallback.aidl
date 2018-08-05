// IPermissionsAidlCallback.aidl
package com.smallraw.library.smallpermissions;

// Declare any non-default types here with import statements

interface IPermissionsAidlCallback {
        /**
         * 正常获取了权限
         * @param permissions 被许可的权限
         */
        void onPermissionGranted(out List<String> Permissions);

        /**
         * 有一部分权限被拒绝
         * @param Permissions 被拒绝的权限
         */
        void onPermissionDenied(out List<String> Permissions);
}
