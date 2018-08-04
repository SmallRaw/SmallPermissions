package com.smallraw.library.smallpermissions.check.actualTest;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;

import java.util.List;

public class LocationAccessCoarseTest extends BaseTest {
  private Context mContext;

  public LocationAccessCoarseTest(Context context) {
    mContext = context;
  }

  @Override
  public boolean test() throws Throwable {
    LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
    List<String> providers = locationManager.getProviders(true);
    boolean networkProvider = providers.contains(LocationManager.NETWORK_PROVIDER);
    if (networkProvider) {
      return true;
    }

    PackageManager packageManager = mContext.getPackageManager();
    boolean networkHardware = packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION_NETWORK);
    if(!networkHardware) return true;

    return !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
  }
}
