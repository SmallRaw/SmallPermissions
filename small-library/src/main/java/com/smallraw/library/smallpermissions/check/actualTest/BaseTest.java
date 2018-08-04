package com.smallraw.library.smallpermissions.check.actualTest;

import android.database.Cursor;

public abstract class BaseTest {
  public abstract boolean test() throws Throwable;

  public static void read(Cursor cursor) {
    int count = cursor.getCount();
    if (count > 0) {
      cursor.moveToFirst();
      int type = cursor.getType(0);
      switch (type) {
        case Cursor.FIELD_TYPE_BLOB:
        case Cursor.FIELD_TYPE_NULL: {
          break;
        }
        case Cursor.FIELD_TYPE_INTEGER:
        case Cursor.FIELD_TYPE_FLOAT:
        case Cursor.FIELD_TYPE_STRING:
        default: {
          cursor.getString(0);
          break;
        }
      }
    }
  }
}
