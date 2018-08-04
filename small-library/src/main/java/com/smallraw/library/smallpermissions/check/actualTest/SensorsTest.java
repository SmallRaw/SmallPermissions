package com.smallraw.library.smallpermissions.check.actualTest;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorsTest extends BaseTest {
  private Context mContext;

  public SensorsTest(Context context) {
    mContext = context;
  }

  @Override
  public boolean test() throws Throwable {
    SensorManager sensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
    try {
      Sensor heartRateSensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
      sensorManager.registerListener(SENSOR_EVENT_LISTENER, heartRateSensor, 3);
      sensorManager.unregisterListener(SENSOR_EVENT_LISTENER, heartRateSensor);
    } catch (Throwable e) {
      PackageManager packageManager = mContext.getPackageManager();
      return !packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_HEART_RATE);
    }
    return true;
  }

  private static final SensorEventListener SENSOR_EVENT_LISTENER = new SensorEventListener() {
    @Override
    public void onSensorChanged(SensorEvent event) {
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
  };
}
