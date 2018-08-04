package com.smallraw.library.smallpermissions.check.actualTest;

import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public class PhoneReadState extends BaseTest {
  private Context mContext;

  public PhoneReadState(Context context) {
    mContext = context;
  }

  @Override
  public boolean test() throws Throwable {
    PackageManager packageManager = mContext.getPackageManager();
    if (!packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY)) return true;

    TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
    return telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_NONE
            || !TextUtils.isEmpty(telephonyManager.getDeviceId())
            || !TextUtils.isEmpty(telephonyManager.getSubscriberId());
  }
}
