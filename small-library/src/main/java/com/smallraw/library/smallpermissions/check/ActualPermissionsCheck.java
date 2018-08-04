package com.smallraw.library.smallpermissions.check;

import android.Manifest;
import android.content.Context;

import com.smallraw.library.smallpermissions.check.actualTest.CalendarReadTest;
import com.smallraw.library.smallpermissions.check.actualTest.CalendarWriteTest;
import com.smallraw.library.smallpermissions.check.actualTest.CallLogReadTest;
import com.smallraw.library.smallpermissions.check.actualTest.CallLogWriteTest;
import com.smallraw.library.smallpermissions.check.actualTest.CameraTest;
import com.smallraw.library.smallpermissions.check.actualTest.ContactsReadTest;
import com.smallraw.library.smallpermissions.check.actualTest.LocationAccessCoarseTest;
import com.smallraw.library.smallpermissions.check.actualTest.LocationAccessFineTest;
import com.smallraw.library.smallpermissions.check.actualTest.PhoneReadState;
import com.smallraw.library.smallpermissions.check.actualTest.RecordAudioTest;
import com.smallraw.library.smallpermissions.check.actualTest.SensorsTest;
import com.smallraw.library.smallpermissions.check.actualTest.SipTest;
import com.smallraw.library.smallpermissions.check.actualTest.SmsReadTest;
import com.smallraw.library.smallpermissions.check.actualTest.StorageReadTest;
import com.smallraw.library.smallpermissions.check.actualTest.StorageWriteTest;

/**
 * @author QuincySx
 * @date 2018/7/30 下午2:22
 */
public class ActualPermissionsCheck implements IPermissionsCheck {
  @Override
  public boolean checkPermissions(Context context, String permission) {
    context = context.getApplicationContext();

    boolean checkPermission = false;
    switch (permission) {
      case Manifest.permission.READ_CALENDAR:
        checkPermission = checkReadCalendar(context);
        break;
      case Manifest.permission.WRITE_CALENDAR:
        checkPermission = checkWriteCalendar(context);
        break;
      case Manifest.permission.CAMERA:
        checkPermission = checkCamera(context);
        break;
      case Manifest.permission.READ_CONTACTS:
        checkPermission = checkReadContacts(context);
        break;
      case Manifest.permission.WRITE_CONTACTS:
        checkPermission = checkWriteContacts(context);
        break;
      case Manifest.permission.GET_ACCOUNTS:
        //todo
        checkPermission = true;
        break;
      case Manifest.permission.ACCESS_FINE_LOCATION:
        checkPermission = checkAccessFineLocation(context);
        break;
      case Manifest.permission.ACCESS_COARSE_LOCATION:
        checkPermission = checkAccessCoarseLocation(context);
        break;
      case Manifest.permission.RECORD_AUDIO:
        checkPermission = checkRecordAudio(context);
        break;
      case Manifest.permission.READ_PHONE_STATE:
        checkPermission = checkReadPhoneState(context);
        break;
      case Manifest.permission.CALL_PHONE:
        //todo
        checkPermission = true;
        break;
      case Manifest.permission.READ_CALL_LOG:
        checkPermission = checkReadCallLog(context);
        break;
      case Manifest.permission.WRITE_CALL_LOG:
        checkPermission = checkWriteCallLog(context);
        break;
      case Manifest.permission.ADD_VOICEMAIL:
        checkPermission = checkVoicemailAdd(context);
        break;
      case Manifest.permission.USE_SIP:
        checkPermission = checkUseSip(context);
        break;
      case Manifest.permission.PROCESS_OUTGOING_CALLS:
        //todo
        checkPermission = true;
        break;
      case Manifest.permission.BODY_SENSORS:
        checkPermission = checkBodySensors(context);
        break;
      case Manifest.permission.SEND_SMS:
        //todo
        checkPermission = true;
        break;
      case Manifest.permission.RECEIVE_SMS:
        //todo
        checkPermission = true;
        break;
      case Manifest.permission.READ_SMS:
        checkPermission = checkReadSms(context);
        break;
      case Manifest.permission.RECEIVE_WAP_PUSH:
        //todo
        checkPermission = true;
        break;
      case Manifest.permission.RECEIVE_MMS:
        //todo
        checkPermission = true;
        break;
      case Manifest.permission.READ_EXTERNAL_STORAGE:
        checkPermission = checkStorageRead(context);
        break;
      case Manifest.permission.WRITE_EXTERNAL_STORAGE:
        checkPermission = checkStorageWrite(context);
        break;
      default:
        checkPermission = true;
    }
    CheckLog.print("权限:" + checkPermission + " permission:" + permission);

    return checkPermission;
  }

  private boolean checkReadCalendar(Context context) {
    try {
      return new CalendarReadTest(context).test();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return false;
  }

  private boolean checkWriteCalendar(Context context) {
    try {
      return new CalendarWriteTest(context).test();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return false;
  }

  private boolean checkCamera(Context context) {
    try {
      return new CameraTest(context).test();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return false;
  }

  private boolean checkReadContacts(Context context) {
    try {
      return new ContactsReadTest(context).test();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return false;
  }

  private boolean checkWriteContacts(Context context) {
    try {
      return new ContactsReadTest(context).test();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return false;
  }

  private boolean checkAccessFineLocation(Context context) {
    try {
      return new LocationAccessFineTest(context).test();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return false;
  }

  private boolean checkAccessCoarseLocation(Context context) {
    try {
      return new LocationAccessCoarseTest(context).test();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return false;
  }

  private boolean checkRecordAudio(Context context) {
    try {
      return new RecordAudioTest(context).test();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return false;
  }

  private boolean checkReadPhoneState(Context context) {
    try {
      return new PhoneReadState(context).test();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return false;
  }

  private boolean checkReadCallLog(Context context) {
    try {
      return new CallLogReadTest(context).test();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return false;
  }

  private boolean checkWriteCallLog(Context context) {
    try {
      return new CallLogWriteTest(context).test();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return false;
  }

  private boolean checkVoicemailAdd(Context context) {
    try {
      return new CallLogWriteTest(context).test();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return false;
  }

  private boolean checkUseSip(Context context) {
    try {
      return new SipTest(context).test();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return false;
  }

  private boolean checkBodySensors(Context context) {
    try {
      return new SensorsTest(context).test();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return false;
  }

  private boolean checkReadSms(Context context) {
    try {
      return new SmsReadTest(context).test();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return true;
  }

  private boolean checkStorageRead(Context context) {
    try {
      return new StorageReadTest(context).test();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return false;
  }

  private boolean checkStorageWrite(Context context) {
    try {
      return new StorageWriteTest(context).test();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    return false;
  }
}
