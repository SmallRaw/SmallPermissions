package com.smallraw.library.smallpermissions.check.actualTest;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;

import java.util.List;

public class LocationAccessFineTest extends BaseTest {
  private Context mContext;

  public LocationAccessFineTest(Context context) {
    mContext = context;
  }

  @Override
  public boolean test() throws Throwable {
    LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
    List<String> providers = locationManager.getProviders(true);
    boolean gpsProvider = providers.contains(LocationManager.GPS_PROVIDER);
    boolean passiveProvider = providers.contains(LocationManager.PASSIVE_PROVIDER);
    if (gpsProvider || passiveProvider) {
      return true;
    }

    PackageManager packageManager = mContext.getPackageManager();
    boolean gpsHardware = packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS);
    if(!gpsHardware) return true;

    return !locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
  }
}
