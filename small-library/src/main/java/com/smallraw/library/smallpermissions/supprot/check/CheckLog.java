package com.smallraw.library.smallpermissions.supprot.check;

import android.os.Build;
import android.util.Log;

/**
 * @author QuincySx
 * @date 2018/7/30 下午3:03
 */
public class CheckLog {
    public static void print(String log) {
        String manufacturer = Build.MANUFACTURER;
        String brand = Build.BRAND;
        String model = Build.MODEL;
        String release = Build.VERSION.RELEASE;
        int sdk_int = Build.VERSION.SDK_INT;

        String BuildInfo = manufacturer + " " + brand + " " + model + " " + release + " " + sdk_int;

        Log.e("checkPermission", log + " -- " + BuildInfo);
    }
}
