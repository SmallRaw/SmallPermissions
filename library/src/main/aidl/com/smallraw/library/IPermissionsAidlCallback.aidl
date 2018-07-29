// IPermissionsAidlCallback.aidl
package com.smallraw.library;

// Declare any non-default types here with import statements

interface IPermissionsAidlCallback {
        /**
         * 正常获取了权限
         */
        void onPermissionGranted();

        /**
         * 有一部分权限被拒绝
         * @param Permissions 被拒绝的权限
         */
        void onPermissionDenied(out List<String> Permissions);
}
