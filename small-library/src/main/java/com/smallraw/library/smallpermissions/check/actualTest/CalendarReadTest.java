package com.smallraw.library.smallpermissions.check.actualTest;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CalendarContract;

public class CalendarReadTest extends BaseTest {
  private Context mContext;
  ContentResolver mContentResolver;

  public CalendarReadTest(Context context) {
    mContext = context;
    mContentResolver = mContext.getContentResolver();
  }

  @Override
  public boolean test() throws Throwable {
    String[] projection = new String[]{CalendarContract.Calendars._ID, CalendarContract.Calendars.NAME};
    Cursor cursor = mContentResolver.query(CalendarContract.Calendars.CONTENT_URI, projection, null, null, null);
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
