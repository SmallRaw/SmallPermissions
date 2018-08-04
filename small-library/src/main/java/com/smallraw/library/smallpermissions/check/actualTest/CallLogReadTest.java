package com.smallraw.library.smallpermissions.check.actualTest;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;

public class CallLogReadTest extends BaseTest {
  private Context mContext;
  private ContentResolver mResolver;

  public CallLogReadTest(Context context) {
    mContext = context;
    mResolver = context.getContentResolver();
  }

  @Override
  public boolean test() throws Throwable {
    String[] projection = new String[]{CallLog.Calls._ID, CallLog.Calls.NUMBER, CallLog.Calls.TYPE};
    Cursor cursor = mResolver.query(CallLog.Calls.CONTENT_URI, projection, null, null, null);
    if (cursor != null) {
      try {
        BaseTest.read(cursor);
      } finally {
        cursor.close();
      }
      return true;
    } else {
      return false;
    }
  }
}
