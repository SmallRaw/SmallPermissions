package com.smallraw.library.smallpermissions.permisson;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;

import com.smallraw.library.IPermissionsAidlCallback;
import com.smallraw.library.smallpermissions.callback.PermissionsCallback;

import java.io.Serializable;
import java.util.List;

public class ActivityPermission implements IPermission {
    private Context mContext;

    public ActivityPermission(Context context) {
        mContext = context;
    }

    @Override
    public void requestPermissions(final String[] permissions, int requestCode, final PermissionsCallback callback) {
        Intent intent = new Intent(mContext, RequestPermissionsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putInt(RequestPermissionsActivity.PERMISSIONS_REQUEST, requestCode);
        bundle.putStringArray(RequestPermissionsActivity.PERMISSIONS_STRINGS, permissions);

        IPermissionsAidlCallback.Stub stub = new IPermissionsAidlCallback.Stub() {
            @Override
            public void onPermissionGranted() throws RemoteException {
                callback.onPermissionGranted();
                Log.e("==activity==", "onPermissionGranted");
            }

            @Override
            public void onPermissionDenied(List<String> Permissions) throws RemoteException {
                callback.onPermissionDenied(Permissions);
                Log.e("==activity==", "onPermissionDenied");
            }
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            bundle.putBinder(RequestPermissionsActivity.PERMISSIONS_CALLBACK, stub);
        }

        intent.putExtra("activity", bundle);
        mContext.startActivity(intent);
    }

    public class ActivityPermissionsCallback implements PermissionsCallback, Serializable {
        private static final long serialVersionUID = 1L;

        @Override
        public void onPermissionGranted() {

        }

        @Override
        public void onPermissionDenied(List<String> Permissions) {

        }
    }

}
