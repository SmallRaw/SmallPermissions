package com.smallraw.library.smaple;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.smallraw.library.smallpermissions.SmallPermission;
import com.smallraw.library.smallpermissions.annotation.PermissionsApply;

public class SampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smaple);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_location_permission:
                getLocationPermission();
                break;
        }
    }

    @PermissionsApply(
            permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
            hint = R.string.app_hint, openSetting = false)
    private static void getLocationPermission() {
        Toast.makeText(SmallPermission.getApplicationContext(), "成功", Toast.LENGTH_SHORT).show();
//        SmallPermission.requestPermission(this,
//                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
//                123,
//                new PermissionsCallback() {
//                    @Override
//                    public void onPermissionGranted() {
//                        Toast.makeText(SampleActivity.this, "成功", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onPermissionDenied(List<String> Permissions) {
//                        Toast.makeText(SampleActivity.this, "失败", Toast.LENGTH_SHORT).show();
//                    }
//                });
    }
}
