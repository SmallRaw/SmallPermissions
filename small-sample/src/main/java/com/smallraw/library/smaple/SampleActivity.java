package com.smallraw.library.smaple;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;


import com.smallraw.library.smallpermissions.SmallPermission;
import com.smallraw.library.smallpermissions.annotation.PermissionsApply;
import com.smallraw.library.smallpermissions.callback.Action;
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
      case R.id.read_calendar:
        request(new String[]{Manifest.permission.READ_CALENDAR});
        break;
      case R.id.write_calendar:
        request(new String[]{Manifest.permission.WRITE_CALENDAR});
        break;
      case R.id.camera:
        request(new String[]{Manifest.permission.CAMERA});
        break;
      case R.id.read_contacts:
        request(new String[]{Manifest.permission.READ_CONTACTS});
        break;
      case R.id.write_contacts:
        request(new String[]{Manifest.permission.WRITE_CONTACTS});
        break;
      case R.id.get_accounts:
        request(new String[]{Manifest.permission.GET_ACCOUNTS});
        break;
      case R.id.access_fine_location:
        request(new String[]{Manifest.permission.ACCESS_FINE_LOCATION});
        break;
      case R.id.access_coarse_location:
        request(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION});
        break;
      case R.id.record_audio:
        request(new String[]{Manifest.permission.RECORD_AUDIO});
        break;
      case R.id.read_phone_state:
        request(new String[]{Manifest.permission.READ_PHONE_STATE});
        break;
      case R.id.call_phone:
        request(new String[]{Manifest.permission.CALL_PHONE});
        break;
      case R.id.read_call_log:
        request(new String[]{Manifest.permission.READ_CALL_LOG});
        break;
      case R.id.write_call_log:
        request(new String[]{Manifest.permission.WRITE_CALL_LOG});
        break;
      case R.id.add_voicemail:
        request(new String[]{Manifest.permission.ADD_VOICEMAIL});
        break;
      case R.id.use_sip:
        request(new String[]{Manifest.permission.USE_SIP});
        break;
      case R.id.process_outgoing_calls:
        request(new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS});
        break;
      case R.id.body_sensors:
        request(new String[]{Manifest.permission.BODY_SENSORS});
        break;
      case R.id.send_sms:
        request(new String[]{Manifest.permission.SEND_SMS});
        break;
      case R.id.receive_sms:
        request(new String[]{Manifest.permission.RECEIVE_SMS});
        break;
      case R.id.read_sms:
        request(new String[]{Manifest.permission.READ_SMS});
        break;
      case R.id.receive_wap_push:
        request(new String[]{Manifest.permission.RECEIVE_WAP_PUSH});
        break;
      case R.id.receive_mms:
        request(new String[]{Manifest.permission.RECEIVE_MMS});
        break;
      case R.id.read_external_storage:
        request(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE});
        break;
      case R.id.write_external_storage:
        request(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE});
        break;
    }
  }


  public void request(String... permissions) {
    SmallPermission.with(this)
            .permission(permissions)
            .onGranted(new Action() {
              @Override
              public void onAction(List<String> Permissions) {
                Toast.makeText(SampleActivity.this, "成功", Toast.LENGTH_SHORT).show();
              }
            })
            .onDenied(new Action() {
              @Override
              public void onAction(List<String> Permissions) {
                Toast.makeText(SampleActivity.this, "失败", Toast.LENGTH_SHORT).show();
              }
            })
            .start();
  }
}
