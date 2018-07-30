package com.smallraw.library.smallpermissions.supprot;

import android.Manifest;
import android.app.AppOpsManager;
import android.content.Context;
import android.os.Process;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author QuincySx
 * @date 2017/12/28 下午10:14
 */
public class AppOp {
    /**
     * Access to coarse location information.
     */
    private static final int OP_COARSE_LOCATION = 0;
    /**
     * Access to fine location information.
     */
    private static final int OP_FINE_LOCATION = 1;
    /**
     * Causing GPS to run.
     */
    private static final int OP_GPS = 2;
    /**   */
    private static final int OP_VIBRATE = 3;
    /**   */
    private static final int OP_READ_CONTACTS = 4;
    /**   */
    private static final int OP_WRITE_CONTACTS = 5;
    /**   */
    private static final int OP_READ_CALL_LOG = 6;
    /**   */
    private static final int OP_WRITE_CALL_LOG = 7;
    /**   */
    private static final int OP_READ_CALENDAR = 8;
    /**   */
    private static final int OP_WRITE_CALENDAR = 9;
    /**   */
    private static final int OP_WIFI_SCAN = 10;
    /**   */
    private static final int OP_POST_NOTIFICATION = 11;
    /**   */
    private static final int OP_NEIGHBORING_CELLS = 12;
    /**   */
    private static final int OP_CALL_PHONE = 13;
    /**   */
    private static final int OP_READ_SMS = 14;
    /**   */
    private static final int OP_WRITE_SMS = 15;
    /**   */
    private static final int OP_RECEIVE_SMS = 16;
    /**   */
    private static final int OP_RECEIVE_EMERGECY_SMS = 17;
    /**   */
    private static final int OP_RECEIVE_MMS = 18;
    /**   */
    private static final int OP_RECEIVE_WAP_PUSH = 19;
    /**   */
    private static final int OP_SEND_SMS = 20;
    /**   */
    private static final int OP_READ_ICC_SMS = 21;
    /**   */
    private static final int OP_WRITE_ICC_SMS = 22;
    /**   */
    private static final int OP_WRITE_SETTINGS = 23;
    /**   */
    private static final int OP_SYSTEM_ALERT_WINDOW = 24;
    /**   */
    private static final int OP_ACCESS_NOTIFICATIONS = 25;
    /**   */
    private static final int OP_CAMERA = 26;
    /**   */
    private static final int OP_RECORD_AUDIO = 27;
    /**   */
    private static final int OP_PLAY_AUDIO = 28;
    /**   */
    private static final int OP_READ_CLIPBOARD = 29;
    /**   */
    private static final int OP_WRITE_CLIPBOARD = 30;
    /**   */
    private static final int OP_TAKE_MEDIA_BUTTONS = 31;
    /**   */
    private static final int OP_TAKE_AUDIO_FOCUS = 32;
    /**   */
    private static final int OP_AUDIO_MASTER_VOLUME = 33;
    /**   */
    private static final int OP_AUDIO_VOICE_VOLUME = 34;
    /**   */
    private static final int OP_AUDIO_RING_VOLUME = 35;
    /**   */
    private static final int OP_AUDIO_MEDIA_VOLUME = 36;
    /**   */
    private static final int OP_AUDIO_ALARM_VOLUME = 37;
    /**   */
    private static final int OP_AUDIO_NOTIFICATION_VOLUME = 38;
    /**   */
    private static final int OP_AUDIO_BLUETOOTH_VOLUME = 39;
    /**   */
    private static final int OP_WAKE_LOCK = 40;
    /**
     * Continually monitoring location data.
     */
    private static final int OP_MONITOR_LOCATION = 41;
    /**
     * Continually monitoring location data with a relatively high power request.
     */
    private static final int OP_MONITOR_HIGH_POWER_LOCATION = 42;

    private static final int OP_GET_USAGE_STATS = 43;
    /**   */
    private static final int OP_MUTE_MICROPHONE = 44;
    /**   */
    private static final int OP_TOAST_WINDOW = 45;
    /**
     * Capture the device's display contents and/or audio
     */
    private static final int OP_PROJECT_MEDIA = 46;
    /**
     * Activate a VPN connection without user intervention.
     */
    private static final int OP_ACTIVATE_VPN = 47;
    /**
     * Access the WallpaperManagerAPI to write wallpapers.
     */
    private static final int OP_WRITE_WALLPAPER = 48;
    /**
     * Received the assist structure from an app.
     */
    private static final int OP_ASSIST_STRUCTURE = 49;
    /**
     * Received a screenshot from assist.
     */
    private static final int OP_ASSIST_SCREENSHOT = 50;
    /**
     * Read the phone state.
     */
    private static final int OP_READ_PHONE_STATE = 51;
    /**
     * Add voicemail messages to the voicemail content provider.
     */
    private static final int OP_ADD_VOICEMAIL = 52;
    /**
     * Access APIs for SIP calling over VOIP or WiFi.
     */
    private static final int OP_USE_SIP = 53;
    /**
     * Intercept outgoing calls.
     */
    private static final int OP_PROCESS_OUTGOING_CALLS = 54;
    /**
     * User the fingerprint API.
     */
    private static final int OP_USE_FINGERPRINT = 55;
    /**
     * Access to body sensors such as heart rate, etc.
     */
    private static final int OP_BODY_SENSORS = 56;
    /**
     * Read previously received cell broadcast messages.
     */
    private static final int OP_READ_CELL_BROADCASTS = 57;
    /**
     * Inject mock location into the system.
     */
    private static final int OP_MOCK_LOCATION = 58;
    /**
     * Read external storage.
     */
    private static final int OP_READ_EXTERNAL_STORAGE = 59;
    /**
     * Write external storage.
     */
    private static final int OP_WRITE_EXTERNAL_STORAGE = 60;
    /**
     * Turned on the screen.
     */
    private static final int OP_TURN_SCREEN_ON = 61;
    /**
     * Get device accounts.
     */
    private static final int OP_GET_ACCOUNTS = 62;
    /**
     * Control whether an application is allowed to run in the background.
     */
    private static final int OP_RUN_IN_BACKGROUND = 63;
    /**   */
    private static final int OP_AUDIO_ACCESSIBILITY_VOLUME = 64;
    /**
     * Read the phone number.
     */
    private static final int OP_READ_PHONE_NUMBERS = 65;
    /**
     * Request package installs through package installer
     */
    private static final int OP_REQUEST_INSTALL_PACKAGES = 66;
    /**
     * Enter picture-in-picture.
     */
    private static final int OP_PICTURE_IN_PICTURE = 67;
    /**
     * Instant app start foreground service.
     */
    private static final int OP_INSTANT_APP_START_FOREGROUND = 68;
    /**
     * Answer incoming phone calls
     */
    private static final int OP_ANSWER_PHONE_CALLS = 69;
    /**   */
    private static final int _NUM_OP = 70;


    /**
     * Access to coarse location information.
     */
    private static final String OPSTR_COARSE_LOCATION = "android:coarse_location";
    /**
     * Access to fine location information.
     */
    private static final String OPSTR_FINE_LOCATION =
            "android:fine_location";
    /**
     * Continually monitoring location data.
     */
    private static final String OPSTR_MONITOR_LOCATION
            = "android:monitor_location";
    /**
     * Continually monitoring location data with a relatively high power request.
     */
    private static final String OPSTR_MONITOR_HIGH_POWER_LOCATION
            = "android:monitor_location_high_power";
    /**
     * Access to {@link android.app.usage.UsageStatsManager}.
     */
    private static final String OPSTR_GET_USAGE_STATS
            = "android:get_usage_stats";
    /**
     * Activate a VPN connection without user intervention. @hide
     */
    private static final String OPSTR_ACTIVATE_VPN
            = "android:activate_vpn";
    /**
     * Allows an application to read the user's contacts data.
     */
    private static final String OPSTR_READ_CONTACTS
            = "android:read_contacts";
    /**
     * Allows an application to write to the user's contacts data.
     */
    private static final String OPSTR_WRITE_CONTACTS
            = "android:write_contacts";
    /**
     * Allows an application to read the user's call log.
     */
    private static final String OPSTR_READ_CALL_LOG
            = "android:read_call_log";
    /**
     * Allows an application to write to the user's call log.
     */
    private static final String OPSTR_WRITE_CALL_LOG
            = "android:write_call_log";
    /**
     * Allows an application to read the user's calendar data.
     */
    private static final String OPSTR_READ_CALENDAR
            = "android:read_calendar";
    /**
     * Allows an application to write to the user's calendar data.
     */
    private static final String OPSTR_WRITE_CALENDAR
            = "android:write_calendar";
    /**
     * Allows an application to initiate a phone call.
     */
    private static final String OPSTR_CALL_PHONE
            = "android:call_phone";
    /**
     * Allows an application to read SMS messages.
     */
    private static final String OPSTR_READ_SMS
            = "android:read_sms";
    /**
     * Allows an application to receive SMS messages.
     */
    private static final String OPSTR_RECEIVE_SMS
            = "android:receive_sms";
    /**
     * Allows an application to receive MMS messages.
     */
    private static final String OPSTR_RECEIVE_MMS
            = "android:receive_mms";
    /**
     * Allows an application to receive WAP push messages.
     */
    private static final String OPSTR_RECEIVE_WAP_PUSH
            = "android:receive_wap_push";
    /**
     * Allows an application to send SMS messages.
     */
    private static final String OPSTR_SEND_SMS
            = "android:send_sms";
    /**
     * Required to be able to access the camera device.
     */
    private static final String OPSTR_CAMERA
            = "android:camera";
    /**
     * Required to be able to access the microphone device.
     */
    private static final String OPSTR_RECORD_AUDIO
            = "android:record_audio";
    /**
     * Required to access phone state related information.
     */
    private static final String OPSTR_READ_PHONE_STATE
            = "android:read_phone_state";
    /**
     * Required to access phone state related information.
     */
    private static final String OPSTR_ADD_VOICEMAIL
            = "android:add_voicemail";
    /**
     * Access APIs for SIP calling over VOIP or WiFi
     */
    private static final String OPSTR_USE_SIP
            = "android:use_sip";
    /**
     * Access APIs for diverting outgoing calls
     */
    private static final String OPSTR_PROCESS_OUTGOING_CALLS
            = "android:process_outgoing_calls";
    /**
     * Use the fingerprint API.
     */
    private static final String OPSTR_USE_FINGERPRINT
            = "android:use_fingerprint";
    /**
     * Access to body sensors such as heart rate, etc.
     */
    private static final String OPSTR_BODY_SENSORS
            = "android:body_sensors";
    /**
     * Read previously received cell broadcast messages.
     */
    private static final String OPSTR_READ_CELL_BROADCASTS
            = "android:read_cell_broadcasts";
    /**
     * Inject mock location into the system.
     */
    private static final String OPSTR_MOCK_LOCATION
            = "android:mock_location";
    /**
     * Read external storage.
     */
    private static final String OPSTR_READ_EXTERNAL_STORAGE
            = "android:read_external_storage";
    /**
     * Write external storage.
     */
    private static final String OPSTR_WRITE_EXTERNAL_STORAGE
            = "android:write_external_storage";
    /**
     * Required to draw on top of other apps.
     */
    private static final String OPSTR_SYSTEM_ALERT_WINDOW
            = "android:system_alert_window";
    /**
     * Required to write/modify/update system settingss.
     */
    private static final String OPSTR_WRITE_SETTINGS
            = "android:write_settings";
    /**
     * @hide Get device accounts.
     */
    private static final String OPSTR_GET_ACCOUNTS
            = "android:get_accounts";
    private static final String OPSTR_READ_PHONE_NUMBERS
            = "android:read_phone_numbers";
    /**
     * Access to picture-in-picture.
     */
    private static final String OPSTR_PICTURE_IN_PICTURE
            = "android:picture_in_picture";
    /**
     * @hide
     */
    private static final String OPSTR_INSTANT_APP_START_FOREGROUND
            = "android:instant_app_start_foreground";
    /**
     * Answer incoming phone calls
     */
    private static final String OPSTR_ANSWER_PHONE_CALLS
            = "android:answer_phone_calls";


    private static final int[] RUNTIME_AND_APPOP_PERMISSIONS_OPS = {
            // RUNTIME PERMISSIONS
            // Contacts
            OP_READ_CONTACTS,
            OP_WRITE_CONTACTS,
            OP_GET_ACCOUNTS,
            // Calendar
            OP_READ_CALENDAR,
            OP_WRITE_CALENDAR,
            // SMS
            OP_SEND_SMS,
            OP_RECEIVE_SMS,
            OP_READ_SMS,
            OP_RECEIVE_WAP_PUSH,
            OP_RECEIVE_MMS,
            OP_READ_CELL_BROADCASTS,
            // Storage
            OP_READ_EXTERNAL_STORAGE,
            OP_WRITE_EXTERNAL_STORAGE,
            // Location
            OP_COARSE_LOCATION,
            OP_FINE_LOCATION,
            // Phone
            OP_READ_PHONE_STATE,
            OP_READ_PHONE_NUMBERS,
            OP_CALL_PHONE,
            OP_READ_CALL_LOG,
            OP_WRITE_CALL_LOG,
            OP_ADD_VOICEMAIL,
            OP_USE_SIP,
            OP_PROCESS_OUTGOING_CALLS,
            OP_ANSWER_PHONE_CALLS,
            // Microphone
            OP_RECORD_AUDIO,
            // Camera
            OP_CAMERA,
            // Body sensors
            OP_BODY_SENSORS,

            // APPOP PERMISSIONS
            OP_ACCESS_NOTIFICATIONS,
            OP_SYSTEM_ALERT_WINDOW,
            OP_WRITE_SETTINGS,
            OP_REQUEST_INSTALL_PACKAGES,
    };

    /**
     * This maps each operation to the private string constant for it.
     * If it doesn't have a private string constant, it maps to null.
     */
    private static String[] sOpToString = new String[]{
            OPSTR_COARSE_LOCATION,
            OPSTR_FINE_LOCATION,
            null,
            null,
            OPSTR_READ_CONTACTS,
            OPSTR_WRITE_CONTACTS,
            OPSTR_READ_CALL_LOG,
            OPSTR_WRITE_CALL_LOG,
            OPSTR_READ_CALENDAR,
            OPSTR_WRITE_CALENDAR,
            null,
            null,
            null,
            OPSTR_CALL_PHONE,
            OPSTR_READ_SMS,
            null,
            OPSTR_RECEIVE_SMS,
            null,
            OPSTR_RECEIVE_MMS,
            OPSTR_RECEIVE_WAP_PUSH,
            OPSTR_SEND_SMS,
            null,
            null,
            OPSTR_WRITE_SETTINGS,
            OPSTR_SYSTEM_ALERT_WINDOW,
            null,
            OPSTR_CAMERA,
            OPSTR_RECORD_AUDIO,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            OPSTR_MONITOR_LOCATION,
            OPSTR_MONITOR_HIGH_POWER_LOCATION,
            OPSTR_GET_USAGE_STATS,
            null,
            null,
            null,
            OPSTR_ACTIVATE_VPN,
            null,
            null,
            null,
            OPSTR_READ_PHONE_STATE,
            OPSTR_ADD_VOICEMAIL,
            OPSTR_USE_SIP,
            OPSTR_PROCESS_OUTGOING_CALLS,
            OPSTR_USE_FINGERPRINT,
            OPSTR_BODY_SENSORS,
            OPSTR_READ_CELL_BROADCASTS,
            OPSTR_MOCK_LOCATION,
            OPSTR_READ_EXTERNAL_STORAGE,
            OPSTR_WRITE_EXTERNAL_STORAGE,
            null,
            OPSTR_GET_ACCOUNTS,
            null,
            null, // OP_AUDIO_ACCESSIBILITY_VOLUME
            OPSTR_READ_PHONE_NUMBERS,
            null, // OP_REQUEST_INSTALL_PACKAGES
            OPSTR_PICTURE_IN_PICTURE,
            OPSTR_INSTANT_APP_START_FOREGROUND,
            OPSTR_ANSWER_PHONE_CALLS,
    };

    /**
     * This optionally maps a permission to an operation.  If there
     * is no permission associated with an operation, it is null.
     */
    private static String[] sOpPerms = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            null,
            Manifest.permission.VIBRATE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.WRITE_CALL_LOG,
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.WRITE_CALENDAR,
            Manifest.permission.ACCESS_WIFI_STATE,
            null, // no permission required for notifications
            null, // neighboring cells shares the coarse location perm
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_SMS,
            null, // no permission required for writing sms
            Manifest.permission.RECEIVE_SMS,
            null,//android.Manifest.permission.RECEIVE_EMERGENCY_BROADCAST,
            Manifest.permission.RECEIVE_MMS,
            Manifest.permission.RECEIVE_WAP_PUSH,
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_SMS,
            null, // no permission required for writing icc sms
            Manifest.permission.WRITE_SETTINGS,
            Manifest.permission.SYSTEM_ALERT_WINDOW,
            null,//android.Manifest.permission.ACCESS_NOTIFICATIONS,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            null, // no permission for playing audio
            null, // no permission for reading clipboard
            null, // no permission for writing clipboard
            null, // no permission for taking media buttons
            null, // no permission for taking audio focus
            null, // no permission for changing master volume
            null, // no permission for changing voice volume
            null, // no permission for changing ring volume
            null, // no permission for changing media volume
            null, // no permission for changing alarm volume
            null, // no permission for changing notification volume
            null, // no permission for changing bluetooth volume
            Manifest.permission.WAKE_LOCK,
            null, // no permission for generic location monitoring
            null, // no permission for high power location monitoring
            Manifest.permission.PACKAGE_USAGE_STATS,
            null, // no permission for muting/unmuting microphone
            null, // no permission for displaying toasts
            null, // no permission for projecting media
            null, // no permission for activating vpn
            null, // no permission for supporting wallpaper
            null, // no permission for receiving assist structure
            null, // no permission for receiving assist screenshot
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ADD_VOICEMAIL,
            Manifest.permission.USE_SIP,
            Manifest.permission.PROCESS_OUTGOING_CALLS,
            Manifest.permission.USE_FINGERPRINT,
            Manifest.permission.BODY_SENSORS,
            null,//Manifest.permission.READ_CELL_BROADCASTS,
            null,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            null, // no permission for turning the screen on
            Manifest.permission.GET_ACCOUNTS,
            null, // no permission for running in background
            null, // no permission for changing accessibility volume
            Manifest.permission.READ_PHONE_NUMBERS,
            Manifest.permission.REQUEST_INSTALL_PACKAGES,
            null, // no permission for entering picture-in-picture on hide
            Manifest.permission.INSTANT_APP_FOREGROUND_SERVICE,
            Manifest.permission.ANSWER_PHONE_CALLS,
    };

    /**
     * Mapping from an app op name to the app op code.
     */
    private static HashMap<String, Integer> sOpStrToOp = new HashMap<>();

    private static HashMap<String, Integer> sPermToOp = new HashMap<>();

    static {
        if (sOpToString.length != _NUM_OP) {
            throw new IllegalStateException("sOpToString length " + sOpToString.length
                    + " should be " + _NUM_OP);
        }
        if (sOpPerms.length != _NUM_OP) {
            throw new IllegalStateException("sOpPerms length " + sOpPerms.length
                    + " should be " + _NUM_OP);
        }
        for (int i = 0; i < _NUM_OP; i++) {
            if (sOpToString[i] != null) {
                sOpStrToOp.put(sOpToString[i], i);
            }
        }
        for (int op : RUNTIME_AND_APPOP_PERMISSIONS_OPS) {
            if (sOpPerms[op] != null) {
                sPermToOp.put(sOpPerms[op], op);
            }
        }
    }

    private static Integer permissionToOpCode(String permission) {
        final Integer opCode = sPermToOp.get(permission);
        if (opCode == null) {
            return null;
        }
        return opCode;
    }

    public static boolean isPermissionGranted(Context context, String permission) {
        try {
            Object object = context.getSystemService(Context.APP_OPS_SERVICE);
            if (object == null) {
                return false;
            }
            Class localClass = object.getClass();
            Class[] arrayOfClass = new Class[3];
            arrayOfClass[0] = Integer.TYPE;
            arrayOfClass[1] = Integer.TYPE;
            arrayOfClass[2] = String.class;
            Method method = localClass.getMethod("checkOp", arrayOfClass);

            if (method == null) {
                return false;
            }

            Integer permissionCode = permissionToOpCode(permission);
            if (permissionCode == null) {
                return true;
            }

            Object[] arrayOfObject = new Object[3];
            arrayOfObject[0] = permissionCode;
            arrayOfObject[1] = Integer.valueOf(Process.myUid());
            arrayOfObject[2] = context.getPackageName();
            int m = ((Integer) method.invoke(object, arrayOfObject)).intValue();
            return m == AppOpsManager.MODE_ALLOWED;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //    private static int strOpToOp(String op) {
//        Integer val = sOpStrToOp.get(op);
//        if (val == null) {
//            throw new IllegalArgumentException("Unknown operation string: " + op);
//        }
//        return val;
//    }

//    private static String permissionToOp(String permission) {
//        final Integer opCode = sPermToOp.get(permission);
//        if (opCode == null) {
//            return null;
//        }
//        return sOpToString[opCode];
//    }
}
