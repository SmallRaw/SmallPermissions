package com.smallraw.library.smaple;

import android.Manifest;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.smallraw.library.smallpermissions.SmallPermission;
import com.smallraw.library.smallpermissions.callback.PermissionsCallback;

import java.util.List;

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

    private void getLocationPermission() {
        SmallPermission.requestPermission(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                123,
                new PermissionsCallback() {
                    @Override
                    public void onPermissionGranted() {
                        Toast.makeText(SampleActivity.this, "成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionDenied(List<String> Permissions) {
                        Toast.makeText(SampleActivity.this, "失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
