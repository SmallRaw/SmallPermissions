package com.smallraw.library.smallpermissions.supprot;

import android.os.Build;
import android.support.annotation.IntDef;

/**
 * @author QuincySx
 * @date 2017/12/29 下午3:44
 */
public class SystemOS {
    /**
     * Build.OSR
     */
    public static final int OS_DEF = 0;
    public static final int OS_HUAWEI = 1;
    public static final int OS_XIAOMI = 2;
    public static final int OS_OPPO = 3;
    public static final int OS_VIVO = 4;
    public static final int OS_MEIZU = 5;
    public static final String sManufacturer = Build.MANUFACTURER;

    @IntDef({OS_DEF, OS_HUAWEI, OS_XIAOMI, OS_OPPO, OS_VIVO, OS_MEIZU})
    public @interface SystemOs {
    }

    @SystemOs
    public static int isSystemOS() {
        if (sManufacturer.equalsIgnoreCase("HUAWEI")) {
            return OS_HUAWEI;
        }
        if (sManufacturer.equalsIgnoreCase("xiaomi")) {
            return OS_XIAOMI;
        }
        if (sManufacturer.equalsIgnoreCase("OPPO")) {
            return OS_OPPO;
        }
        if (sManufacturer.equalsIgnoreCase("vivo")) {
            return OS_VIVO;
        }
        if (sManufacturer.equalsIgnoreCase("meizu")) {
            return OS_MEIZU;
        }
        return OS_DEF;
    }
}
