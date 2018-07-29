package com.smallraw.library.smallpermissions.permisson;

import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.smallraw.library.IPermissionsAidlCallback;
import com.smallraw.library.R;
import com.smallraw.library.smallpermissions.callback.PermissionsCallback;
import com.smallraw.library.smallpermissions.handler.PermissionsHandler;

import java.util.List;

public class RequestPermissionsActivity extends AppCompatActivity implements IPermission {
    private final PermissionsHandler mPermissionsHandler = PermissionsHandler.getInstance();
    public static final String PERMISSIONS_CALLBACK = "permissions_callback";
    public static final String PERMISSIONS_STRINGS = "permissions_strings";
    public static final String PERMISSIONS_REQUEST = "permissions_request";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.setGravity(Gravity.LEFT | Gravity.TOP);
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 0;
        params.y = 0;
        params.height = 1;
        params.width = 1;
        window.setAttributes(params);

        Bundle bundle = getIntent().getBundleExtra("activity");
        final IPermissionsAidlCallback binder = IPermissionsAidlCallback.Stub.asInterface(bundle.getBinder(RequestPermissionsActivity.PERMISSIONS_CALLBACK));

        String[] permissions = bundle.getStringArray(RequestPermissionsActivity.PERMISSIONS_STRINGS);
        int request = bundle.getInt(RequestPermissionsActivity.PERMISSIONS_REQUEST, -1);
        requestPermissions(permissions, request, new PermissionsCallback() {
            @Override
            public void onPermissionGranted() {
                try {
                    binder.onPermissionGranted();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onPermissionDenied(List<String> Permissions) {
                try {
                    binder.onPermissionDenied(Permissions);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void requestPermissions(String[] permissions, int requestCode, PermissionsCallback callback) {
        String[] permission = mPermissionsHandler.checkPermissions(this, permissions);
        if (permission.length == 0) {
            callback.onPermissionGranted();
            finish();
        } else {
            try {
                mPermissionsHandler.addPermissionCallback(requestCode, callback);
                requestPermissions(permission, requestCode);
            } catch (Exception e) {
                callback.onPermissionGranted();
                mPermissionsHandler.removePermissionCallback(requestCode);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mPermissionsHandler.onRequestPermissionsResult(requestCode, permissions, grantResults);
        finish();
    }
}
